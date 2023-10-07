import java.io.IOException;
import java.util.ArrayList;

public class FileTester {
    public static boolean testLogReverse(ArrayList<String> contents) throws IOException {
        Writer writer = new Writer("logReverseTest.txt");
        writer.logReverse(contents);
        writer.closeWriter();

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Reversed file contents: ");
        expected.add("58");
        expected.add("12");
        expected.add("19");
        expected.add("42");
        expected.add("12");
        expected.add("End of file.");

        Reader testReader = new Reader();
        ArrayList<String> result = testReader.getFileContents("logReverseTest.txt");
        
        if(expected.equals(result)) return true;
        else return false;
    }

    public static boolean testLogMax(ArrayList<String> contents) throws IOException {
        Writer writer = new Writer("logMaxTest.txt");
        writer.logMax(contents);
        writer.closeWriter();

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("The largest number in the file is: 58");
        expected.add("End of file.");

        Reader testReader = new Reader();
        ArrayList<String> result = testReader.getFileContents("logMaxTest.txt");
        
        if(expected.equals(result)) return true;
        else return false;
    }

    public static boolean testLogDuplicates(ArrayList<String> contents) throws IOException {
        Writer writer = new Writer("logDuplicatesTest.txt");
        writer.logDuplicates(contents);
        writer.closeWriter();

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Duplicates found: true");
        expected.add("End of file.");

        Reader testReader = new Reader();
        ArrayList<String> result = testReader.getFileContents("logDuplicatesTest.txt");
        
        if(expected.equals(result)) return true;
        else return false;
    }
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        ArrayList<String> fileContents = reader.getFileContents("nums.txt");
        
        System.out.println("logReverse test passed? " + testLogReverse(fileContents));
        System.out.println("logMax test passed? " + testLogMax(fileContents));
        System.out.println("logDuplicates test passed? " + testLogDuplicates(fileContents));
    }
}
