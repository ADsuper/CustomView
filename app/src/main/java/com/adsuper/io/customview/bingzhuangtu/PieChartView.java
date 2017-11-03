package com.adsuper.io.customview.bingzhuangtu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


/**
 * 饼状图
 */
public class PieChartView extends View {

    private static final String TAG = "PieChartView";
    private Paint mPaint;

    //view 宽高
    private int mWidth, mHeight;
    //初始绘制角度
    private float mStartAngle = 0;
    //颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    // 数据
    private ArrayList<PieChartBean> mData;

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
        }
        mPaint.setStyle(Paint.Style.FILL);//模式为填充
        mPaint.setAntiAlias(true);//抗锯齿
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mData == null) {
            return;
        }
        float currentStartAngle = mStartAngle;// 初始角度
        canvas.translate(mWidth / 2, mHeight / 2);//画布的坐标原点移动至中心位置
        float r = (float) (Math.min(mHeight, mWidth) / 2 * 0.8);//计算半径
        RectF rectF = new RectF(-r, -r, r, r);//饼状图绘制区域
        for (int i = 0; i < mData.size(); i++) {
            PieChartBean pieChartBean = mData.get(i);
            mPaint.setColor(pieChartBean.getColor());
            canvas.drawArc(rectF, currentStartAngle, pieChartBean.getAngle(), true, mPaint);
            currentStartAngle += pieChartBean.getAngle();
        }
    }

    /**
     * 设置初始角度
     *
     * @param angle
     */
    public void setStartAngle(float angle) {
        this.mStartAngle = angle;
        invalidate();//刷新
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(ArrayList<PieChartBean> data) {
        this.mData = data;
        handleData(data);
        invalidate();
    }

    /**
     * 对数据进一步处理
     *
     * @param data
     */
    private void handleData(ArrayList<PieChartBean> data) {
        if (data == null || data.size() == 0) {
            Log.d(TAG, "initData: 没有数据");
            return;
        }
        float sumValue = 0;//计算总值，然后根据总值计算百分比和角度
        for (int i = 0; i < data.size(); i++) {
            PieChartBean pieChartBean = data.get(i);
            //防止颜色为负数
            if (pieChartBean.getColor() <= 0) {
                pieChartBean.setColor(mColors[i % mColors.length]);
            }
            float value = pieChartBean.getValue();
            //防止 value 为负数
            if (value <= 0) {
                data.remove(i);
            }
            sumValue += value;
        }
        //计算百分比和角度
        for (int i = 0; i < data.size(); i++) {
            PieChartBean pieChartBean = data.get(i);
            float percentage = pieChartBean.getValue() / sumValue; //百分比
            float angle = percentage * 360; //对应的角度
            pieChartBean.setPercentage(percentage);
            pieChartBean.setAngle(angle);
        }


    }

}
