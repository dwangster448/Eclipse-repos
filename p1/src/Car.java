/**
 * This class models a Car object
 * 
 * @param <T>
 */
public class Car implements Comparable<Car>, CarInterface  {
  private String brand;
  private String model;
  private int year;
  private double price;
  private double mileage;

  /**
   * Creates an instance of a Car
   *
   * @param brand   - the car brand
   * @param model   - the car model
   * @param year    - the car year
   * @param price   - the car price
   * @param mileage - the car mileage
   */
  public Car(String brand, String model, int year, double price, double mileage) {
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.price = price;
    this.mileage = mileage;
  }

  /**
   * Accessor method for the car brand
   *
   * @return - the car brand
   */
  @Override
  public String getBrand() {
    return brand;
  }

  /**
   * Accessor method for the car model
   *
   * @return - the car model
   */
  @Override
  public String getModel() {
    return model;
  }

  /**
   * Accessor method for the car year
   *
   * @return - the car year
   */
  @Override
  public int getYear() {
    return year;
  }

  /**
   * Accessor method for the car price
   *
   * @return - the car price
   */
  @Override
  public double getPrice() {
    return price;
  }

  /**
   * Accessor method for the car mileage
   *
   * @return - the car mileage
   */
  @Override
  public double getMileage() {
    return mileage;
  }

  @Override
  public int compareTo(Car o) {
    double otherMileage = o.getMileage();
    double thisMileage = this.getMileage();
    if (o.getMileage() == this.getMileage()) {
      return 0;
    } else if (o.getMileage() - (this.getMileage()) > 0) {
      return -1;
    } else {
      return 1;
    }
  }

}
