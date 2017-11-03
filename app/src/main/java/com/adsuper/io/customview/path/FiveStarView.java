package com.adsuper.io.customview.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.adsuper.io.customview.R;

/**
 * 作者：珞神 on 11/2 0002 14:33
 * 邮箱：rsf411613593@gmail.com
 */

public class FiveStarView extends View {

    private static final String TAG = "FiveStarView";
    private Paint mPaint;

    private int mCenterX, mCenterY; //圆心坐标
    private float maxRadius;    //外圆半径

    private float mRadian;

    public FiveStarView(Context context) {
        this(context, null);
    }

    public FiveStarView(Context context, @Nullable AttributeSet attrs) {
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
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w / 2;
        mCenterY = h / 2;
        maxRadius = (float) (Math.min(w, h) / 2 * 0.9f);
        Log.d(TAG, "onSizeChanged: 圆心：" + mCenterX + "," + mCenterY);
        mRadian = (float) (Math.PI * 2 / 5); //弧度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setColor(Color.BLACK);
        canvas.drawLine(mCenterX, mCenterY, mCenterX + 500, mCenterY, mPaint);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY + 500, mPaint);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - 500, mPaint);
        canvas.drawLine(mCenterX, mCenterY, mCenterX - 500, mCenterY, mPaint);

        Path path = new Path();
        for (int i = 0; i < 5; i++) {

            float x = (float) (mCenterX + Math.cos(mRadian * (i + 1)) * maxRadius);
            float y = (float) (mCenterY + Math.sin(mRadian * (i + 1)) * maxRadius);


            Log.d(TAG, "onDraw: x" + i + ": " + x + ",y" + i + ": " + y);

            if (i == 0) {
                canvas.drawCircle(x, y, 10, mPaint);
                path.moveTo(x, y);
                continue;
            }
            if (i == 1){

                canvas.drawCircle(x, y, 10, mPaint);
            }

            path.lineTo(x, y);

        }
        path.close();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawPath(path, mPaint);

    }
}
