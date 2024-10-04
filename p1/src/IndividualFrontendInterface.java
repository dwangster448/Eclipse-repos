import java.util.List;

/**
 * The interface for the frontend of Car Organizer, responsible for handling user input and command.
 * Send and retrieve data from the backend and displaying them to the user. 
 */
public interface IndividualFrontendInterface {
  /*
  public FrontendInterface(BackendInterface backend, Scanner input){};
   */

  /**
   * start the command loop of the program, list out all the available command for the user and
   * allow user input to choose command
   */
  public void start();

  /**
   * Exist the program
   */
  public void exit();

  /**
   * get data using the parameter filename from user input. If data failed to load, the function
   * should prompt the user the correct format for entering the filename
   */
  public void getData();

  /**
   * list all the vehicle with a lower mileage than the parameter maxMileage
   */
  public void getLowestMile();

  /**
   * list all the vehicle with a higher mileage than the parameter minMileage
   * @param threshold the threshold for the list
   */
  public void getThresholdMile(int threshold);

  /**
   * Check the command user inputs after prompting for a command
   */
  public void commandSelection();



}
