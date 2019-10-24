import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class GameHandlers {

    public List<JButton> randomGame(List<JButton> buttons) {
        Collections.shuffle(buttons);
        return buttons;
    }

    public boolean checkWinner(List<JButton> buttons, List<String> winList) {

        for (int i = 0; i < 16; i++) {
            String number = buttons.get(i).getText();
            System.out.println("buttons nr is = " + number);
            System.out.println("winList nr is = " + winList.get(i));
            if (!number.equals(winList.get(i))) {
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "U r WINNER dawg");
        return true;

    }

    public boolean solvable(List<JButton> buttons) {
        int antal = 0;
        int row = -1;
        for (int i = 0; i < buttons.size() - 1; i++) {
            if (buttons.get(i).getText().equals("")) {
                if (i >= 1 && i <= 4 || i >= 9 && i <= 12) {
                    row = 2;
                } else {
                    row = 1;
                }
            }
            try {
                String temp = buttons.get(i).getText();
                String temp2 = buttons.get(i + 1).getText();
                int intTemp = Integer.parseInt(temp);
                int intTemp2 = Integer.parseInt(temp2);
                if (intTemp > intTemp2) {
                    antal++;
                }

            } catch (NumberFormatException e) {
                int trash = 0;
            }

        }
        if (row == 1) {
            if (antal % 2 == 0) {
                System.out.println("row 1 solvable" + antal);
                return true;
            }
        }
        else if (row == 2) {
            if (antal % 2 != 0) {
                System.out.println("row 2 solvable" + antal);
                return true;
            }
        }
        System.out.println(row + " not solvable " + antal);
        return false;
    }


}
