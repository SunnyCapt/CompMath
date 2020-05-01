package capt.sunny.gaussian;

public class GaussianElim {
    public static double calculateDeterminant(MatrixWrapper data) {
        if (data.getDeterminant() != null)
            return data.getDeterminant();

        if (data.getTriangularMatrix() == null)
            calculateTriangularMatrix(data);

        double determinant = 1.0;
        for (int i = 0; i < data.getMatrixSize(); i++) {
            determinant *= data.getTriangularMatrix()[i][i];
        }

        data.setDeterminant(determinant);

        return determinant;
    }

    public static double[][] calculateTriangularMatrix(MatrixWrapper data) {
        if (data.getTriangularMatrix() != null)
            return data.getTriangularMatrix();

        double[][] tmpTriangularMatrix = new double[data.getMatrixSize()][data.getMatrixSize() + 1];
        for (int i = 0; i < data.getMatrixSize(); i++) {
            System.arraycopy(
                    data.getMainCoefficients()[i], 0,
                    tmpTriangularMatrix[i], 0,
                    data.getMatrixSize()
            );
            tmpTriangularMatrix[i][data.getMatrixSize()] = data.getFreeCoefficients()[i][0];
        }

        for (int j = 0; j < data.getMatrixSize() - 1; j++) {
            try {
                prepareSubj(j, tmpTriangularMatrix, data);
            } catch (Exception e) {
                continue;
            }
            for (int i = data.getMatrixSize() - 1; i > j; i--) {
                if (tmpTriangularMatrix[i][j] != 0)
                    toZero(tmpTriangularMatrix[i], tmpTriangularMatrix[j], j);
            }
        }

        data.setTriangularMatrix(tmpTriangularMatrix);
        return tmpTriangularMatrix;
    }

    public static double[][] calculateUnknownVars(MatrixWrapper data) {
        if (data.getUnknownVars() != null)
            return data.getUnknownVars();

        if (data.getTriangularMatrix() == null)
            calculateTriangularMatrix(data);

        double[] knownVars = new double[data.getMatrixSize()];
        for (int i = data.getMatrixSize() - 1; i >= 0; i--) {
            knownVars[i] = calcUnknownVar(i, knownVars, data);
        }

        double[][] unknownVars = new double[data.getMatrixSize()][1];
        for (int i = 0; i < data.getMatrixSize(); i++)
            unknownVars[i][0] = knownVars[i];

        data.setUnknownVars(unknownVars);

        return unknownVars;
    }

    public static double[][] calculateNevyazka(MatrixWrapper data) throws Exception {
        if (data.getNevyazka() != null)
            return data.getNevyazka();

        if (data.getUnknownVars() == null)
            calculateUnknownVars(data);

        double[][] nevyazka = new double[data.getMatrixSize()][1];
        for (int i = 0; i < data.getMatrixSize(); i++) {
            double leftPartSum = 0;
            for (int j = 0; j < data.getMatrixSize(); j++) {
                leftPartSum += data.getMainCoefficients()[i][j] * data.getUnknownVars()[j][0];
            }
            nevyazka[i][0] = leftPartSum - data.getFreeCoefficients()[i][0];
        }
        return nevyazka;
    }

    private static double calcUnknownVar(int i, double[] knownVars, MatrixWrapper data) {
        double sumOfRightPart = data.getTriangularMatrix()[i][data.getMatrixSize()];
        double sumOfLeftpart = 0;
        for (int k = i + 1; k < data.getMatrixSize(); k++) {
            sumOfLeftpart += knownVars[k] * data.getTriangularMatrix()[i][k];
        }
        return (sumOfRightPart - sumOfLeftpart) / data.getTriangularMatrix()[i][i];
    }

    private static void prepareSubj(int j, double[][] tmpTriangularMatrix, MatrixWrapper data) throws Exception {
        if (tmpTriangularMatrix[j][j] != 0)
            return;
        double[] s = null;
        for (int p = j + 1; p < data.getMatrixSize(); p++) {
            if (tmpTriangularMatrix[p][j] != 0) {
                s = tmpTriangularMatrix[p];
                break;
            }
        }

        if (s == null)
            throw new Exception("All subelements are zero");

        for (int k = 0; k < data.getMatrixSize() + 1; k++)
            tmpTriangularMatrix[j][k] += s[k];
    }

    private static void toZero(double[] obj, double[] subj, int i) {
        double subjMul = -1 * obj[i] / subj[i];
        for (int k = 0; k < subj.length; k++) {
            obj[k] += subj[k] * subjMul;
        }
    }
}


