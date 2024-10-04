import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) {
    Scanner fileIn = null;
    try {
      fileIn = new Scanner (new File("items.txt"));
      
      String line = "";
      while (fileIn.hasNextLine()) {
        String[] parts = line.split("\\| ");
        String itemName = parts[0];
        try {
          double price = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
          System.out.println("need to have values");
        }
      }
    } catch(FileNotFoundException e) {
      System.out.println("we caught an exception");
    }
  }

}
