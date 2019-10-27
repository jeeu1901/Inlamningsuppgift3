import javax.swing.*;
import java.awt.*;
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
        image = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        j.setIcon(icon);
    }

    public void addPaws(JButton j) {
        ImageIcon paws = new ImageIcon("src/Images/paws4.jpg");
        Image img = paws.getImage();
        Image newimg = img.getScaledInstance( 700, 490,  Image.SCALE_SMOOTH );
        paws = new ImageIcon(newimg);
        j.setIcon(paws);
    }

    public boolean checkWinner(List<JButton> buttons, List<String> winList) {

        for (int i = 0; i < winList.size()-1; i++) {
            String number = buttons.get(i).getText();
            System.out.println("Winlist: " + winList.get(i));
            System.out.println("ButtonList: " + number);
            if (!number.equals(winList.get(i))) {
                return false;
            }
        }
        return true;

    }

    public boolean playAble(List<JButton> bricks, JButton button) {
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
            addPaws(transpButton);
            addCat(button);
            return true;
        }
        else {
            return false;
        }

    }

/*
( (grid width odd) && (#inversions even) )  ||  ( (grid width even) && ((blank on odd row from bottom) == (#inversions even)) ) */
    public boolean solvable(List<JButton> bricks) {
        ArrayList<JButton> copy = new ArrayList<>();
        int inversions = 0;
        int position = 0;

        for(JButton j: bricks) {
            position++;
            if(!j.getText().equals("")) {
                copy.add(j);
            }
        }
        int row = 0;
        for(int i = 0; i < bricks.size(); i++) {
            if(bricks.get(i).getText().equals("")) {
                position = i+1;
                if(position >= 13 && position <= 16|| position >= 5 && position <= 8) {
                    row = 1;
                }
                else if(position <= 4 && position > 0 || position >= 9 && position <= 13) {
                    row = 2;
                }
            }
        }

        for(int i = 0; i < copy.size(); i++) {
            for(int j = i + 1; j < copy.size(); j++) {
                if(Integer.parseInt(copy.get(i).getText()) > Integer.parseInt(copy.get(j).getText())) {
                    inversions++;
                }
            }
        }

        System.out.println("Inversions: " + inversions + "\nBlank pos = " + position);
        if((inversions % 2 == 0) && row == 1) {
            System.out.println("solvable row odd, even inv" + " with inversions: "+ inversions + " and blank pos: " + position);
            return true;
        }
        else if((inversions % 2 == 1) && row == 2) {
            System.out.println("solvable row even, odd inv" + " with inversions: "+ inversions + " and blank pos: " + position);
            return true;
        }
        else {
            System.out.println("Not solvable");
            return false;
        }

    }
    /*public boolean solvable(List<JButton> bricks) {
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
      *//*  if (position <= 4 || position >= 9 && position <= 12) {
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
        } *//*
        if(inversions % 2 == 0) {
            return true;
        }
        else
            return false;
    }*/

}
