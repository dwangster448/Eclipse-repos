// --== CS400 File Header Information ==--
// Name: Daniel Wang
// Email: dwang448@wisc.edu
// Group and Team: G45
// Group TA: Zheyang Xiong
// Lecturer: Florian Heimerl
// Notes to Grader: NONE

import java.util.LinkedList;
import java.util.List;

/**
 * This class utilizes Dijkstra's shortest path algorithm via DijkstraGraph's method to create
 * requested data according to user's input of start node, destination node, and graph created by
 * from dot file
 */
public class ShortestPath implements ShortestPathInterface {

  public DijkstraGraph<String, Double> graph;

  public String start;

  public String dest;

  /*
   * Construct a new instance of this class that implements Dijkstra's algorithm
   */
  public ShortestPath(String start, String dest, DijkstraGraph<String, Double> graph) {
    this.graph = graph;
    this.start = start;
    this.dest = dest;
  }

  /**
   * Calls shortestPathData from DijkstraGraph to return a linked list of nodes within the shortest
   * path algorithm
   * 
   * @return the linked list of all nodes from start to end
   */
  @Override
  public List<String> getPath() {
    return graph.shortestPathData(start, dest);
  }

  /**
   * Calls shortestPathData from DijkstraGraph to get edge weight between each node in the shortest
   * path.
   * 
   * @return the linked list of all edge weights between the start to end nodes
   */
  @Override
  public List<Double> getWalkTimes() {
    List<Double> time = new LinkedList<>();
    DijkstraGraph<String, Double>.SearchNode node = graph.computeShortestPath(start, dest);
    while (node.predecessor != null) {
      time.add(0, node.cost - node.predecessor.cost);
      node = node.predecessor;

    }
    return time;
  }

  /**
   * Calls the shortestPathCost to retrieve the ending node's cost, which contains the accumulated
   * weight of all edges between the start and end node.
   * 
   * @return the total time of the entire shortest path sequence by retrieving accumulated time from
   *         the end node
   */
  @Override
  public double getTotalTime() {
    return graph.shortestPathCost(start, dest);
  }

}
