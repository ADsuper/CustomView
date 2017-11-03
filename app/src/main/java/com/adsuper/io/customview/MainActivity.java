package com.adsuper.io.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.adsuper.io.customview.bingzhuangtu.PieChartViewActivity;
import com.adsuper.io.customview.cuoqie.SkewActivity;
import com.adsuper.io.customview.huizhiwenzi.DrawTextActivity;
import com.adsuper.io.customview.path.PathActivity;
import com.adsuper.io.customview.suofang.ScaleActivity;
import com.adsuper.io.customview.xuanzhuan.RotateActivity;
import com.adsuper.io.customview.zhendonghua.DrawBitmapActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(mContext, PieChartViewActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(mContext, ScaleActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(mContext, RotateActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(mContext, SkewActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(mContext, DrawBitmapActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(mContext, DrawTextActivity.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(mContext, PathActivity.class));
                break;
        }
    }
}
