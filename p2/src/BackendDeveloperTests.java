import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import java.util.Scanner;

public class BackendDeveloperTests {

  /*
   * Tests backend properly builds directed graph based on campus.dot
   */
  @Test
  public void testReadData() {
    Backend backend = new Backend();
    try {
      backend.readData("campus.dot");
    } catch (IOException e) {
      e.printStackTrace();
      Assertions.fail();
    }

    // Verify that the edges leaving Memorial Union matches the arithmetic count in the dot file
    Assertions.assertFalse(!(backend.graph.nodes.get("Memorial Union").edgesLeaving.size() == 5));

    // Verify that all edges were built using Memorial Union as a test location
    Assertions.assertFalse(!(backend.graph.containsEdge("Memorial Union", "Science Hall")));

    Assertions.assertFalse(!(backend.graph.containsEdge("Memorial Union", "Brat Stand")));

    Assertions.assertFalse(!(backend.graph.containsEdge("Memorial Union", "Helen C White Hall")));

    Assertions.assertFalse(!(backend.graph.containsEdge("Memorial Union", "Radio Hall")));

    Assertions.assertFalse(
        !(backend.graph.containsEdge("Memorial Union", "Wisconsin State Historical Society")));

  }


  /*
   * Expects shortestPath to return correct results, will use simple three node path in DOT file All
   * three methods in ShortestPath are expected to return correct reuslt using this node path
   */
  @Test
  public void testShortestPath() {
    Backend backend = new Backend();
    try {
      backend.readData("campus.dot");
    } catch (IOException e) {
      e.printStackTrace();
      Assertions.fail();
    }

    // test getPath() method to expected list
    List<String> expected = new LinkedList<String>();
    expected.add("Memorial Union");
    expected.add("Radio Hall");
    expected.add("North Hall");
    List<String> result = backend.findShortestPath("Memorial Union", "North Hall").getPath();
    for (String node : result) {
      if (!expected.contains(node)) {
        Assertions.fail();
      }
    }

    // test getWalkTimes() method to expected list
    List<Double> expectedTime = new LinkedList<Double>();
    expectedTime.add(176.7);
    expectedTime.add(190.40000000000003);
    List<Double> time = backend.findShortestPath("Memorial Union", "North Hall").getWalkTimes();
    for (double node : time) {
      if (!expectedTime.contains(node)) {
        Assertions.fail();
      }
    }

    // test getTotalTime() method to expected total time
    Assertions.assertEquals(367.1,
        backend.findShortestPath("Memorial Union", "North Hall").getTotalTime());
  }

  /*
   * Expects NoSuchElementException when inputting invalid inputs into findShortestPath() Inputs
   * test at start & destination nodes are null nodes and nonexistent nodes
   */
  @Test
  public void testInvalidShortestPath() {
    Backend backend = new Backend();

    // Test for start node being null
    Assertions.assertThrows(Exception.class, () -> {
      backend.readData("campus.dot");
      backend.findShortestPath(null, "Plant Sciences");
    });

    // Test for destination node being null
    Assertions.assertThrows(Exception.class, () -> {
      backend.readData("campus.dot");
      backend.findShortestPath("Memorial Union", null);
    });
    // Test for destination node being null
    Assertions.assertThrows(Exception.class, () -> {
      backend.readData("campus.dot");
      backend.findShortestPath("Memorial Union", null);
    });

    // Test for start node not in graph
    Assertions.assertThrows(Exception.class, () -> {
      backend.readData("campus.dot");
      backend.findShortestPath("Nitty Gritty", "Plant Sciences");
    });

    // Test for destination node not in graph
    Assertions.assertThrows(Exception.class, () -> {
      backend.readData("campus.dot");
      backend.findShortestPath("Memorial Union", "Nitty Gritty");
    });
  }

  /*
   * Expects backend to produce a string of all data in graph after reading a file
   */
  @Test
  public void testGraphStats() {
    Backend backend = new Backend();
    try {
      backend.readData("campus.dot");
      Assertions.assertEquals("number of buildings: 160\n" + "number of edges: 508\n"
          + "total walking time: 76604.99999999985 seconds.", backend.getGraphStats());
    } catch (IOException e) {
      e.printStackTrace();
      Assertions.fail();
    }

  }

  /*
   * Expects that program to handle findShortestPath() and getGraphStats() when readFile() has not
   * been called
   */
  @Test
  public void testPathBeforeReadFile() {
    Backend backend = new Backend();

    // Calling findShortestPath() before readFile()
    Assertions.assertThrows(NoSuchElementException.class, () -> {
      backend.findShortestPath("Memorial Union", "Plant Sciences");
    });

    // Calling getGraphStats() before readFile()
    Assertions.assertThrows(NoSuchElementException.class, () -> {
      backend.getGraphStats();
    });
  }

  /*
   * Expects frontend class to output string of map statistics before and after map is created
   */
  @Test
  public void testIntegrationLoadStats() {
    try {
      // 1. Create a new TextUITester object for each test, and
      // pass the text that you'd like to simulate a user typing as only argument.
      TextUITester tester = new TextUITester("shortestPath\nMemorial Union3\nNorth Hall\nexit\n");
      // 2. Create a Scanner, and run then code that you want to test here:
      // (this code should read from System.in and write to System.out)
      Scanner sc = new Scanner(System.in);
      Backend backend = new Backend();
      Frontend frontend = new Frontend(sc, backend);
      frontend.startLoop();
      Assertions.fail();
    } catch (Exception e) {
      // Expects frontend to catch NoSuchElementExceptions when calling computeShortestPath()
      // before readFile
      System.out.println("Successful catch");
    }
    try {
      // 1. Create a new TextUITester object for each test, and
      // pass the text that you'd like to simulate a user typing as only argument.
      TextUITester tester = new TextUITester("stats\nexit\n");
      // 2. Create a Scanner, and run then code that you want to test here:
      // (this code should read from System.in and write to System.out)
      Scanner sc = new Scanner(System.in);
      Backend backend = new Backend();
      Frontend frontend = new Frontend(sc, backend);
      frontend.startLoop();
      Assertions.fail();
    } catch (Exception e) {
      // Expects frontend to catch NoSuchElementException when calling listStatistics()
      // before readFile()
      System.out.println("Successful catch");
    }
    try {
      // 1. Create a new TextUITester object for each test, and
      // pass the text that you'd like to simulate a user typing as only argument.
      TextUITester tester = new TextUITester("load\nexit\n");
      // 2. Create a Scanner, and run then code that you want to test here:
      // (this code should read from System.in and write to System.out)
      Scanner sc = new Scanner(System.in);
      Backend backend = new Backend();
      Frontend frontend = new Frontend(sc, backend);
      frontend.startLoop();
      Assertions.fail();
    } catch (Exception e) {
      // Expects frontend to properly handle invalid file paths
      System.out.println("Successful catch");
    }

    try {
      // 1. Create a new TextUITester object for each test, and
      // pass the text that you'd like to simulate a user typing as only argument.
      TextUITester tester = new TextUITester("load\ncampus.dot\nstats\nexit\n");
      // 2. Create a Scanner, and run then code that you want to test here:
      // (this code should read from System.in and write to System.out)
      Scanner sc = new Scanner(System.in);
      Backend backend = new Backend();
      Frontend frontend = new Frontend(sc, backend);
      frontend.startLoop();
      // Note hat you cannot see output printed by your program between the
      // constructor call above and the checkOutput call below.
      // 3. Check whether the output printed to System.out matches your expectations.
      String output = tester.checkOutput();

      // Expects frontend to directly output results from backend's getGraphStats()
      Assertions.assertTrue(output.contains("number of buildings: 160\n" + "number of edges: 508\n"
          + "total walking time: 76604.99999999985 seconds."));


      if (output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
          && output.contains("Goodbye!")) {
        System.out.println("Test passed.");
      } else {
        System.out.println("Test FAILED.");
        Assertions.fail("Command loop failed");
      }

    } catch (Exception e) {
      Assertions.fail("TEST FAILED");
    }

  }

  /*
   * Expects frontend class to output output all data related to a shortestpath call to backend
   * before and after map is created
   */
  @Test
  public void testIntegrationShortestPath() {
    try {

      // 1. Create a new TextUITester object for each test, and
      // pass the text that you'd like to simulate a user typing as only argument.
      TextUITester tester = new TextUITester(
          "load\ncampus.dot\nshortestPath" + "\nMemorial Union\nNorth Hall\nexit\n");
      // 2. Create a Scanner, and run then code that you want to test here:
      // (this code should read from System.in and write to System.out)
      Scanner sc = new Scanner(System.in);
      Backend backend = new Backend();
      Frontend frontend = new Frontend(sc, backend);
      frontend.startLoop();
      // Note hat you cannot see output printed by your program between the
      // constructor call above and the checkOutput call below.
      // 3. Check whether the output printed to System.out matches your expectations.
      String output = tester.checkOutput();

      // Expects frontend to properly output data from shortestpath class
      Assertions.assertTrue(output
          .contains("The shortest path from Memorial Union to North Hall"
              + " is [Memorial Union, Radio Hall, North Hall]\n")
          && output.contains("176.7 seconds") && output.contains("190.40000000000003 seconds"));

      if (output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
          && output.contains("Goodbye!")) {
        System.out.println("Test passed.");
      } else {
        System.out.println("Test FAILED.");
        Assertions.fail("Command loop failed");
      }

    } catch (Exception e) {
      Assertions.fail("TEST FAILED");
    }

  }



}
