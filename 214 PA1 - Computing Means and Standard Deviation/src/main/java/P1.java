import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;

public class P1 {

  public static ArrayList<String> readInputFileToString(String str){ 
    ArrayList<String> strOutput = new ArrayList<>();
    File fileInput = new File(str);
    try{
      Scanner scnr = new Scanner(fileInput);
      while (scnr.hasNextLine()){
        String[] stringArr = scnr.nextLine().split("\\s+");
        for (String s : stringArr) {
          strOutput.add(s);
        }
      }
      scnr.close();
    } catch (FileNotFoundException ex){
      System.out.println("File Not Found");
      ex.printStackTrace();
    } catch (Exception ex){
      System.out.println("Unknown File Error");
      ex.printStackTrace();
    } 
    return strOutput;
  }

  public static ArrayList<Double> arrayListToDouble(ArrayList<String> str){
    ArrayList<Double> doubleArr = new ArrayList<Double>();
    try {
      for (String s : str){
        if (!s.isEmpty()) {
          doubleArr.add(Double.parseDouble(s));
        }
      }
    } catch (NumberFormatException ex) {
      ex.printStackTrace();
    }
    return doubleArr;
  }
  
  public static void main(String[] args) {
    String fileName = args[0];
    ArrayList<String> fileToString = readInputFileToString(fileName);
    ArrayList<Double> doubleArr = arrayListToDouble(fileToString);
    if (doubleArr.isEmpty()){
      System.out.println("UNDEFINED UNDEFINED");
    } else {
      double mean = MathMean.computeMean(doubleArr);
      if (doubleArr.size() == 1) {
        System.out.println(mean + " UNDEFINED");
      } else {
        double standardDeviation = MathStandardDeviation.computeStandardDeviation(doubleArr);
        System.out.println(mean + " " + standardDeviation);
      }
    } 
  }
}
