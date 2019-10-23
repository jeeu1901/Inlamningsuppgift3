import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class GameHandlers {

    public List<JButton> randomGame(List<JButton> buttons) {
        Collections.shuffle(buttons);
        return buttons;
    }

    public boolean checkWinner(List<JButton> buttons, List<JButton> winList) {

        for(int i = 0; i < 15; i++) {
            String number = buttons.get(i).getText();
            if(!number.equals(winList.get(i))) {
                return false;
            }
        }
        return true;

    }
}
