import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class BackendDeveloperTests {
  
//Utility method to get an implementation of the BackendInterface.
 private Backend getImplementation() {
   return new Backend();
 }
 
  @Test
  public void testLoadValidData() {
    Backend backend = getImplementation();
    try {
      backend.loadData("src/cars.csv");
      assertTrue(true, "Data should load without exceptions for valid paths.");
    } catch (Exception e) {
      fail("Exception should not be thrown when loading data from a valid file.");
    }
  }
  @Test
  public void testLoadInvalidData() {
    Backend backend = new Backend();
    // Use assertThrows to check if an IOException is thrown
    assertThrows(IOException.class, () -> {
      backend.loadData("src/cas.csv");
    });
  }
  @Test
  public void testGetCarsWithMinMileage() {
      // Create a new backend
      Backend backend = new Backend();
      // Load sample data into the backend
      try {
          backend.loadData("src/cars.csv");
      } catch (IOException e) {
          e.printStackTrace();
      }
      // Create a frontend
      Frontend frontend = new Frontend(backend, null); // Pass null for Scanner
      // Simulate user input to get the lowest mileage cars
      frontend.getLowestMile();
      // Retrieve the cars with the lowest mileage from the backend
      List<Car> lowestMileageCars = backend.getCarsWithMinMileage();
      // Check that calling getLowestMile() results in an empty list
      assertEquals(6, lowestMileageCars.size(), "There should be no cars with the lowest mileage.");
  }
@Test
  public void testGetCarsWithMileageAboveThreshold() {
    Backend backend = getImplementation();
    try {
      backend.loadData("src/cars.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    double threshold = 5000.0;
    List<Car> cars = backend.getCarsWithMileageAbove(threshold);
    for (Car car : cars) {
      assertTrue(car.getMileage() > threshold,
          "All cars in the result should have mileage above the threshold.");
    }
  }
  @Test
  public void testGetCarsWithUnrealisticMileageAboveThreshold() {
    Backend backend = getImplementation();
    try {
      backend.loadData("src/cars.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    double threshold = 10000000.0;
    List<Car> cars = backend.getCarsWithMileageAbove(threshold);
    assertTrue(cars.isEmpty(), "Should return an empty list for non-realistic mileage thresholds.");
  }
  @Test
  public void testCarBrand() {
    Backend backend = getImplementation();
    try {
      backend.loadData("src/cars.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<Car> cars = backend.getCarsWithMinMileage();
    for (Car car : cars) {
      assertNotNull(car.getBrand(), "Car brand should not be null.");
      assertFalse(car.getBrand().isEmpty(), "Car brand should not be empty.");
    }
  }
}
