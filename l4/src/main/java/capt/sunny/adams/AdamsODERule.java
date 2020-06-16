package capt.sunny.adams;

import org.apache.commons.math3.util.Precision;

public class AdamsODERule {
    private CauchyProblemWrapper equation;
    private double[][] valueTable;

    public void calculate(CauchyProblemWrapper equation) {
        this.equation = equation;
        valueTable = equation.getTable();
        rungeKuttaRule();
        adamsRule();
    }

    private void rungeKuttaRule() {
        double h = equation.getH();
        for (int i = 0; i < 3; i++) {
            double k1 = equation.getFunction(valueTable[i][0], valueTable[i][1]);
            double k2 = equation.getFunction((valueTable[i][0] + h / 2), (valueTable[i][1] + h * k1 / 2));
            double k3 = equation.getFunction((valueTable[i][0] + h / 2), (valueTable[i][1] + h * k2 / 2));
            double k4 = equation.getFunction((valueTable[i][0] + h), (valueTable[i][1] + h * k3));
            double nextY = valueTable[i][1] + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            valueTable[i + 1][1] = Precision.round(nextY, 6);
        }

    }

    private void adamsRule() {
        double h = equation.getH();
        for (int i = 3; i < valueTable.length - 1; i++) {
            double nextY = valueTable[i][1] + h * getFi(i + 1) +
                    Math.pow(h, 2) / 2 * getDeltaFi(i + 1) +
                    5 * Math.pow(h, 3) / 12 * getDelta2Fi(i + 1) +
                    3 * Math.pow(h, 4) / 8 * getDelta3Fi(i + 1);
            valueTable[i + 1][1] = Precision.round(nextY, 6);
        }
        equation.setTable(valueTable);
    }

    private double getFi(int i) {
        return equation.getFunction(valueTable[i - 1][0], valueTable[i - 1][1]);
    }

    private double getDeltaFi(int i) {
        return getFi(i) - getFi(i - 1);
    }

    private double getDelta2Fi(int i) {
        return getFi(i) - 2 * getFi(i - 1) + getFi(i - 2);
    }

    private double getDelta3Fi(int i) {
        return getFi(i) - 3 * getFi(i - 1) + 3 * getFi(i - 2) + getFi(i - 3);
    }
}
