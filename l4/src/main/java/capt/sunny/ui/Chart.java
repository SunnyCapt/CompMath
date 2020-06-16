package capt.sunny.ui;

import capt.sunny.spline.SplineProblemWrapper;
import capt.sunny.spline.data.DataSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

public class Chart {
    private JPanel jPanel;
    private XYDataset first;
    private DefaultXYDataset points;
    //    private double[][] data;
//    private FunctionWrapper function;
    private SplineProblemWrapper problem;


    public Chart(SplineProblemWrapper problem) {
        this.problem = problem;
        setDataset();
        jPanel = setElement();
    }


    private JPanel setElement() {
        JPanel jPanel = new JPanel();
        jPanel.add(createPanel());
        return jPanel;
    }


    private JPanel createPanel() {
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        });
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setMouseZoomable(true);
        return chartPanel;
    }

    private void setDataset() {
        first = DataSet.createXYDataSet(problem);
        points = new DefaultXYDataset();
        points.addSeries(
                "Points",
                new double[][]{problem.getSample().getY(), problem.getSample().getX()}
        );
    }


    private JFreeChart createChart() {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graphs",
                "X",
                "Y",
                points,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = (XYPlot) chart.getPlot();

        plot.setRenderer(0, new XYLineAndShapeRenderer());
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer(0);
//        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible (0, true);

        plot.setDataset(1, first);
        plot.setRenderer(1, new StandardXYItemRenderer());


        plot.getDomainAxis().setLowerMargin(0.0);
        plot.getDomainAxis().setUpperMargin(0.0);
        return chart;
    }

    private double[][] getLeftAndRightBorders() {
        DoubleSummaryStatistics stat = Arrays.stream(problem.getSample().getX()).summaryStatistics();
        return new double[][]{{stat.getMax(), stat.getMin()}};
    }


//    static class FunctionSolver implements Function2D {
//        Expression ex;
//        Type type;
//
//        FunctionSolver(Expression ex, Type type) {
//            this.ex = ex;
//            this.type = type;
//        }
//
//        public double getValue(double x) {
//            if ((type == Type.HYPERBOLA || type == Type.POWER || type == Type.EXP) && x == 0) x += 0.1E-1;
//            return ex.setVariable("x", x).evaluate();
//        }
//    }

    public JPanel getJPanel() {
        return jPanel;
    }
}
