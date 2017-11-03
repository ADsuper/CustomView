package com.adsuper.io.customview.bingzhuangtu;

/**
 * 作者：珞神 on 10/30 0030 14:06
 * 邮箱：rsf411613593@gmail.com
 *
 *      饼状图对应的数据封装
 */

public class PieChartBean {

    private String name;   //名字
    private float value;    //数值
    private float percentage;   //百分比

    private int color;  //颜色
    private float angle;    //角度


    public PieChartBean() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getColor() {
        return color;
    }

    public float getAngle() {
        return angle;
    }
}
