import java.util.*;
import java.io.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CS_214_Project_Tester {

  List<double[]> rankings = List.of(
    new double[]{2, 3, 4},
    new double[]{4, 4, 0},
    new double[]{1, 3, 4},
    new double[]{3, 0, 5}
    );
  List<double[]> centroids = List.of(
    new double[]{2, 3, 4},
    new double[]{1, 1, 1}
  );
  List<List<Integer>> clusters = CS_214_Project.assignToClusters(rankings, centroids);

  @Test
  public void testMain(){
    String[] args = {"input_files/song0", "input_files/rating0", "output_files/ma0", "5", "2"};
    CS_214_Project.main(args);
    File outputFile = new File("output_files/ma0");
    assertTrue(outputFile.exists());
  }

  @Test
  public void testAssignToClustersFirstCol(){
    assertEquals(2, clusters.size());
  }

  @Test
  public void testAssignToClustersSecondCol(){
    assertEquals(3, clusters.get(0).size());
  }

  @Test
  public void testAssignToClustersThirdCol(){
    assertEquals(1, clusters.get(1).size());
  }

  @Test
  public void testFindClosestCluster(){
    double[] ranking = new double[]{3, 0, 5};
    List<double[]> clusterCenters = List.of(
      new double[]{2, 3, 4},
      new double[]{1, 1, 1},
      new double[]{4, 4, 0}
    );
    int closestClusterIndex = CS_214_Project.distanceBetweenClusters(ranking, clusterCenters);
    assertEquals(2, closestClusterIndex);
  }

  @Test
  public void testReadNames(){
    List<String> songNames = CS_214_Project.readNames("input_files/songFileName");
    assertEquals(14, songNames.size());
  }

  @Test
  public void testReadRanks(){
    List<double[]> rankings = CS_214_Project.readRanks("input_files/ratingsFileName");
    assertEquals(14, rankings.size());
    assertEquals(10, rankings.get(0).length);
  }

  @Test
  public void testInitializeCentroids(){
    List<double[]> rankings = CS_214_Project.readRanks("input_files/ratingsFileName");
    List<double[]> centroids = CS_214_Project.initCentroids(rankings, 3);
    assertEquals(3, centroids.size());
  }
}
