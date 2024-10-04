import java.util.Scanner;

//This is the Frontend interface
public interface IndividualFrontendInterface {  
  
  //Active scanner that stores user inputs
  Scanner input = new Scanner(System.in);
  
  //starts main command loop
  public void start();
  
  //Checks the command user inputs after prompting for a command
  public void commandSelection();
  
  //checks whether the data is within the list
  //prints data if successful otherwise prints out recommendation about finding valid data file
  public String dataFile();
  
  //backend reader
  
  //Prints all vehicles with low mileage
  public String lowestMileageVehicle();
  
  //Prints all vehicles with mileages equal or greater than user input
  public String aboveThresholdVehicle();
  
  //Print results of any command
  public void resultPrint();
  
  //Prints an error message and recommends user to enter valid commands
  public void error();
  
  //Exits app
  public void exit();

}
