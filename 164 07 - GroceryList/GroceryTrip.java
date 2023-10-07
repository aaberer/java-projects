import java.util.Scanner;

public class GroceryTrip {
    Scanner scnr = new Scanner(System.in);

    GroceryList grocList = new GroceryList();

    public void printOptions() {
        System.out.println("You are now using the GroceryTrip App!");
        System.out.println("When asked for an action use one of these options:");
        System.out.println("[A]dd Grocery to Grocery List");
        System.out.println("[R]emove Grocery from Grocery List");
        System.out.println("[P]rint Grocery List");
        System.out.println("[G]et Aisle Groceries");
        System.out.println("[T]otal Price of Grocery List");
        System.out.println("[Q]uit the GroceryTrip App");
    }

    public String getAction() {
        return scnr.nextLine();
    }

    public void actionLoop(String action) {
        while(action.equalsIgnoreCase(action)) {
            if(action.equalsIgnoreCase("a")) {
                addAction();
            }
            else if(action.equalsIgnoreCase("r")) {
                removeAction();
            }
            else if(action.equalsIgnoreCase("p")) {
                printAction();
            }
            else if(action.equalsIgnoreCase("g")) {
                getAisleAction();
            }
            else if(action.equalsIgnoreCase("t")) {
                totalAction();
            }
            else if(action.equalsIgnoreCase("q")) {
                System.out.println("Thank you for using the GroceryTrip App!");
                break;
            }
            else {
                System.out.println("Please enter an option, A, R, P, G, T, or Q.");
            }
            System.out.println("Enter a new option:");
            action = getAction();
        }
    }

    public void addAction() {
        System.out.println("Enter grocery info in the format \"name-price-aisle-calories\":");
        String grocStr = scnr.nextLine();
        Grocery groc = new Grocery(grocStr);
        grocList.addGrocery(groc);
        System.out.println(groc.getName() + " added!");
    }

    public void removeAction() {
        System.out.println("Enter the exact name of the Grocery you would like to remove:");
        String grocName = scnr.nextLine();
        grocList.removeGrocery(grocName);
        System.out.println(grocName + " removed!");
    }

    public void printAction() {
        System.out.println("Printing entire Grocery List:");
        String grocString = grocList.toString();
        System.out.println(grocString);
    }

    public void getAisleAction() {
        System.out.println("Enter the number of the aisle that you would like to display:");
        int aisleNum = scnr.nextInt();
        String aisleStr = grocList.getAisleGroceries(aisleNum);
        System.out.println(aisleStr);
    }

    public void totalAction() {
        System.out.println("Printing Grocery List Totals:");
        String totalsString = grocList.getTotals();
        System.out.println(totalsString);
    }
}