/* Graph class
* For cs165 at CSU
*
* A basic graph which has a depth first search print method. It uses
* link references to keep track of edges.
* */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Graph<E extends Comparable<E>> extends GraphAbstract<E> {
    public Graph() {
        nodes = new ArrayList<GraphNode>();
    }

    /* addEdge
    * Params: data1 & data2, both data items to be added and connected
    * If the either of the data items are not in the nodes ArrayList,
    * add them as new nodes. Otherwise, find the nodes in the ArrayList so
    * you can make them point to each other. Implement so there are no duplicates
    * added to either of the nodes neighbors ArrayList.
    * TODO: implement this method.
    * */
    @Override
    public void addEdge(E data1, E data2) {
    	GraphNode node1 = null, node2 = null;
        for (GraphNode node : nodes) {
            if (node.data.equals(data1)) node1 = node;
            if (node.data.equals(data2)) node2 = node;
        }
        if (node1 == null) {
            node1 = new GraphNode(data1);
            nodes.add(node1);
        }
        if (node2 == null) {
            node2 = new GraphNode(data2);
            nodes.add(node2);
        }
        if (!node1.neighbors.contains(node2)) node1.neighbors.add(node2);
        if (!node2.neighbors.contains(node1)) node2.neighbors.add(node1);
    }

    /* depthFirst
     * Param: startNode, the node to start the traversal at
     *
     * First, find the startNode based off of startItem (hint: indexOf())
     * I recommend having an ArrayList of GraphNodes called visisted to keep
     * track of the nodes you've visited.
     *
     * Prints all of the nodes in the graph in depth first order (with a space between nodes)
     * TODO: implement this method.
     * */
    @Override
    public void depthFirst(E startItem) {
    	ArrayList<GraphNode> visited = new ArrayList<>();
        int startIdx = nodes.indexOf(new GraphNode(startItem));
        if (startIdx != -1){
            GraphNode startNode = nodes.get(startIdx);
            depthFirst(startNode, visited);
        }
    }

    // Recursive helper method for depthFirst
    private void depthFirst(GraphNode curr, ArrayList<GraphNode> visited) {
    	visited.add(curr);
        System.out.print(curr.data + " ");
        for (GraphNode next : curr.neighbors) {
            if (!visited.contains(next)) depthFirst(next, visited);
        }
    } 
}
