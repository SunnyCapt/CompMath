package capt.sunny.spline.data;

import capt.sunny.spline.SplineInterpolationRule;
import capt.sunny.spline.SplineProblemWrapper;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataSet {

    private double[] X;
    private double[] Y;

    public double[] getX() {
        return X;
    }

    public double[] getY() {
        return Y;
    }

    private double a, b;

    public DataSet(double[][] data) {
        this.X = data[0];
        this.Y = data[1];
        this.a = X[0];
        this.b = X[X.length - 1];
    }

    public static XYDataset createXYDataSet(SplineProblemWrapper problem) {
        if (!problem.isValid())
            return null;

        DataSet dataset = problem.getSample();

        XYSeries s1 = new XYSeries("График №1");
        double j = dataset.a;
        double h = (dataset.b - dataset.a) / 1000;
        while (j <= dataset.b) {
            s1.add(j, problem.getFunction().apply(j));
            j += h;
        }

        double i = dataset.a;
        XYSeries s2 = new XYSeries("График №2");
        while (i <= dataset.b) {
            s2.add(i, SplineInterpolationRule.calculateInterpolateY(i, problem));
            i += h;
        }

        XYSeries s3 = new XYSeries("");
        for (int k = 0; k < dataset.X.length; k++) {
            s3.add(dataset.X[k], SplineInterpolationRule.calculateInterpolateY(dataset.X[k], problem));
        }

        XYSeriesCollection xyDataSet = new XYSeriesCollection();
        xyDataSet.addSeries(s1);
        xyDataSet.addSeries(s2);
        xyDataSet.addSeries(s3);

        return xyDataSet;
    }
}
