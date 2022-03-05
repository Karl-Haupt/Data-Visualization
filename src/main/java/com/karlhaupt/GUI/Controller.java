package com.karlhaupt.GUI;

import com.karlhaupt.Charts.AreaChart;
import com.karlhaupt.Charts.Chart;
import com.karlhaupt.Entity.ChartNode;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Controller implements ActionListener {
    private JFrame frame;
    private JPanel pnlNorth, pnlCenter, pnlSouth;
    private JLabel lblChartTitle, lblTypeOfChart, lblChartDescription, lblxAxisTitle, lblyAxisTitle, lblxData, lblyData, lblSeriousName;
    private JTextField txtChartTitle, txtChartDescription, txtxAxisTitle, txtyAxisTitle, txtSeriousName;
    private JComboBox jcbTypeOfChart;
    private JTextArea jtaxData, jtayData;
    private JButton btnAddSerious, btnConstructChart;
    private Font fontHeading;

    private Chart chart;
    private ArrayList<ChartNode> seriousData;

    public Controller() {
        frame = new JFrame("Data Visualizer");
        seriousData = new ArrayList<>();

        pnlNorth = new JPanel();
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();

        lblChartTitle = new JLabel("Chart title: ");
        lblTypeOfChart = new JLabel("Type of Chart: ");
        lblChartDescription = new JLabel("Chart Description: ");
        lblxAxisTitle = new JLabel("  X axis title: ");
        lblyAxisTitle = new JLabel("  Y axis title: ");
        lblxData = new JLabel("Add Data (x axis):  ");
        lblyData = new JLabel("Add Data (y axis):  ");
        lblSeriousName = new JLabel("Serious Name: ");

        txtChartTitle = new JTextField();
        txtChartDescription = new JTextField();
        txtxAxisTitle = new JTextField();
        txtyAxisTitle = new JTextField();
        txtSeriousName = new JTextField();

        jcbTypeOfChart = new JComboBox();

        jtaxData = new JTextArea();
        jtayData = new JTextArea();

        btnAddSerious = new JButton("Add serious");
        btnConstructChart = new JButton("Construct chart");

        setGUI();

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setGUI() {
        fontHeading = new Font("Sans Serif", Font.BOLD, 14);

        txtChartTitle.setBackground(Color.GRAY);
        txtChartDescription.setBackground(Color.GRAY);
        txtxAxisTitle.setBackground(Color.GRAY);
        txtyAxisTitle.setBackground(Color.GRAY);
        jcbTypeOfChart.setBackground(Color.GRAY);

        btnAddSerious.addActionListener(this);
        btnConstructChart.addActionListener(this);

        pnlNorth.setLayout(new GridLayout(3, 4));
        pnlNorth.setBackground(Color.GRAY);
        pnlNorth.setFont(fontHeading);

        pnlNorth.add(lblChartTitle);
        pnlNorth.add(txtChartTitle);
        pnlNorth.add(lblxAxisTitle);
        pnlNorth.add(txtxAxisTitle);
        pnlNorth.add(lblTypeOfChart);
        pnlNorth.add(jcbTypeOfChart);
        pnlNorth.add(lblyAxisTitle);
        pnlNorth.add(txtyAxisTitle);
        pnlNorth.add(lblChartDescription);
        pnlNorth.add(txtChartDescription);

        pnlCenter.setLayout(new GridLayout(4,4));
        pnlCenter.setBackground(Color.GRAY);
        pnlCenter.setFont(fontHeading);

        pnlCenter.add(new JLabel());
        pnlCenter.add(new JLabel());
        pnlCenter.add(new JLabel());
        pnlCenter.add(new JLabel());

        pnlCenter.add(lblSeriousName);
        pnlCenter.add(txtSeriousName);
        pnlCenter.add(new JLabel());
        pnlCenter.add(new JLabel());

        pnlCenter.add(lblxData);
        pnlCenter.add(jtaxData);
        pnlCenter.add(lblyData);
        pnlCenter.add(jtayData);

        pnlCenter.add(new JLabel());
        pnlCenter.add(new JLabel());
        pnlCenter.add(new JLabel());
        pnlCenter.add(new JLabel());

        pnlSouth.setLayout(new GridLayout(1,2));
        pnlSouth.setBackground(Color.GRAY);
        pnlSouth.setFont(fontHeading);
        btnConstructChart.setBackground(Color.GREEN);
        btnAddSerious.setBackground(Color.BLUE);

        pnlSouth.add(btnConstructChart);
        pnlSouth.add(btnAddSerious);

        frame.add(pnlNorth, BorderLayout.NORTH);
        frame.add(pnlCenter, BorderLayout.CENTER);
        frame.add(pnlSouth, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, "Separate the values by using ',' ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAddSerious) {
            seriousData.add(new ChartNode(txtSeriousName.getText(), extractNumbersFromTxtArea(jtaxData), extractNumbersFromTxtArea(jtayData)));
            jtaxData.setEnabled(false);
            jtayData.setText("");
            txtSeriousName.setText("");
        } else if(e.getSource() == btnConstructChart) {
            if(validate() && seriousData.size() > 0) {
                chart = new AreaChart();
                chart.buildChart();

                chart.setChartTitle(txtChartTitle.getText());
                chart.setXAxisTitle(txtxAxisTitle.getText());
                chart.setYAxisTitle(txtxAxisTitle.getText());
                chart.setChartDescription(txtChartDescription.getText());

                addChartSerious();

                chart.displayChart();
            } else JOptionPane.showMessageDialog(null, "Data Missing");
        }
    }

    private boolean validate() {
        if(txtChartTitle.getText() != "" || txtChartDescription.getText() != "" || txtxAxisTitle.getText() != "" || txtyAxisTitle.getText() != "")
            return true;

        return false;
    }

    private void addChartSerious() {
        for (var chartNode : seriousData)
            chart.addSerious(chartNode.getSeriousName(), chartNode.getxData(), chartNode.getyData());
    }


    private ArrayList<Number> extractNumbersFromTxtArea(JTextArea txtData) {
        ArrayList<Number> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(txtData.getText(), ",");
        while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken().trim()));

        return list;
    }
}
