import ui.UserInterface;

import javax.swing.*;

public class InitialPoint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInterface();
            }
        });
    }
}
