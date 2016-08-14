package ru.zets_swe.calculator;

import java.util.List;

/**
 * Created by Zets on 11.08.2016.
 */
public class SelectionBall {
    String factory;
    String series;
    String mark;
    double diameter_min;
    double diameter_max;
    double flow_min;
    double flow_max;
    double pressure_min;
    double pressure_max;

    public SelectionBall(String factory, String series, String mark, double diameter_min, double diameter_max, double flow_min, double flow_max, double pressure_min, double pressure_max) {
        this.factory = factory;
        this.series = series;
        this.mark = mark;
        this.diameter_min = diameter_min;
        this.diameter_max = diameter_max;
        this.flow_min = flow_min;
        this.flow_max = flow_max;
        this.pressure_min = pressure_min;
        this.pressure_max = pressure_max;
    }

    public SelectionBall(Ball ball) {
        this.factory = ball.getFactory();
        this.series = ball.getSeries();
        this.mark = ball.getMark();
        this.diameter_min = ball.getDiameter_min();
        this.diameter_max = ball.getDiameter_max();
        this.flow_min = ball.getFlow_min();
        this.flow_max = ball.getFlow_max();
        this.pressure_min = ball.getPressure_min();
        this.pressure_max = ball.getPressure_max();
    }


}
