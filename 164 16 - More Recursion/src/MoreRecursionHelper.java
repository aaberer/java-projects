public class MoreRecursionHelper {
    /** Student: Self-Explanation
     * take parameter and check base case to see if it equals 0
     * else return parameters remainder + 10 so it will either be
     * 11 or 10 and multiply by recusive call of parameter / 2
     * to increment recusive loop
     */
    
    public static int toBinary(int toConvert){
        if (toConvert == 0) {
            return 0;
        } else {
            return toConvert % 2 + 10 * toBinary(toConvert / 2);
        }
    }
    
    /** Student: Self-Explanation
     * take parameter and add base case to see if it equals 0
     * then else statement will return total + method(parameter -1)
     * 
     */
    public static int pyramidTotal(int rows) {
        if (rows == 0) {
            return 0;
        } else {
            return rows + pyramidTotal(rows - 1);
        }
    }
    /** Student: Self-Explanation
     * take input string using a for loop for each char in string
     * then add base case to make sure there is chars left in string
     * then recursively call start of that string to end + 1 for each char
     */
    public static void combinations(String str, int index, String currStr) {
        if (index == str.length()) {
            return;
        } 
        System.out.println(currStr);
        for (int i = index + 1; i < str.length(); i++) {
            combinations(str, i, currStr + str.charAt(i));
        }
    }

    public static void main(String[] args){
        System.out.println(toBinary(17));
    }
}