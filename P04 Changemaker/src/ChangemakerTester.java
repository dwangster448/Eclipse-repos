///////////////////////////////////////////////////////////////////////////////
//
// Title: Changemaker
// Course: CS 300 Summer 2023
//
// Author: Daniel Wang
// Email: dwang448@wisc.edu
// Lecturer: Michelle Jensen
//
///////////////////////////////////////////////////////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

// implements six tester methods, with two test methods dedicated to each of the Changemaker methods
public class ChangemakerTester {

  /**
   * This method tests whether the count() method behaves correctly on a base case of the problem by
   * implementing three scenarios:
   * 
   * 1. value is negative
   * 
   * 2. value is positive but there is no way to make change by choosing sum of all coins smaller
   * than the value
   * 
   * 3. value = 0
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testCountBase() {
    {
      int[] coinsRemaining = new int[] {3, 3, 3, 3};
      int[] denominations = new int[] {1, 2, 3, 4};
      int expected = 0;
      int actual = Changemaker.count(denominations, coinsRemaining, -1);
      if (expected != actual)
        return false;
    }
    {
      int[] coinsRemaining = new int[] {1, 0, 0, 0};
      int[] denominations = new int[] {4, 5, 6, 7};
      int expected = 0;
      int actual = Changemaker.count(denominations, coinsRemaining, 20);
      if (expected != actual)
        return false;
    }
    {
      int[] coinsRemaining = new int[] {1, 0, 0, 0};
      int[] denominations = new int[] {4, 5, 6, 7};
      int expected = 1;
      int actual = Changemaker.count(denominations, coinsRemaining, 0);
      if (expected != actual)
        return false;
    }
    return true;
  }

  /**
   * This method determines whether the count() method behaves correctly on a recursive case of the
   * problem by implementing three scenarios:
   * 
   * 1. at least three different coins can be used to make change
   * 
   * 2. at least two different optimal ways to make change using the same number of coins
   * 
   * 3. greedily choosing the largest coin first does not produce a result with the minimum number
   * of coins used
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testCountRecursive() {
    {
      int[] denominations = new int[] {1, 3, 2, 10};
      int[] coinsRemaining = new int[] {1, 1, 1, 1};
      int expected = 6;
      int actual = Changemaker.count(denominations, coinsRemaining, 15);
      if (expected != actual)
        return false;
    }
    {
      int[] denominations = new int[] {9, 5, 5, 1};
      int[] coinsRemaining = new int[] {1, 1, 1, 1};
      int expected = 4;
      int actual = Changemaker.count(denominations, coinsRemaining, 10);
      if (expected != actual)
        return false;
    }
    {
      int[] denominations = new int[] {1, 3, 3, 4};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int expected = 5;
      int actual = Changemaker.count(denominations, coinsRemaining, 6);
      if (expected != actual)
        return false;
    }
    return true;
  }

  /**
   * This method tests whether the minCoins() method behaves correctly on a base case by
   * implementing three scenarios:
   * 
   * 1. value is negative
   * 
   * 2. value is positive but there is no way to make change
   * 
   * 3. value = 0
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testMinCoinsBase() {
    {
      int[] coinsRemaining = new int[] {3, 3, 3, 3};
      int[] denominations = new int[] {1, 2, 3, 4};
      int expected = -1;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, -1);
      if (expected != actual)
        return false;
    }
    {
      int[] coinsRemaining = new int[] {1, 0, 0, 0};
      int[] denominations = new int[] {4, 5, 6, 7};
      int expected = -1;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, 20);
      if (expected != actual)
        return false;
    }
    {
      int[] coinsRemaining = new int[] {1, 0, 0, 0};
      int[] denominations = new int[] {4, 5, 6, 7};
      int expected = 0;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, 0);
      if (expected != actual)
        return false;
    }
    return true;
  }

  /**
   * This method determines whether the minCoins() method behaves correctly on a recursive case of
   * the problem by implementing three scenarios:
   * 
   * 1. at least three different coins can be used to make change
   * 
   * 2. at least two different optimal ways to make change using the same number of coins
   * 
   * 3. greedily choosing the largest coin first does not produce a result with the minimum number
   * of coins used
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testMinCoinsRecursive() {
    {
      int[] denominations = new int[] {1, 3, 2, 10};
      int[] coinsRemaining = new int[] {1, 1, 1, 1};
      int expected = 3;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, 15);
      if (expected != actual)
        return false;
    }
    {
      int[] denominations = new int[] {9, 5, 5, 1};
      int[] coinsRemaining = new int[] {1, 1, 1, 1};
      int expected = 2;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, 10);
      if (expected != actual)
        return false;
    }
    {
      int[] denominations = new int[] {1, 2, 3, 4};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int expected = 2;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, 6);
      if (expected != actual)
        return false;
    }
    return true;
  }

  /**
   * This method tests whether the makeChange() method behaves correctly on a base case of the
   * problem by implementing three scenarios:
   * 
   * 1. value is negative
   * 
   * 2. value is positive but there is no way to make change by choosing sum of all coins smaller
   * than the value
   * 
   * 3. value = 0
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testMakeChangeBase() {
    {
      int[] coinsRemaining = new int[] {3, 3, 3, 3};
      int[] denominations = new int[] {1, 2, 3, 4};
      int[] expected = null;
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, -1);
      if (expected != actual)
        return false;
    }
    {
      int[] coinsRemaining = new int[] {1, 0, 0, 0};
      int[] denominations = new int[] {4, 5, 6, 7};
      int[] expected = null;
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, 20);
      if (expected != actual)
        return false;
    }
    {
      int[] coinsRemaining = new int[] {1, 0, 0, 0};
      int[] denominations = new int[] {4, 5, 6, 7};
      int[] expected = {0, 0, 0, 0};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, 0);
      if (!Arrays.equals(expected, actual))
        return false;
    }
    return true;
  }

  /**
   * This method determines whether the makeChange() method behaves correctly on a recursive case of
   * the problem by implementing three scenarios:
   * 
   * 1. at least three different coins can be used to make change
   * 
   * 2. at least two different optimal ways to make change using the same number of coins
   * 
   * 3. greedily choosing the largest coin first does not produce a result with the minimum number
   * of coins used
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testMakeChangeRecursive() {
    {
      int[] denominations = new int[] {1, 3, 2, 10};
      int[] coinsRemaining = new int[] {1, 1, 1, 1};
      int[] expected = {0, 1, 1, 1};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, 15);
      if (!Arrays.equals(expected, actual))
        return false;
    }
    {
      int[] denominations = new int[] {9, 5, 5, 1};
      int[] coinsRemaining = new int[] {1, 1, 1, 1};
      int[] expected = {1, 0, 0, 1};
      int[] expected2 = {0, 1, 1, 0};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, 10);
      if (!(Arrays.equals(expected, actual) || Arrays.equals(expected2, actual)))
        return false;
    }
    {
      int[] denominations = new int[] {1, 3, 3, 4};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int[] expected = {0, 1, 1, 0};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, 6);
      if (!Arrays.equals(expected, actual))
        return false;
    }
    return true;
  }

  /**
   * Runs each of the tester methods and displays their result
   * 
   * @param args none
   */
  public static void main(String[] args) {
    System.out.println("count base " + testCountBase());
    System.out.println("count recursive " + testCountRecursive());
    System.out.println("mincoins base " + testMinCoinsBase());
    System.out.println("mincoins recursive " + testMinCoinsRecursive());
    System.out.println("makechange base " + testMakeChangeBase());
    System.out.println("makechange recursive " + testMakeChangeRecursive());
  }

}
