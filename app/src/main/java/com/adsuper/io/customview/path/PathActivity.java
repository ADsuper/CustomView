package com.adsuper.io.customview.path;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.adsuper.io.customview.R;
import com.adsuper.io.customview.path.beisaierquxian.HeartViewActivity;
import com.adsuper.io.customview.path.buerzhi.YinYangFishActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PathActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this,Path_one_Activity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this,Path_two_Activity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this,GriddingViewActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this,FiveStarActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(this,HeartViewActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(this,YinYangFishActivity.class));
                break;
        }
    }
}
