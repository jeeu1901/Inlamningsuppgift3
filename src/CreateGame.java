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
        b16.setOpaque(false);
        b16.setContentAreaFilled(false);
        b16.setBorderPainted(false);

        //Handlers
        newGame.addActionListener(l -> {

            gameButtons.removeAll();
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
            j.addActionListener(new myButtonListern());
            gameButtons.add(j);
        }

        //Osynlig knapp som ej kan klickas.
   //     gameButtons.add(b16);
        gamePanel.add(newGame);gamePanel.add(quitGame);

    }

     class myButtonListern implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            int indexOfB16 = 0, clickedIndex = 0;
            for(int i = 1; i < 16; i++) {
                if (buttonList.get(i).getText().equals(button.getText())) {
                    clickedIndex = i;
                }
                else if (buttonList.get(i).getText().equals("")){
                    indexOfB16 = i;
                }
            }

            if(indexOfB16 == clickedIndex - 1 || indexOfB16 == clickedIndex + 1
            || indexOfB16 == clickedIndex + 4 || indexOfB16 == clickedIndex - 4) {
                b16 = buttonList.get(indexOfB16);
                b16.setText(button.getText());
                button.setText("");
                b16.setOpaque(true);
                b16.setContentAreaFilled(true);
                b16.setBorderPainted(true);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
            }
        }
    }


}
