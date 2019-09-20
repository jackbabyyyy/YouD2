package com.ud.share.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImageView extends ImageView {
    private Path mPath;
    private RectF mRectF;
    private int mCorner = 20;
    private Paint mPaint;

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);//PS:一定不要再draw里面新建RectF，一定不要再draw里面新建RectF，一定不要再draw里面新建RectF，//重要的事情说三遍，会严重消耗内存
        mRectF = new RectF();
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(0xffE6EEF6);
    }





    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
         mPath.reset();//将mRectF设置为imageview本身的宽高
         mRectF.set(0,0, getWidth(), getHeight());//将path设置rect值
         mPath.addRoundRect(mRectF,mCorner,mCorner, Path.Direction.CW);//切割画布，只留下自己需要的部分
        canvas.clipPath(mPath);//保留imageview本身的绘制图片super.draw(canvas);//画出描边canvas.drawRoundRect(mRectF,mCorner,mCorner,mPaint);
    }
}



