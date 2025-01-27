import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Backend implements BackendInterface {

  /*
   * Storage of cars in a Red-Black Tree based on mileage
   */
  private IterableMultiKeyRBT<Car> carsDatabase;

  /*
   * Construct a new instance of Iterable class that implements Multi-key RBT Iteration algorithm
   */
  public Backend() {
    this.carsDatabase = new IterableMultiKeyRBT<>();
  }

  /**
   * Loads data from a CSV file located at the specified 'filePath' and inserts the data into the
   * 'carsDatabase' as a Car object. The CSV file is expected to have a header row, which is skipped
   * during the loading process.
   *
   * @param filePath The path to the CSV file to be loaded.
   * @throws IOException If an I/O error occurs while reading the CSV file.
   */
  @Override
  public void loadData(String filePath) throws IOException {
    String line;
    // catch any expected exceptions during the reading of the file in backend
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
      reader.readLine();
      while ((line = reader.readLine()) != null) {
        // Split the line into columns using a comma as the delimiter
        String[] data = line.split(",");
        //assign all car properties into variables
        int price = Integer.parseInt(data[0].trim());
        String brand = data[1].trim();
        String model = data[2].trim();
        int year = Integer.parseInt(data[3].trim());
        double mileage = Double.parseDouble(data[5].trim());
        //create car object
        Car car = new Car(brand, model, year, price, mileage);
        //add car object into the current Backend instance's Red-Black Tree
        carsDatabase.insertSingleKey(car);
      }
      reader.close();
    } catch (IOException e) {
      throw new IOException("Invalid file path");
    }
  }

  /**
   * Retrieves a list of cars with the minimum mileage from the Red-Black Tree containing car
   * records. Each added minimum mileage car sets and decreases the minimum mileage as that car's
   * mileage. Iterates until there are no more cars and/or minimum mileage car reached
   *
   * @return A list of cars with the minimum mileage.
   */
  @Override
  public List<Car> getCarsWithMinMileage() {
    if (carsDatabase.isEmpty()) {
      // Return an empty list if the database is empty
      return new ArrayList<>();
    }
    carsDatabase.setIterationStartPoint(null);
    List<Car> result = new ArrayList<>();
    double minMileage = Double.MAX_VALUE;
    for (Car car : carsDatabase) {
      double mileage = car.getMileage();
      if (mileage < minMileage) {
        // If a car with lower mileage is found, update minMileage
        minMileage = mileage;
        result.clear(); // Clear the result list since we found a new minimum mileage
        result.add(car);
      } else if (mileage == minMileage) {
        // If a car with the same mileage is found, add it to the result list
        result.add(car);
      }
    }
    return result;
  }

  /**
   * Retrieves a list of cars with a mileage above the specified mileage threshold from the
   * Red-Black Tree containing car records.
   *
   * @param mileageThreshold The minimum mileage value to filter the cars.
   * @return A list of cars with mileage above the mileage threshold.
   */
  @Override
  public List<Car> getCarsWithMileageAbove(double mileageThreshold) {
    List<Car> result = new ArrayList<>();
    carsDatabase.setIterationStartPoint(new Car("brand", "model", 2022, 20.0, mileageThreshold));
    for (Car car : carsDatabase) {
      // Process the element as needed
      if (car.getMileage() > mileageThreshold) {
        // System.out.println("Mileage: "+element.getMileage());
        result.add(car);
      }

    }
    return result;
  }

  /**
   * The entry point of the application. Initializes the backend and frontend components, and starts
   * the user interface for interacting with car data.
   *
   * @param unused
   * @throws IOException If an I/O error occurs during the interaction with the user.
   */
  public static void main(String[] args) throws IOException {
    // create instance of frontend
    Frontend frontend = new Frontend(new Backend(), new Scanner(System.in));
    // start program
    frontend.start();
  }
}
