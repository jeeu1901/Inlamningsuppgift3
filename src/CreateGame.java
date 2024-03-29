import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class CreateGame {
    private JFrame f=new JFrame();
    private JPanel p=new JPanel();
    private JPanel op=new JPanel();
    private JPanel top=new JPanel();
    private JLabel clock=new JLabel("Timer: ");
    private JLabel count=new JLabel("Clicks: ");
    private JButton newGame = new JButton("New Game");
    private JButton quitGame = new JButton("Quit Game");
    private List<JButton>bricks = new ArrayList<JButton>();
    private List<String> winList = new ArrayList<>();
    private int startCounter = 1;
    private GameHandlers gh = new GameHandlers();
    private UserInput ui = new UserInput();
    private Timer ur;

    CreateGame() {
        ui.getInput();
        f.setLayout(new BorderLayout());
        p.setLayout(new GridLayout(ui.getRow(),ui.getColInput()));
        f.setTitle("Best Game Ever");
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(1200, 1100);
        f.add("South",op);f.add("Center",p);f.add("North", top);
        top.add(clock);top.add(count);
        op.add(newGame);op.add(quitGame);
        f.setLocationRelativeTo(null);

        for (int i=0;i<(ui.getRow()*ui.getColInput());i++) {
            winList.add(String.valueOf(i+1));
            bricks.add(new JButton(String.valueOf(i+1)));
            bricks.get(i).setForeground(Color.PINK);
            bricks.get(i).setFont(new Font("SansSerif", Font.BOLD,40));
            gh.addPaws(bricks.get(i));
            bricks.get(i).setBorder(BorderFactory.createLineBorder(Color.pink));
            bricks.get(i).setBorderPainted(true);
            bricks.get(i).setHorizontalTextPosition(JButton.CENTER);
            bricks.get(i).setVerticalTextPosition(JButton.CENTER);
            bricks.get(i).addActionListener(new myButtonListern());
        }
        bricks.get(bricks.size()-1).setText("");
        gh.addCat(bricks.get(bricks.size()-1));
        f.setVisible(true);
        addButtons();

        // Start och Quit
        newGame.addActionListener(l -> {
            ur.stop();
            startCounter = 1;
            addButtons();
            p.revalidate();
        });
        quitGame.addActionListener(l -> {
            System.out.println("Avslutas");
            ur.stop();
            System.exit(0);
        });

    }

    private void addButtons() {

        //Anropar metod för att shuffla brickorna

        gh.randomGame(bricks);
        for (int i=0;i<bricks.size();i++){
            p.add(bricks.get(i));
        }
        timer();


        if(!gh.solvable(bricks, ui.getRow(), ui.getColInput())) {
            addButtons();
            f.revalidate();
        }
    }

    class myButtonListern implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if(gh.playAble(bricks, button, ui.getColInput())) {
                count.setText("Clicks: " + counter());
            }
            if(gh.checkWinner(bricks, winList)) {
                winnerMessage();
            }
        }
    }

    private void winnerMessage() {
        ur.stop();
        JFrame winFrame = new JFrame();
        JButton winNewGame = new JButton("Try Again");
        JButton winQuitGame = new JButton("Quit");
        JLabel winText = new JLabel("Wow, you won!", SwingConstants.CENTER);
        JLabel winMoves = new JLabel(count.getText(), SwingConstants.CENTER);
        JLabel winTimer = new JLabel(clock.getText() , SwingConstants.CENTER);
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
            startCounter = 1;
            addButtons();
            f.revalidate();
        });
        winQuitGame.addActionListener(l -> {
            System.out.println("Spelet avslutas");
            System.exit(0);
        });

    }

    /**
     * Startar en klocka, output för varje sekund.
     */
    private void timer() {
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

    private int counter() {
        return startCounter++;
    }

}