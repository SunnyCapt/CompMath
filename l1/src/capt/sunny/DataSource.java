package capt.sunny;

import capt.sunny.gaussian.MatrixWrapper;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.random;

enum SourceType {
    STDIN,
    FILE,
    RANDOM
}

enum Type {
    INT,
    DOUBLE,
    STRING,
}

public class DataSource {
    private Scanner scanner;
    private SourceType dataSource = SourceType.STDIN;

    public DataSource() throws Exception {
        scanner = new Scanner(System.in);
        String dataSrc = read("Select data source s[tdin]/f[ile]/r[andom]: ");
        SourceType st = (dataSrc.equals("s") || dataSrc.equals("stdin")) ? SourceType.STDIN :
                (dataSrc.equals("f") || dataSrc.equals("file")) ? SourceType.FILE :
                        (dataSrc.equals("r") || dataSrc.equals("random")) ? SourceType.RANDOM : null;

        if (st == null)
            throw new Exception("Wrong data source type argument");

        if (st == SourceType.FILE) {
            String filePath = read("Enter path to file: ");
            dataSource = st;
            File file = new File(filePath);
            if (file.exists() && file.isFile())
                scanner = new Scanner(file);
            else
                throw new Exception("Wrong file path");
        }
        else
            dataSource = st;
    }

    public Object getData(String dataName, Type type) throws Exception {
        String data = read(dataName);

        try {
            if (type == Type.INT)
                return Integer.parseInt(data);
            else if (type == Type.DOUBLE)
                return Double.parseDouble(data);
            else if (type == Type.STRING)
                return data;
            System.out.println("\nWrong data.\n");
            return null;
        } catch (Exception e) {
            throw new Exception(String.format("\nWrong data, expected %s, but received %s.\n", type, data));
        }
    }

    private String read(String str) {
        if (dataSource != SourceType.FILE)
            System.out.print(str);

        String line = scanner.nextLine();

        if (dataSource == SourceType.FILE)
            System.out.printf("\n%s %s", str, line);

        return line;
    }

    private MatrixWrapper getMatrixFromConsole() throws Exception {
        int size = (int) getData("Enter n<=20 (size of matrix): ", Type.INT);
        double[][] mainCoefficients = new double[size][size];
        double[][] freeCoefficients = new double[size][1];

        for (int i = 0; i < size; i++) {
            String[] coefficients = (
                    (String) getData(
                            String.format("Enter %d coefficients of %d equations (use ; as delimiter): ", size + 1, i),
                            Type.STRING
                    )
            ).split(";");

            if (coefficients.length != size + 1)
                throw new Exception(
                        String.format(
                                "Expected %d coefficients, but received %d.",
                                size + 1,
                                coefficients.length
                        )
                );

            freeCoefficients[i][0] = Double.parseDouble(coefficients[size].strip().replace(',', '.'));
            for (int j = 0; j < size; j++) {
                mainCoefficients[i][j] = Double.parseDouble(coefficients[j].strip().replace(',', '.'));
            }
        }

        return new MatrixWrapper(size, mainCoefficients, freeCoefficients);
    }

    private MatrixWrapper getRandomMatrix() throws Exception {
        int size = (int) getData("Enter n<=20 (size of matrix): ", Type.INT);
        double[][] mainCoefficients = new double[size][size];
        double[][] freeCoefficients = new double[size][1];
        double[][] coefficientsToPrint = new double[size][size + 1];
        for (int i = 0; i < size; i++) {
            coefficientsToPrint[i][size] = freeCoefficients[i][0] = random() * 60 - 30;
            for (int j = 0; j < size; j++) {
                coefficientsToPrint[i][j] = mainCoefficients[i][j] = random() * 60 - 30;
            }
        }
        System.out.printf(
                "Generated matrix:\n%s",
                Arrays.deepToString(coefficientsToPrint)
                        .replace("[[", "[\n\t[")
                        .replace("], [", "],\n\t[")
                        .replace("]]", "]\n]\n")
        );
        return new MatrixWrapper(size, mainCoefficients, freeCoefficients);
    }

    public MatrixWrapper getMatrix() throws Exception {
        if (dataSource == SourceType.RANDOM)
            return getRandomMatrix();
        return getMatrixFromConsole();
    }
}
