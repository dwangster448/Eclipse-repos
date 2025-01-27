// --== CS400 File Header Information ==--
// Name: Daniel Wang
// Email: dwang448@wisc.edu 
// Group and Team: G45
// Group TA: ZHEYANG XIONG
// Lecturer:  Florian Heimerl

import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This class extends the BaseGraph data structure with additional methods for computing the total
 * cost and list of node data along the shortest path connecting a provided starting to ending
 * nodes. This class makes use of Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
    implements GraphADT<NodeType, EdgeType> {

  /**
   * While searching for the shortest path between two nodes, a SearchNode contains data about one
   * specific path between the start node and another node in the graph. The final node in this path
   * is stored in its node field. The total cost of this path is stored in its cost field. And the
   * predecessor SearchNode within this path is referened by the predecessor field (this field is
   * null within the SearchNode containing the starting node in its node field).
   *
   * SearchNodes are Comparable and are sorted by cost so that the lowest cost SearchNode has the
   * highest priority within a java.util.PriorityQueue.
   */
  protected class SearchNode implements Comparable<SearchNode> {
    public Node node;
    public double cost;
    public SearchNode predecessor;

    public SearchNode(Node node, double cost, SearchNode predecessor) {
      this.node = node;
      this.cost = cost;
      this.predecessor = predecessor;
    }

    public int compareTo(SearchNode other) {
      if (cost > other.cost)
        return +1;
      if (cost < other.cost)
        return -1;
      return 0;
    }
  }

  /**
   * Constructor that sets the map that the graph uses.
   *
   * @param map the map that the graph uses to map a data object to the node object it is stored in
   */
  public DijkstraGraph(MapADT<NodeType, Node> map) {
    super(map);
  }

  /**
   * This helper method creates a network of SearchNodes while computing the shortest path between
   * the provided start and end locations. The SearchNode that is returned by this method is
   * represents the end of the shortest path that is found: it's cost is the cost of that shortest
   * path, and the nodes linked together through predecessor references represent all of the nodes
   * along that shortest path (ordered from end to start).
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return SearchNode for the final end node within the shortest path
   * @throws NoSuchElementException when no path from start to end is found or when either start or
   *                                end data do not correspond to a graph node
   */
  protected SearchNode computeShortestPath(NodeType start, NodeType end) {
    if (nodes.get(start) == null || nodes.get(end) == null) {
      throw new NoSuchElementException("No start node or end node found.");
    }
    PriorityQueue<SearchNode> queue = new PriorityQueue<>();
    PlaceholderMap<NodeType, SearchNode> map = new PlaceholderMap<>();

    // Add the start node to the PriorityQueue with cost 0
    SearchNode startNode = new SearchNode(nodes.get(start), 0, null);
    queue.add(startNode);

    // While the PriorityQueue is not empty
    while (!queue.isEmpty()) {
      // Dequeue the node with the lowest cost
      SearchNode current = queue.remove();

      // If the dequeue node is the end node, return it
      if (current.node.data.equals(end)) {
        return current;
      }
      // if map contains dequeue node
      if (!map.containsKey(current.node.data)) {
        map.put(current.node.data, current);
        
        // for each outgoing edges from dequeue node
        for (Edge edge : current.node.edgesLeaving) {
          Node neighbor = edge.successor;

          // Compute the new cost to a outgoing edge
          double cost = current.cost + edge.data.doubleValue();
         
          // If this edge is not in map
          if (!map.containsKey(neighbor.data)) {
            // Create an edge node with the path from dequeue node
            SearchNode newNeighborNode = new SearchNode(neighbor, cost, current);
            // Add the edge node to PriorityQueue
            queue.add(newNeighborNode);
          }
        }
 
      }
      
    }
    throw new NoSuchElementException("No path found from " + start + " to " + end);
  }

  /**
   * Returns the list of data values from nodes along the shortest path from the node with the
   * provided start value through the node with the provided end value. This list of data values
   * starts with the start value, ends with the end value, and contains intermediary values in the
   * order they are encountered while traversing this shorteset path. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return list of data item from node along this shortest path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    SearchNode endNode = computeShortestPath(start, end);
    // Build the list of data values along the path
    LinkedList<NodeType> pathData = new LinkedList<>();
    while (endNode != null) {
      pathData.addFirst(endNode.node.data);
      endNode = endNode.predecessor;
    }
    return pathData;
  }

  /**
   * Returns the cost of the path (sum over edge weights) of the shortest path freom the node
   * containing the start data to the node containing the end data. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return the cost of the shortest path between these nodes
   */
  public double shortestPathCost(NodeType start, NodeType end) {
    // Compute the shortest path using Dijkstra's algorithm
    SearchNode endNode = computeShortestPath(start, end);
    return endNode.cost;
  }

  /*
   * Checks if the hand computation that was traced through in lecture
   * matches the implementation coded.
   */
  @Test
  public void testLectureShortestPath() {
    DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
    graph.insertNode("G");
    graph.insertNode("L");
    graph.insertEdge("G", "L", 7);

    graph.insertNode("D");
    graph.insertEdge("D", "G", 2);

    graph.insertNode("F");
    graph.insertEdge("F", "G", 9);

    graph.insertNode("I");
    graph.insertEdge("I", "L", 5);
    graph.insertEdge("I", "D", 1);

    graph.insertNode("H");
    graph.insertEdge("I", "H", 2);
    graph.insertEdge("H", "I", 2);

    graph.insertNode("A");
    graph.insertEdge("D", "A", 7);
    graph.insertEdge("A", "H", 8);

    graph.insertNode("B");
    graph.insertEdge("A", "B", 1);
    graph.insertEdge("H", "B", 6);

    graph.insertNode("M");
    graph.insertEdge("B", "M", 3);
    graph.insertEdge("A", "M", 5);
    graph.insertEdge("M", "F", 4);

    graph.insertNode("E");
    graph.insertEdge("M", "E", 14);

    Assertions.assertEquals(17, graph.shortestPathCost("D", "I"));
  }

  /*
   * Tests the cost and sequence of data along the shortest path between
   * a different start and end node.
   */
  @Test
  public void testAlternativeShortestPath() {
    DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
    graph.insertNode("G");
    graph.insertNode("L");
    graph.insertEdge("G", "L", 7);

    graph.insertNode("D");
    graph.insertEdge("D", "G", 2);

    graph.insertNode("F");
    graph.insertEdge("F", "G", 9);

    graph.insertNode("I");
    graph.insertEdge("I", "L", 5);
    graph.insertEdge("I", "D", 1);

    graph.insertNode("H");
    graph.insertEdge("I", "H", 2);
    graph.insertEdge("H", "I", 2);

    graph.insertNode("A");
    graph.insertEdge("D", "A", 7);
    graph.insertEdge("A", "H", 8);

    graph.insertNode("B");
    graph.insertEdge("A", "B", 1);
    graph.insertEdge("H", "B", 6);

    graph.insertNode("M");
    graph.insertEdge("B", "M", 3);
    graph.insertEdge("A", "M", 5);
    graph.insertEdge("M", "F", 4);

    graph.insertNode("E");
    graph.insertEdge("M", "E", 14);

    Assertions.assertEquals(11, graph.shortestPathCost("D", "M"));
    Assertions.assertEquals("[D, A, B, M]", graph.shortestPathData("D", "M").toString());
  }

  /*
   * Tests the behavior of the implementation when the nodes that we are searching for a path
   * between existing nodes in the graph, but there is no sequence of directed edges that
   * connects them from the start to the end.
   */
  @Test
  public void testInvalidShortestPath() {
    DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
    graph.insertNode("G");
    graph.insertNode("L");
    graph.insertEdge("G", "L", 7);

    graph.insertNode("D");
    graph.insertEdge("D", "G", 2);

    graph.insertNode("F");
    graph.insertEdge("F", "G", 9);

    graph.insertNode("I");
    graph.insertEdge("I", "L", 5);
    graph.insertEdge("I", "D", 1);

    graph.insertNode("H");
    graph.insertEdge("I", "H", 2);
    graph.insertEdge("H", "I", 2);

    graph.insertNode("A");
    graph.insertEdge("D", "A", 7);
    graph.insertEdge("A", "H", 8);

    graph.insertNode("B");
    graph.insertEdge("A", "B", 1);
    graph.insertEdge("H", "B", 6);

    graph.insertNode("M");
    graph.insertEdge("B", "M", 3);
    graph.insertEdge("A", "M", 5);
    graph.insertEdge("M", "F", 4);

    graph.insertNode("E");
    graph.insertEdge("M", "E", 14);

    Assertions.assertThrows(NoSuchElementException.class,
        () -> graph.computeShortestPath("E", "G"));
  }

}
