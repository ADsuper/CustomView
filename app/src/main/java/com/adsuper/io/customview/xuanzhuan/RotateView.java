package com.adsuper.io.customview.xuanzhuan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：珞神 on 10/30 0030 16:55
 * 邮箱：rsf411613593@gmail.com
 *
 * 旋转示意图
 */

public class RotateView extends View {
    private Paint mPaint;

    private int mWidth, mHeight;

    public RotateView(Context context) {
        this(context, null);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);//设置样式为描边
        mPaint.setStrokeWidth(5);//描边的宽度
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);//指定半径

        canvas.drawCircle(0, 0, r, mPaint);//画圆
        canvas.drawCircle(0, 0, r * 0.95f, mPaint);


        for (int i = 0; i < 360; i += 10) {

            canvas.drawLine(0, r, 0, 0.95f * r, mPaint);//画线段
            canvas.rotate(10);

        }


    }
}
