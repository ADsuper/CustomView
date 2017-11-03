package com.adsuper.io.customview.huizhiwenzi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.adsuper.io.customview.R;

/**
 * 作者：珞神 on 10/31 0031 15:16
 * 邮箱：rsf411613593@gmail.com
 *
 *  利用 canvas.drawText 绘制文字
 */

public class DrawTextView extends View {

    private Paint mPaint;

    public DrawTextView(Context context) {
        this(context,null);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    /**
     * 初始化画笔
     * @param context
     */
    private void initPaint(Context context) {

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String text = "珞神";
        canvas.drawText(text,100,100,mPaint);//给固定字符串指定位置

        text = "ABCDEFG";
        canvas.drawText(text,100,200,mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(100,0,100,100,mPaint);
        canvas.save();//保存之前画布的状态入栈
        canvas.rotate(-90,100,100);
        canvas.drawLine(100,0,100,100,mPaint);

//        canvas.rotate(90,100,100);//旋转之后重新旋转回来，便于以下操作
        canvas.restore();//回滚到之前保存的栈顶的状态
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawText(text,0,5,100,300,mPaint);

    }
}
