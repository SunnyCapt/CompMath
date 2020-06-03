package capt.sunny.spline;

import capt.sunny.data.DataReceiver;
import capt.sunny.data.DataSet;

import java.util.function.Function;

public class SplineProblemWrapper {
    private Function<Double, Double> function = Math::sin;
    private double[][] points;
    private Spline[] coefficients;
    private double[] interpolatedY;
    private DataSet sample = new DataSet(DataReceiver.receiveData(1));

    public void setSample(DataSet sample) {
        this.sample = sample;
    }

    public DataSet getSample() {
        return sample;
    }

    public void setInterpolatedY(double[] interpolatedY) {
        this.interpolatedY = interpolatedY;
    }

    public void setCoefficients(Spline[] coefficients) {
        this.coefficients = coefficients;
    }

    public void setFunction(Function<Double, Double> function) {
        this.function = function;
    }

    public void setPoints(double[][] points) {
        this.points = points;
    }

    public double[] getInterpolatedY() {
        return interpolatedY;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public Spline[] getCoefficients() {
        return coefficients;
    }

    public double[][] getPoints() {
        return points;
    }

    public boolean isValid() {
        return function != null && coefficients != null && sample != null;
    }
}
