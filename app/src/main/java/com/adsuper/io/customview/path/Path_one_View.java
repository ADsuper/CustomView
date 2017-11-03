package com.adsuper.io.customview.path;

import android.content.Context;
import android.graphics.Canvas;
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
 */

public class Path_one_View extends View {

    private Paint mPaint;

    public Path_one_View(Context context) {
        this(context, null);
    }

    public Path_one_View(Context context, @Nullable AttributeSet attrs) {
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
        //空心三角形  path.close(),连接最后一个操作点和起始操作点，如果 close 之后不闭合的话，colse 不会执行闭合操作
        canvas.drawText("path.lineTo()", 100, 100, mPaint);
        canvas.drawText("path.close()", 100, 160, mPaint);
        canvas.translate(100, 200);
        Path path = new Path();
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        path.close();
        canvas.drawPath(path, mPaint);

        //path.moveTo()  	移动下一次操作的起点位置,对之前的操作没有影响
        canvas.drawText("path.moveTo()", 500, -100, mPaint);
//        mPaint.setStyle(Paint.Style.FILL);
        canvas.translate(500, 0);// 在之前基础上向右移动 600 px
        Path path2 = new Path();
        path2.lineTo(200, 200);
        path2.moveTo(200, 100);
        path2.lineTo(200, 0);
//        path2.close();
        canvas.drawPath(path2, mPaint);

        //添加一个圆
        canvas.translate(-400, 600);// 在之前基础上向 左 下各移动 600 px
        canvas.drawText("path.addCircle()", -100, -200, mPaint);
        canvas.drawText("path.setLastPoint()", -100, -140, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path3 = new Path();
        path3.addCircle(0, 0, 100, Path.Direction.CCW);//添加一个圆形，最后一个参数表示方向 （CW 顺时针，CCW，逆时针）
        //如果没有关闭硬件加速，那么： 参数：坐标点不能超过 添加的图形的范围
        path3.setLastPoint(0, 0);//设置之前操作的最后一个点位置，对之前的操作有影响,
        canvas.drawPath(path3, mPaint);

        //添加一个矩形
        canvas.translate(500,0);
        canvas.drawText("path.addRect()", -100, -200, mPaint);
        RectF rectF = new RectF(-100, -100, 100, 100);
        Path path4 = new Path();
        path4.addRect(rectF, Path.Direction.CW);
        path4.setLastPoint(-150,150);
        canvas.drawPath(path4,mPaint);

        //path5.addPath
        canvas.translate(-500, 500);
        canvas.drawText("path.addPath()", -100, -200, mPaint);
        Path path5 = new Path();
        path5.addRect(-100,-100,100,100, Path.Direction.CW);
        Path path6 = new Path();
        path6.addCircle(0,0,100, Path.Direction.CW);
        path5.addPath(path6,0,100);//后两个参数表示先将原点向下移动 100 px 之后，在此基础上绘制 path6
        canvas.drawPath(path5,mPaint);

    }

}
