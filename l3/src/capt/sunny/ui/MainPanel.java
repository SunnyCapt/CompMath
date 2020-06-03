package capt.sunny.ui;

import capt.sunny.spline.SplineInterpolationRule;
import capt.sunny.spline.SplineProblemWrapper;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {

    private SplineProblemWrapper problem = new SplineProblemWrapper();
    private JButton button = new JButton("Вычислить");
    private JTextField input = new JTextField("", 1);
    private JLabel label = new JLabel("Координата X : ");
    private JRadioButton radio1 = new JRadioButton("Первая выборка", true);
    private JRadioButton radio2 = new JRadioButton("Вторая выборка");
    private JRadioButton radio3 = new JRadioButton("Третья выборка");
    private JRadioButton radio4 = new JRadioButton("Четвертая выборка");

    {
        radio1.setName("1");
        radio2.setName("2");
        radio3.setName("3");
        radio4.setName("4");
        SplineInterpolationRule.calculateSplines(problem);
    }


    public MainPanel() throws Exception {
        super("График функции");
        this.setBounds(100, 100, 600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        group.add(radio4);

        label.setPreferredSize(new Dimension(50, 25));
        input.setPreferredSize(new Dimension(25, 25));
        button.setPreferredSize(new Dimension(25, 25));

        Chart chart = new Chart(radio1, radio2, radio3, button, input);
        Container container = this.getContentPane();

        container.add(radio1);
        container.add(radio2);
        container.add(radio3);
        container.add(radio4);
        container.add(label);
        container.add(input);
        container.add(button);

        JPanel myPanel = chart.createDemoPanel(problem);
        container.add(myPanel);

        ChangeGraphic changeGraphic = new ChangeGraphic(container, chart, problem);

        radio1.addActionListener(changeGraphic);
        radio2.addActionListener(changeGraphic);
        radio3.addActionListener(changeGraphic);
        radio4.addActionListener(changeGraphic);

        setLayout(new BoxLayout(container, 1));
    }
}



