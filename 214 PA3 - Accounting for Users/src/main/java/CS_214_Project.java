import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CS_214_Project {
  public static ArrayList<String> name_list = new ArrayList<String>();
  public static ArrayList<ArrayList<Double>> count_list = new ArrayList<ArrayList<Double>>();
  public static void main(String[] args) {
    if(args.length < 3){
      System.err.println("Error : To Few Arguments \nUsage: ./PA2 \"Song_Names_File\" \"Ratings_File\" \"Output_File\"");
      System.exit(-1);
    }
    Read_Names(args[0]);
    Read_Count(args[1]);
    removeUncooperativeUsers();
    normalizeRankings();
    Build_Output_File(args[2]);
  }

  private static void Read_Names(String name){
    try{
      File file = new File(name);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        String line = scanner.nextLine();
        if(line.isEmpty()){
          System.err.println("Error : Song File Missing a Title");
          System.exit(-1);
        }
        name_list.add(line);
      }
      scanner.close();
    } catch(FileNotFoundException file){
      System.err.println("Error : Song File " + file.getMessage());
      System.exit(-1);
    }
  }

  private static void Read_Count(String name){
    try{
      File file = new File(name);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        splitCount(scanner.nextLine());
      }
      scanner.close();
    } catch(FileNotFoundException file){
      System.err.println("Error : Counts file " + file.getMessage());
      System.exit(-1);
    }
  }

  private static void splitCount(String line){
    String[] split_string = line.split(" ");
    ArrayList<Double> double_storage = new ArrayList<Double>();
    for(String string : split_string){
      try{
        double_storage.add(Double.parseDouble(string));
      }catch(NumberFormatException exception){
        System.err.println("Error: " + exception.getMessage() + " " + exception.fillInStackTrace());
        System.exit(-1);
      }
    }
    count_list.add(double_storage);
  }

  private static void Build_Output_File(String name){
    if(count_list.size() != name_list.size()){
      System.err.println("Error : Unequal Number of Songs and Rattings " + count_list.size() +  " Vs " + name_list.size());
      System.exit(-1);
    }
    try{
      FileWriter file = new FileWriter(name);
      for(int i = 0; i < name_list.size(); i++){
        ArrayList<Double> userRatings = count_list.get(i);
        double mean = Double.parseDouble(calc_mean(userRatings));
        double sdv = Double.parseDouble(calc_sdv(userRatings));
        file.write(name_list.get(i)+ " " + mean + " " + sdv + "\n");
      }
      file.close();
    }catch(IOException error){
      System.err.println("Error : " + error.getMessage());
    }
  }

  private static void removeUncooperativeUsers() {
    ArrayList<ArrayList<Double>> filteredCountList = new ArrayList<>();
    for (ArrayList<Double> userRatings : count_list){
      boolean allZero = true;
      boolean allSame = true;
      double firstRating = userRatings.get(0);
      for (double rating : userRatings){
        if (rating != 0){allZero = false;}
        if (rating != firstRating){allSame = false;}
      }
      if (!allZero && !allSame){filteredCountList.add(userRatings);}
    }
    count_list = filteredCountList;
  }

  private static void normalizeRankings(){
    for (int i = 0; i < count_list.size(); ++i){
      ArrayList<Double> userRatings = count_list.get(i);
      if (isUncooperative(userRatings)){
        count_list.set(i, null);
      } else {
        double mean = Double.parseDouble(calc_mean(userRatings));
        double sdv = Double.parseDouble(calc_sdv(userRatings));
        for (int j = 0; j < userRatings.size(); ++j){
          double rating = userRatings.get(j);
          if (rating != 0){
            double normalizedRank = (rating - mean) / sdv;
            userRatings.set(j, normalizedRank);
          }
        }
      }
    }
  }

  private static boolean isUncooperative(ArrayList<Double> ratings) {
    if (ratings == null || ratings.isEmpty()){return true;}
    double firstRating = ratings.get(0);
    for (double rating : ratings){
      if (rating != 0 && rating != firstRating){return false;}
    }
    return true;
  }

  private static String calc_mean(ArrayList<Double> list){
    if(list.size() < 1){return "UNDEFINED";}
    Double sum = 0.0;
    for(Double d : list){sum += d;}
    return Double.toString(sum/list.size());
  }
  private static String calc_sdv(ArrayList<Double> list){
    if(list.size() <= 1){return "UNDEFINED";}
    Double mean = Double.parseDouble(calc_mean(list));
    Double sum = 0.0;
    for(Double l : list){sum += (l-mean)*(l-mean);}
    return Double.toString(Math.sqrt(sum/(list.size()-1)));
  }
}