package com.adsuper.io.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.adsuper.io.customview.R;

/**
 * 作者：珞神 on 11/1 0001 10:49
 * 邮箱：rsf411613593@gmail.com
 * <p>
 * canvas.drawPath 相关
 *
 *  path.addArc 和   path2.arcTo 区别
 */

public class Path_two_View extends View {

    private Paint mPaint;

    public Path_two_View(Context context) {
        this(context, null);
    }

    public Path_two_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    /**
     * 初始化画笔
     *
     * @param context
     */
    private void initPaint(Context context) {

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(50);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(300,200);
        canvas.drawLine(0,0,500,0,mPaint);
        canvas.drawLine(0,0,-500,0,mPaint);
        canvas.drawLine(0,0,0,500,mPaint);
        canvas.drawLine(0,0,0,-500,mPaint);
        RectF rectF = new RectF(-200,-100,400,100);
        Path path = new Path();
        path.lineTo(100,50);
        path.addArc(rectF,0,300);// 画出来圆弧的方向默认为 顺时针
//        path.arcTo(rectF,0,300,true); // 等价于上边的方法 path.addArc
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path,mPaint);

        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.translate(300,700);
        canvas.drawLine(0,0,500,0,mPaint);
        canvas.drawLine(0,0,-500,0,mPaint);
        canvas.drawLine(0,0,0,500,mPaint);
        canvas.drawLine(0,0,0,-500,mPaint);
        RectF rectF2 = new RectF(-200,-100,400,100);
        Path path2 = new Path();
        path2.lineTo(100,50);
        path2.arcTo(rectF,0,300,false);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path2,mPaint);



    }

}
