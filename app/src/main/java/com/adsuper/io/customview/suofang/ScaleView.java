package com.adsuper.io.customview.suofang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：珞神 on 10/30 0030 16:37
 * 邮箱：rsf411613593@gmail.com
 *
 * 缩放
 */

public class ScaleView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;


    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
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
        mPaint.setStrokeWidth(10);//描边的宽度
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
        RectF rectF = new RectF(-400, -400, 400, 400);
        for (int i = 0; i < 20; i++) {
            canvas.drawRect(rectF, mPaint);
            canvas.scale(0.9f, 0.9f);//缩放为原来的 0.9 ，缩放中心为坐标原点
        }


    }
}
