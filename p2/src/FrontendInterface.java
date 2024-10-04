public interface FrontendInterface {

    /**
     * initialize the loop that repeatedly prompts users to choose a command, restarting after a command completes
     */
    void startLoop();

    /**
     * prompt the user for a file with the scanner
     * don't take method parameters, ask for the file location with the scanner
     */
    void loadFile(); // prompt user for file with scanner (don't take method parameters)

    /**
     * shows statistics about the dataset that includes the number of buildings (nodes), the number of edges, and the total walking time (sum of all edge weights) in the graph,
     */
    void listStatistics();

    /**
     * a command that asks the user for a start and destination building, then lists the shortest path between those buildings, including all buildings on the way, the estimated walking time for each segment, and the total time it takes to walk the path
     */
    void listShortestPath(); // prompt user for a start and destination building
    /**
     * exit the program using System.exit()
     */
    void exit();

    /**
     * print an error for the user, formatted nicely
     * @param error the error to display to the user
     */
    void printError(String error);
}