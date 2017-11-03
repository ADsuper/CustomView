package com.adsuper.io.customview.path.buerzhi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;

/**
 * 作者：珞神 on 11/3 0003 15:44
 * 邮箱：rsf411613593@gmail.com
 */

public class YinYangFishView extends View {


    private Paint mPaint;

    private int mWidth,mHeight;
    private Path path1;

    public YinYangFishView(Context context) {
        this(context,null);
    }

    public YinYangFishView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 草稿
        canvas.save();

        canvas.translate(mWidth/2,mHeight/4);
        canvas.drawLine(-1000,0,1000,0,mPaint);
        canvas.drawLine(0,-1000,0,2000,mPaint);

        path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0,0,200, Path.Direction.CW);//  Path.Direction.CW 顺时针
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(100);
        canvas.drawPath(path1,mPaint);

        path2.addRect(0,-200,200,200, Path.Direction.CW);
        mPaint.setColor(Color.BLUE);
        mPaint.setAlpha(100);
        canvas.drawPath(path2,mPaint);

        path3.addCircle(0,-100,100, Path.Direction.CW);
        mPaint.setColor(Color.GRAY);
        mPaint.setAlpha(100);
        canvas.drawPath(path3,mPaint);

        path4.addCircle(0,100,100, Path.Direction.CW);
        mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(100);
        canvas.drawPath(path4,mPaint);

        canvas.restore();

        //非草稿
        canvas.translate(mWidth/2,mHeight/4*3);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(-1000,0,1000,0,mPaint);
        //左侧阴阳鱼
            // op 方法的最低 API 为 19
        path1.op(path2, Path.Op.DIFFERENCE);//Path1中减去Path2后剩下的部分 差集
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path1,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0,-100,10,mPaint);//中间红点

        //右侧阴阳鱼   因为 path1 已经改变，所以需要重新创建一个 path11   代替path1
        Path path11 = new Path();
        path11.addCircle(0,0,200, Path.Direction.CW);
        path11.op(path2, Path.Op.INTERSECT);//path1 与 path2 交集
        path11.op(path4, Path.Op.UNION);//包含全部Path1和Path4 ,并集
        path11.op(path3, Path.Op.DIFFERENCE);
        mPaint.setColor(Color.RED);
        canvas.drawPath(path11,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0,100,10,mPaint);//中间黑点

    }
}
