import java.util.ArrayList;

public class MessageApp {
    private final ArrayList<OpenMessage> messages;
    private final MessageView view;

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
                    s.append(view.messageToString(m));
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
                    s.append(view.messageToString(m));
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
                    s.append(view.messageToString(m));
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

}