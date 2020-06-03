package capt.sunny.ui;

import capt.sunny.data.DataReceiver;
import capt.sunny.data.DataSet;
import capt.sunny.spline.SplineInterpolationRule;
import capt.sunny.spline.SplineProblemWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeGraphic implements ActionListener {
    private Container container;
    private Chart chart;
    private SplineProblemWrapper problem;

    public ChangeGraphic(Container container, Chart chart, SplineProblemWrapper problem) {
        this.container = container;
        this.chart = chart;
        this.problem = problem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JRadioButton) {
            DataSet sample = new DataSet(DataReceiver.receiveData(Integer.parseInt(((JRadioButton) obj).getName())));
            problem.setSample(sample);
            SplineInterpolationRule.calculateSplines(problem);
        }
        container.remove(7);
        container.add(chart.createDemoPanel(problem));
        container.revalidate();
        container.repaint();
    }
}
