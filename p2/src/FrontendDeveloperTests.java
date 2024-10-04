// --== CS400 File Header Information ==--
// Name: Evan Wruk
// Email: ewruk@wisc.edu
// Group and Team: G45
// Group TA: Zheyang Xiong
// Lecturer: Florian Heimerl
// Notes to Grader: NONE

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class tests the functionality of the Frontend class
 */
public class FrontendDeveloperTests {

    /**
     * Tests to make sure that command loop starts correctly
     * and that the exit command works properly
     */
    @Test
    public void testStartAndExit() {
        try {

            // 1. Create a new TextUITester object for each test, and
            // pass the text that you'd like to simulate a user typing as only argument.
            TextUITester tester = new TextUITester("exit\n");
            // 2. Create a Scanner, and run then code that you want to test here:
            // (this code should read from System.in and write to System.out)
            Scanner sc = new Scanner(System.in);
            Backend backend = new Backend();
            Frontend frontend = new Frontend(sc,backend);
            frontend.startLoop();
            // Note hat you cannot see output printed by your program between the
            // constructor call above and the checkOutput call below.
            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            if(output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
                    &&  output.contains("Goodbye!")) {
                System.out.println("Test passed.");
            } else {
                System.out.println("Test FAILED.");
                Assertions.fail("Start of exit failed");
            }

        } catch (Exception e) {
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests to make sure that invalid user input and handled properly
     */
    @Test
    public void testInvalidCommand() {
        try {

            // 1. Create a new TextUITester object for each test, and
            // pass the text that you'd like to simulate a user typing as only argument.
            TextUITester tester = new TextUITester("invalid\nexit\n");
            // 2. Create a Scanner, and run then code that you want to test here:
            // (this code should read from System.in and write to System.out)
            Scanner sc = new Scanner(System.in);
            Backend backend = new Backend();
            Frontend frontend = new Frontend(sc,backend);
            frontend.startLoop();
            // Note hat you cannot see output printed by your program between the
            // constructor call above and the checkOutput call below.
            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            if(output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
                    &&  output.contains("Invalid command\nPlease enter a command: ")) {
                System.out.println("Test passed.");
            } else {
                System.out.println("Test FAILED.");
                Assertions.fail("invalid command test failed");
            }

        } catch (Exception e) {
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests the load file method
     */
    @Test
    public void testLoad() {
        try {

            // 1. Create a new TextUITester object for each test, and
            // pass the text that you'd like to simulate a user typing as only argument.
            TextUITester tester = new TextUITester("load\ncampus.dot\nexit\n");
            // 2. Create a Scanner, and run then code that you want to test here:
            // (this code should read from System.in and write to System.out)
            Scanner sc = new Scanner(System.in);
            Backend backend = new Backend();
            Frontend frontend = new Frontend(sc,backend);
            frontend.startLoop();
            // Note hat you cannot see output printed by your program between the
            // constructor call above and the checkOutput call below.
            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            if(output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
                    &&  output.contains("Please enter the file path: ")
                    && output.contains("File loaded.\nPlease enter a command: ")) {
                System.out.println("Test passed.");
            } else {
                System.out.println("Test FAILED.");
                Assertions.fail("load test failed");
            }

        } catch (Exception e) {
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests the listStatistics method
     */
    @Test
    public void testStats() {
        try {

            // 1. Create a new TextUITester object for each test, and
            // pass the text that you'd like to simulate a user typing as only argument.
            TextUITester tester = new TextUITester("load\ncampus.dot\nstats\nexit\n");
            // 2. Create a Scanner, and run then code that you want to test here:
            // (this code should read from System.in and write to System.out)
            Scanner sc = new Scanner(System.in);
            Backend backend = new Backend();
            Frontend frontend = new Frontend(sc,backend);
            frontend.startLoop();
            // Note hat you cannot see output printed by your program between the
            // constructor call above and the checkOutput call below.
            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            if(output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
                    &&  output.contains("Number of buildings: 3\nNumber of path segments: 2\nTotal walking time: 10.0 minutes")) {
                System.out.println("Test passed.");
            } else {
                System.out.println("Test FAILED.");
                Assertions.fail("stats test failed");
            }

        } catch (Exception e) {
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests the listShortestPath method
     */
    @Test
    public void testShortestPath() {
        try {

            // 1. Create a new TextUITester object for each test, and
            // pass the text that you'd like to simulate a user typing as only argument.
            TextUITester tester = new TextUITester("load\ncampus.dot\nshortestPath\nA\nC\nexit\n");
            // 2. Create a Scanner, and run then code that you want to test here:
            // (this code should read from System.in and write to System.out)
            Scanner sc = new Scanner(System.in);
            Backend backend = new Backend();
            Frontend frontend = new Frontend(sc,backend);
            frontend.startLoop();
            // Note hat you cannot see output printed by your program between the
            // constructor call above and the checkOutput call below.
            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            if(output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
                    && output.contains("Please enter a starting location: ")
                    && output.contains("Please enter a destination: ")
                    && output.contains("""
                    The shortest path from A to C is [A, B, C]
                    It takes 6.0 seconds to get from A to B
                    It takes 4.0 seconds to get from B to C
                    Total walk time is 10.0 seconds.""")) {

                System.out.println("Test passed.");
            } else {
                System.out.println("Test FAILED.");
                Assertions.fail("shortest path test failed");
            }

        } catch (Exception e) {
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests the statistics functionality of the program using backend class.
     */
    @Test
    public void integrationTestStats() {
        try {

            // 1. Create a new TextUITester object for each test, and
            // pass the text that you'd like to simulate a user typing as only argument.
            TextUITester tester = new TextUITester("load\n/home/wruk/p2/campus.dot\nstats\nexit\n");
            // 2. Create a Scanner, and run then code that you want to test here:
            // (this code should read from System.in and write to System.out)
            Scanner sc = new Scanner(System.in);
            Backend backend = new Backend();
            Frontend frontend = new Frontend(sc,backend);
            frontend.startLoop();
            // Note hat you cannot see output printed by your program between the
            // constructor call above and the checkOutput call below.
            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            if(output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
                    &&  output.contains("number of buildings: 160\n" +
                    "number of edges: 1016\n" +
                    "total walking time: 153209.9999999997")) {
                System.out.println("Test passed.");
            } else {
                System.out.println("Test FAILED.");
                Assertions.fail("stats integration test failed");
            }

        } catch (Exception e) {
            System.out.println("unexpected exception");
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests the statistics functionality of the program using the backend class.
     */
    @Test
    public void integrationTestShortestPath() {
        try {

            // 1. Create a new TextUITester object for each test, and
            // pass the text that you'd like to simulate a user typing as only argument.
            TextUITester tester = new TextUITester("load\n/home/wruk/p2/campus.dot\nshortestPath\nMemorial Union\nScience Hall\nexit\n");
            // 2. Create a Scanner, and run then code that you want to test here:
            // (this code should read from System.in and write to System.out)
            Scanner sc = new Scanner(System.in);
            Backend backend = new Backend();
            Frontend frontend = new Frontend(sc,backend);
            frontend.startLoop();
            // Note hat you cannot see output printed by your program between the
            // constructor call above and the checkOutput call below.
            // 3. Check whether the output printed to System.out matches your expectations.
            String output = tester.checkOutput();
            if(output.startsWith("Welcome to UW Path Finder.\nPlease enter a command: ")
                    && output.contains("Please enter a starting location: ")
                    && output.contains("Please enter a destination: ")
                    && output.contains("""
                    The shortest path from Memorial Union to Science Hall is [Memorial Union, Science Hall]
                    It takes 105.8 seconds to get from Memorial Union to Science Hall
                    Total walk time is 105.8 seconds.""")) {

                System.out.println("Test passed.");
            } else {
                System.out.println("Test FAILED.");
                Assertions.fail("shortest path integration test failed");
            }

        } catch (Exception e) {
            System.out.println("unexpected exception");
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests the functionality of the getGraphStats() method in Backend
     */
    @Test
    public void testBackendStats() {
        try {
            Backend backend = new Backend();
            backend.readData("/home/wruk/p2/campus.dot");
            String expected = "number of buildings: 160\n" +
                    "number of edges: 1016\n" +
                    "total walking time: 153209.9999999997";
            String actual = backend.getGraphStats();
            if (!actual.equals(expected)) {
                System.out.println("Backend stats failed");
                Assertions.fail("Backend stats failed");
            }

        } catch (Exception e) {
            System.out.println("unexpected exception");
            Assertions.fail("unexpected exception");
        }
    }

    /**
     * Tests the functionality of the findShortestPath() method in Backend
     */
    @Test
    public void testBackendShortestPath() {
        try {
            Backend backend = new Backend();
            backend.readData("/home/wruk/p2/campus.dot");
            ShortestPath shortestPath = backend.findShortestPath("Memorial Union","Science Hall");
            String actualPath = shortestPath.getPath().toString();
            String expectedPath = "[Memorial Union, Science Hall]";
            String actualTimes = shortestPath.getWalkTimes().toString();
            String expectedTimes = "[105.8]";
            double actualTotal = shortestPath.getTotalTime();
            double expectedTotal = 105.8;
            if (!actualPath.equals(expectedPath)
                    || !actualTimes.equals(expectedTimes)
                    || actualTotal != expectedTotal) {
                System.out.println("Backend getShortestPath failed");
                Assertions.fail("Backend getShortestPath failed");
            }

        } catch (Exception e) {
            System.out.println("unexpected exception");
            Assertions.fail("unexpected exception");
        }
    }



}
