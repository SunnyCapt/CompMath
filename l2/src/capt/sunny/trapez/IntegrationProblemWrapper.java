package capt.sunny.trapez;

import java.util.function.Function;

public class IntegrationProblemWrapper {
    private Function function;
    private double lowLimit;
    private double highLimit;
    private double allowableEpsilon;
    private int n = 1;
    private Double result;
    private double resultEpsilon;
    private boolean isLimitsSwapped = false;

    public IntegrationProblemWrapper(Function function, double lowLimit, double highLimit, double allowableEpsilon) {
        this.function = function;
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.allowableEpsilon = allowableEpsilon;
    }

    public boolean isLimitsSwapped() {
        return isLimitsSwapped;
    }


    public Function getFunction() {
        return function;
    }

    public double getLowLimit() {
        return lowLimit;
    }

    public double getHighLimit() {
        return highLimit;
    }

    public double getAllowableEpsilon() {
        return allowableEpsilon;
    }

    public int getN() {
        return n;
    }

    public double getResultEpsilon() {
        return resultEpsilon;
    }

    public Double getResult() {
        return result;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setResultEpsilon(double resultEpsilon) {
        this.resultEpsilon = resultEpsilon;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setHighLimit(double value) {
        if (value != highLimit && value != this.lowLimit)
            throw new RuntimeException("you can only swap low and high limits");
        highLimit = value;
    }

    public void swapLimits() {
        double lowLimit = this.lowLimit;
        this.lowLimit = this.highLimit;
        this.highLimit = lowLimit;
        isLimitsSwapped = !isLimitsSwapped;
    }
}
