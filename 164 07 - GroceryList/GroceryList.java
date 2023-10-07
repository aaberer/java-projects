//  Andrew Aberer | aaberer@colosate.edu
import java.util.ArrayList;

public class GroceryList {
    
    ArrayList<Grocery> grocList = new ArrayList<Grocery>();

    public ArrayList<Grocery> getGrocList() {
        return grocList;
    }

    public void addGrocery(Grocery groc) {
        grocList.add(groc);
    }

    /*
    * Student Self-Explanation:
    * remove grocery needs to loop through the array list
    * and checks to see if it equals the entered grocery name
    * if the conditional returns true the grocery will be removed from the list
    */
    public void removeGrocery(String grocName) {
        for(Grocery g: grocList) {
            if(g.getName().equals(grocName)) {
                grocList.remove(g);
                break;
            }
        }
    }

    public String toString() {
        String strList = "";
        for(Grocery g: grocList) {
            strList += (g.toString());
        }
        return strList;
    }

    public String getAisleGroceries(int aisle) {
        String aisleString = "";
        for(Grocery g: grocList) {
            if(aisle == g.getAisle()){
                aisleString += g.toString() + '\n';
            }
        }
        return aisleString;
    }

    public String getTotals() {
        double priceSum = 0;
        int caloriesSum = 0;
        for(Grocery g: grocList) {
            priceSum += g.getPrice();
            caloriesSum += g.getCalories();
        }
        String rtn = "The total price of this Grocery Trip: $" + priceSum + '\n';
        rtn += "The total calories of this Grocery Trip: " + caloriesSum + "cal\n";
        return rtn;
    }
}