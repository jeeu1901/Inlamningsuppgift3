import javax.swing.*;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CreateGame {

    JFrame gameBoard = new JFrame();
    JPanel gameButtons = new JPanel();
    JPanel gamePanel = new JPanel();
    JButton newGame = new JButton("Nytt spel");
    JButton quitGame = new JButton("Avsluta spelet");
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton b10 = new JButton("10");
    JButton b11 = new JButton("11");
    JButton b12 = new JButton("12");
    JButton b13 = new JButton("13");
    JButton b14 = new JButton("14");
    JButton b15 = new JButton("15");

    CreateGame() {
        gameBoard.setLayout(new BorderLayout());
        gameBoard.setSize(1000, 1000);
        gameBoard.setVisible(true);
        gameBoard.setLocationRelativeTo(null);
        gameBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameBoard.add("South", gamePanel);gameBoard.add("Center", gameButtons);

        gameButtons.setLayout(new GridLayout(4, 4));

        gameButtons.add(b1);gameButtons.add(b2);gameButtons.add(b3);gameButtons.add(b4);gameButtons.add(b5);gameButtons.add(b6);
        gameButtons.add(b7);gameButtons.add(b8);gameButtons.add(b9);gameButtons.add(b10);gameButtons.add(b11);gameButtons.add(b12);
        gameButtons.add(b13);gameButtons.add(b14);gameButtons.add(b15);

        gamePanel.add(newGame);gamePanel.add(quitGame);
    }



}
