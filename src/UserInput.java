import javax.swing.*;

public class UserInput {
    private int rowInput, colInput;
    UserInput(){}
    protected void getInput()
    {
        JTextField rows = new JTextField(5);
        JTextField cols = new JTextField(5);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Rad:"));
        myPanel.add(rows);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Kolumner:"));
        myPanel.add(cols);

        boolean inputCheck = false;
        while (!inputCheck) {
            try {
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Vänligen ange rad och kolumner värden", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    rowInput = Integer.parseInt(rows.getText().trim());
                    colInput = Integer.parseInt(cols.getText().trim());
                    setRow(rowInput);
                    inputCheck = true;
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Avslutar programmet");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Fel input, ange heltal bara!");
                rows.setText("");
                cols.setText("");
            }
        }


    }

    protected void setRow(int row) {
        this.rowInput = row;
    }

    protected int getRow() {
        return rowInput;
    }

    protected int getColInput() {
        return colInput;
    }

    protected void setColInput(int col) {
        this.colInput = col;
    }
}
