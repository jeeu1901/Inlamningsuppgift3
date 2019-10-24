import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Bricks extends JFrame{
    JFrame f=new JFrame();
    JPanel p=new JPanel();
    JLabel l=new JLabel();
    JPanel op=new JPanel();
    JButton newGame = new JButton("New Game");
    JButton quitGame = new JButton("Quit Game");
    ArrayList<JButton>bricks=new ArrayList<JButton>();
    int Rows=4;
    int Cols=4;

    Bricks() throws IOException{
        f.setLayout(new BorderLayout());
        p.setLayout(new GridLayout(Rows,Cols));
        f.setTitle("Best Game Ever");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.add("South",op);
        f.add("Center",p);
        f.setVisible(true);
        f.setLocation(400,400);

        //steg 1
        for (int i=0;i<(Rows*Cols);i++) {
            bricks.add(new JButton(String.valueOf(i+1)));
            bricks.get(i).setBackground(Color.LIGHT_GRAY);
            bricks.get(i).setFont(new Font("Arial", Font.PLAIN,40));
        }

        //ta fram nummer 16.
        bricks.get(15).setText("");
        bricks.get(15).setOpaque(false);
        bricks.get(15).setContentAreaFilled(false);
        bricks.get(15).setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("Cat.jpg"));
            Image newimg=img.getScaledInstance(430,265, Image.SCALE_SMOOTH);
            bricks.get(15).setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            System.out.println(ex);
        }





        op.add(newGame);op.add(quitGame);

        addButtons();

        // Start och Quit
        newGame.addActionListener(l -> {

            //p.removeAll();
            try {
                addButtons();
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.revalidate();

        });
        quitGame.addActionListener(l -> {
            JOptionPane.showMessageDialog(null, "Spelet avslutas");
            System.exit(0);
        });

    }

    public void addButtons() throws IOException{

        //Anropar metod för att shuffla brickorna
        shuffleBricks();
        for (int i=0;i<bricks.size();i++){
            p.add(bricks.get(i));
        }

    }

    public void shuffleBricks(){
        Collections.shuffle(bricks);
    }



}
