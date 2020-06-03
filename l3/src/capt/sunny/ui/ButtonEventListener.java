package capt.sunny.ui;

import capt.sunny.spline.SplineInterpolationRule;
import capt.sunny.spline.SplineProblemWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEventListener implements ActionListener {

    private JTextField input;
    private SplineProblemWrapper problem;

    public ButtonEventListener(JTextField input, SplineProblemWrapper problem) {
        this.input = input;
        this.problem = problem;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (input.getText().trim().isEmpty())
                return;
            double x = Double.parseDouble(input.getText().trim());
            double y = SplineInterpolationRule.calculateInterpolateY(x, problem);
            String message = "Приближённое значение функции : " + y;
            JOptionPane.showMessageDialog(null, message, "Value", JOptionPane.PLAIN_MESSAGE);
        } catch (NumberFormatException e1) {
            e1.printStackTrace();
        }
    }
}
