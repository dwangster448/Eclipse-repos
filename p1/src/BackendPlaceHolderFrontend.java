import java.util.ArrayList;
import java.util.List;

public class BackendPlaceHolderFrontend extends Backend implements BackendInterface {

  @Override
  public List<Car> getCarsWithMinMileage() {
    List<Car> list = new ArrayList<>();
    Car car1 = new Car("Toyota", "Camry", 2022, 10000, 15000.00);
    Car car2 = new Car("Toyota", "Corolla", 2020, 7500, 20000.00);
    list.add(car1);
    list.add(car2);
    return list;
  }

  @Override
  public List<Car> getCarsWithMileageAbove(double mileageThreshold) {
    List<Car> list = new ArrayList<>();
    Car car1 = new Car("Chevrolet", "Camero", 2018, 8000, 10000.00);
    Car car2 = new Car("Ford", "Bronco", 2016, 12000, 18000.00);
    list.add(car1);
    list.add(car2);
    return list;
  }

  @Override
  public void loadData(String filePath) {
    System.out.println("Camry 2022, 5,000 Miles");
    
  }

}
