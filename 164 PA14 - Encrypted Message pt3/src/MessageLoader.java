import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MessageLoader {

    public MessageLoader(){
    }

    public static ArrayList<OpenMessage> loadFile(String filename) {
        ArrayList<OpenMessage> messages = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String to = parseLine(scanner.nextLine());
                String from = parseLine(scanner.nextLine());
                String subject = parseLine(scanner.nextLine());
                String body = parseLine(scanner.nextLine());
                messages.add(new OpenMessage(to, from, subject, body));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return messages;
    }

    public static String parseLine(String line) {
        int colonIndex = line.indexOf(":");
        if (colonIndex == -1) {
            return "";
        }
        return line.substring(colonIndex + 1).trim();
    }

}
