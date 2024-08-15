public class Main extends Recursion{

    public static void main(String [] args){
        //This is essentially the same as the main in Recursion.java
        //This tests each method implemented and is a read-only file.


        System.out.println("Testing the fibonacci method");
        System.out.printf("fib(0) should be 0 -> %d\n", fib(0));
        System.out.printf("fib(1) should be 1 -> %d\n", fib(1));
        System.out.printf("fib(2) should be 1 -> %d\n", fib(2));
        System.out.printf("fib(5) should be 5 -> %d\n", fib(5));
        System.out.printf("fib(10) should be 55 -> %d\n", fib(10));
        System.out.printf("fib(13) should be 233 -> %d\n", fib(13));
        System.out.println();

        System.out.println("Testing out the multiplication method");
        System.out.printf("mult(8, 2) should be 16 -> %d\n", mult(8, 2));
        System.out.printf("mult(2, 8) should be 16 -> %d\n", mult(2, 8));
        System.out.printf("mult(-2, -8) should be 16 -> %d\n", mult(-2, -8));
        System.out.printf("mult(4, -3) should be -12 -> %d\n", mult(4, -3));
        System.out.printf("mult(-3, 4) should be -12 -> %d\n", mult(-3, 4));
        System.out.println();

        System.out.println("Testing out the exponent method");
        System.out.printf("expt(2, 5) should be 32 -> %d\n", expt(2, 5));
        System.out.printf("expt(5, 2) should be 25 -> %d\n", expt(5, 2));
        System.out.println();

        System.out.println("Testing out the palindrome method");
        System.out.printf("isPalindrome(\"a\") should be true -> %b\n", isPalindrome("a"));
        System.out.printf("isPalindrome(\"Aibohphobia\") should be true -> %b\n", isPalindrome("Aibohphobia"));
        System.out.printf("isPalindrome(\"noon\") should be true -> %b\n", isPalindrome("noon"));
        System.out.printf("isPalindrome(\"Recursion\") should be false -> %b\n", isPalindrome("Recursion"));
        System.out.println();


        System.out.println("Testing out the longestWordLength method\n");
        String quoteOne =
                "Grit, one of the keys to success. The person who perseveres is the one who\n" +
                        "will surely win. Success does not come from giving up, it comes from believing\n" +
                        "in yourself and continuously working towards the realization of a worthy ideal.\n" +
                        "Do not ever give up on what you want most. You know what you truly want.\n" +
                        "Believe in your dreams and goals and take daily consistent action in order to\n" +
                        "make your dreams a reality.";

        System.out.printf("The longest word in the following quote:\n%s\nshould be 12 -> %d\n\n", quoteOne, longestWordLength(quoteOne));
        String quoteTwo = "Try to be like the turtle – at ease in your own shell.";
        System.out.printf("The longest word in the following quote:\n%s\nshould be 6 -> %d\n\n", quoteTwo, longestWordLength(quoteTwo));
        System.out.println();


        System.out.println("Testing the dedupeChars method");
        System.out.printf("dedupeChars(\"a\") should be a -> %s\n", dedupeChars("a"));
        System.out.printf("dedupeChars(\"aa\") should be a -> %s\n", dedupeChars("aa"));
        System.out.printf("dedupeChars(\"MiSsisSiPpi\") should be MiSisiPi -> %s\n", dedupeChars("MiSsisSiPpi"));
        System.out.printf("dedupeChars(\"swimMmMming\") should be swiming -> %s\n", dedupeChars("swimMmMming"));

    }
}
