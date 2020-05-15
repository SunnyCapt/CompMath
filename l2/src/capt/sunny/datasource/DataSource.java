package capt.sunny.datasource;

import java.util.Scanner;

public class DataSource {
    private Scanner scanner;

    public DataSource(){
        scanner = new Scanner(System.in);
    }

    public Object getData(String txtToPrint, DataType type) throws Exception {
        String data = read(txtToPrint);

        try {
            if (type == DataType.INT)
                return Integer.parseInt(data);
            else if (type == DataType.DOUBLE)
                return Double.parseDouble(data.replace(',', '.'));
            else if (type == DataType.STRING)
                return data;
            System.out.println("\nWrong data.\n");
            return null;
        } catch (Exception e) {
            throw new Exception(String.format("\nWrong data, expected %s, but received %s.\n", type, data));
        }
    }

    private String read(String str) {
        System.out.print(str);
        return scanner.nextLine();
    }
}
