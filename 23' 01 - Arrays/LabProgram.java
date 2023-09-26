import java.util.Arrays;
import java.lang.Math;

public class LabProgram {

    public static int[] removeEvens(int [] nums) {
        int [] arr;
        int count = 0;
        for (int i = 0; i < nums.length; ++i){
            if ((nums[i] % 2) == 1 || (Math.abs(nums[i]) % 2) == 1){
                ++count;
            }
        }
        arr = new int[count];
        int count2 = 0;
        for (int i = 0; i < nums.length; ++i){
            if (((nums[i] % 2) == 1) || (Math.abs(nums[i]) % 2) == 1){
                arr[count2] = nums[i];
                ++count2;
            }
        }
        return arr;
    }

    public static void main(String[] args) {

        int [] input = {-1, 0, 4, 4, 4};
        int [] result = removeEvens(input);

        // Helper method Arrays.toString() converts int[] to a String
        System.out.println(Arrays.toString(result)); // Should print [1, 3, 5, 7, 9]
    }
}
