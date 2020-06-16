package capt.sunny;

import capt.sunny.adams.CauchyProblemWrapper;
import capt.sunny.adams.AdamsODERule;
import capt.sunny.adams.data.DataManager;
import capt.sunny.spline.SplineProblemWrapper;
import capt.sunny.spline.data.DataSet;
import capt.sunny.ui.Chart;
import capt.sunny.ui.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Application {
    private MainPanel mainPanel;
    private Chart chart;

    public static void main(String[] args) {
        new Application();
    }

    public Application() {
        mainPanel = new MainPanel(1200, 700);
        addActionListener(mainPanel);
    }

    private void addActionListener(MainPanel mainPanel) {

        mainPanel.getButton().addActionListener(li -> {
            CauchyProblemWrapper equation;
            mainPanel.getErrorLabel().setText("");
            try {
                equation = DataManager.parseFromMainPanel(mainPanel);
            } catch (NumberFormatException e) {
                mainPanel.getErrorLabel().setText("Enter parameters:");
                return;
            }

            mainPanel.getPanel().remove(mainPanel.getPanelTable());
            JScrollPane wrapperTable = mainPanel.initTable(equation.getTable());
            GridBagConstraints baseConstraints = new GridBagConstraints();
            mainPanel.setPanelTable(wrapperTable);

            baseConstraints.gridx = 0;
            baseConstraints.gridy = 1;
            mainPanel.getPanel().add(wrapperTable, baseConstraints);

            SplineProblemWrapper problem;
            try {
                double[][] data = equation.getTable();
                problem = new SplineProblemWrapper();
                double[][] sampleData = new double[2][data.length];
                for (int i=0; i< data.length; i++) {
                    sampleData[0][i] = data[i][1];
                    sampleData[1][i] = data[i][0];
                }
                DataSet sample = new DataSet(sampleData);
                problem.setSample(sample);
            } catch (ArithmeticException | IllegalArgumentException e) {
                e.printStackTrace();
                mainPanel.getErrorLabel().setText("Errors occurred during approximation");
                return;
            }


            if (chart != null) {
                mainPanel.getPanelChart().remove(chart.getJPanel());
            }

            chart = new Chart(problem);
            mainPanel.getPanelChart().add(chart.getJPanel());

            mainPanel.getPanel().revalidate();
            mainPanel.getPanel().repaint();
        });
    }


}
