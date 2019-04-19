package com.yd.youd.net;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.yd.youd.model.BaseJson;
import com.yd.youd.ui.login.LoginActivity;
import com.yd.youd.utils.SP;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    private volatile static HttpUtil okHttpUtil;//会被多线程使用，所以使用关键字volatile
    private OkHttpClient client;
    private Handler mHandler;
    private Context mContext;
    private static final String TAG = HttpUtil.class.getSimpleName();


    public static final int NORMAL = 1;
    public static final String TOKEN = "user-token";


    private static SSLSocketFactory createSSL() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = context.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return ssfFactory;

    }

    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }




    //私有化构造方法
    private HttpUtil(Context context) {
        mContext = context;
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;//设置缓存大小
        OkHttpClient.Builder builder = new OkHttpClient.Builder()

                .sslSocketFactory(createSSL())
                .hostnameVerifier(new TrustAllHostnameVerifier())

                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));//设置缓存的路径
        client = builder.build();
        mHandler = new Handler();
    }

    //单例模式，全局得到一个OkHttpUtil对象
    public static HttpUtil getInstance(Context context) {
        if (okHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (okHttpUtil == null) {
                    okHttpUtil = new HttpUtil(context);
                }
            }
        }
        return okHttpUtil;
    }

    /**
     * get异步请求
     *
     * @param url
     * @param callback
     */
    public void getAsynHttp(String url, final ResultCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(response.body().string(), callback);
            }
        });
    }

    /**
     * 提交表单数据
     *
     * @param url
     * @param map
     * @param callback
     */
    public void postForm(String url, Map<String, String> map, final ResultCallback callback) {
        FormBody.Builder form = new FormBody.Builder();//表单对象，包含以input开始的对象,以html表单为主
//        if (map != null && !map.isEmpty()){
        //遍历Map集合
        RequestBody body = null;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                form.add(entry.getKey(), entry.getValue());
            }
            body = form.build();
        } else {
            body = RequestBody.create(null, "");
        }
        Request request = new Request.Builder().url(url).post(body)
                .addHeader(TOKEN, SP.getToken(mContext)).build();//采用post提交数据


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "fail: " + request.url() + "-----" + e.getMessage());
                sendFailedCallback(call, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "token " + SP.getToken(mContext));

                if (response == null || !response.isSuccessful()) {
                    Log.e(TAG, "onResponse: is null or fail"
                            + request.url()+"||"+response.body().string()
                    );
                }

                if (response.isSuccessful() && response != null) {
                    String s = response.body().string();
                    Log.d(TAG, "request: " + request.url());
                    Log.d(TAG, "response: " + s);
                    BaseJson baseJson = JSON.parseObject(s, BaseJson.class);

                    if (baseJson.code == NORMAL) {
                        sendSuccessCallback(s, callback);
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                               // Toast.makeText(mContext, baseJson.msg, Toast.LENGTH_LONG).show();
                            }
                        });
                    }


                }
            }
        });
//        }

    }

    //img_file  avatar
    public void upload(boolean isAvatar,String url, String path, String name, ResultCallback callback) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(isAvatar?"avatar":"img_file", name,
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(path)))
                .build();

        Request request = new Request.Builder()
                .addHeader(TOKEN, SP.getToken(mContext))
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                sendFailedCallback(call, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response == null || !response.isSuccessful()) {
                    Log.d(TAG, "onResponse: is null or fail"
                            + response.body().string()
                    );
                }

                if (response.isSuccessful() && response != null) {
                    String s = response.body().string();
                    Log.d(TAG, "request: " + request.url());
                    Log.d(TAG, "response: " + s);
                    sendSuccessCallback(s, callback);
                }


            }
        });


    }

    public void downdload() {

    }

    private void handleTokenError() {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("chenzhiyuan", "token 过期 ");
                SP.saveToken(mContext, "");
                Toast.makeText(mContext, "登录已过期，请重新登录", Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, LoginActivity.class));

            }
        });
    }

    /**
     * 当请求失败时，都会调用这个方法
     *
     * @param call
     * @param e
     * @param callback
     */
    private void sendFailedCallback(final Call call, final IOException e, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("main", "当前线程：" + Thread.currentThread().getName());
                if (callback != null) {
                    callback.onError(call.request(), e);
                }
            }
        });
    }

    /**
     * 请求成功调用该方法
     *
     * @param response 返回的数据
     * @param callback 回调的接口
     */
    private void sendSuccessCallback(final String response, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("main", "当前线程：" + Thread.currentThread().getName());
                if (callback != null) {
                    try {
                        callback.onResponse(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //创建接口，回调给调用者
    public interface ResultCallback {
        void onError(Request request, Exception e);

        void onResponse(String s) throws IOException;
    }

}