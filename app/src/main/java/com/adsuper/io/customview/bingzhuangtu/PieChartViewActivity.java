package com.adsuper.io.customview.bingzhuangtu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.adsuper.io.customview.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义饼状图
 */
public class PieChartViewActivity extends AppCompatActivity {

    @BindView(R.id.piechart_view)
    PieChartView piechartView;
    //颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //value值
    private int[] mValues = {100, 200, 150, 300, 180, 50, 60,
            80, 350};

    private ArrayList<PieChartBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_view);
        ButterKnife.bind(this);

        initData();
        piechartView.setData(mData);
    }

    private void initData() {

        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieChartBean pieChartBean = new PieChartBean();
            pieChartBean.setColor(mColors[i]);
            pieChartBean.setValue(mValues[i]);

            mData.add(pieChartBean);
        }
    }
}
