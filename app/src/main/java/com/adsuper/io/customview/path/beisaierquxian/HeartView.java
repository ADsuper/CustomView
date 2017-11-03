package com.adsuper.io.customview.path.beisaierquxian;

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
 * 作者：珞神 on 11/3 0003 10:32
 * 邮箱：rsf411613593@gmail.com
 * <p>
 * 心形 View
 */

public class HeartView extends View {

    private Paint mPaint;

    private int mCenterX, mCenterY;

    private float[] valuesData = new float[8]; //数据点
    private float[] ctrlData = new float[16];   //控制点

    private float mCircleRadius = 300;          // 圆半径

    private static final float C = 0.551915024494f; //一个常量，用来计算 贝塞尔曲线的控制点位置
    private float distance = mCircleRadius * C; //控制点与数据点的差值，也是为了确定控制点的位置

    private float mDuration = 1000;                     // 变化总时长
    private float mCurrent = 0;                         // 当前已进行时长
    private float mCount = 100;                         // 将时长总共划分多少份
    private float mPiece = mDuration/mCount;            // 每一份的时长
    private String TAG = "HeartView";


    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initValuesData();
        initCtrlDatas();

    }

    /**
     * 初始化数据点为 圆与 坐标轴 的交点
     */
    private void initValuesData() {

        valuesData[0] = 0;
        valuesData[1] = mCircleRadius;

        valuesData[2] = mCircleRadius;
        valuesData[3] = 0;

        valuesData[4] = 0;
        valuesData[5] = -mCircleRadius;

        valuesData[6] = -mCircleRadius;
        valuesData[7] = 0;
    }

    /**
     * 初始化控制点,根据数据点确定控制点
     */
    private void initCtrlDatas() {
        //Y轴正方向右侧
        ctrlData[0] = valuesData[0] + distance;
        ctrlData[1] = valuesData[1];
        //X 轴 正方向下侧
        ctrlData[2] = valuesData[2];
        ctrlData[3] = valuesData[3] + distance;
        //X 轴 正方向上侧
        ctrlData[4] = valuesData[2];
        ctrlData[5] = valuesData[3] - distance;
        //Y 轴 负方向右侧
        ctrlData[6] = valuesData[4] + distance;
        ctrlData[7] = valuesData[5];
        // Y 轴 负方向 左侧
        ctrlData[8] = valuesData[4] - distance;
        ctrlData[9] = valuesData[5];
        //X 轴 负方向上侧
        ctrlData[10] = valuesData[6];
        ctrlData[11] = valuesData[7] - distance;
        //X 轴 负方向下侧
        ctrlData[12] = valuesData[6];
        ctrlData[13] = valuesData[7] + distance;
        // Y 轴正方向 左侧
        ctrlData[14] = valuesData[0] - distance;
        ctrlData[15] = valuesData[1];


    }

    /**
     * -
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStrokeWidth(5);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制坐标系
//        drawCoordinateSystem(canvas);

        //绘制贝塞尔曲线
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.translate(mCenterX, mCenterY);
        canvas.scale(1, -1);         //翻转 Y 轴
        Path path = new Path();
        path.moveTo(valuesData[0], valuesData[1]);
        path.cubicTo(ctrlData[0], ctrlData[1], ctrlData[2], ctrlData[3], valuesData[2], valuesData[3]);
        path.cubicTo(ctrlData[4], ctrlData[5], ctrlData[6], ctrlData[7], valuesData[4], valuesData[5]);
        path.cubicTo(ctrlData[8], ctrlData[9], ctrlData[10], ctrlData[11], valuesData[6], valuesData[7]);
        path.cubicTo(ctrlData[12], ctrlData[13], ctrlData[14], ctrlData[15], valuesData[0], valuesData[1]);
        //默认画出来是一个圆
        canvas.drawPath(path, mPaint);

        mCurrent += mPiece;
        //控制渐变
        if (mCurrent < mDuration){

            valuesData[1] -= 160/mCount;//控制心形 上定点,值(160)越小越圆润
            //控制心形 下方的圆润长度，值(160)越小越圆润
            ctrlData[7] += 160/mCount;
            ctrlData[9] += 160/mCount;
            //控制心形两侧的圆润程度，值(50) 越小越圆润
            ctrlData[4] -= 50/mCount;
            ctrlData[10] += 50/mCount;

            postInvalidateDelayed((long) mPiece);
        }else {
            //打印出来的点为最后的心形形状，
            Log.d(TAG, "onDraw: 心形上::"+valuesData[0] +",,"+valuesData[1]);
            Log.d(TAG, "onDraw: 心形下::"+ctrlData[7] +",,"+ctrlData[9]);
            Log.d(TAG, "onDraw: 心形两侧::"+ctrlData[4] +",,"+ctrlData[10]);
        }

    }

    /**
     * 绘制坐标系
     *
     * @param canvas
     */
    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(-1000, 0, 1000, 0, mPaint);
        canvas.drawLine(0, 1000, 0, -1000, mPaint);
        canvas.restore();
    }
}
