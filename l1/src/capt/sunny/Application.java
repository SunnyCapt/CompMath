package capt.sunny;

import capt.sunny.gaussian.GaussianElim;
import capt.sunny.gaussian.MatrixWrapper;

import java.util.Arrays;

public class Application {
    private static boolean debug;

    public static void main(String[] args) throws Exception {
        try {
            MatrixWrapper data = new DataSource().getMatrix();
            System.out.println("\n\nResult:");

            double determinant = GaussianElim.calculateDeterminant(data);
            System.out.printf("Determinant: %f\n", determinant);

            double[][] triangularMatrix = GaussianElim.calculateTriangularMatrix(data);
            System.out.printf(
                    "Triangular matrix:\n%s\n",
                    Arrays.deepToString(triangularMatrix)
                            .replace("[[", "[\n\t[")
                            .replace("], [", "],\n\t[")
                            .replace("]]", "]\n]")
            );

            if (determinant == 0) {
                System.out.println("\nThe system of equations has an infinite number of solutions");
                System.exit(0);
            }

            double[][] unknownVars = GaussianElim.calculateUnknownVars(data);
            System.out.printf(
                    "Unknown vars:\n%s\n",
                    Arrays.deepToString(unknownVars)
                            .replace("[[", "[\n\t[")
                            .replace("], [", "],\n\t[")
                            .replace("]]", "]\n]")
            );

            double[][] nevyazka = GaussianElim.calculateNevyazka(data);
            System.out.printf(
                    "Nevyazka:\n%s\n",
                    Arrays.deepToString(nevyazka)
                            .replace("[[", "[\n\t[")
                            .replace("], [", "],\n\t[")
                            .replace("]]", "]\n]")
            );
        } catch (Exception e) {
            System.out.printf("\nSmth wrong (%s).\nBye :3", e.getMessage());
            System.exit(-1);
        }
    }
}