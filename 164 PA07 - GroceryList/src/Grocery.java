public class Grocery {
    
    String name = "";
    double price = 0;
    int aisle = 0;
    int calories = 0;

    public Grocery(String groceryStr) {
        int firstHyphenIndex = groceryStr.indexOf("-");
        int secondHyphenIndex = groceryStr.indexOf("-", firstHyphenIndex+1);
        int thirdHyphenIndex = groceryStr.indexOf("-", secondHyphenIndex+1);

        name = groceryStr.substring(0, firstHyphenIndex);
        /*
        * Here the Integer and Double wrapper classes are being used.
        * The values returned from using the str.split("char")
        * are Strings, so an easy conversion is using the Integer
        * Wrapper class to convert a *String* to an *int*.
        * Here is the Integer Wrapper class Javadoc. 
        * https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html
        */
        price = Double.parseDouble(groceryStr.substring(firstHyphenIndex+1, secondHyphenIndex));
        aisle = Integer.parseInt(groceryStr.substring(secondHyphenIndex+1, thirdHyphenIndex));
        calories = Integer.parseInt(groceryStr.substring(thirdHyphenIndex));
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAisle() {
        return aisle;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        String str = String.format("%s which costs: $%.2f, located in the aisle %d.", name, price, aisle);
        return str;
    }

    public boolean equals(Object compareObj) {
        if(!(compareObj instanceof Grocery)) {
           return false;
        }
        Grocery compareGrocery = (Grocery) compareObj;
        return name.equals(compareGrocery.getName()) 
                 && price == compareGrocery.getPrice();
    }
}