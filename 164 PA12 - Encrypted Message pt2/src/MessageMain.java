import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MessageMain {

    public static void main(String[] args) {
        String filename = "input.txt";
        if(args.length > 0) // has a least one program argument
            filename = args[0]; // we will come back to this in the future, but common!
        ArrayList<OpenMessage> messages = MessageLoader.loadFile(filename);
        MessageApp app = new MessageApp(messages);
        app.go();
        MessageView view = new MessageView();
        String saveFile = view.getInput("Would you like to save your messages from this session? (y/n)");
        if(saveFile.equals("y")){
            try{
                PrintWriter pw = new PrintWriter(filename);
                for(OpenMessage m : app.messages){
                    pw.print(m.toString());
                }
                pw.close();
            }catch (FileNotFoundException e){
                System.err.println("File not found!");
            }
        }
    }
}








