package capt.sunny;

import capt.sunny.ui.MainPanel;

public class Application {
    public static void main(String[] args) throws Exception {
        try {
            MainPanel mainPanel = new MainPanel();
            mainPanel.setVisible(true);
        } catch (Exception e) {
            System.out.printf("\nSmth wrong (%s).\nBye :3", e.getMessage());
            throw e;
        }
    }
}
