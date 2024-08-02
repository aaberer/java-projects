import java.io.*;
import java.util.*;

public class CS_214_Project {
  public static void main(String[] args) {
    if (args.length != 5){
      System.err.println("Error: Incorrect Arguments \nExpected: \"input_files/songFileName\" \"input_files/ratingsFileName\"\" output_files/songOut\" K N");
      System.exit(-1);
    }

    String songName = args[0];
    String ratingFile = args[1];
    String outputFile = args[2];
    int K = Integer.parseInt(args[3]);
    int N = Integer.parseInt(args[4]);

    if ((K <= 0) || (N <= 0) || (N > K)){
      System.err.println("Error: Incorrect Values for K and N. \nK and N must be positive integers, with N <= K");
      System.exit(-1);
    }

    try {
      List<String> songNames = readNames(songName);
      List<double[]> rankings = readRanks(ratingFile);
      List<double[]> centroid = initCentroids(rankings, K);
      List<List<Integer>> clusters = new ArrayList<>();
      for (int iteration = 0; iteration < 10; iteration++){ // 10 iterations
        clusters = assignToClusters(rankings, centroid);
        centroid = updateCentroids(rankings, clusters);
      }
      writeClustersToOutput(clusters, songNames, N, outputFile); 
    } catch (Exception ex){
      System.err.println("Error: Exception reading or writing files: " + ex.getMessage());
      System.exit(-1);
    }
  }

  public static List<String> readNames(String fileName){
    List<String> songNames = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) songNames.add(line.trim());
    } catch (IOException ex) {
      System.err.println("Error: IOException reading song file: " + ex.getMessage());
      System.exit(-1);
    } catch (Exception ex) {
      System.err.println("Error: Exception reading song file: " + ex.getMessage());
      System.exit(-1);
    }
    return songNames;
  }

  public static List<double[]> readRanks(String fileName){
    List<double[]> rankings = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
      String line;
      while ((line = br.readLine()) != null){
        String[] parts = line.trim().split(" ");
        double[] ranks = new double[parts.length];
        for (int i = 0; i < parts.length; ++i) ranks[i] = Double.parseDouble(parts[i]);
        rankings.add(ranks);
      }
    } catch (IOException ex){
      System.err.println("Error: IOException reading ranking file: " + ex.getMessage());
      System.exit(-1);
    } catch (NumberFormatException ex){
      System.err.println("Error: NumberFormatException reading ranking file: " + ex.getMessage());
      System.exit(-1);
    } catch (Exception ex) {
      System.err.println("Error: Exception reading ranking file: " + ex.getMessage());
      System.exit(-1);
    }
    return rankings;
  }

  public static List<double[]> initCentroids(List<double[]> rankings, int K){
    List<double[]> centroid = new ArrayList<>();
    for (int i = 0; (i < K) && (i < rankings.size()); ++i) centroid.add(rankings.get(i));
    return centroid;
  }

  public static List<List<Integer>> assignToClusters(List<double[]> rankings, List<double[]> centroids){
    List<List<Integer>> clusters = new ArrayList<>();
    for (int i = 0; i < centroids.size(); ++i) clusters.add(new ArrayList<>());
    for (int j = 0; j < rankings.size(); ++j){
      int closestClusterIndex = distanceBetweenClusters(rankings.get(j), centroids);
      clusters.get(closestClusterIndex).add(j);
    }
    return clusters;
  }

  public static int distanceBetweenClusters(double[] ranking, List<double[]> centroids){
    double minDistance = Double.MAX_VALUE;
    int closestClusterIndex = -1;
    for (int i = 0; i < centroids.size(); ++i){
      double distance = calculateDistance(ranking, centroids.get(i));
      if (distance < minDistance){
        minDistance = distance;
        closestClusterIndex = i;
      }
    }
    return closestClusterIndex;
  }

  public static List<double[]> updateCentroids(List<double[]> rankings, List<List<Integer>> clusters){
    List<double[]> newCentroids = new ArrayList<>();
    for (List<Integer> cluster : clusters){
      double[] avgRanking = calculateAverageRanking(rankings, cluster);
      newCentroids.add(avgRanking);
    }
    return newCentroids;
  }

  public static double calculateDistance(double[] ranking1, double[] ranking2){
    double sum = 0;
    for (int i = 0; i < ranking1.length; ++i) sum += Math.pow(ranking1[i] - ranking2[i], 2);
    return Math.sqrt(sum);
  }

  public static double[] calculateAverageRanking(List<double[]> rankings, List<Integer> cluster){
    double[] avgRanking = new double[rankings.get(0).length];
    for (int idx : cluster){
      double[] ranking = rankings.get(idx);
      for (int i = 0; i < avgRanking.length; ++i) avgRanking[i] += ranking[i];
    }
    for (int i = 0; i < avgRanking.length; ++i) avgRanking[i] /= cluster.size();
    return avgRanking;
  }

  public static void writeClustersToOutput(List<List<Integer>> clusters, List<String> songNames, int N, String outputFile){
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
      for (int idx : clusters.get(N - 1)) writer.write(songNames.get(idx) + "\n");
    } catch (IOException ex) {
      System.err.println("Error: IOException writing to output file: " + ex.getMessage());
      System.exit(-1);
    } catch (Exception ex) {
      System.err.println("Error: Exception writing to output file: " + ex.getMessage());
      System.exit(-1);
    }
  }
}
