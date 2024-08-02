import java.util.Arrays;

public class PriorityQueue {

    /* This class is finished for you.
     */
    public static class Customer implements Comparable<Customer> {
        private double donation;

        public Customer(double donation) {
            this.donation = donation;
        }

        public double getDonation() {
            return donation;
        }

        public void donate(double amount) {
            donation += amount;
        }

        public int compareTo(Customer other) {
            double diff = other.donation - donation;
            if (diff > 0) {
                return -1;
            } else if (diff < 0) {
                return 1;
            } else {
                return 0;
            }
        }

        public String toString() {
            return String.format("$%.2f", donation);
        }
    }

    private Customer[] data;
    private int size;

    public PriorityQueue(int capacity) {
        data = new Customer[capacity];
        size = 0;
    }
    
    public PriorityQueue() {
        this(10);
    }

    /* Add a customer to the queue.
     * Remember to do so in a way that keeps the queue in sorted order!
     */
    public void push(Customer customer) {
        if (size == data.length) {
            throw new IllegalStateException(); // check to see if array can hold the new el
        }
        int idx = 0;
        while (idx < size && customer.compareTo(data[idx]) < 0) {
            idx++;
        }
        for (int i = size - 1; i >= idx; i--) {
            data[i + 1] = data[i]; // right shift all el
        }
        data[idx] = customer;
        size++;
    }

    /* Remove and return the highest priority customer from the queue.
     */
    public Customer pop() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        Customer rtr = data[0];
        for (int i = 1; i < size; i++) {
            data[i - 1] = data[i]; // left shift all el
        }
        size--;
        return rtr;
    }

    /* Return, but don't remove, the highest priority item from the queue.
     */
    public Customer peek() {
        if (size == 0) throw new IllegalStateException(); 
        return data[0];
    }

    /* Given the index of a customer, increase their donation amount, letting
     * them cut in line if necessary. 
     *
     * Refer to the Customer class to remind yourself the operations you can do
     * on a customer.
     */
    public void bump(int customerIndex, double amount) {
        if (customerIndex < 0 || customerIndex >= size) {
            throw new IndexOutOfBoundsException();
        }
        Customer customer = data[customerIndex];
        customer.donate(amount);
        while (customerIndex > 0 && customer.compareTo(data[customerIndex - 1]) > 0) {
            data[customerIndex] = data[customerIndex - 1];
            customerIndex--;
        }
        data[customerIndex] = customer;
    }

    public String toString() {
        return Arrays.toString(data);
    }
    
    public int getSize() {
    	return size;
    }

    public static void main(String[] args) {
        
    }
}