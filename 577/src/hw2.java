import java.util.*;
import java.util.Map.Entry;


public class hw2 {

  public static class Node {
    String data;
    String[] neighbors;

    public Node(String data) {
      this.data = data;
    }


  }

  static int input = 0;

  @SuppressWarnings("unlikely-arg-type")
  public static <T> void readData(int amount, Scanner scanner) {
    Map<Integer, Node> graph = new LinkedHashMap<>();
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();

    String node;
    for (int i = 0; i < amount; i++) {
      node = scanner.next();
      Node placeholder = new Node(node);
      graph.put(i, placeholder);
      if (scanner.nextLine().length() > 0) {
        String line;
        line = scanner.nextLine();
        placeholder.neighbors = line.split("//s");
        for (int j = 0; j < placeholder.neighbors.length; j++) {
          queue.add(placeholder.neighbors[j].toString());
        }
      }
    }
    int counter = 0;
    // Push the current source node
    while (!(queue.isEmpty() & graph.isEmpty())) {
      String currentVertex = queue.poll();
      if (!visited.contains(currentVertex)) {
        System.out.print(currentVertex + " ");
        visited.add(currentVertex);
        for (Entry<Integer, Node> entry : graph.entrySet()) {
          if (entry.getValue().data.equals(currentVertex)) {
            for (String neighbors : entry.getValue().neighbors) {
              queue.add(neighbors);
            }
            break; // Exit the loop once the key is found
          }
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
      int nodes = input;
      input = scanner.nextInt();
      for (int i = 0; i < nodes; i++) {
        readData(input, scanner);
      }
    } else {
      for (int j = 0; j < input; j++) {
        readData(input, scanner);
      }
    }

    scanner.close();

  }
}
