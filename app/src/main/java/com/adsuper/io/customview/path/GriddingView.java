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
import android.view.WindowManager;
import android.widget.Toast;

import com.adsuper.io.customview.R;

/**
 * 作者：珞神 on 11/1 0001 16:10
 * 邮箱：rsf411613593@gmail.com
 * <p>
 * Android雷达图(蜘蛛网图)绘制
 */

public class GriddingView extends View {

    private Context mContext;

    private static final String TAG = "GriddingView";
    private Paint mGliddingPaint;   //网格区域的画笔
    private Paint mTextPaint;       //文字区域的画笔
    private Paint mCoverPaint;      //覆盖物的画笔

    private int mCenterX, mCenterY;  //中心点 （圆心）
    private float mMaxRadius;       //最大半径
    private float mRadius;          //蜘蛛丝之间的距离

    private int mCircleCount = 6; // 决定有多少圈的网格
    private int mCount = 6; // 6 表示六边形e

    private double[] valueDate = {150, 160, 170, 180, 190, 200};//绘制覆盖区域的 value 值
    private double maxValueDate = 250; // valueDate 的最大值

    private int[] scaleplateDate = {50, 100, 150, 200};//刻度标尺

    private float mRadian = (float) (Math.PI * 2 / mCount); //弧度

    private String[] titles = {"命中","闪避","内功","外功","会心","会防"};//文本说明

    public GriddingView(Context context) {
        this(context, null);
    }

    public GriddingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint(context);
    }

    public GriddingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    /**
     * 初始化画笔
     *
     * @param context
     */
    private void initPaint(Context context) {

        mContext = context;
        mGliddingPaint = new Paint();
        mGliddingPaint.setColor(Color.BLACK);
        mGliddingPaint.setStyle(Paint.Style.STROKE);
        mGliddingPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(30);
        mTextPaint.setAntiAlias(true);

        mCoverPaint = new Paint();
        mCoverPaint.setColor(getResources().getColor(R.color.colorAccent));
        mCoverPaint.setAntiAlias(true);
        mCoverPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: 宽度：" + w + ",高度：" + h);
        mCenterX = w / 2;
        mCenterY = h / 2;
        mMaxRadius = Math.min(w, h) / 2 * 0.9f;
        Log.d(TAG, "onSizeChanged: mCenterX--" + mCenterX + ",mCenterY---" + mCenterY + ",mMaxRadius---" + mMaxRadius);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPolygon(canvas);

        drawLine(canvas);

        drawCover(canvas);

        drawText(canvas);


    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i=0;i<mCount;i++){
            float x = (float) (mCenterX+(mMaxRadius+fontHeight/2)*Math.cos(mRadian*i));
            float y = (float) (mCenterX+(mMaxRadius+fontHeight/2)*Math.sin(mRadian*i));
            if(mRadian*i>=0&&mRadian*i<=Math.PI/2){//第4象限
                canvas.drawText(titles[i], x,y,mTextPaint);
            }else if(mRadian*i>=3*Math.PI/2&&mRadian*i<=Math.PI*2){//第3象限
                canvas.drawText(titles[i], x,y,mTextPaint);
            }else if(mRadian*i>Math.PI/2&&mRadian*i<=Math.PI){//第2象限
                float dis = mTextPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,mTextPaint);
            }else if(mRadian*i>=Math.PI&&mRadian*i<3*Math.PI/2){//第1象限
                float dis = mTextPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,mTextPaint);
            }
        }

    }

    /**
     * 绘制覆盖物
     * @param canvas
     */
    private void drawCover(Canvas canvas) {
        double max = 0;
        //1.获取数组最大值
        for (int i = 0; i < valueDate.length; i++) {
            if (valueDate[i] > max) {
                max = valueDate[i];
            }
        }
        if (max > maxValueDate) {
            Toast.makeText(mContext, "标尺最大值过小", Toast.LENGTH_SHORT).show();
            return;
        }
        //2.绘制
        Path path = new Path();
        for (int i = 0; i < mCount; i++) {

            //2获取百分比
            double percent = valueDate[i] / maxValueDate;

            //根据百分比计算出对应每条线上的坐标
            float x = (float) (mCenterX + Math.cos(mRadian * i) * mMaxRadius * percent);
            float y = (float) (mCenterY + Math.sin(mRadian * i) * mMaxRadius * percent);

            if (i == 0) {
                path.moveTo(x, mCenterY);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x,y,5,mCoverPaint);

        }
        mCoverPaint.setAlpha(120);
        canvas.drawPath(path, mCoverPaint);
    }

    /**
     * 绘制直线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < mCount; i++) {
            path.reset();
            path.moveTo(mCenterX, mCenterY);
            float x = (float) (mCenterX + Math.cos(mRadian * i) * mMaxRadius);
            float y = (float) (mCenterY + Math.sin(mRadian * i) * mMaxRadius);
            Log.d(TAG, "onDraw: 圆上坐标点:" + x + "-----" + y);
            path.lineTo(x, y);
            canvas.drawPath(path, mGliddingPaint);
        }
    }

    /**
     * 绘制多边形 和 刻度尺
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        mRadius = mMaxRadius / (mCircleCount - 1);
        Log.d(TAG, "onDraw: mRadius-----" + mRadius);

        for (int j = 0; j < mCircleCount; j++) {
            if (j == 0) {
                canvas.drawText("0", mCenterX, mCenterY, mTextPaint);
                continue;
            }
            float mCurrentRadius = mRadius * j;
            path.reset();
            for (int i = 0; i < mCount; i++) {
                if (i == 0) {
                    path.moveTo(mCenterX + mCurrentRadius, mCenterY);
                    Log.d(TAG, "onDraw: moveTo：" + mCurrentRadius + "---" + mCenterY);
                    if (j >= 1 && j <= scaleplateDate.length) {

                        canvas.drawText(scaleplateDate[j - 1] + "", mCenterX + mCurrentRadius, mCenterY, mTextPaint);
                        Log.d(TAG, "onDraw: drawText：" + scaleplateDate[j - 1]);
                    }
                } else {

                    float x = (float) (mCenterX + Math.cos(mRadian * i) * mCurrentRadius);
                    float y = (float) (mCenterY + Math.sin(mRadian * i) * mCurrentRadius);
                    Log.d(TAG, "onDraw: 圆上坐标点:" + x + "-----" + y);
                    path.lineTo(x, y);
                }

            }
            path.close();
            canvas.drawPath(path, mGliddingPaint);
        }
    }
}
