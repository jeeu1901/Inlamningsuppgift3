import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Arrays;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CreateGame {
    //Skapar själva JFramen
    JFrame gameBoard = new JFrame();
    //Gör två paneler, en som håller spel-knapparna och en som håller "Nytt spel" och "Avsluta spelet"
    JPanel gameButtons = new JPanel();
    JPanel gamePanel = new JPanel();
    // Skapar alla knappar.
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
    // Gör en lista av alla knappar
    List<JButton> buttonList = Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16);
    // Gör en lista som har hur arrayen ska se ut om man har vunnit.
    List<String> winList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "");
    // Objekt för filen GameHandlers
    GameHandlers gh = new GameHandlers();

    CreateGame() {
        //Design
        // BorderLayout för panelerna
        gameBoard.setLayout(new BorderLayout());
        gameBoard.setSize(1000, 1000);
        gameBoard.setTitle("15-spel");
        gameBoard.setVisible(true);
        gameBoard.setLocationRelativeTo(null);
        gameBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Sätter panelen med "Nytt spel" och "Avbrutet spel" längst nere "South"
        gameBoard.add("South", gamePanel);
        // Sätter knapparna i mitten
        gameBoard.add("Center", gameButtons);
        // Gör så att knapparna har en GridLayout 4x4
        gameButtons.setLayout(new GridLayout(4, 4));
        // Kallar på knapp metoden
        addButtons();

        //Handlers
        // Lambda för "nytt spel" knappen
        // Kallar på addButtons metoden | revalidate gör att det funkar.
        newGame.addActionListener(l -> {
            addButtons();
            gameButtons.revalidate();
        });

        //Lambda för avsluta spelet.
        quitGame.addActionListener(l -> {
            JOptionPane.showMessageDialog(null, "Spelet avslutas");
            System.exit(0);
        });


    }

    public void addButtons() {
        // Skickar min lista med knappar till randomGame metoden i GameHandlers.java
        // Retunerar en lista som är shufflad.
        gh.randomGame(buttonList);
        //Lägger till alla knappar
        // For-each loop. Sätter j till en knapp i Listan
        for(JButton j: buttonList) {
            j.setOpaque(true);
            j.setContentAreaFilled(true);
            j.setBorderPainted(true);
            gamePanel.add(newGame);gamePanel.add(quitGame);
            // Om knappen är knappen som är ("") d.v.s den blanka rutan.
            // Så sätter jag så den inte syns och är helt blank.
            if(j.getText().equals("")) {
                j.setOpaque(false);
                j.setContentAreaFilled(false);
                j.setBorderPainted(false);
            }
            // Sätter actionListener på alla knappar
            j.addActionListener(new myButtonListern());
            // Lägger till alla knappar i gameButtons panelen
            gameButtons.add(j);
        }
        // Om metoden returnar false så startar den ett nytt spel tills spelet är "Lösbart"
        if(!gh.solvable(buttonList)) {
            addButtons();
        }

        // Meow
        System.out.println("Jag är en katt");


    }
    // Inre-klass ActionListener
     class myButtonListern implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            // e.getSource() ger mig den knapp som blev klickad
            // Sätter den till en ny knapp jag kallar för button
            JButton button = (JButton) e.getSource();

            // Int transp och clicked kan lika gärna ha värdet -1 bara det är under 0.
            int transp = Integer.MIN_VALUE, clicked = Integer.MIN_VALUE;
            // Looper igenom hela Listan med knappar
            for(int i = 0; i < buttonList.size(); i++) {
                // om den knappen som blev klickad (e.getSource()) som nu har namnet button
                // Jämför texterna i den i buttonList(i) och button.getText() om de är lika så sätter jag
                // clicked till i.
                if (buttonList.get(i).getText().equals(button.getText())) {
                    clicked = i;
                }
                // Om knappen är lika med den tomma platsen så sätter vi transp knappen till i.
                else if (buttonList.get(i).getText().equals("")){
                    transp = i;
                }
            }

            // Om den blanka knappen är lika med (clicked (-1) (-4) (+1) (+4)) någon av de
            // Så byter jag platser på dem. Clicked + 4 är under, -4 är över, -1 är till vänster +1 till höger
            // om den klickade knappen. (Tänk placering i 4x4 griden)
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
            // Bara test för att se om funktionen fungerar
            // Skickar in buttonList och listan som har "vinst" raden.
            if(!gh.checkWinner(buttonList, winList)) {
                System.out.println("u lost");
            }
            else if(gh.checkWinner(buttonList, winList)) {
                System.out.println("u mega won");
            }

        }
    }


}
