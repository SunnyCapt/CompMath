package capt.sunny.spline;


public class SplineInterpolationRule {
    public static void calculateSplines(SplineProblemWrapper problem) {
        double[] x = new double[problem.getSample().getX().length];
        double[] y = new double[problem.getSample().getY().length];
        for (int i = 0; i < problem.getSample().getX().length; i++) {
            x[i] = problem.getSample().getX()[i];
            y[i] = problem.getSample().getY()[i];
        }
        int n = x.length;
        double[] h = new double[n];
        double[] l = new double[n];
        double[] delta = new double[n];
        double[] lambda = new double[n];
        Spline[] coefficients = new Spline[n];
        problem.setCoefficients(coefficients);
        coefficients[0] = new Spline();
        for (int i = 1; i < n; i++) {
            Spline tmp = new Spline();
            tmp.setX(x[i]);
            tmp.setA(y[i]);
            coefficients[i] = tmp;
            h[i] = x[i] - x[i - 1];
            l[i] = (y[i] - y[i - 1]) / h[i];
        }
        delta[1] = -h[2] / (2 * (h[1] + h[2]));
        lambda[1] = 1.5 * (l[2] - l[1]) / (h[1] + h[2]);
        for (int k = 3; k < n; k++) {
            double v = 2 * h[k - 1] + 2 * h[k] + h[k - 1] * delta[k - 2];
            delta[k - 1] = -h[k] / v;
            lambda[k - 1] = (3 * l[k] - 3 * l[k - 1] - h[k - 1] * lambda[k - 2]) / v;
        }
        coefficients[0].setC(0);
        coefficients[n - 1].setC(0);

        for (int k = n - 1; k >= 2; k--) {
            coefficients[k - 1].setC(delta[k - 1] * coefficients[k].getC() + lambda[k - 1]);
        }
        for (int k = 1; k <= n - 1; k++) {
            coefficients[k].setD((coefficients[k].getC() - coefficients[k - 1].getC()) / (3 * h[k]));
            coefficients[k].setB(l[k] + (2 * coefficients[k].getC() * h[k] + h[k] * coefficients[k - 1].getC()) / 3);
        }
    }

    public static double calculateInterpolateY(double x, SplineProblemWrapper problem) {
        Spline s;
        if (x <= problem.getCoefficients()[1].getX()) { //х ниже
            s = problem.getCoefficients()[1];
        } else if (x >= problem.getCoefficients()[problem.getCoefficients().length - 1].getX()) {//x выше
            s = problem.getCoefficients()[problem.getCoefficients().length - 1];
        } else { //x внутри
            int i = 1;
            int j = problem.getCoefficients().length;
            while (i + 1 < j) {
                int k = i + (j - i) / 2;
                if (x <= problem.getCoefficients()[k].getX()) {
                    j = k;
                } else {
                    i = k;
                }
            }

            s = problem.getCoefficients()[j];
        }
        double dx = x - s.getX();
        return s.getA() + s.getB() * dx + s.getC() * dx * dx + s.getD() * dx * dx * dx;
    }
}