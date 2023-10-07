import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MessageApp {
    public final ArrayList<OpenMessage> messages;
    private final MessageView view;
    protected final ArrayList<Key> keys;
    public MessageApp(ArrayList<OpenMessage> messages) {
        this.messages = messages;
        view = new MessageView();
    }
    public void go() {
        String command = "y";
        while (!command.equals("x")) {
            view.printMenu();
            String input = view.getInput("What would you like to do?");
            command = input.toLowerCase().substring(0, 1); // first character only, lower case
            processCommand(command);
        }
    }
    public void processCommand(String command) {
        String lowerCaseCommand = command.toLowerCase();
        if (lowerCaseCommand.equals("s")) {
            String searchResult = searchHelper();
            System.out.println(searchResult);
        }
        if (lowerCaseCommand.equals("c")){
            composeHelper();
        }
    }
    public int KeyByName(String keyName){ //TODO

    }
    private String searchHelper() {
        String searchType = view.getInput("What type of search would you like to do?\nOptions are: subject, to, from").toLowerCase();
        String term = view.getInput("Enter your search phrase: ").toLowerCase();
        StringBuilder s = new StringBuilder();
        if (searchType.contains("to")) {
            for (OpenMessage m : messages) {
                if (m.searchTo(term)) {
                    s.append("Found message!\n");
                    //System.out.println(m.toString());
                    s.append(m.toString());
                }
            }
            if(!(s.length() == 0)){
                return s.toString();
            }
            else {
                return "No message found.";
            }
        }
        else if (searchType.contains("from")) {
            for (OpenMessage m : messages) {
                if (m.searchFrom(term)) {
                    s.append("Found message!\n");
                    //System.out.println(m.toString());
                    s.append(m.toString());
                }
            }
            if(!(s.length() == 0)){
                return s.toString();
            }
            else {
                return "No message found.";
            }
        }
        else if (searchType.contains("subject")) {
            for (OpenMessage m : messages) {
                if (m.searchSubject(term)) {
                    s.append("Found message!\n");
                    //System.out.println(m.toString());
                    s.append(m.toString());
                }
            }
            if(!(s.length() == 0)){
                return s.toString();
            }
            else {
                return "No message found.";
            }
        }
        return "Invalid command.";
    }
    public void composeHelper(){ //TODO
        String to = view.getInput("TO:"); //Get input TO, FROM, SUBJECT, and BODY
        String from = view.getInput("FROM:");
        String subject = view.getInput("SUBJECT:");
        String body = view.getInput("BODY:");
        OpenMessage message = new OpenMessage(to, from, subject, body); //New OpenMessage obj
        messages.add(message); //Add to arraylist
        String fileName = view.getInput("What file would you like to write the message to?");  //Get filename
        try {
            PrintWriter outputFile = new PrintWriter(fileName); //PrintWriter to file
            Key caesarKey = new Key(1, 3); //Hard coded key
            String encryptedMessage = message.encrypt(message.toString(), caesarKey); //Encrypt to
            String decryptedMessage = message.decrypt(encryptedMessage, caesarKey);
            outputFile.println(encryptedMessage); //Write encrypted to file
            outputFile.println(decryptedMessage); //Write decrypted encrypted to file
            outputFile.close(); //Close file

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
            e.printStackTrace();
        }
    }
}