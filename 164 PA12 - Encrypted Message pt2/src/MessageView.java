import java.util.Scanner;

public class MessageView extends Object {
    private Scanner scanner; // stored here so we don't cause conflicts with System.in

    public MessageView(){
        this.scanner = new Scanner(System.in);
    }

    public String getInput(String msg) {
        System.out.println(msg + " ");
        if (scanner.hasNextLine()){
            return scanner.nextLine();
        } else {
            return "";
        }
    }
    public String messageToString(OpenMessage m) {
        String s = "TO: " + m.getTo() + "\n";
        s += "FROM: " + m.getFrom() + "\n";
        s += "SUBJECT: " + m.getSubject() + "\n";
        s += "BODY: " + m.getBody() + "\n";
        return s;
    }

    public void printMenu() {
        System.out.println("Welcome to Wonderland Messenger.");
        System.out.println("[S]: search for a message");
        System.out.println("[X]: exit");
        System.out.println("What would you like to do?");
    }
}
