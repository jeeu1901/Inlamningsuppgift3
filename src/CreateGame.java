import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

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
    JButton b16 = new JButton("");
    List<JButton> buttonList = Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16);
    GameHandlers gh = new GameHandlers();

    CreateGame() {
        //Design
        gameBoard.setLayout(new BorderLayout());
        gameBoard.setSize(1000, 1000);
        gameBoard.setTitle("15-spel");
        gameBoard.setVisible(true);
        gameBoard.setLocationRelativeTo(null);
        gameBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameBoard.add("South", gamePanel);
        gameBoard.add("Center", gameButtons);
        gameButtons.setLayout(new GridLayout(4, 4));
        addButtons();



        //Handlers
        newGame.addActionListener(l -> {
            addButtons();
            gameButtons.revalidate();
        });

        quitGame.addActionListener(l -> {
            JOptionPane.showMessageDialog(null, "Spelet avslutas");
            System.exit(0);
        });


    }
    public void addButtons() {
        gh.randomGame(buttonList);
        //Lägger till alla knappar
        for(JButton j: buttonList) {
            j.setOpaque(true);
            j.setContentAreaFilled(true);
            j.setBorderPainted(true);
            gamePanel.add(newGame);gamePanel.add(quitGame);
            if(j.getText().equals("")) {
                j.setOpaque(false);
                j.setContentAreaFilled(false);
                j.setBorderPainted(false);
            }
            j.addActionListener(new myButtonListern());
            gameButtons.add(j);
        }

    }

     class myButtonListern implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            int transp = Integer.MIN_VALUE, clicked = Integer.MIN_VALUE;
            for(int i = 0; i < buttonList.size(); i++) {
                if (buttonList.get(i).getText().equals(button.getText())) {
                    clicked = i;
                }
                else if (buttonList.get(i).getText().equals("")){
                    transp = i;
                }
            }

            if(transp == clicked - 1 || transp == clicked + 1
            || transp == clicked + 4 || transp == clicked - 4) {
                JButton transpButton = buttonList.get(transp);
                transpButton.setText(button.getText());
                button.setText("");
                transpButton.setOpaque(true);
                transpButton.setContentAreaFilled(true);
                transpButton.setBorderPainted(true);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
            }
        }
    }


}
