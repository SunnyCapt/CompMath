package capt.sunny.gaussian;

import java.util.Arrays;

public class MatrixWrapper {
    private Integer matrixSize;
    private double[][] mainCoefficients;
    private double[][] freeCoefficients;
    private double[][] triangularMatrix;
    private Double determinant;
    private double[][] unknownVars;
    private double[][] nevyazka;

    public MatrixWrapper(int matrixSize, double[][] mainCoefficients, double[][] freeCoefficients) {
        this.matrixSize = matrixSize;
        this.mainCoefficients = mainCoefficients;
        this.freeCoefficients = freeCoefficients;
    }

    public Integer getMatrixSize() {
        return matrixSize;
    }

    public double[][] getMainCoefficients() {
        double[][] mainCopy = new double[mainCoefficients.length][];
        for (int i = 0; i < mainCoefficients.length; i++)
            mainCopy[i] = Arrays.copyOf(mainCoefficients[i], mainCoefficients[i].length);
        return mainCopy;
    }

    public double[][] getFreeCoefficients() {
        double[][] freeCopy = new double[freeCoefficients.length][];
        for (int i = 0; i < freeCoefficients.length; i++)
            freeCopy[i] = Arrays.copyOf(freeCoefficients[i], freeCoefficients[i].length);
        return freeCopy;
    }

    public double[][] getTriangularMatrix() {
        return triangularMatrix;
    }

    public Double getDeterminant() {
        return determinant;
    }

    public double[][] getUnknownVars() {
        return unknownVars;
    }

    public double[][] getNevyazka() {
        return nevyazka;
    }

    public void setTriangularMatrix(double[][] triangularMatrix) {
        this.triangularMatrix = triangularMatrix;
    }

    public void setDeterminant(Double determinant) {
        this.determinant = determinant;
    }

    public void setUnknownVars(double[][] unknownVars) {
        this.unknownVars = unknownVars;
    }

    public void setNevyazka(double[][] nevyazka) {
        this.nevyazka = nevyazka;
    }
}
