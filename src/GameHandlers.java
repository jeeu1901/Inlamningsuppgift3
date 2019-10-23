import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class GameHandlers {

    public List<JButton> randomGame(List<JButton> buttons) {
        Collections.shuffle(buttons);
        return buttons;
    }
}
