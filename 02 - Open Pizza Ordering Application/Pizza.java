public class Pizza {
    int pizzaSize = 0;
    int numToppings = 0;
    double pizzaPrice = 0;
    double tax = 0;

    public void setSize(int size) {
        pizzaSize = size;
    }

    public void setToppings(int toppings) {
        numToppings = toppings;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void calculateBasePizzaPrice() {
        pizzaPrice = (pizzaSize * 6.5) + (numToppings * 0.65);
    }

    public void addDeliveryFee(int delivery) {
        if (delivery == 1)
            pizzaPrice = pizzaPrice + 5;
    }

    public void addPizzaTax(double taxRate) {
        pizzaPrice = pizzaPrice + (pizzaPrice * (taxRate/100));
    }

    public double getPrepTime() {
        double ptime = (pizzaSize * 8) + (numToppings * 1.5);
        return ptime;
    }
}
