package capt.sunny.adams;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.commons.math3.util.Precision;

public class CauchyProblemWrapper {
    private double h;
    private String function;
    private double[][] table;

    public CauchyProblemWrapper(double x0, double y0, double xn, double h, String function) {
        this.h = Math.pow(h, 0.25);
        int iterations = (int) (Math.ceil((xn - x0) / this.h));
        table = new double[iterations + 1][2];
        table[0][0] = x0;
        table[0][1] = y0;
        this.function = function;
        for (int i = 1; i < iterations; i++) {
            table[i][0] = Precision.round(table[i - 1][0] + this.h, 6);
        }
        table[iterations][0] = xn;
    }

    public double getFunction(double x, double y) {
        try {
            Expression e = new ExpressionBuilder(function)
                    .variables("x", "y")
                    .build()
                    .setVariable("x", x)
                    .setVariable("y", y);
            return (e.evaluate());
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    public double[][] getTable() {
        return table;
    }

    public double getH() {
        return h;
    }

    public void setTable(double[][] valueTable) {
        this.table = valueTable;
    }
}
