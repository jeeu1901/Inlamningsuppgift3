import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class FileHandler {
    List<String> timeRecord = new ArrayList<>();
    List<String> clickRecord = new ArrayList<>();
    public void writeFile(JLabel time, JLabel clicks) {
        try(BufferedWriter w = new BufferedWriter(new FileWriter("src/Files/records.txt"))) {
            w.write(time.getText() + " " + clicks.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFile() {

        try(Scanner sc = new Scanner(new FileReader("src/Files/records.txt"))) {
            while(sc.hasNextLine()) {
                String record = sc.nextLine();
                String clicks = record.substring(record.indexOf(" ") + 1, record.indexOf("T") - 1);
                String time = record.substring(record.lastIndexOf(':') - 2);
                System.out.println(clicks + " " + time);
                timeRecord.add(time);
                clickRecord.add(clicks);
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public List<String> getTimeRecord() {
        return timeRecord;
    }

    public List<String> getClickRecord() {
        return clickRecord;
    }
}
