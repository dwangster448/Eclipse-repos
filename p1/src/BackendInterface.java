import java.io.IOException;
import java.util.List;

// Constructor comment: Every implementing class should have a constructor with these parameters.
  /*
  public CarDataBackend(String filePath) {
        // Constructor goes here.
        // this.filePath = filePath;
  }
  */
public interface BackendInterface {
  // Read data from a CSV file and store it in the Red-Black Tree
  void loadData(String filePath) throws IOException;

  // Get a list of cars with the minimum mileage from the Red-Black Tree
  List<Car> getCarsWithMinMileage();

  // Get a list of cars with the mileage at or above the specified threshold from the Red-Black Tree
  List<Car> getCarsWithMileageAbove(double mileageThreshold);

}
