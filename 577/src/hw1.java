import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hw1 {
  static int input = 0;
  public static List<String> list = new ArrayList<>();
  private static String ouput = "";

  public static int insertInstances(Scanner scanner) {
    System.out.print("How many strings are input?: ");
    return scanner.nextInt();
  }

  public static void insertString(int input, Scanner scanner) {
    scanner.nextLine();
    while (input > 0) {
      System.out.print("Enter string: ");
      list.add(scanner.nextLine());
      input--;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    input = insertInstances(scanner);
    insertString(input, scanner);
    scanner.close();

    for (String element : list) {
      if (element != null) {
        ouput += "\nHello, " + element + "!";
      }
    }
    System.out.print(ouput.substring(1));
  }
}
