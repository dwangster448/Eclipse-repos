import java.util.*;

public class GraphTraversal {
  
    public static void readData(int nodes, Scanner scanner) {
   // Map to store adjacency list for each node
      Map<String, List<String>> graph = new HashMap<>();
      
      // Set to store unique node names in the order they are encountered
      LinkedHashSet<String> nodeOrder = new LinkedHashSet<>();

      // Read the connections and build the graph
      for (int i = 0; i < nodes; i++) {
          String currentNode = scanner.next();
          nodeOrder.add(currentNode);

          // Read neighbors
          while (scanner.hasNext()) {
              String neighbor = scanner.next();
              if (neighbor.equals(currentNode)) {
                  break;  // Stop reading neighbors when the current node is encountered again
              }
              graph.computeIfAbsent(currentNode, k -> new ArrayList<>()).add(neighbor);
          }
      }

      // Perform DFS and print the result based on the input order
      List<String> result = new ArrayList<>();
      for (String node : nodeOrder) {
          if (!result.contains(node)) {
              dfs(node, graph, result);
          }
      }

      // Print the output
      for (String nodeName : result) {
          System.out.print(nodeName + " ");
      }
      System.out.println();
  }

  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of test cases
        int i = scanner.nextInt();
        
        if (scanner.hasNextInt()) {
          for (int t = 0; t < i; t++) {
            int testCases = scanner.nextInt();
            readData(testCases, scanner);
          }
        } else {
          readData(i, scanner);
        }
        
        

        
    }

    private static void dfs(String node, Map<String, List<String>> graph, List<String> result) {
      result.add(node);

      for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
          if (!result.contains(neighbor)) {
              dfs(neighbor, graph, result);
          }
      }
  }
}
