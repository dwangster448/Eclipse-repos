import java.util.*;
import java.util.Map.Entry;
import hw2.Node;

public class thehw2 {

  public int instances;
  public static int input;


  public static class Node {
    public String data;
    private String[] adjacencyList;

    String getData() {
      return this.data;
      
    }

    @SuppressWarnings("unchecked")
    public Node(String data, String[] strings) {
      this.data = data;
        this.adjacencyList = strings;
    }
    
    

  }


  public static void DFS(int vertices, Scanner scanner) {
    Map<Integer, Node> graph = new HashMap<>();
    
    String node;
    for (int i = 0; i < vertices; i++) {
      node = scanner.next();
      if (scanner.nextLine().length() > 0) {
        graph.put(i, new Node(node, scanner.nextLine().split("//s")));
      } else {
        graph.put(i, new Node(node, null));
      }
    }
    // Create a stack for DFS
    Stack<String> stack = new Stack<>();

    // Mark all vertices as not visited
    Set<String> visited = new HashSet<>();

    // Push the current source node
    stack.push(graph.get(0).getData());

    while (!stack.isEmpty()) {
      // Pop a vertex from stack and print it
      String currentVertex = stack.pop();
      if (!visited.contains(currentVertex)) {
        System.out.print(currentVertex + " ");
        visited.add(currentVertex);
      }
      // Get all adjacent vertices of the popped vertex
      // If the adjacent vertex is not visited, push it to the stack
      Integer toFind = 100;
      for (Entry<Integer, Node> entry : graph.entrySet()) {
        if (entry.getValue().getData().equals(currentVertex)) {
            toFind = entry.getKey();
            break; // Exit the loop once the key is found
        }
      }
      
      String[] neighbors = graph.get(toFind).adjacencyList;
      for (String neighbor : neighbors) {
          if (!visited.contains(neighbor)) {
              stack.push(neighbor);
          }
      }
    }
    for (String neighbor : visited) {
      System.out.print(neighbor + " ");
    }
    System.out.println("");
  }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    input = scanner.nextInt();

    if (scanner.hasNextInt()) {
      int instances = input;
      input = scanner.nextInt();
      for (int i = 0; i < instances; i++) {
        DFS(input, scanner);
      }
    } else {
      for (int j = 0; j < input; j++) {
        DFS(input, scanner);
      }
    }

  }
}
