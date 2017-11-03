package com.adsuper.io.customview.cuoqie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：珞神 on 10/30 0030 17:28
 * 邮箱：rsf411613593@gmail.com
 *
 *      错切效果图
 */

public class SkewView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;


    public SkewView(Context context) {
        this(context,null);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
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
        mPaint.setStrokeWidth(2);//描边的宽度
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

        canvas.translate(mWidth/2,mHeight/2);

        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.drawRect(rectF,mPaint);

        canvas.skew(1,0);//水平错切 45 度，参数为 tan 值

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);

    }
}
