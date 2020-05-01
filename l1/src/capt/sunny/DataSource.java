package capt.sunny;

import capt.sunny.gaussian.MatrixWrapper;

import java.io.File;
import java.util.Scanner;

enum SourceType {
    STDIN,
    FILE
}

enum Type {
    INT,
    DOUBLE,
    STRING,
}

public class DataSource {
    private Scanner scanner;
    private String filePath;
    private SourceType dataSource = SourceType.STDIN;

    public DataSource() throws Exception {
        scanner = new Scanner(System.in);
        String dataSrc = read("Select data source s[tdin]/f[ile]: ");
        SourceType st = (dataSrc.equals("s") || dataSrc.equals("stdin")) ? SourceType.STDIN :
                (dataSrc.equals("f") || dataSrc.equals("file")) ? SourceType.FILE : null;

        if (st == null)
            throw new Exception("Wrong data source type argument");

        if (st == SourceType.FILE) {
            filePath = read("Enter path to file: ");
            dataSource = st;
            File file = new File(filePath);
            if (file.exists() && file.isFile())
                scanner = new Scanner(file);
            else
                throw new Exception("Wrong file path");
        }
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
        if (dataSource == SourceType.STDIN)
            System.out.print(str);

        String line = scanner.nextLine();

        if (dataSource == SourceType.FILE)
            System.out.printf("\n%s %s", str, line);

        return line;
    }

    public MatrixWrapper getMatrixFromConsole() throws Exception {
        int size = (int) getData("Enter n<=20 (size of matrix): ", Type.INT);
        double[][] mainCoefficients = new double[size][size];
        double[][] freeCoefficients = new double[size][1];

        for (int i = 0; i < size; i++) {
            String[] coefficients = (
                    (String) getData(
                            String.format("Enter %d coefficients of %d equations: ", size + 1, i),
                            Type.STRING
                    )
            ).split(",");

            if (coefficients.length != size + 1)
                throw new Exception(
                        String.format(
                                "Expected %d coefficients, but received %d.",
                                size + 1,
                                coefficients.length
                        )
                );

            freeCoefficients[i][0] = Double.parseDouble(coefficients[size].strip());
            for (int j = 0; j < size; j++) {
                mainCoefficients[i][j] = Double.parseDouble(coefficients[j].strip());
            }
        }

        return new MatrixWrapper(size, mainCoefficients, freeCoefficients);
    }
}
