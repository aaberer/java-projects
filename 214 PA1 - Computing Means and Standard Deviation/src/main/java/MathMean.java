import java.util.ArrayList;
public class MathMean {
    public static double findSum(ArrayList<Double> arr){
        double count = 0;
        for (double x : arr){
            count += x;
        }
        return count;
    }
    public static double findTotal(ArrayList<Double> arr){
        double count = 0;
        for (double x : arr){
            count += 1;
        }
        return count;
    }
    public static double findMean(double sum, double total){
        return sum / total;
    }
    public static double computeMean(ArrayList<Double> arr){
        double sum = findSum(arr);
        double total = findTotal(arr);
        double mean = findMean(sum, total);
        return mean;
    }
}