import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The interface for the frontend of Car Organizer, responsible for handling user input and command.
 * Send and retrieve data from the backend and displaying them to the user.
 */
public class Frontend implements FrontendInterface {

  /*
   * Backend instance
   */
  Backend backend;

  /*
   * Scanner instance
   */
  private Scanner scanner;
  
  /*
   * Constructs a Frontend object, initializing it with the provided Backend and Scanner instances.
   */
  public Frontend(Backend backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
  }

  /*
   * Constructs a Frontend object, initializing it with the Backend placeholder 
   * and Scanner instances. This is used for pre-fuctional Backend class
   */
  public Frontend(BackendPlaceHolderFrontend backendPlaceHolderFrontend, Scanner scanner) {
    this.backend = backendPlaceHolderFrontend;
    this.scanner = scanner;
  }

  


  /**
   * start the command loop of the program, list out all the available command for the user and
   * allow user input to choose command
   */
  public void start() {
    System.out.println("=== Menu ===");
    while (true) {
      // Menu prompt
      System.out.println("Choose an option:");
      System.out.println("1. Get data from a file");
      System.out.println("2. Get the car with the lowest mileage");
      System.out.println("3. Get cars with mileage above a threshold");
      System.out.println("Q. Quit");
      System.out.print("Enter your choice: ");
      // Seek the first string character input
      String input = scanner.next().toLowerCase();

      // exit program with "q"
      if (input.startsWith("q")) {
        System.out.println("Goodbye!");
        break;
        // loadData with "q"
      } else if (input.equals("1")) {
        System.out.print("Enter the file name: ");
        String filename = scanner.next();
        boolean success = getData(filename);
        if (success) {
          System.out.println("Successfully loaded file");
        } else {
          System.out.println("failed to load file");
        }
        // get minimum mileage car(s) in Red-Black Tree with "2"
      } else if (input.equals("2")) {
        getLowestMile();
        // get car(s) with mileage mileage in Red-Black Tree with "3"
      } else if (input.equals("3")) {
        System.out.print("Enter the minimum mileage: ");
        double mileage = Double.valueOf(scanner.next());
        getThresholdMile(mileage);
      } else {
        //Disregard invalid inputs
        System.out.println("Unknown command: " + input);
      }

      System.out.println(); // Add an empty line for spacing
    }
  }


  /**
   * get data using the parameter filename from user input. If data failed to load, the function
   * should prompt the user the correct format for entering the filename
   *
   * @param filename The path to the CSV file to be loaded.
   * @throws IOException If an I/O error occurs while reading the CSV file.
   */
  public boolean getData(String filename) {
    //Check 
    try {
      backend.loadData(filename);
      return true;
    } catch (IOException e) {
      // Handle the exception within the getData method
      
      return false;
    }
  }

  /**
   * list all the vehicle with a lower mileage than the parameter maxMileage
   */
  public void getLowestMile() {
    List<Car> list = backend.getCarsWithMinMileage();
    // return message if list is empty or no entry
    if (list.isEmpty()) {
      System.out.println("No cars with mileage below the threshold.");
      return;
    }
    // Print out list of minimum mileage cars
    System.out.print("Cars with lowest mileage: [");

    String delimiter = "";
    for (Car car : list) {
      System.out.print(delimiter + car.getModel());
      delimiter = ", ";
    }
    //close off the list
    System.out.println("]");
  }

  /**
   * list all the vehicle with a higher mileage than the parameter minMileage
   * 
   * @param threshold the threshold for the list
   */
  public void getThresholdMile(double threshold) {
    List<Car> list = backend.getCarsWithMileageAbove(threshold);
    // return message if list is empty or no entry
    if (list.isEmpty()) {
      System.out.println("No cars with mileage above the threshold.");
      return;
    }
    // Print out list of appropriate cars above mileage threshold
    System.out.print("Cars with mileage above the threshold: [");

    String delimiter = "";
    for (Car car : list) {
      System.out.print(delimiter + car.getModel());
      delimiter = ", ";
    }
    //close off the list
    System.out.println("]");
  }

  /**
   * The entry point of the application. Initializes the backend and frontend components, and starts
   * the user interface for interacting with car data.
   *
   * @param unused
   * @throws IOException If an I/O error occurs during the interaction with the user.
   */
  public static void main(String[] args) {
    // create a frontend instance
    Frontend frontend = new Frontend(new Backend(), new Scanner(System.in));
    // starts program
    frontend.start();
  }

}
