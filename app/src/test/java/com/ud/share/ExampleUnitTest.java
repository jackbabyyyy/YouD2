package com.ud.share;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ud.share.model.AllDeviceBean;
import com.ud.share.model.BaseJson;
import com.ud.share.utils.CardNumUtils;
import com.ud.share.utils.Luhn;
import com.ud.share.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {



        String a="3.30";
        String b="3.31";
        BigDecimal b1 = new BigDecimal(a);
        BigDecimal b2 = new BigDecimal(b);

       double v=b2.subtract(b1).doubleValue();





        assertEquals(1,String.valueOf(v));
       // assertEquals(4,StringUtils.timeStamp2Date("1559318400"));
    }

    @Test
    public void card(){
        assertEquals(false, CardNumUtils.matchLuhn("6215583700002640074"));
    }

    @Test
    public void ti(){
        assertEquals(2,f(50));

    }

    public int f(int n){
        if (n==1)return  1;
        if (n==2)return 2;
        return f(n-1)+f(n-2);
    }

    @Test
    public void json(){

        String s="sdjfsf";
        assertEquals(0, JSON.parseObject(s, BaseJson.class));
    }

    @Test
    public void x(){
        List<String> a=new ArrayList<>();
        a.add(1,"1");
        a.add(0,"2");
    }

    @Test
    public void y(){

       float a= Float.parseFloat("2,000.00");
       assertEquals(2000.0,a,0.1);

    }

    @Test
    public void glide(){
String s="{\n" +
        "\t\"code\": 1,\n" +
        "\t\"msg\": \"操作成功\",\n" +
        "\t\"total\": {\n" +
        "\t\t\"binded\": 0,\n" +
        "\t\t\"unbind\": 0\n" +
        "\t},\n" +
        "\t\"data\": []\n" +
        "}";
        AllDeviceBean bean = JSON.parseObject(s, AllDeviceBean.class);
        assertEquals(100,bean.total.unbind);


    }

    private int[] rewards={1,2,5,10};
    private static final  int EQUAL=10;
    private List<Integer> result=new ArrayList<>();

    public void get(){
        for (int i=0;i<rewards.length;i++){

            result.add(rewards[i]);
            

        }

    }


}