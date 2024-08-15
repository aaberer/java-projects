//  Andrew Aberer | aaberer@colosate.edu
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ShippingMain {
    /**
     * Self Explanation
     * The while loop is impemented to check if there is any informarion in thre next line
     * the if statements then check to see if that line contains any of the varauables looking for
     * and sets said value equal to the respective variable if the item matches then returns all
     * collected info and adds it to the product constructor
     * 
     */
    public static Product createProduct(Scanner fileScanner) {
        String name = "";
        int sku = -1;
        double price = -1;
        double weight = -1;
        int destination = -1;
        int quantity = -1;

        while (fileScanner.hasNextLine()) {
            String readline = fileScanner.nextLine();
            
            if (readline.contains("NAME:")) {
                name = readline.substring(readline.indexOf(":") + 1).trim();
            } else if (readline.contains("SKU:")) {
                sku = Integer.parseInt(readline.substring(readline.indexOf(":") + 1).trim());
            } else if (readline.contains("PRICE:")) {
                price = Double.parseDouble(readline.substring(readline.indexOf(":") + 1).trim());
            } else if (readline.contains("WEIGHT:")) {
                weight = Double.parseDouble(readline.substring(readline.indexOf(":") + 1).trim());
            } else if (readline.contains("DESTINATION:")) {
                destination = Integer.parseInt(readline.substring(readline.indexOf(":") + 1).trim());
            } else if (readline.contains("QUANTITY:")) {
                quantity = Integer.parseInt(readline.substring(readline.indexOf(":") + 1).trim());
            } else {
                System.out.println("ERROR Invalid line: " + readline);
            }
        }

        return new Product(name, sku, price, weight, destination, quantity);
    }

    public static ShippingManifest createManifest(ArrayList<File> fileList) {
        FileHelper fileHelper = new FileHelper();
        ShippingManifest shipManifest = new ShippingManifest();

        for(File file: fileList) {
            Scanner fileScanner = fileHelper.getFileScanner(file);

            Product newProd = createProduct(fileScanner);
            shipManifest.addProduct(newProd);

            fileScanner.close();
        }

        return shipManifest;
    }

    public static void printSplash() {
        System.out.println("Please type:");
        System.out.println("\"X\" to exit program.");
        System.out.println("\"D\" to distribute products to addresses.");
        System.out.println("\"F-ZIPCODE\" to forwards products to destinations.");
        System.out.println("\"P\" to print current manifest.");
    }
    /**
     * Self Explanation
     * This method go uses the method starts with to check user input being passed through
     * and the substring in order to gather the zipcode from the string
     * 
     */
    public static void go(Scanner scnr, ShippingManifest shipManifest) {
        printSplash();
    
        String inputFfile = scnr.nextLine();
    
        while (!inputFfile.startsWith("X")) {
            if (inputFfile.startsWith("D")) {
                shipManifest.distributeProducts();
            } else if (inputFfile.startsWith("F-")) {
                String zip = inputFfile.substring(2);
                int zipcode = Integer.parseInt(zip);
                shipManifest.forwardProducts(zipcode);
            } else if (inputFfile.startsWith("P")) {
                shipManifest.printManifest();
            } else {
                System.out.println("ERROR Unrecognized command");
            }
    
            inputFfile = scnr.nextLine();
        }
    }

    public static void main(String[] args) {
        String directoryPath = "ShipmentFolder";
        FileHelper fileHelper = new FileHelper();
        ArrayList<File> fileList = fileHelper.getFileDirectory(directoryPath);

        ShippingManifest shipManifest = createManifest(fileList);
        System.out.println("Manifest created from " + directoryPath + "!");

        shipManifest.printManifest();

        Scanner scnr = new Scanner(System.in);
        go(scnr, shipManifest);
    }
}