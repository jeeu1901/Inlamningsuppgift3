import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class GameHandlers {

    public List<JButton> randomGame(List<JButton> buttons) {
        Collections.shuffle(buttons);
        return buttons;
    }

    public boolean checkWinner(List<JButton> buttons, List<JButton> winList) {

        for (int i = 0; i < 15; i++) {
            String number = buttons.get(i).getText();
            if (!number.equals(winList.get(i))) {
                return false;
            }
        }
        return true;

    }

    public boolean solvable(List<JButton> buttons) {
        int antal = 0;
        for (int i = 0; i < buttons.size()-1; i++) {
            try {
                String temp = buttons.get(i).getText();
                String temp2 = buttons.get(i + 1).getText();
                int intTemp = Integer.parseInt(temp);
                int intTemp2 = Integer.parseInt(temp2);
                if (intTemp > intTemp2) {
                    antal++;
                }
            }
            catch(NumberFormatException e) {
                int trash = 0;
            }

        }
        if (antal % 2 == 0) {
            System.out.println(antal);
            System.out.println("solvable");
            return true;
        }
        System.out.println("not solvable" + antal);
        return false;
    }


}
