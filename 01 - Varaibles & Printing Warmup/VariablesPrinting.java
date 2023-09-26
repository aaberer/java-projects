//  Andrew Aberer | aaberer@colosate.edu
//Question 1: What is the class name?
public class VariablesPrinting {
   
// the class name is VaraiblesPrinting

    /**
     * Below this is the "main" method which is always called
     * first in program execution.
     */
    public static void main(String[] args) {
        //Question 2: What is this variables name and value?
        String mainVar = "James Gosling";
        // mainVar is being created and assigned as a string with a value of "James Gosling"

        /**
         * Here we are calling the "Warmup" method below and
         * giving it the "mainVar" variable as the parameter.
         */
        warmup(mainVar);
        // Then we call the next method without a parameter.
        debug();
    }
 
    /** SELF EXPLANATION  
     * Students, add your paragraph here for the end of Step 1. 
     * Starting off the class is declared at the top and on line 10 the public line
     * tells the program where to start. Then a string variable is created and assigned
     * which is later called in the warmup function.
    */
    /**
     * Given that the "Warmup" method was called with "mainVar"
     * in the main method, the value of "mainVar" is now stored
     * within the variable known as "warmupVar".
     * Now "warmupVar" is practically equal to "mainVar", it's
     * name is the only thing changed.
     */
    public static void warmup(String warmupVar) {
        //Question 3: What is printed by the following two lines?
        //(Indicate where the newline is.)
        /** The output will be:
         * Java's creator is (space)
         * James Gosling
         */ 
        System.out.print("Java's creator is ");
        System.out.println(warmupVar);

        int x = 13;
        int y = 42;
        //Question 4: What is printed by the following line?
        System.out.println(x + y);
        // 13 + 42 which results in 55 is printed
        //Question 5: What is printed by the following line?
        System.out.println(x + " " + y);
        // 13 42

        int z = y % x;
        //Question 6: What is printed by the following line?
        System.out.println(z);
        // 3

        //Question 7: In total, how many lines are printed?
        // 5 lines
        //Question 8: In total, how many variables are used?
        // 4 variables
    }

    // Step 2 - fix the code, and get it running
    public static void debug() {
        int a = 9;
        int b = 12;

        String word = "debugging!";

        System.out.println(a);
        System.out.println("We are " + word);
    }
}