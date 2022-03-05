package com.karlhaupt.Entity;

import java.util.ArrayList;

public class ChartNode {
    private String seriousName;

    private ArrayList<Number> xData, yData;
    public ChartNode(String seriousName, ArrayList<Number> xData, ArrayList<Number> yData) {
        this.seriousName = seriousName;
        this.xData = xData;
        this.yData = yData;
    }

    public String getSeriousName() {
        return seriousName;
    }

    public ArrayList<Number> getxData() {
        return xData;
    }

    public ArrayList<Number> getyData() {
        return yData;
    }
}

