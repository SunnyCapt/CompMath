package capt.sunny.data;

public class DataReceiver {
    public static double[][] receiveData(int value) {

        switch (value) {
            case 1:
                return new double[][]{
                        {0.2 * Math.PI, 0.8 * Math.PI, 1.7 * Math.PI, 2 * Math.PI},
                        {0.5877852522924731, 0.5877852522924732, -0.8090169943749476, -2.4492935982947064E-16}
                };
            case 2:
                return new double[][]{
                        {0.2 * Math.PI, 0.4 * Math.PI, 0.6 * Math.PI, 0.8 * Math.PI, Math.PI, 1.2 * Math.PI, 1.4 * Math.PI, 1.6 * Math.PI, 1.8 * Math.PI, 2 * Math.PI, 2.4 * Math.PI},
                        {0.5877852522924731, 0.9510565162951535, 0.9510565162951536, 0.5877852522924732, 1.2246467991473532E-16, -0.587785252292473, -0.9510565162951535, -0.9510565162951536, -0.5877852522924734, -2.4492935982947064E-16, 0.9510565162951535}
                };
            case 3:
                return new double[][]{
                        {0.2 * Math.PI, 0.4 * Math.PI, 0.6 * Math.PI, 0.8 * Math.PI, Math.PI, 1.2 * Math.PI, 1.4 * Math.PI, 1.6 * Math.PI, 1.8 * Math.PI, 2 * Math.PI, 2.4 * Math.PI},
                        {0.5877852522924731, 0.9510565162951535, 0.9510565162951536, 5, 1.2246467991473532E-16, -0.587785252292473, -0.9510565162951535, -0.9510565162951536, -0.5877852522924734, -2.4492935982947064E-16, 0.9510565162951535}
                };
            case 4:
                return new double[][]{
                        {4.5 * Math.PI, 9.5 * Math.PI, 15 * Math.PI, 19.5 * Math.PI, 25 * Math.PI, 29.5 * Math.PI, 35 * Math.PI, 39.5 * Math.PI, 45 * Math.PI, 49.5 * Math.PI},
                        {1.0, -1.0, 5.3896838775215305E-15, -1.0, -4.91096680932118E-16, -1.0, 7.838977475816237E-15, -1.0, 1.9581969173625882E-15, -1.0}
                };

        }

        return null;
    }
}