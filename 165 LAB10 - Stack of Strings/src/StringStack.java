//Andrew Aberer | aaberer@colostate.edu

import java.util.*;

public class StringStack {

    private String[] strArr;
    private int size = 0;
    /* Puts the stack into a valid state, ready for us to call methods on.
     * This constructor will create a stack with a different capacity than the default.
     */
    public StringStack(int capacity) {
        strArr = new String[capacity];
    }

    public StringStack() {
        strArr = new String[10];
    }

    public boolean empty() {
        if (size == 0) return true;
        return false;
    }

    public String peek() { //look at the top of the stack
        if (size == 0){
            throw new NoSuchElementException();
        }
        return strArr[size - 1];
    }

    public String pop() { //remove
        if (size == 0){
            throw new NoSuchElementException();
        }
        String rtr = strArr[size - 1];
        strArr[size - 1] = null;
        size -= 1;
        return rtr;
    }

    public String push(String s) { //add
        if (size >= strArr.length){
            throw new IllegalStateException();
        }
        strArr[size] = s;
        size += 1;
        return strArr[size - 1];
    }

    public int search(String s) {
        for (int i = 0; i < size; ++i){
            if (strArr[i].equals(s)){
                return size - i - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StringStack stack_ten = new StringStack();
        StringStack stack_three = new StringStack(3);


        assertEquals(stack_ten.empty(), true,
            "Your stack returned false for empty(), even though it was just "
            + "created and should be empty.");

        stack_ten.push("Alef");
        stack_ten.push("Bet");
        stack_ten.push("Gimel");

        assertEquals(stack_ten.empty(), false,
            "Your stack returned true for empty(), even though elements were "
            + "pushed into it.");

        String top = stack_ten.pop();
        assertEquals(top, "Gimel",
            "Didn't get the correct element from a pop()! Should have been \" "
            + "Gimel\", but was \"" + top + "\".");

        top = stack_ten.peek();
        assertEquals(top, "Bet",
            "Didn't get the correct element from a peek()! Should have been "
            + "\"Bet\", but was \"" + top + "\".");
    
        // Making sure a peek doesn't change anything!
        top = stack_ten.peek();
        assertEquals(top, "Bet",
            "Didn't get the correct element from a peek()! Should have been "
            + "\"Bet\", but was \"" + top + "\".");

        stack_ten.pop();
        stack_ten.pop();

        assertEquals(stack_ten.empty(), true,
            "Your stack returned false for empty, even though all of its "
            + "elements were popped off.");

        boolean caught = false;
        try {
            stack_ten.pop();
        } catch (NoSuchElementException e) {
            caught = true;
        }
        if (!caught) {
            throw new AssertionError(
                "Popping an empty stack should have caused a "
                + "NoSuchElementException, but it did not!");
        }
        caught = false;
        try {
            stack_ten.peek();
        } catch (NoSuchElementException e) {
            caught = true;
        }
        if (!caught) {
            throw new AssertionError(
                "Peeking an empty stack should have caused a "
                + "NoSuchElementException, but it did not!");
        }
        caught = false;

        String[] moreLetters = new String[] {
            "Dalet", "He", "Vav", "Zayin", "Het", "Tet", "Yod", "Kaf", "Lamed"
        };
        for (String l : moreLetters) {
            stack_ten.push(l);
        }
        
        int searched = stack_ten.search("Het");
        assertEquals(searched, 4,
            "Incorrect result from .search(): got " + searched
            + ", but should have found 4");

        searched = stack_ten.search("Lamed");
        assertEquals(searched, 0,
            "Incorrect result from .search(): got " + searched
            + ", but should have found 0");

        searched = stack_ten.search("Not in the stack!");
        assertEquals(searched, -1,
            "Incorrect result from .search(): got " + searched
            + ", but should have found -1");


        stack_three.push("A");
        stack_three.push("B");
        stack_three.push("G");
        try {
            stack_three.push("D");
        } catch (IllegalStateException e) {
            caught = true;
        }
        if (!caught) {
            throw new AssertionError(
                "Trying to push onto a full stack should have caused an "
                + "IllegalStateException, but it did not.");
        }

        System.out.println("All tests passed! Now go play some Jenga, because "
            + "you're the stacking master.");
    }

    private static <T1, T2> void assertEquals(T1 e1, T2 e2, String msg) {
        if (!e1.equals(e2)) {
            throw new AssertionError(msg);
        }
    }
}