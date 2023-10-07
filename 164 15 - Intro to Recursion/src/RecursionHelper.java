public class RecursionHelper {
    /** Self Explanation - Remember to detail your base and recursive cases.
     * reverseString method will take an input string and have an if statement
     * recursive case will call method again in a return statement appending the selected
     * char and recursively calling the method inputting rest of str
     */
    public String reverseString(String str) {
        //TODO: Student
        if (str.length() == 1){return str;}
        return str.charAt(str.length() - 1) + reverseString(str.substring(0, str.length() - 1));
    }
    /** Self Explanation - Remember to detail your base and recursive cases.
     * armstrong number method will take num & 10 to get remainder and set equal to
     * var, math.pow(var, 3) for power and insert new var and cube it and set += to
     * total so it updates and can be passed again, make a new num var divide by 10
     * for the recursive case armstrongNumber(var, total) then return total when
     * base case is met
     */
    public int armstrongNumber(int num, int total) {
        //TODO: Student
        if (num == 0){return total;}
        int modVar = num % 10;
        total += Math.pow(modVar, 3);
        num = num / 10;
        return armstrongNumber(num, total);
    }
    /** Self Explanation - Remember to detail your base and recursive cases.
     * two base cases one for if it is, one for if it isnt
     * takes a string as a parameter a checks if it is a palindrome
     * we can call reverse string method and compare to original input
     */
    //compare str and revStr returns true if equal, else returns false

    public boolean palindromeChecker(String str) {
        //TODO: Student
        if (str.length() == 1){return true;}
        if (str.charAt(0) == str.charAt(str.length() - 1)){
            return palindromeChecker(str.substring(1, str.length() - 1));
        }
        else {return false;}
    }
}
