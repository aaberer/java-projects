import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    private Scanner getFileScanner(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scnr = new Scanner(file);

            return scnr;
        } catch(FileNotFoundException e) {
            System.err.println("\"" + fileName + "\" not found, please ensure that the file trying to be read is in the folder just above the src directory.");;
            System.err.println("Program now exiting!");
            System.exit(-1);
        }
        return null;
    }

    public ArrayList<String> getFileContents(String fileName) {
        Scanner fileScnr = getFileScanner(fileName);

        ArrayList<String> fileContents = new ArrayList<String>();
        while(fileScnr.hasNextLine()) {
            fileContents.add(fileScnr.nextLine());
        }

        return fileContents;
    }
}