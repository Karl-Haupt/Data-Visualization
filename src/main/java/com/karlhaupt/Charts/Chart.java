package com.karlhaupt.Charts;

import java.util.ArrayList;

public interface Chart {
    //TODO: add different types of chart

    //setChartTitle
    //setChartDescription
    //getTypeOfChart(Chart chart) : Chart
    //setXAxisTitle
    //setYAxisTitle
    //addSerious : DataType

    void setChartTitle(String chartTitle);
    void setChartDescription(String description);
    Chart getTypeOfChart(Chart chart);
    void setXAxisTitle(String title);
    void setYAxisTitle(String title);
    void addSerious(String seriousName, ArrayList<Number> xData, ArrayList<Number> yData);
    void buildChart();
    void displayChart();
}
