import javax.swing.*;
import java.awt.*;
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

        //Räkna ut inversions d.v.s. Nummer till höger om ett tal som är mindre.
        for(int i = 0; i < bricks.size(); i++) {
            if(bricks.get(i).getText().equals("")) {
                bricks.get(i).setText("0");
            }
            tempNr1 = Integer.parseInt(bricks.get(i).getText());
            for(int j = i+1; j < bricks.size(); j++) {
                if(bricks.get(j).getText().equals("")) {
                    bricks.get(j).setText("0");
                }
                tempNr2 = Integer.parseInt(bricks.get(j).getText());
                if(tempNr2 > tempNr1) {
                    inversions++;
                }
            }
        }
        // Tar ut blanka positionen och sätter tbx blankt istället för 0a.
        // Ändrade till 0 för att förhindra parse error (NumberForma..)
        for(int i = 0; i < bricks.size(); i++) {
            if(bricks.get(i).getText().equals("0")) {
                position = i+1;
                bricks.get(i).setText("");
            }
        }
        System.out.println(inversions);
        if (position <= 4 || position >= 9 && position <= 12) {
            if(inversions % 2 == 0) {
                System.out.println("Unsolvable");
                return false;
            }
            else {
                System.out.println("Solvable");
                return true;
            }
        } else {
            if(inversions % 2 == 1) {
                System.out.println("Unsolvable");
                return false;
            }
            else {
                System.out.println("Solvable");
                return true;
            }
        }



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

    public void WinnerMessage(JFrame game) {
        JFrame winFrame = new JFrame();
        JButton newGame = new JButton("Try Again");
        JButton quitGame = new JButton("Quit");
        JLabel winText = new JLabel("Wow, you won!" + "\nYou won with " + "<--Ska lägga in counter-->" + "moves");
        JPanel winPanel = new JPanel();

        winFrame.setLayout(new BorderLayout());
        winFrame.add("Center", winText);
        winFrame.add("South", winPanel);
        winPanel.setLayout(new FlowLayout());
        winPanel.add(newGame);winPanel.add(quitGame);
        winFrame.setLocationRelativeTo(game);
        winFrame.pack();

        newGame.addActionListener(l -> {
            CreateGame startNewGame = new CreateGame();
        });

    }


}
