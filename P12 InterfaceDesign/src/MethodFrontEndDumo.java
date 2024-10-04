import java.util.Scanner;
import java.util.ArrayList;

public class MethodFrontEndDumo <T> {

  ArrayList<T> list = new ArrayList<>();
  
  public static void main(String[] args) {
    // Need to seek advice: is main command loop defined by looping through main method?

  }
  /*
   * 
   */
  public static void commandSelection() {
  }
  
  //Add more documentation in the future, currently supports an user input
  //checks whether the data is within the list
  // returns data if successful otherwise prints out recommendation
  public void dataFile() {
    Scanner inputScanner = new Scanner(System.in);
    System.out.println("What vehicle would you like to access?");
    //user enters the data to access
    String input = inputScanner.next();
    inputScanner.close();
    if (list.contains(input)) {
      int index = list.indexOf(input);
      T vehicleData = list.get(index);
      //current template of successful data retrieval
      System.out.println("Vehicle data: " + vehicleData.toString()); 
    }
    else {
      System.out.println("Invalid input, please enter a valid vehicle");
      return;
    }
  }
  
  //Returns (currently prints) all vehicles with low mileage
  //Current compatibility with list, expect to interact with RBT in the future
  //Currently lowest mileage threshold is 1000, else all else don't print
  public void lowestMileageVehicle() {
    String toReturn = "";
    int mileageThreshold = 1000;
    for (int i =0; i < list.size(); i++) {
      if ((int)list.get(i) < mileageThreshold) {
        toReturn += list.get(i) + ", ";
      }
    }
    System.out.println("All vehicles with lowest mileage: " + 
        toReturn.substring(0,toReturn.length()-1));
  }
  
  //Returns (currently prints) all vehicles with mileages equal or greater than user input
  //Current interacts with a list, expect to interact with a RBT in future
  public void aboveThresholdVehicle() {
    Scanner inputScanner = new Scanner(System.in);
    System.out.println("What is the lowest mileage you are seeking?");
    //user enters the data to access
    int input = inputScanner.nextInt();
    inputScanner.close();
    String toReturn = "";
    int currentPointer = 0;
    if (list.size() < 1) {
      return;
    }
    else {
      currentPointer = 0;
    }
    while (list.get(currentPointer) != null) {
      if ((int)list.get(currentPointer) > input) {
        toReturn += list.get(currentPointer) + ", ";
      }
    }
    System.out.println("List of all vehicles with mileage with at least " + input + "miles: " + 
        toReturn.substring(0,toReturn.length()-1));
  }

}
