import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Writer {
    PrintWriter outputFile;

    public Writer(String fileName) throws IOException{
        FileOutputStream filestream = new FileOutputStream(fileName);
        outputFile = new PrintWriter(filestream);
    }

    public void closeWriter() {
        outputFile.print("End of file.");
        outputFile.close();
    }

    /** Student Self-Explanation
     * logReverse method starts at the end of the array list and prints
     * to the file ignoring the element type stored in the list
     */
    public void logReverse(ArrayList<String> fileContents) {
        outputFile.println("Reversed file contents: ");
        for (int i = fileContents.size() - 1; i >= 0; i--) {
            outputFile.println(fileContents.get(i));
        }
    }
    /** Student Self-Explanation
     * logMax goes through the array list and stores the largest number
     * within using a wrapper class to convert strings into integers
     */
    public void logMax(ArrayList<String> fileContents) {
        int storeMax = 0;
        for (int i = 0; i < fileContents.size(); i++){
            int parse = Integer.parseInt(fileContents.get(i));
            if (parse > storeMax){
                storeMax = parse;
            }
        }

        outputFile.print("The largest number in the file is: ");
        //You will want to print your max number on this line.
        outputFile.println(storeMax);
    }
    /** Student Self-Explanation
     * logDuplicates method will read thorough the array list and
     * find any duplicates inside returning a true or false value depending
     * on what is found
     */
    public void logDuplicates(ArrayList<String> fileContents) {
        outputFile.print("Duplicates found: ");

        for (int i = 0; i < fileContents.size(); i++){
            for (int j = i + 1; j < fileContents.size(); j++){
                if (fileContents.get(i).equals(fileContents.get(j))){
                    outputFile.println(true);
                    return;
                }
            }
        }
        outputFile.println(false);
    }
}
