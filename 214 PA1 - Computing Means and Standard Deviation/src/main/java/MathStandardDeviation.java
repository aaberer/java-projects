import java.lang.Math;
import java.util.ArrayList;
public class MathStandardDeviation {
    public static double standardDeviation(ArrayList<Double> arr, double mean, double count){
        double standardDeviationNumerator = 0;
        double standardDeviation = 0;
        for (double x : arr){
            standardDeviationNumerator += Math.pow((x - mean),2);
        }
        standardDeviation = Math.sqrt(standardDeviationNumerator / (count - 1));
        return standardDeviation;
    }
    public static double countDoubleArray(ArrayList<Double> arr){
        double count = 0.0;
        for (double x : arr){
            ++count;
        }
        return count;
    }
    public static double computeStandardDeviation(ArrayList<Double> arr){
        double mean = MathMean.computeMean(arr);
        double count = countDoubleArray(arr);
        double standardDeviation = standardDeviation(arr, mean, count);
        return standardDeviation;
    }
}