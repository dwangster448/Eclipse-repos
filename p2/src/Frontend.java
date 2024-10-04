// --== CS400 File Header Information ==--
// Name: Evan Wruk
// Email: ewruk@wisc.edu
// Group and Team: G45
// Group TA: Zheyang Xiong
// Lecturer: Florian Heimerl
// Notes to Grader: NONE

import java.util.Scanner;

/**
 * Drives an interactive loop prompting the user to select a command,
 * then requests any required details about that command from the user,
 * and then displays the results of the command.
 */
public class Frontend implements FrontendInterface {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Backend backend = new Backend();
        Frontend frontend = new Frontend(sc,backend);
        frontend.startLoop();
    }

    protected Scanner sc;
    protected BackendInterface backend;

    /**
     * Frontend constructor
     * @param sc the scanner object used by frontend
     * @param backend the backend object used by frontend
     */
    public Frontend(Scanner sc, BackendInterface backend) {
        if (sc == null || backend == null) {
            throw new IllegalArgumentException("Scanner or Backend cannot be null");
        }
        this.sc = sc;
        this.backend = backend;
    }

    /**
     * initialize the loop that repeatedly prompts users to choose a command,
     * restarting after a command completes
     */
    @Override
    public void startLoop() {
        System.out.println("Welcome to UW Path Finder.");
        System.out.println("Please enter a command: ");
        String command = sc.nextLine();

        while (!command.equals("exit")) {
            if (command.equals("load")) {
                loadFile();
            }
            else if (command.equals("stats")) {
                listStatistics();
            }
            else if (command.equals("shortestPath")) {
                listShortestPath();
            }
            else {
                printError("Invalid command");
            }
            System.out.println("Please enter a command: ");
            command = sc.nextLine();
        }
        exit();
    }

    /**
     * prompt the user for a file with the scanner
     * don't take method parameters, ask for the file location with the scanner
     */
    @Override
    public void loadFile() {
        System.out.println("Please enter the file path: ");
        String filePath = sc.nextLine();
        try {
            if (backend.readData(filePath)) {
                System.out.println("File loaded.");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Failed to load file.");
        }
    }

    /**
     * shows statistics about the dataset that includes the number of buildings (nodes),
     * the number of edges, and the total walking time (sum of all edge weights) in the graph,
     */
    @Override
    public void listStatistics() {
        System.out.println(backend.getGraphStats());
    }
    

    /**
     * a command that asks the user for a start and destination building,
     * then lists the shortest path between those buildings,
     * including all buildings on the way, the estimated walking time for each segment,
     * and the total time it takes to walk the path
     */
    @Override
    public void listShortestPath() {
        System.out.println("Please enter a starting location: ");
        String start = sc.nextLine();
        System.out.println("Please enter a destination: ");
        String dest = sc.nextLine();
        ShortestPathInterface shortestPath = backend.findShortestPath(start,dest);
        System.out.println("The shortest path from " + start + " to " + dest + " is " + shortestPath.getPath());
        for (int i=0; i<shortestPath.getWalkTimes().size(); i++) {
            System.out.println("It takes " + shortestPath.getWalkTimes().get(i)
                    + " seconds to get from " + shortestPath.getPath().get(i)
                    + " to " + shortestPath.getPath().get(i+1));
        }
        System.out.println("Total walk time is " + shortestPath.getTotalTime() + " seconds.");
    }

    /**
     * exit the program using System.exit()
     */
    @Override
    public void exit() {
        System.out.println("Goodbye!");
    }

    /**
     * print an error for the user, formatted nicely
     * @param error the error to display to the user
     */
    @Override
    public void printError(String error) {
        System.out.println(error);
    }

}
