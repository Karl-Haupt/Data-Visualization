package com.karlhaupt.Charts;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AreaChart implements Chart {
    private XYChart chart;
    private String chartDescription;

    public AreaChart() {

    }
    //first
    @Override
    public void buildChart() {
        this.chart = new XYChartBuilder().width(600).height(400).build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
    }

    @Override
    public void displayChart() {
        JFrame frame = new JFrame("Chart - Data ");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        frame.add(chartPanel, BorderLayout.CENTER);

        JLabel label = new JLabel(chartDescription, SwingConstants.CENTER);
        frame.add(label, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void setChartTitle(String chartTitle) {
        chart.setTitle(chartTitle);
    }

    @Override
    public void setChartDescription(String description) {
        this.chartDescription = description;
    }

    @Override
    public void setXAxisTitle(String title) {
        chart.setXAxisTitle(title);
    }

    @Override
    public void setYAxisTitle(String title) {
        chart.setYAxisTitle(title);
    }

    @Override
    public void addSerious(String seriousName, ArrayList<Number> xData, ArrayList<Number> yData) {
        chart.addSeries(seriousName, xData, yData);
    }

    @Override
    public Chart getTypeOfChart(Chart chart) {
        return chart;
    }
}
