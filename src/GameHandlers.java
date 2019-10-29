import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class GameHandlers {
    UserInput u = new UserInput();


    protected void randomGame(List<JButton> bricks) {
        Collections.shuffle(bricks);
    }

    /**
     * Byter bild på knapp till en katt.
     * @param j JButton
     */
    protected void addCat(JButton j) {
        ImageIcon icon = new ImageIcon("src/Images/Cat.jpg");
        Image image = icon.getImage();
        image = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        j.setIcon(icon);
    }

    /**
     * Byter bild till "paws"
     * @param j JButton
     */
    protected void addPaws(JButton j) {
        ImageIcon paws = new ImageIcon("src/Images/paws4.jpg");
        Image img = paws.getImage();
        Image newimg = img.getScaledInstance(700, 490, Image.SCALE_SMOOTH);
        paws = new ImageIcon(newimg);
        j.setIcon(paws);
    }

    /**
     * Jämför knapplistan och vinstlistan.
     * return true när listorna är lika.
     * @param buttons
     * @param winList
     * @return bool
     */
   protected boolean checkWinner(List<JButton> buttons, List<String> winList) {

        for (int i = 0; i < winList.size() - 1; i++) {
            String number = buttons.get(i).getText();
            System.out.println("Winlist: " + winList.get(i));
            System.out.println("ButtonList: " + number);
            if (!number.equals(winList.get(i))) {
                return false;
            }
        }
        return true;

    }

    /**
     * Gör spelet spelbart. Gör så att man kan flytta på knapparna
     * Knapparna byter också bild/text.
     * @param bricks
     * @param button
     * @param rowCol
     * @return bool
     */
   protected boolean playAble(List<JButton> bricks, JButton button, int rowCol) {
        int transp = Integer.MIN_VALUE, clicked = Integer.MIN_VALUE;
        // Tar ut den klickade knappen & den blanka knappen
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).getText().equals(button.getText())) {
                clicked = i;
            } else if (bricks.get(i).getText().equals("")) {
                transp = i;
            }
        }
        // Kolla om den blanka är = den klickade knappen -1 +1 för höger och vänster
        // + Kolumner och -Kolumner för att få upp och ner.
        if (transp == clicked - 1 || transp == clicked + 1
                || transp == clicked + rowCol || transp == clicked - rowCol) {
            JButton transpButton = bricks.get(transp);
            transpButton.setText(button.getText());
            button.setText("");
            addPaws(transpButton);
            addCat(button);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Kollar om spelet går att lösa.
     * @param bricks
     * @param rowInput
     * @param colInput
     * @return bool
     */
    protected boolean solvable(List<JButton> bricks, int rowInput, int colInput) {
        ArrayList<JButton> copy = new ArrayList<>();
        int inversions = 0;
        int position = 0;
        int index = 0;
        int evenOdd = 0;

        //Kopia av knapplistan utan den blanka knappen
        for (JButton j : bricks) {
            if (!j.getText().equals("")) {
                copy.add(j);
            }
        }
        // Tar ut positionen av den blanka listan i spelet
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).getText().equals("")) {
                position = i + 1;
            }
        }

        // Kollar om den blanka rutan är på en jämn eller ojämn rad
        // Räknat från nedersta raden till översta.
        for (int i = rowInput; i > 0; i--) {
            for (int j = colInput; j > 0; j--) {
                index++;
                if (i % 2 == 0) {
                    if (index == position) {
                        evenOdd = 2;
                    }
                } else {
                    if (index == position) {
                        evenOdd = 1;
                    }
                }
            }
        }
        // Räknar ut inversions i den kopierade listan (utan blank)
        for (int i = 0; i < copy.size(); i++) {
            for (int j = i + 1; j < copy.size(); j++) {
                if (Integer.parseInt(copy.get(i).getText()) > Integer.parseInt(copy.get(j).getText())) {
                    inversions++;
                }
            }
        }

        // Test outputs
        /*
        System.out.println("index is : " + index);
        System.out.println("Position is: " + position);
        System.out.println("the row is " + evenOdd);
        System.out.println("The number of inversions : " + inversions);
         */

        // Om "width"(Kolumner) är jämn
        if (colInput % 2 == 0) {
            if ((inversions % 2 == 0) && evenOdd == 1) {
                System.out.println("solvable row odd, even inv" + " with inversions: " + inversions + " and blank pos: " + position);
                return true;
            } else if ((inversions % 2 == 1) && evenOdd == 2) {
                System.out.println("solvable row even, odd inv" + " with inversions: " + inversions + " and blank pos: " + position);
                return true;
            } else {
                System.out.println("Not solvable");
                return false;
            }
        }
        // Om "width"(Kolumner) är ojämn
        else {
            if (inversions % 2 == 0) {
                System.out.println("solvable odd width");
                return true;
            } else {
                System.out.println("not solvable odd width");
                return false;
            }
        }
    }
}
