import java.util.List;

/**
 * This is an interface for a class that stores the results of the shortest path search.
 */
public interface ShortestPathInterface {

    /* constructor - @param start is the start node to begin
                     @param dest is the destination node to end
                     @param graph is the map that connects all nodes in DOT file
        ShortestPath(String start, String dest, DijkstraGraph<String, Double> graph);
     */

    /**
     * Getter method for the path (stored as a list of buildings along the path).
     */
    public List<String> getPath();

    /**
     * Getter method for a list of the walking times of the path segments (the time it takes to
     * walk from one building to the next).
     */
    public List<Double> getWalkTimes();

    /**
     * Getter method for the total path cost as the estimated time it takes to walk from the start
     * to the destination building.
     */
    public double getTotalTime();
}
