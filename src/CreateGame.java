import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class CreateGame {
    JFrame f=new JFrame();
    JPanel p=new JPanel();
    JPanel op=new JPanel();
    JPanel top=new JPanel();
    JLabel clock=new JLabel("Timer: ");
    JLabel count=new JLabel("Clicks: ");
    JButton newGame = new JButton("New Game");
    JButton quitGame = new JButton("Quit Game");
    ArrayList<JButton>bricks=new ArrayList<JButton>();
    List<String> winList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "");
    int Rows=4;
    int Cols=4;
    int startCounter = 0;
    GameHandlers gh = new GameHandlers();
    Timer ur;



    CreateGame() {
        f.setLayout(new BorderLayout());
        p.setLayout(new GridLayout(Rows,Cols));
        f.setTitle("Best Game Ever");
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(1000, 1000);
        f.add("South",op);f.add("Center",p);f.add("North", top);
        top.add(clock);top.add(count);
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
            ur.stop();
            addButtons();
            p.revalidate();
        });
        quitGame.addActionListener(l -> {
            System.out.println("Avslutas");
            ur.stop();
            System.exit(0);
        });

    }

    public void addButtons() {

        //Anropar metod för att shuffla brickorna

        gh.randomGame(bricks);
        for (int i=0;i<bricks.size();i++){
            p.add(bricks.get(i));
        }
        startCounter = 0;
        timer();


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
            if(gh.playAble(bricks, button)) {
                count.setText("Clicks: " + counter());
            }
            if(gh.checkWinner(bricks, winList)) {
                winnerMessage();
            }
        }
    }

    public void winnerMessage() {
        ur.stop();
        JFrame winFrame = new JFrame();
        JButton winNewGame = new JButton("Try Again");
        JButton winQuitGame = new JButton("Quit");
        JLabel winText = new JLabel("Wow, you won!", SwingConstants.CENTER);
        JLabel winMoves = new JLabel("With"  + count.getText() + "moves", SwingConstants.CENTER);
        JLabel winTimer = new JLabel("It took you " + clock.getText() + " to complete the game", SwingConstants.CENTER);
        JPanel winPanel = new JPanel();
        JPanel winOutput = new JPanel();

        winFrame.setLayout(new BorderLayout());
        winFrame.add("Center", winOutput);
        winFrame.add("South", winPanel);
        winOutput.setLayout(new GridLayout(3, 1));
        winPanel.setLayout(new FlowLayout());
        winPanel.add(winNewGame);winPanel.add(winQuitGame);
        winOutput.add(winText);winOutput.add(winMoves);winOutput.add(winTimer);

        winFrame.setLocationRelativeTo(f);
        winFrame.pack();
        winFrame.setVisible(true);

        winNewGame.addActionListener(l -> {
            winFrame.setVisible(false);
            addButtons();
            f.revalidate();
        });
        winQuitGame.addActionListener(l -> {
            System.out.println("Spelet avslutas");
            System.exit(0);
        });

    }

    public void timer() {
        Instant startTime = Instant.now();
        ur = new Timer(1000, e -> {
            Instant now = Instant.now();
            Duration duration = Duration.between(startTime, now);
            String formatted = String.format("Timer: %02d:%02d", (duration.getSeconds() % 3600) / 60, (duration.getSeconds() % 60));
            clock.setText(formatted);
            clock.repaint();
        });
        ur.start();
    }

    public int counter() {
        return startCounter++;
    }
}