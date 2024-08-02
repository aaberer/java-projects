// GraphImplementation.java - supplied code for graph assignment

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GraphImplementation extends GraphAbstract {
    // Main entry point
    public static void main(String[] args) {

      // Instantiate code
        GraphImplementation impl = new GraphImplementation();
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        String startCity = input.nextLine();
        // Read distances chart
        System.out.println("Reading Chart: " + fileName);
        impl.readGraph(fileName);
        System.out.println();

        // Print depth first search
        System.out.println("Depth First Search:");
        impl.depthFirst(startCity);
        System.out.println();
        
        System.out.println("Breadth First Search:");
        impl.breadthFirst(startCity);
        System.out.println();
    
    }
    // Reads mileage chart from CSV file
    public void readGraph(String filename) {
        ArrayList<String> distList = readFile(filename);
        String[] city = distList.get(0).split(",");
        for(int i = 0; i < city.length; ++i){
            String str = city[i].trim();
            if(!str.equals("")){
                cities.add(new GraphNode(city[i].trim()));
            } 
        }
        for (int j = 1; j < city.length; ++j){
            String[] edgeList = distList.get(j).split(",");
            String startingCity = edgeList[0].trim();
            for (int k = 1; k < edgeList.length; k++){
                String node = edgeList[k].trim();
                if(!node.equals("")){
                    String finalCity = cities.get(k-1).name.trim();
                    int distance = Integer.parseInt(edgeList[k].trim());
                    GraphEdge firstEdge = new GraphEdge(getIdx(startingCity),
                    getIdx(finalCity), distance);
                    GraphEdge secondEdge = new GraphEdge(getIdx(finalCity),
                    getIdx(startingCity), distance);
                    cities.get(getIdx(startingCity)).edges.add(firstEdge);
                    cities.get(getIdx(finalCity)).edges.add(secondEdge);
                }
            }
        }
        for(GraphNode node: cities){
            Collections.sort(node.edges);
        }
    }

    public int getIdx(String city){
        String str = city.trim();
        for(int i = 0; i < cities.size(); ++i){
            if(cities.get(i).name.equals(str)){
                return i;
            }
        }
        return -1;
    }

    public void depthFirst(String startCity) {
        ArrayList<Integer> visited = new ArrayList<Integer>();
        depthFirst(getIdx(startCity), visited);
    }
    
    public void depthFirst(int index, ArrayList<Integer> visited) {
        visited.add(index);
        System.out.println("Visited " + cities.get(index).name);
        GraphNode node = cities.get(index);
        ArrayList<GraphEdge> edgeConnections = node.edges;
        for(GraphEdge edge : edgeConnections){
            if(!visited.contains(edge.toIndex)){
                depthFirst(edge.toIndex, visited);
            }
        }
    }

    public void breadthFirst(String startCity) {
        ArrayList<GraphNode> visited = new ArrayList<GraphNode>();
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        GraphNode startingCity = cities.get(getIdx(startCity));
        queue.add(startingCity);
        while(!queue.isEmpty()){
            GraphNode curr = queue.remove();
            System.out.println("Visited " + curr.name);
            visited.add(curr);
            for(GraphEdge edge: curr.edges){   
                if(!visited.contains(cities.get(edge.toIndex)) && !queue.contains(cities.get(edge.toIndex))){
                    queue.add(cities.get(edge.toIndex));
                }
            }
        }
    }
    
    // Helper functions

    /**
     * Reads the contents of file to {@code ArrayList}
     * @param filename the file to read from
     * @return an ArrayList of the contents
     */
    static ArrayList<String> readFile(String filename) {
        ArrayList<String> contents = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty())
                    contents.add(line);
            }
        } catch (IOException e) {
            System.err.println("Cannot read chart: " + filename);
        }
        return contents;
    }
}
