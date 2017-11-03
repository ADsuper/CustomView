package com.adsuper.io.customview.zhendonghua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adsuper.io.customview.R;


/**
 * 作者：珞神 on 10/31 0031 10:16
 * 邮箱：rsf411613593@gmail.com
 * <p>
 * 使用 canvas.drawBitmap() 实现帧动画
 */

public class CheckView extends View {

    private Context mContext;

    private Paint mPaint;
    private Bitmap mBitmap;

    private int mWidth, mHeight;

    private int maxPage = 13;
    private int currentPage = -1;

    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private int animDuration = 500;         // 动画时长
    private int animState = ANIM_NULL;      // 动画状态

    private boolean isCheck = false;        // 是否是选中状态
    private String TAG = "checkView";
    private Handler  mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (currentPage >= 0 && currentPage < maxPage) {
                invalidate();
                if (animState == ANIM_NULL) {
                    return;
                }
                if (animState == ANIM_CHECK) {
                    currentPage++;
                }
                if (animState == ANIM_UNCHECK) {
                    currentPage--;
                }
                this.sendEmptyMessageDelayed(0,animDuration/maxPage);
                Log.d(TAG, "handleMessage: currentPage:::"+currentPage);

            }else {
                if (isCheck) {
                    currentPage = maxPage - 1;// 设置为最大值减一，就把最后一张图片显示出来了

                }else {
                    currentPage = -1;//设置为 - 1 就显示不出来了
                }
                invalidate();// 这个刷新是为了将效果固定下来
                animState = ANIM_NULL;

            }





        }
    };;


    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    /**
     * 初始化操作
     */
    private void init(Context context) {

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.checkmark);


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

        canvas.drawCircle(0, 0, 240, mPaint);

        int mBitmapHeight = mBitmap.getHeight();
        // bitmap 的大小为 1300 * 100, 切割下来的图片为 100 * 100

        // 图像的显示区域(图像按什么样的尺寸切割)
        Rect rect = new Rect(mBitmapHeight * currentPage, 0, mBitmapHeight * (currentPage + 1), mBitmapHeight);
        // 图像的实际绘制位置（切割完的图像要显示在什么位置）
        Rect rect1 = new Rect(-200, -200, 200, 200);

        canvas.drawBitmap(mBitmap,rect,rect1,mPaint);

    }

    /**
     * 选中
     */
    public void check(){

        if (isCheck || animState != ANIM_NULL) {
            Toast.makeText(mContext, "已经是 check 状态", Toast.LENGTH_SHORT).show();
            return;
        }
        animState = ANIM_CHECK;
        currentPage = 0;
        isCheck = true;
        mHandler.sendEmptyMessageDelayed(0,animDuration/maxPage);
    }

    /**
     * 未选中
     */
    public void unCheck(){
        if (!isCheck || animState != ANIM_NULL) {
            Toast.makeText(mContext, "已经是 uncheck 状态", Toast.LENGTH_SHORT).show();
            return;
        }
        animState = ANIM_UNCHECK;
        currentPage = maxPage-1;
        isCheck = false;
        mHandler.sendEmptyMessageDelayed(0,animDuration/maxPage);
    }


}
