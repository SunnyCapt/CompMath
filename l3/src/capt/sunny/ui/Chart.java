package capt.sunny.ui;

import capt.sunny.data.DataSet;
import capt.sunny.spline.SplineProblemWrapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class Chart {
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;
    private JButton button;
    private JTextField input;
    ButtonEventListener listener = null;


    public Chart(JRadioButton radio1, JRadioButton radio2, JRadioButton radio3, JButton button, JTextField input) {
        this.radio1 = radio1;
        this.radio2 = radio2;
        this.radio3 = radio3;
        this.button = button;
        this.input = input;
    }

    public JPanel createDemoPanel(SplineProblemWrapper problem) {
        JFreeChart chart = createChart(DataSet.createXYDataSet(problem));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 480));

        button.removeActionListener(listener);
        listener = new ButtonEventListener(input, problem);
        button.addActionListener(listener);

        return chartPanel;

    }


    public JFreeChart createChart(XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Интерполяция функции sin(x) кубическими сплайнами",
                null,                        // x axis label
                null,                        // y axis label
                dataset,                              // data
                PlotOrientation.VERTICAL,
                false,                         // include legend
                false,                        // tooltips
                false                           // urls
        );

        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);

        // Определение отступа меток делений
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        // Скрытие осевых линий и меток делений
        ValueAxis axis = plot.getDomainAxis();
        axis.setAxisLineVisible(false);    // осевая линия

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Настройка графика (цвет, ширина линии) Series 3
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesPaint(1, Color.green);
        renderer.setSeriesPaint(2, Color.green);
        renderer.setSeriesStroke(1, new BasicStroke(2.5f));

        plot.setRenderer(renderer);

        // Настройка NumberAxis
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }


}
