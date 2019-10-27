import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    Main() {
        JTextField rows = new JTextField(5);
        JTextField cols = new JTextField(5);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Rad:"));
        myPanel.add(rows);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Kolumner:"));
        myPanel.add(cols);

        int row = 0, col = 0;
        boolean inputCheck = false;
        while(!inputCheck) {
            try {
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    row = Integer.parseInt(rows.getText().trim());
                    col = Integer.parseInt(cols.getText().trim());
                    inputCheck = true;
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Avslutar programmet");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Fel input, ange heltal bara!");
                rows.setText("");
                cols.setText("");
            }
        }

        CreateGame gameStarter = new CreateGame(row, col);


    }


    public static void main(String[] args) {
        Main start = new Main();
    }


}
