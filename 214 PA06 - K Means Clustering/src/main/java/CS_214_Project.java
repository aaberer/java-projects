import java.io.*;
import java.util.*;

public class CS_214_Project {

    public static ArrayList<String> nameList = new ArrayList<>();
    public static Double[][] matrixNormalizedRatings;
    public static Double[][] matrixUserRating;
    public static Double[][] matrixFilteredUsers;
    public static Double[][] matrixUserSimilarity;
    public static Double[][] matrixOutput;
    public static Double[][][] clusters;
    public static int[] clusterAssignments;

    public static void main(String[] args){
        // long start = System.currentTimeMillis(); 
        // if (args.length != 5){
        //     System.err.println("Error: Incorrect Arguments \nExpected: \"input_files/songFileName\" \"input_files/ratingsFileName\"\" output_files/songOut\" K N");
        //     System.exit(0);
        // }
        // String songName = args[0]; // argv[1] contains the name of a file containing song names
        // String ratingFile = args[1]; // argv[2] contains the name of a file with rankings
        // String outputFile = args[2]; // argv[3] is the name of the output file
        // int numClusters = Integer.parseInt(args[3]); // argv[4] is K (an integer), the number of clusters
        // int clusterOfInterest = Integer.parseInt(args[4]); // argv[5] is N (an integer), the cluster of interest

        // FOR TESTING
        String songName = "input_files/newSong0";
        String ratingFile = "input_files/rating0";
        String outputFile = "output_files/master_out0";
        int numClusters = 5;
        int clusterOfInterest = 2;

        readNames(songName);
        addMatrixToUser(readCount(ratingFile));
        setUserMatrix();
        kMeans(numClusters, clusterOfInterest, outputFile);
        buildUserFile(outputFile);

        // long end = System.currentTimeMillis();
        // System.out.println(end - start);
    }

    public static void readNames(String name){
        // System.out.println("Reading Song Names");
        try {
            File file = new File(name);
            try (Scanner scnr = new Scanner(file)){
                if (file.length() == 0){
                    System.err.println("Error: Song File is Blank");
                    System.exit(-1);
                }
                while (scnr.hasNextLine()){
                    String line = scnr.nextLine();
                    if (line.isEmpty()){
                        System.err.println("Error: Song File Missing a Title");
                        System.exit(-1); 
                    }
                    nameList.add(line);
                }
                scnr.close();
            }
        } catch (Exception ex){
            System.err.println("Error: Reading Song Name File - " + ex.getMessage());
        }
    }

    public static List<Double[]> readCount(String name){
        // System.out.println("Reading Count");
        List<Double[]> doubleStorage = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(name))){
            if (!br.ready()){
                System.err.println("Error: Ratings File blank");
                System.exit(-1);
            } else {
                String parseLine;
                while ((parseLine = br.readLine()) != null){
                    String[] parsedString = parseLine.trim().split("\\s+");
                    Double[] valueRow = new Double[parsedString.length];
                    try {
                        for (int i = 0; i < parsedString.length; ++i){
                            Double values = Double.valueOf(parsedString[i]);
                            if (values > 5.0 || values < 0.0){
                                System.err.println("Error: Ratings out of Range");
                                System.exit(-1);
                            }
                            if (values == 0.0){valueRow[i] = Double.NaN;
                            } else {valueRow[i] = values;}
                        }
                        doubleStorage.add(valueRow);
                    } catch (Exception ex){
                        System.err.println("Error: Missing User Ratings - " + ex.getMessage());
                        System.exit(-1);
                    }
                }
            }
        } catch (Exception ex){
            System.err.println("Error: Ratings File Problem - " + ex.getMessage());
            System.exit(-1);
        }
        return doubleStorage;
    }

    public static void addMatrixToUser(List<Double[]> rows){
        // System.out.println("Adding Matrix to User");
        int rowSize = rows.size();
        Double[][] ratings = new Double[rowSize][];
        for (int i = 0; i < rowSize; ++i){
            ratings[i] = rows.get(i);
        }
        checkUserValidRating(ratings);
    }

    public static void checkUserValidRating(Double[][] ratings){
        // System.out.println("Checking User Valid Rating");
        if (User.matrixSizeCheck(ratings)){
            System.out.println("Error: Row + Column Unequal Lengths");
            System.exit(-1);
        }
        matrixFilteredUsers = User.filterUncooperativeUser(ratings);
    }

    public static void setUserMatrix(){ // init
        // System.out.println("Setting User Matrix");
        matrixUserRating = new Double[matrixFilteredUsers.length][matrixFilteredUsers[0].length];
        matrixNormalizedRatings = User.normalizeUser(matrixFilteredUsers);
        matrixUserSimilarity = Calculation.calcUserSimilarity(matrixNormalizedRatings);
        matrixOutput = User.confirmMatrixEntries(matrixFilteredUsers, matrixNormalizedRatings, matrixUserSimilarity);
    }

    public static void kMeans(int numClusters, int clusterOfInterest, String outputFile){
        clusters = new Double[numClusters][][];
        clusterAssignments = new int[matrixUserRating.length];
        for (int i = 0; i < numClusters; ++i){
            clusters[i] = new Double[][] {matrixUserRating[i]};
        }
        for (int j = 0; j < 10; ++j){
            for (int k = 0; k < matrixUserRating.length; ++k){
                double minDistance = Double.MAX_VALUE;
                for (int l = 0; l < numClusters; ++l){
                    if ((matrixUserRating[k] != null && clusters[l][0] != null) && (Arrays.stream(matrixUserRating[k]).noneMatch(Objects::isNull)) && (Arrays.stream(clusters[l][0]).noneMatch(Objects::isNull))){
                        double distance = Calculation.distanceBetweenRankings(matrixUserRating[k], clusters[l][0]);
                        if (distance < minDistance){
                            minDistance = distance;
                            clusterAssignments[k] = l;
                        }
                    }
                }
            }
            for (int m = 0; m < numClusters; ++m){
                List<Double[]> clusterSongs = new ArrayList<>();
                for (int n = 0; n < clusterAssignments.length; ++n){
                    if (clusterAssignments[n] == m && matrixUserRating[n] != null){
                        clusterSongs.add(matrixUserRating[n]);
                    }
                }
                if (!clusterSongs.isEmpty() && clusterSongs.stream().noneMatch(Objects::isNull)){
                    clusters[m][0] = Calculation.averageArrayRanking(clusterSongs);
                }
            }
        }
        clusterToOutput(outputFile, numClusters, clusterOfInterest);
    }
    
    public static void clusterToOutput(String outputFile, int numClusters, int clusterOfInterest){
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))){
            for (int i = 0; i < clusterAssignments.length; ++i){
                if (clusterAssignments[i] == clusterOfInterest - 1){
                    writer.println(nameList.get(i));
                }
            }
        } catch (IOException ex){
            System.err.println("Error: Cluster to output file - " + ex.getMessage());
            System.exit(-1); 
        }
    }

    public static int[][] matrixOutput(Double[][] matrix){
        // System.out.println("Matrix Output");
        int[][] returnMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i){
            for (int j = 0; j < matrix[0].length; ++j){
                returnMatrix[i][j] = matrix[i][j].intValue();
            }
        }
        return returnMatrix;
    }

    public static void buildUserFile(String name){
        // System.out.println("Building User File");
        if (nameList.isEmpty() || matrixUserRating.length < 1){
            System.err.println("Error: Blank files");
            System.exit(-1); 
        }
        if (nameList.size() != matrixUserRating.length){
            System.err.println("Error: Song file and rating file are dif");
            System.exit(-1);
        }
        try {
            FileWriter file = new FileWriter(name);
            int[][] matrixUser = matrixOutput(matrixOutput);
            int rows = matrixUser.length;
            for (int i = 0; i < rows; ++i){
                int[] row = matrixUser[i];
                for (int in : row){
                    file.write(String.format("%d", in) + " ");
                }
                if (i < rows - 1){file.write("\n");}
            }
            file.close();
        } catch (IOException ex){
            System.err.println("Error: Build user file IOException - " + ex.getMessage());
            System.exit(-1);
        } catch (Exception ex){
            System.err.println("Error: Build user file - " + ex.getMessage());
            System.exit(-1);
        }
    }
}