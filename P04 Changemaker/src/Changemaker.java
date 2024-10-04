//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Changemaker
// Course: CS 300 Summer 2023
//
// Author: Daniel Wang
// Email: dwang448@wisc.edu
// Lecturer: Michelle Jensen
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class simulates multiple coin changing methods on input scenarios
 */
public class Changemaker {

  /**
   * Totals the amount of coins used to create change in an array
   * <p>
   * 
   * @param coins represents coins currently used in our solution
   * @return total coins used
   */
  private static int sum(int[] coins) {
    int total = 0;
    for (int i = 0; i < coins.length; i++) {
      total += coins[i];
    }
    return total;
  }

  /**
   * Creates a copy of a coins organized in an array and decrements a coin by 1 at indicated index
   * <p>
   * 
   * @param coinsRemaining represents how many coins of each corresponding denomination in the
   *                       denominations array are remaining in our cash register
   * @return copy the decremented coins array
   */
  private static int[] useCoin(int[] coinsRemaining, int coinToUse) {
    int[] copy = new int[coinsRemaining.length];
    for (int i = 0; i < coinsRemaining.length; i++) {
      copy[i] = coinsRemaining[i];
    }
    copy[coinToUse] -= 1;
    return copy;
  }

  /**
   * This method counts the number of possible ways to make change for a given value
   * <p>
   * 
   * @param denominations  represents a list of various denominations or values of coins
   * @param coinsRemaining represents how many coins of each corresponding denomination in the
   *                       denominations array are remaining in our cash register
   * @param value          total value that we wish to make change for
   * @return permute the possible ways to make change or 0 if there is no way to make change
   */
  public static int count(int[] denominations, int[] coinsRemaining, int value) {
    boolean noCoins = true;
    for (int i = 0; i < coinsRemaining.length; i++) {
      if (coinsRemaining[i] > 0) {
        noCoins = false;
      }
    }
    int permute = 0;
    if (value == 0) {
      return 1;
    }
    if (value < 0 || noCoins == true) {
      return 0;
    } else {
      for (int i = 0; i < denominations.length; i++) {
        if (value >= denominations[i] && coinsRemaining[i] > 0) {
          permute += count(denominations, useCoin(coinsRemaining, i), value - denominations[i]);
        }
      }
    }
    return permute;
  }

  /**
   * Finds the minimum total number of coins needed to make change for the given value
   * <p>
   * 
   * @param denominations  represents a list of various denominations or values of coins
   * @param coinsRemaining represents how many coins of each corresponding denomination in the
   *                       denominations array are remaining in our cash register
   * @param value          total value that we wish to make change for
   * @return minimum minimum total number of coins or -1 if there is no way to make change
   */
  public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
    boolean noCoins = true;
    for (int i = 0; i < coinsRemaining.length; i++)
      if (coinsRemaining[i] > 0) {
        noCoins = false;
      }
    int minimum = Integer.MAX_VALUE;
    if (value < 0 || noCoins == true) {
      return -1;
    } else if (value == 0) {
      return 0;
    } else {
      for (int i = 0; i < denominations.length; i++) {
        if (value >= denominations[i] && coinsRemaining[i] > 0) {
          int current =
              minCoins(denominations, useCoin(coinsRemaining, i), value - denominations[i]);
          if (current == -1) {
            continue;
          }
          if (current + 1 < minimum) {
            minimum = current + 1;
          }
        }
      }
    }
    if (minimum == Integer.MAX_VALUE) {
      return -1;
    }
    return minimum;
  }

  /**
   * Finds an array representing the exact number of each type of coin needed to make change
   * optimally
   * <p>
   * 
   * @param denominations  represents a list of various denominations or values of coins
   * @param coinsRemaining represents how many coins of each corresponding denomination in the
   *                       denominations array are remaining in our cash register
   * @param value          total value that we wish to make change for
   * @return minimum exact number of each type of coin or null if there is no way to make change
   */
  public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
    int[] change;
    boolean noCoins = true;
    int[] dummy = {10000, 10000, 10000, 10000};
    for (int i = 0; i < coinsRemaining.length; i++)
      if (coinsRemaining[i] > 0) {
        noCoins = false;
      }
    int[] minimum = {10000, 10000, 10000, 10000};
    if (value < 0 || noCoins == true) {
      return null;
    } else if (value == 0) {
      change = new int[coinsRemaining.length];
      Arrays.fill(minimum, 0);
      return change;
    } else {
      change = new int[coinsRemaining.length];
      for (int i = 0; i < denominations.length; i++) {
        if (value >= denominations[i] && coinsRemaining[i] > 0) {

          int[] current =
              makeChange(denominations, useCoin(coinsRemaining, i), value - denominations[i]);
          if (current == null) {
            continue;
          }
          if (sum(current) < sum(minimum)) {
            for (int j = 0; j < coinsRemaining.length; j++) {
              minimum[j] = current[j];
            }
            minimum[i]++;
          }
        }
      }
    }
    if (Arrays.equals(minimum, dummy)) {
      return null;
    }
    return minimum;
  }

}
