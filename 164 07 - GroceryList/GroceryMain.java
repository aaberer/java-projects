public class GroceryMain {
    public static void main(String[] args) {
        GroceryTrip groceryApp = new GroceryTrip();

        groceryApp.printOptions();

        String firstAction = groceryApp.getAction();
        groceryApp.actionLoop(firstAction);
    }
}