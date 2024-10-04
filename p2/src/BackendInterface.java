import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * This is an interface for the backend of the UWPathFinder App.
 */
public interface BackendInterface {

    /* constructor that takes an instance of the GraphADT as a constructor parameter
        Backend(GraphADT<String, Double> graph);
     */

    /**
     * This method reads data from a file.
     *
     * @param filepath stores the path to the file being loaded
     * @return true if file was loaded successfully, false otherwise
     */
    public boolean readData(String filepath) throws IOException;

    /**
     * Gets the shortest path from a start to a destination building in the dataset.
     *
     * @return the shortest path formatted as a String
     */
    public ShortestPath findShortestPath(String start, String dest) throws NoSuchElementException;

    /**
     * Gets a String with statistics about the dataset that includes the number of nodes
     * (buildings), the number of edges, and the total walking time (sum of weights) for all edges
     * in the graph.
     *
     * @return a String with statistics about the dataset
     */
    public String getGraphStats() throws NoSuchElementException;

}
