//  Andrew Aberer | aaberer@colosate.edu
import java.util.ArrayList;

public class MessageMain {

    public static void main(String[] args) {
        String filename = "input.txt";
        if(args.length > 0) // has a least one program argument
            filename = args[0]; // we will come back to this in the future, but common!
        ArrayList<OpenMessage> messages = MessageLoader.loadFile(filename);
        MessageApp app = new MessageApp(messages);
        app.go();
    }
}








