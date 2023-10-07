public class GroceryTests {
    public static boolean removeGroceryTest1() {
        GroceryList grocList = new GroceryList();
        grocList.addGrocery(new Grocery("Noodles-1.99-1-1300"));
        grocList.addGrocery(new Grocery("Eggplant-3.20-5-300"));
        grocList.addGrocery(new Grocery("Eggs-4.50-7-640"));


        Grocery grocery1 = grocList.grocList.get(0);
        grocList.removeGrocery("Noodles");
        Grocery grocery2 = grocList.grocList.get(0);

        //Groceries should be different after Noodles has been removed
        if(!grocery1.equals(grocery2)) return true;
        else return false;
    }
    
    public static boolean removeGroceryTest2() {
        GroceryList grocList = new GroceryList();
        grocList.addGrocery(new Grocery("Noodles-1.99-1-1300"));
        grocList.addGrocery(new Grocery("Eggplant-3.20-5-300"));
        grocList.addGrocery(new Grocery("Eggs-4.50-7-640"));


        Grocery grocery1 = grocList.grocList.get(2);
        grocList.removeGrocery("Noodles");
        Grocery grocery2 = grocList.grocList.get(1);

        //Groceries should be the same after Noodles has been removed
        if(grocery1.equals(grocery2)) return true;
        else return false;
    }

    public static boolean toStringTest() {
        GroceryList grocList = new GroceryList();
        grocList.addGrocery(new Grocery("Noodles-1.99-1-1300"));
        grocList.addGrocery(new Grocery("Eggplant-3.20-5-300"));
        grocList.addGrocery(new Grocery("Eggs-4.50-7-640"));

        String expectedString = "Noodles which costs: $1.99, located in the aisle 1.\n";
        expectedString += "Eggplant which costs: $3.20, located in the aisle 5.\n";
        expectedString += "Eggs which costs: $4.50, located in the aisle 7.\n";

        String actualString = grocList.toString();
        
        if(expectedString.equals(actualString)) return true;
        else return false; 
    }

    public static boolean getAisleGroceriesTest() {
        GroceryList grocList = new GroceryList();
        grocList.addGrocery(new Grocery("Noodles-1.99-1-1300"));
        grocList.addGrocery(new Grocery("Eggplant-3.20-5-300"));
        grocList.addGrocery(new Grocery("Eggs-4.50-7-640"));
        grocList.addGrocery(new Grocery("Pasta-3.00-1-500"));

        String expectedString = "Noodles which costs: $1.99, located in the aisle 1.\n";
        expectedString += "Pasta which costs: $3.00, located in the aisle 1.\n";

        String actualString = grocList.getAisleGroceries(1);

        if(expectedString.equals(actualString)) return true;
        else return false;
    }
    public static void main(String[] args) {
        //removeGrocery Test 1
        System.out.println("removeGrocery Test 1: " + removeGroceryTest1() + ", should return true.");

        //removeGrocery Test 2
        System.out.println("removeGrocery Test 2: " + removeGroceryTest2() + ", should return true.");

        //toString Test
        //System.out.println("toString Test: " + toStringTest() + ", should return true.");

        //getAisleGroceries Test
        //System.out.println("getAisleGroceries Test: " + getAisleGroceriesTest() + ", should return true.");

    }
}
