// --== CS400 File Header Information ==--
// Name: Daniel Wang
// Email: dwang448@wisc.edu
// Group and Team: G45
// Group TA: Zheyang Xiong
// Lecturer: Florian Heimerl
// Notes to Grader: NONE

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interactive backend program that allows frontend programmer to create a map, get results of a
 * shortest path algorithm from start to end destination, and get overall map statistics
 */
public class Backend implements BackendInterface {

  protected DijkstraGraph<String, Double> graph;

  private ShortestPath path;

  private double weight;

  /**
   * Backend constructor
   */
  public Backend() {
    graph = new DijkstraGraph<>(new PlaceholderMap<>());
  }

  /**
   * Loads data from a dot file located at the specified 'filePath' and inserts the data into an
   * interactive hashmap connected via edges. Nodes and edge weights on each line are read via
   * regex.
   * 
   * @param filePath The path to the dot file to be loaded.
   * @throws IOException If an I/O error occurs while reading the dot file.
   * @return True if load was successful otherwise throws exception for invalid path
   */
  @Override
  public boolean readData(String filePath) throws IOException {
    String line;
    // catch any expected exceptions during the reading of the file in backend
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {

      // Skip title description
      reader.readLine();
      while ((line = reader.readLine()) != null) {
        // Split the line into columns using a comma as the delimiter
        String data = line;

        // Define the regular expression pattern
        String regex = "\"([^\"]+)\"\\s*--\\s*\"([^\"]+)\"\\s*\\[seconds=([0-9.]+)\\];";
        Pattern pattern = Pattern.compile(regex);
        // Create a Matcher object
        Matcher matcher = pattern.matcher(data);

        // Use regex expression on current line
        if (matcher.find()) {
          String location1 = matcher.group(1);
          String location2 = matcher.group(2);
          double time = Double.parseDouble(matcher.group(3));

          // Initialize any nonexisting nodes and edges in graph
          if (!graph.containsNode(location1)) {
            graph.insertNode(location1);

          }
          if (!graph.containsNode(location2)) {
            graph.insertNode(location2);

          }
          if (!graph.containsEdge(location1, location2)) {
            graph.insertEdge(location1, location2, time);
            weight += time;
          }
          if (!graph.containsEdge(location2, location1)) {
            graph.insertEdge(location2, location1, time);
            weight += time;
          }
        }
      }
      reader.close();
      return true;
    } catch (IOException e) {
      throw new IOException("Invalid file path");
    }
  }

  /**
   * Uses Dijkstra's shortest path algorithm to create an object containing a sequence of nodes from
   * the start to the end node, including all intermediate edges
   * 
   * @param start The starting node
   * @param dest  The ending node
   * @return Shortestpath object containing getter methods and data of shortest path
   */
  @Override
  public ShortestPath findShortestPath(String start, String dest) throws NoSuchElementException {
    if (!(graph.edgeCount == 0 && graph.getNodeCount() == 0)) {
      if (start != null && dest != null && this.graph != null) {
        if (!graph.containsNode(start)) {
          throw new NoSuchElementException("Start node not in graph");
        }
        if (!graph.containsNode(dest)) {
          throw new NoSuchElementException("Dest node not in graph");
        }
        path = new ShortestPath(start, dest, this.graph);
        return path;
      }
      throw new NoSuchElementException("Cannot path null node(s)");
    }
    throw new NoSuchElementException("Map does not exist");
  }

  /**
   * Returns three data related to the loaded map: total edges, total nodes, and total weight of
   * edges. Edge and node weights are divided by half to prevent double counting
   *
   * @return String of all three data
   */
  @Override
  public String getGraphStats() throws NoSuchElementException {
    if (!(graph.edgeCount == 0 && graph.getNodeCount() == 0)) {
      return "number of buildings: " + graph.getNodeCount() + "\nnumber of edges: "
          + (graph.getEdgeCount() / 2) + "\ntotal walking time: " + (weight / 2) + " seconds.";
    }
    throw new NoSuchElementException("Map does not exist");
  }


}
