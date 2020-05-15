package capt.sunny.trapez;

import java.util.function.Function;

import capt.sunny.datasource.DataSource;
import capt.sunny.datasource.DataType;

public class ConsoleManager {
    private DataSource dataSource;

    private Function<Double, Double> expX = Math::exp;
    private Function<Double, Double> logX = Math::log;
    private Function<Double, Double> sinX = Math::sin;

    public ConsoleManager() {
        dataSource = new DataSource();
    }

    public Function getFunc() throws Exception {
        String text = "Choose function:\n" +
                      "1) e^x\n" +
                      "2) log(x)\n" +
                      "3) sin(x)\n: ";

        int chosenFuncNumber = (int) dataSource.getData(text, DataType.INT);

        switch (chosenFuncNumber) {
            case 1:
                return expX;
            case 2:
                return logX;
            case 3:
                return sinX;
            default:
                throw new Exception("Wrong function choice");
        }
    }

    public double getLowLimit() throws Exception {
        return (double) dataSource.getData("Enter low limit: ", DataType.DOUBLE);
    }

    public double getHighLimit() throws Exception {
        return (double) dataSource.getData("Enter high limit: ", DataType.DOUBLE);
    }

    public double getAllowableEpsilon() throws Exception {
        return (double) dataSource.getData("Enter allowable epsilon: ", DataType.DOUBLE);
    }
}
