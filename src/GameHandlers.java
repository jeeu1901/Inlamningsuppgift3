import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class GameHandlers {

    public List<JButton> randomGame(List<JButton> buttons) {
        Collections.shuffle(buttons);
        return buttons;
    }

    public boolean checkWinner(List<JButton> buttons, List<String> winList) {
        // Denna metod kallas efter varje button tryck (ActionListener)

        // Tar både knapp listan och vinst listan
        // Loopar 16 gånger.
        for (int i = 0; i < 16; i++) {
            // Sätter number till texten i alla knappar
            String number = buttons.get(i).getText();
            System.out.println("buttons nr is = " + number);
            System.out.println("winList nr is = " + winList.get(i));
            // Om numret inte stämmer med den i vinstlistan så retunerar den false
            // Skulle alla nummer stämma så går vi ur if-loopen och retunerar true.
            if (!number.equals(winList.get(i))) {
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "U r WINNER dawg");
        return true;

    }

    public boolean solvable(List<JButton> buttons) {
        // Lättare att förklara på plats

        /*
        * Kort förklarat
        * Jag räknar ut vilken rad den blanka rutan ligger på
        * Är den på en "jämn" eller "ojämn" plats (jämn = 2 - 4 - 6) i vårat fall är det bara rad 2 och 4 som är jämn
        * och 1-3 ojämn.
        * Om raden är jämn så får inte Inversion vara jämn
        * Om raden är ojämn så får inte Inversion vara ojämn
        * Inversion är när numret på plats 1 är större än numret på plats 2.
        * T.e.x. Om spelplanen såg ut såhär (2, 5, 6, 1, 8, 10, 3) så är det 2 Inversions
        * 6 är större än 1 och 10 är större än 3.
        *  */
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
