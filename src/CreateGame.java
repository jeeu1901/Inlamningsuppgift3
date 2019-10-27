import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class CreateGame {
    JFrame f=new JFrame();
    JPanel p=new JPanel();
    JLabel l=new JLabel();
    JPanel op=new JPanel();
    JButton newGame = new JButton("New Game");
    JButton quitGame = new JButton("Quit Game");
    ArrayList<JButton>bricks=new ArrayList<JButton>();
    List<String> winList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "");
    int Rows=4;
    int Cols=4;
    GameHandlers gh = new GameHandlers();


    CreateGame() {
        f.setLayout(new BorderLayout());
        p.setLayout(new GridLayout(Rows,Cols));
        f.setTitle("Best Game Ever");
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(1000, 1000);
        f.add("South",op);f.add("Center",p);
        op.add(newGame);op.add(quitGame);
        f.setLocationRelativeTo(null);

        for (int i=0;i<(Rows*Cols);i++) {
            bricks.add(new JButton(String.valueOf(i+1)));
            bricks.get(i).setBackground(Color.LIGHT_GRAY);
            bricks.get(i).setFont(new Font("Arial", Font.PLAIN,40));
            bricks.get(i).addActionListener(new myButtonListern());

        }
        bricks.get(15).setText("");
        gh.addCat(bricks.get(15));
        f.setVisible(true);
        addButtons();

        // Start och Quit
        newGame.addActionListener(l -> {
            addButtons();
            p.revalidate();
        });
        quitGame.addActionListener(l -> {
            System.exit(0);
        });

    }

    public void addButtons() {

        //Anropar metod för att shuffla brickorna

        gh.randomGame(bricks);
        for (int i=0;i<bricks.size();i++){
            p.add(bricks.get(i));
        }
        //Urfunktion just nu.
        /*
        if(!gh.solvable(bricks)) {
            addButtons();
            f.revalidate();
        } */
    }

    class myButtonListern implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            gh.playAble(bricks, button);

            if(gh.checkWinner(bricks, winList)) {
                winnerMessage();
            }
        }
    }
    public void winnerMessage() {
        JFrame winFrame = new JFrame();
        JButton winNewGame = new JButton("Try Again");
        JButton winQuitGame = new JButton("Quit");
        JLabel winText = new JLabel("Wow, you won!" + "\nYou won with " + "<--Ska lägga in counter-->" + "moves");
        JPanel winPanel = new JPanel();

        winFrame.setLayout(new BorderLayout());
        winFrame.add("Center", winText);
        winFrame.add("South", winPanel);
        winPanel.setLayout(new FlowLayout());
        winPanel.add(winNewGame);winPanel.add(winQuitGame);
        winFrame.setLocationRelativeTo(f);
        winFrame.setSize(350, 350);
        winFrame.setVisible(true);

        winNewGame.addActionListener(l -> {
            winFrame.setVisible(false);
            addButtons();
            f.revalidate();
        });
        winQuitGame.addActionListener(l -> {
            System.exit(0);
        });

    }
}