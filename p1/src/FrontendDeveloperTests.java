import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FrontendDeveloperTests {

  /**
   * This tests whether exit is properly implemented
   */
  @Test
  public void testExit() {
    TextUITester uiTester = new TextUITester("q\n");
    Frontend frontend = new Frontend(new BackendPlaceHolderFrontend(), new Scanner(System.in));

    frontend.start();
    String systemOutput = uiTester.checkOutput();
    Assertions.assertTrue(systemOutput.contains("Goodbye!"));
  }

  /**
   * Tests whether the command loop is correctly implemented
   */
  @Test
  public void testStart() {
    TextUITester uiTester = new TextUITester("f\nQ\n");
    Frontend frontend = new Frontend(new BackendPlaceHolderFrontend(), new Scanner(System.in));
    frontend.start();
    String systemOutput = uiTester.checkOutput();
    Assertions.assertTrue(systemOutput.contains("Unknown command: f"));
  }


  /**
   * Tests for the correct implementation of calling loadData from the backend
   */
  @Test
  public void testGetData() {
    TextUITester uiTester = new TextUITester("1\nsrc/cars.csv\nQ\n");
    Frontend frontend = new Frontend(new BackendPlaceHolderFrontend(), new Scanner(System.in));
    frontend.start();
    String systemOutput = uiTester.checkOutput();
    Assertions.assertTrue(systemOutput.contains("Successfully loaded file"));
  }



  /**
   * Tests whether the getCarsWithMinMileage method is correctly implemented
   */
  @Test
  public void testLowestMile() {
    TextUITester uiTester = new TextUITester("1\nsrc/cars.csv\n2\nQ\n");
    Frontend frontend = new Frontend(new BackendPlaceHolderFrontend(), new Scanner(System.in));
    frontend.start();
    String systemOutput = uiTester.checkOutput();
    Assertions.assertTrue(systemOutput.contains("Camry") && systemOutput.contains("Corolla"));
  }

  /**
   * Tests whether the getCarsWithMileageAbove method is correctly implemented
   */
  @Test
  public void testThesholdMile() {
    TextUITester uiTester = new TextUITester("1\nsrc/cars.csv\n3\n7999.00\nQ\n");
    Frontend frontend = new Frontend(new BackendPlaceHolderFrontend(), new Scanner(System.in));
    frontend.start();
    String systemOutput = uiTester.checkOutput();
    Assertions.assertTrue(systemOutput.contains("[Camero, Bronco]"));
  }

  /**
   * Tests whether the backend returns the correct list of minimum mileage cars.
   */
  @Test
  public void IntegrationTestGetLowestMileFromBackend() {
    TextUITester uiTester = new TextUITester("1\nsrc/cars.csv\n2\nq\n");
    Frontend frontend = new Frontend(new Backend(), new Scanner(System.in));
    frontend.start();
    String systemOutput = uiTester.checkOutput();
    Assertions.assertTrue(systemOutput.contains("[door, chassis, truck, door, door, door]"));
  }

  /**
   * Ensure that the frontend can get cars with mileage above an a requested threshold by 
   * the user. The backend will generate a list of cars that meet this requirement.
   */
  @Test
  public void IntegrationTestGetThresholdMile() {
    TextUITester uiTester = new TextUITester("1\nsrc/cars.csv\n3\n666666.00\nQ\n");
    Frontend frontend = new Frontend(new Backend(), new Scanner(System.in));
    frontend.start();
    String systemOutput = uiTester.checkOutput();
    Assertions.assertTrue(systemOutput.contains("[truck, truck, door, truck]"));
  }
  
  

}
