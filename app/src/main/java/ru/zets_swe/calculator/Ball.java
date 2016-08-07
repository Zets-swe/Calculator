package ru.zets_swe.calculator;

/**
 * Created by Zets on 04.08.2016.
 */
public class Ball {

    //region getter/setter

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public double getDiameter_min() {
        return diameter_min;
    }

    public void setDiameter_min(double diameter_min) {
        this.diameter_min = diameter_min;
    }

    public double getDiameter_max() {
        return diameter_max;
    }

    public void setDiameter_max(double diameter_max) {
        this.diameter_max = diameter_max;
    }

    public double getFlow_min() {
        return flow_min;
    }

    public void setFlow_min(double flow_min) {
        this.flow_min = flow_min;
    }

    public double getFlow_1_bar() {
        return flow_1_bar;
    }

    public void setFlow_1_bar(double flow_1_bar) {
        this.flow_1_bar = flow_1_bar;
    }

    public double getFlow_1_5_bar() {
        return flow_1_5_bar;
    }

    public void setFlow_1_5_bar(double flow_1_5_bar) {
        this.flow_1_5_bar = flow_1_5_bar;
    }

    public double getFlow_1_7_bar() {
        return flow_1_7_bar;
    }

    public void setFlow_1_7_bar(double flow_1_7_bar) {
        this.flow_1_7_bar = flow_1_7_bar;
    }

    public double getFlow_2_bar() {
        return flow_2_bar;
    }

    public void setFlow_2_bar(double flow_2_bar) {
        this.flow_2_bar = flow_2_bar;
    }

    public double getFlow_2_5_bar() {
        return flow_2_5_bar;
    }

    public void setFlow_2_5_bar(double flow_2_5_bar) {
        this.flow_2_5_bar = flow_2_5_bar;
    }

    public double getFlow_3_bar() {
        return flow_3_bar;
    }

    public void setFlow_3_bar(double flow_3_bar) {
        this.flow_3_bar = flow_3_bar;
    }

    public double getFlow_3_4_bar() {
        return flow_3_4_bar;
    }

    public void setFlow_3_4_bar(double flow_3_4_bar) {
        this.flow_3_4_bar = flow_3_4_bar;
    }

    public double getFlow_4_bar() {
        return flow_4_bar;
    }

    public void setFlow_4_bar(double flow_4_bar) {
        this.flow_4_bar = flow_4_bar;
    }

    public double getFlow_5_bar() {
        return flow_5_bar;
    }

    public void setFlow_5_bar(double flow_5_bar) {
        this.flow_5_bar = flow_5_bar;
    }

    public double getFlow_6_bar() {
        return flow_6_bar;
    }

    public void setFlow_6_bar(double flow_6_bar) {
        this.flow_6_bar = flow_6_bar;
    }

    public double getFlow_7_bar() {
        return flow_7_bar;
    }

    public void setFlow_7_bar(double flow_7_bar) {
        this.flow_7_bar = flow_7_bar;
    }

    public double getFlow_8_bar() {
        return flow_8_bar;
    }

    public void setFlow_8_bar(double flow_8_bar) {
        this.flow_8_bar = flow_8_bar;
    }

    public double getFlow_8_3_bar() {
        return flow_8_3_bar;
    }

    public void setFlow_8_3_bar(double flow_8_3_bar) {
        this.flow_8_3_bar = flow_8_3_bar;
    }

    public double getFlow_9_bar() {
        return flow_9_bar;
    }

    public void setFlow_9_bar(double flow_9_bar) {
        this.flow_9_bar = flow_9_bar;
    }

    public double getFlow_9_7_bar() {
        return flow_9_7_bar;
    }

    public void setFlow_9_7_bar(double flow_9_7_bar) {
        this.flow_9_7_bar = flow_9_7_bar;
    }

    public double getFlow_10_bar() {
        return flow_10_bar;
    }

    public void setFlow_10_bar(double flow_10_bar) {
        this.flow_10_bar = flow_10_bar;
    }

    public double getFlow_11_bar() {
        return flow_11_bar;
    }

    public void setFlow_11_bar(double flow_11_bar) {
        this.flow_11_bar = flow_11_bar;
    }

    public double getFlow_12_bar() {
        return flow_12_bar;
    }

    public void setFlow_12_bar(double flow_12_bar) {
        this.flow_12_bar = flow_12_bar;
    }

    public double getFlow_12_4_bar() {
        return flow_12_4_bar;
    }

    public void setFlow_12_4_bar(double flow_12_4_bar) {
        this.flow_12_4_bar = flow_12_4_bar;
    }

    public double getFlow_13_8_bar() {
        return flow_13_8_bar;
    }

    public void setFlow_13_8_bar(double flow_13_8_bar) {
        this.flow_13_8_bar = flow_13_8_bar;
    }

    public double getFlow_16_bar() {
        return flow_16_bar;
    }

    public void setFlow_16_bar(double flow_16_bar) {
        this.flow_16_bar = flow_16_bar;
    }

    public double getFlow_max() {
        return flow_max;
    }

    public void setFlow_max(double flow_max) {
        this.flow_max = flow_max;
    }

    public double getPressure_min() {
        return pressure_min;
    }

    public void setPressure_min(double pressure_min) {
        this.pressure_min = pressure_min;
    }

    public double getPressure_max() {
        return pressure_max;
    }

    public void setPressure_max(double pressure_max) {
        this.pressure_max = pressure_max;
    }

    //endregion

    private String type;
    private String factory;
    private String series;
    private String mark;
    private int angle;
    private double diameter_min;
    private double diameter_max;
    private double flow_min;
    private double flow_1_bar;
    private double flow_1_5_bar;
    private double flow_1_7_bar;
    private double flow_2_bar;
    private double flow_2_5_bar;
    private double flow_3_bar;
    private double flow_3_4_bar;
    private double flow_4_bar;
    private double flow_5_bar;
    private double flow_6_bar;
    private double flow_7_bar;
    private double flow_8_bar;
    private double flow_8_3_bar;
    private double flow_9_bar;
    private double flow_9_7_bar;
    private double flow_10_bar;
    private double flow_11_bar;
    private double flow_12_bar;
    private double flow_12_4_bar;
    private double flow_13_8_bar;
    private double flow_16_bar;
    private double flow_max;
    private double pressure_min;
    private double pressure_max;


}
