import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameHandlers {

    public void randomGame(List<JButton> bricks) {
        Collections.shuffle(bricks);
    }

    public void addCat(JButton j) {
        ImageIcon icon = new ImageIcon("src/Images/Cat.jpg");
        Image image = icon.getImage();
        image = image.getScaledInstance(430, 265, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        j.setIcon(icon);
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
        System.out.println("U wonnnnnnn");
        return true;

    }

    public boolean solvable(List<JButton> bricks) {
        int inversions = 0;
        int position = -1;
        int tempNr1, tempNr2;

        for(int i = 0; i < bricks.size(); i++) {
            if(bricks.get(i).getText().equals("")) {
                position = i+1;
            }
        }


        List<JButton> copy = new ArrayList<>();
        for(JButton j: bricks) {
            if(!j.getText().equals("")) {
                copy.add(j);
            }
        }

        //Räkna ut inversions d.v.s. Nummer till höger om ett tal som är mindre.
        for(int i = 0; i < copy.size()-1; i++) {
            tempNr1 = Integer.parseInt(copy.get(i).getText());
            for(int j = i+1; j < copy.size(); j++) {
                tempNr2 = Integer.parseInt(copy.get(j).getText());
                if(tempNr2 > tempNr1) {
                    inversions++;
                }
            }
        }
        // Tar ut blanka positionen och sätter tbx blankt istället för 0a.
        // Ändrade till 0 för att förhindra parse error (NumberForma..)

        System.out.println(inversions + " " + position + " ");
      /*  if (position <= 4 || position >= 9 && position <= 12) {
            if(inversions % 2 == 0) {
                System.out.println("Unsolvable - Creating new puzzle" );
                return false;
            }
            else {
                System.out.println("Solvable" + "even");
                return true;
            }
        } else if(position >=5 && position <=8 ||position >= 13 ){
            if(inversions % 2 == 1) {
                System.out.println("Unsolvable - Creating new puzzlee");
                return false;
            }
            else {
                System.out.println("Solvable" + " odd");
                return true;
            }
        } */
        if(inversions % 2 == 0) {
            return true;
        }
        else
            return false;
    }

    public void playAble(List<JButton> bricks, JButton button) {
        int transp = Integer.MIN_VALUE, clicked = Integer.MIN_VALUE;
        for(int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).getText().equals(button.getText())) {
                clicked = i;
            }
            else if (bricks.get(i).getText().equals("")){
                transp = i;
            }
        }
        if(transp == clicked - 1 || transp == clicked + 1
                || transp == clicked + 4 || transp == clicked - 4) {
            JButton transpButton = bricks.get(transp);
            transpButton.setText(button.getText());
            button.setText("");
            transpButton.setIcon(null);
            addCat(button);
        }

    }

}
