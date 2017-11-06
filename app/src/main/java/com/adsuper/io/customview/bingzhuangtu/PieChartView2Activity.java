package com.adsuper.io.customview.bingzhuangtu;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.adsuper.io.customview.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 第三方饼状图
 */
public class PieChartView2Activity extends AppCompatActivity implements OnChartValueSelectedListener {

    private static final String TAG = "PieChartView2Activity";

    @BindView(R.id.piechart)
    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_view2);
        ButterKnife.bind(this);


        piechart.setUsePercentValues(true);// 是否显示百分比
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(5, 10, 5, 5);

        piechart.setDragDecelerationFrictionCoef(0.95f);

        piechart.setCenterTextTypeface(Typeface.DEFAULT);//设置文字类型
        piechart.setCenterText("天龙八部属性");//设置中间的文字
        piechart.setCenterTextSize(20f);//设置中间的文字大小
        piechart.setCenterTextColor(getResources().getColor(R.color.colorAccent));//设置中间的文字颜色

        piechart.setDrawHoleEnabled(true);//是否显示中间的空白圆圈区域
        piechart.setHoleColor(Color.WHITE);//设置中间空白圆圈区域的背景色
        float radius = 38;
        piechart.setHoleRadius(radius);//设置中间空白部分半径,

        piechart.setTransparentCircleRadius(radius+5);//半透明圈半径，
        piechart.setTransparentCircleColor(Color.WHITE);//半透明圈的颜色
        piechart.setTransparentCircleAlpha(110);//半透明圈的透明度

        piechart.setDrawCenterText(true);// 饼状图中间是否可以添加文字

        piechart.setRotationAngle(0);//设置初始旋转角度
        // enable rotation of the chart by touch
        piechart.setRotationEnabled(true);// 是否可以手动旋转
        piechart.setHighlightPerTapEnabled(true);//设置被点击的部分是否往外扩展一部分，同时还有点击事件

        piechart.setOnChartValueSelectedListener(this);//设置圆弧区域的点击事件

        setData(5);//设置数据

        piechart.animateY(1400, Easing.EasingOption.EaseInOutQuad);//设置开始时候的旋转动画

        //设置右上侧的说明
        Legend l = piechart.getLegend();
        //设置位置
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);//Y 轴排列方向的每两个之间的距离
        l.setYOffset(0f);//距离顶部的距离
        l.setTextSize(10f);//设置颜色方块右侧的 文本的字体大小
        l.setFormToTextSpace(10f);//设置颜色方块与字体之间的距离
        l.setFormSize(10f);//设置颜色方块的大小

        // entry label styling
        piechart.setEntryLabelColor(Color.WHITE);//设置圆弧部分的字体颜色
        piechart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);//设置圆弧部分的字体类型
        piechart.setEntryLabelTextSize(12f);//设置圆弧部分的字体大小


    }

    protected String[] mParties = new String[] {
            "攻击", "防御", "命中", "闪避", "会心"
    };
    protected final int[] COLORFUL_COLORS = {
            Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31), Color.rgb(179, 100, 53)
    };

    /**
     * 设置饼状图数据
     */
    private void setData(int count) {

        ArrayList<PieEntry> listData = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            //第一个参数：每一块对应的值，第二个参数：各个部分的名称，第三个参数：每个部分显示的图标
            PieEntry pieEntry = new PieEntry(40,mParties[i],getResources().getDrawable(R.mipmap.star));
            listData.add(pieEntry);
        }

        PieDataSet pieDataSet = new PieDataSet(listData, "天龙八部");//设置右上角最下侧的文字
        pieDataSet.setDrawIcons(true);//是否显示设置的图标（pieEntry 的 第三个参数）

        pieDataSet.setSliceSpace(3f);//相邻两个部分之间的空隙
        pieDataSet.setIconsOffset(new MPPointF(0, 40));//设置图标的位置
        pieDataSet.setSelectionShift(5f);//设置选中模块往外延伸的长度

        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < COLORFUL_COLORS.length; i++) {
            colors.add(COLORFUL_COLORS[i]);
        }
        pieDataSet.setColors(colors);//饼状图颜色

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(21f);//设置显示的百分比的字体大小
        pieData.setValueTextColor(Color.WHITE);//设置显示的百分比的字体颜色
        pieData.setValueTypeface(Typeface.DEFAULT);//设置显示的百分比的字体样式

        piechart.setData(pieData);

        // undo all highlights
        piechart.highlightValues(null);

        piechart.invalidate();

    }

    /**
     * 当某个部分被点击之后触发该方法
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.d(TAG, "onValueSelected: Entry ::"+e);
        Log.d(TAG, "onValueSelected: Highlight ::"+h);
    }

    /**
     * 当某个部分被点击之后，再一次点击触发该方法
     * 或者点击 饼状图之外的部分也会触发该方法
     */
    @Override
    public void onNothingSelected() {
        Log.d(TAG, "onNothingSelected: ");
    }
}
