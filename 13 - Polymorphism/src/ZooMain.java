import java.util.Scanner;

public class ZooMain {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Welcome to the Zoo Database!");
        System.out.println("What file would you like to read?");

        String fileName = scnr.nextLine();

        ZooProcessing processor = new ZooProcessing();
        processor.processFile(fileName);
        processor.interact();

        scnr.close();
    }
}
