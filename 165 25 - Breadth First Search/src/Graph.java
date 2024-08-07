import java.util.*;

public class Graph {
    ArrayList<List<Integer>> adjList;

    /**
     * Initializes the Graph to have adjacency lists for each node in the graph
     * @param numNodes
     */
    public Graph(int numNodes){
        adjList = new ArrayList<List<Integer>>(numNodes);
        for(int i = 0; i < numNodes; i++){
             adjList.add(new ArrayList<Integer>());
        }
    }

    /**TODO - Complete This Method
     * This method should add a new edge to the graph by getting the adjacency list for node1 and
     * adding the node2 to that list.
     *
     * It should also add the node1 to node2's adjacency list.
     *
     * If either node is not in the graph(if the given node's index is less than 0 or if it's out of bounds for the
     * list throw a NoSuchElementException
     *
     *
     * @param node1 - a node in the graph
     * @param node2 - a node in the graph
     * @throws java.util.NoSuchElementException if the node is not in the graph
     */
    public void addEdge(int node1, int node2){
        if (node1 < 0 || node1 >= adjList.size() || node2 < 0 || node2 >= adjList.size()) { //Out of bounds
            throw new NoSuchElementException();
        }
        adjList.get(node1).add(node2);
        adjList.get(node2).add(node1);
    }

    /**TODO - Complete This Method
     * 1. For a BFS you need a Queue to keep track of the unvisited nodes and a list to keep track of the
     *  visited nodes(Note: you can use a LinkedList like a queue add() and poll())
     * 2. start by adding the startNode to the unvisited queue
     * 3. then while there are still items in the unvisited queue:
     *      pop the node off the front of the queue and store it as the current node
     *
     *      print out "Visited Node " + the index of the node you just popped off the queue
     *
     *      loop through all of the current node's neighbors   
     *
     *      determine which of the neighbors have not been visited and add those neighbors to the unvisited queue
     *
     *      mark the current node as visited by adding it to the visited list
     */
    public void breadthFirst(int startNode){
        Queue<Integer> unVisitQ = new LinkedList<>();
        List<Integer> visitList = new ArrayList<>();
        unVisitQ.add(startNode);
        while (!unVisitQ.isEmpty()) {
            int currentNode = unVisitQ.poll();
            System.out.println("Visited Node " + currentNode);
            for (int neighbor : adjList.get(currentNode)) {
                if (!visitList.contains(neighbor) && !unVisitQ.contains(neighbor)) {
                    unVisitQ.add(neighbor);
                }
            }
            visitList.add(currentNode);
        }  
    }

    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        int i = 0;
        for(List<Integer> l : adjList){
            String list = String.format("Node %d: %s\n", i, l);
            ret.append(list);
            i++;
        }
        return ret.toString();
    }
    public static int getRand(Random r, int numNodes, int i){
        int rand = r.nextInt(numNodes);
        while(rand == i){
            rand = r.nextInt(numNodes);
        }
        return rand;
    }
    public static void main(String[] args){
        /* Testing Graph */
        int numNodes = 10;
        Random r = new Random(2020);
        Graph g = new Graph(numNodes);
        for(int i = 0; i < numNodes; i++){
            g.addEdge(i, getRand(r, numNodes, i));
            g.addEdge(i, getRand(r, numNodes, i));
            g.addEdge(i, getRand(r, numNodes, i));
        }
        System.out.println(g);
        
        /* Testing Exception on addEdge() */
        try{
            g.addEdge(-3, 20);
        }catch(NoSuchElementException e){
            System.out.println("You threw the exception. Yay!");
        }
//        /* Testing BFS */
//        System.out.println("\nBFS 1:");
//        g.breadthFirst(0);
//        System.out.println("\nBFS 2:");
//        g.breadthFirst(6);
//        System.out.println("\nBFS 3:");
//        g.breadthFirst(4);      

    }
}
