package com.adsuper.io.customview.zhendonghua;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.adsuper.io.customview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawBitmapActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.checkview)
    CheckView checkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bitmap);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                checkview.check();

                break;
            case R.id.btn2:
            checkview.unCheck();

                break;
        }
    }
}
