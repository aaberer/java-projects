import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMain {

    public static void main(String[] args) throws IOException{
        Scanner scnr = new Scanner(System.in);

        System.out.print("Please enter the name of the file you would like to read: ");
        String fileName = scnr.next();

        Reader reader = new Reader();
        ArrayList<String> fileContents = reader.getFileContents(fileName);

        System.out.println("Please enter a name for your new file: ");
        String newFileName = scnr.next();

        Writer fileOut = new Writer(newFileName);

        fileOut.logReverse(fileContents);
        fileOut.logMax(fileContents);
        fileOut.logDuplicates(fileContents);

        fileOut.closeWriter();
        scnr.close();
    }
}