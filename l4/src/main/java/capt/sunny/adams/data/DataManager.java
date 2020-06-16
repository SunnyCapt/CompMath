package capt.sunny.adams.data;

import capt.sunny.adams.AdamsODERule;
import capt.sunny.adams.CauchyProblemWrapper;
import capt.sunny.ui.MainPanel;

public class DataManager {
    public static CauchyProblemWrapper parseFromMainPanel(MainPanel mainPanel) {
        double x0 = Double.parseDouble(mainPanel.getBeginXField().getText());
        double y0 = Double.parseDouble(mainPanel.getBeginYField().getText());
        double xn = Double.parseDouble(mainPanel.getEndXField().getText());
        double h = Double.parseDouble(mainPanel.getAccuracyField().getText());
        String function = mainPanel.getFunctionField().getText();
        CauchyProblemWrapper equation = new CauchyProblemWrapper(x0, y0, xn, h, function);
        AdamsODERule numericalMethods = new AdamsODERule();
        numericalMethods.calculate(equation);
        return equation;
    }
}
