package capt.sunny.trapez;

import java.util.function.Function;

public class TrapezoidalRule {
    public static double calculate(IntegrationProblemWrapper problem) {
        normalizeLimits(problem);

        Function func = problem.getFunction();

        double y0 = (double) func.apply(problem.getLowLimit());
        double yN = (double) func.apply(problem.getHighLimit());

        double halfOfExtremeY = (y0 + yN) / 2;
        double result;
        double resultEpsilon;

        int n = problem.getN();
        double allowableEpsilon = problem.getAllowableEpsilon();

        problem.setResult(yN*problem.getHighLimit());

        do {
            n *= 2;
            problem.setN(n);
            result = calculateWithNStep(problem, n, halfOfExtremeY)
                    * (problem.isLimitsSwapped()?-1:1);
            resultEpsilon = calculateEpsilon(problem, result);
            problem.setResult(result);
        } while (resultEpsilon > allowableEpsilon);

        problem.setResultEpsilon(resultEpsilon);

        return result;
    }

    private static void normalizeLimits(IntegrationProblemWrapper problem) {
        double minLimit = Math.min(problem.getLowLimit(), problem.getHighLimit());

        if (minLimit != problem.getLowLimit())
            problem.swapLimits();
    }

    private static Double calculateEpsilon(
            IntegrationProblemWrapper problem,
            double newResult
    ) {
        if (problem.getResult() == null)
            return Double.POSITIVE_INFINITY;
        return Math.abs(newResult - problem.getResult()) / 3;
    }

    private static double calculateWithNStep(
            IntegrationProblemWrapper problem,
            int n,
            double halfOfExtremeY
    ) {
        double h = (problem.getHighLimit() - problem.getLowLimit()) / n;
        if (n < 3)
            return h * halfOfExtremeY;

        Function func = problem.getFunction();
        double lowLimit = problem.getLowLimit();
        double result = halfOfExtremeY;

        for (int step = 1; step < n; step++)
            result += (double) func.apply(lowLimit + step * h);

        return h * result;
    }
}
