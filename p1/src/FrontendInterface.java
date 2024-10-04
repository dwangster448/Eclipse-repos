/**
 * The interface for the frontend of Car Organizer, responsible for handling user input and command.
 * Send and retrieve data from the backend and displaying them to the user. 
 */
public interface FrontendInterface {
  /**
   * start the command loop of the program, list out all the available command for the user and
   * allow user input to choose command
   */
  public void start();

  /**
   * get data using the parameter filename from user input. If data failed to load, the function
   * should prompt the user the correct format for entering the filename
   * @return 
   */
  public boolean getData(String filename);

  /**
   * /**
   * Lists all the vehicles with the lowest mileage
   */
  public void getLowestMile();

  /**
   * list all the vehicle with a higher mileage than the parameter minMileage
   * @param threshold the threshold for the list
   */
  public void getThresholdMile(double threshold);
}
