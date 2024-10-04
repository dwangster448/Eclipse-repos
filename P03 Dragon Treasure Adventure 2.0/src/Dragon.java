//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dragon Treasure Adventure 2.0
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

import java.util.Random;

/**
 * Class to represent the dragon of the dungeon as an object
 */
public class Dragon extends Character implements Moveable {
  private Random randGen; // random num generator used for moving

  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";

  private static final String DRAGON_ENCOUNTER =
      "Oh no! You ran into the fire " + "breathing dragon!\n";

  /**
   * Constructor for a Dragon object. Initializes all instance fields. The label should be "DRAGON"
   * by default
   * 
   * @param currentRoom the room that the Dragon starts in
   * @throws IllegalArgumentException with descriptive message if currentRoom is not a TreasureRoom
   */
  public Dragon(Room currentRoom) {
    super(currentRoom, "DRAGON");
    if (!(getCurrentRoom() instanceof TreasureRoom))
      throw new IllegalArgumentException("DRAGON DOES NOT START IN TREASURE ROOM");
  }

  /**
   * Moves the Dragon to the destination room
   * 
   * @param destination the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination) {
    if (canMoveTo(destination) == false)
      return false;
    setCurrentRoom(destination);
    return true;
  }

  /**
   * Checks if the dragon can move to the given destination. A valid move is the destination not a
   * PortalRoom
   * 
   * @param destination the room to check if the dragon can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    if (getCurrentRoom().isAdjacent(destination))
      return true;
    return false;
  }

  /**
   * Picks randomly ONCE an adjacent room to move into
   * 
   * @return the room that this Dragon should try to move into
   */
  public Room pickRoom() {
    randGen = new Random();
    int randValue = randGen.nextInt(getAdjacentRooms().size());
    return getAdjacentRooms().get(randValue);
  }

  /**
   * Getter for DRAGON_WARNING
   * 
   * @return the string for warning about a dragon being nearby
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING;
  }

  /**
   * Getter for DRAGON_ENCOUNTER
   * 
   * @return the string for letting the player know they ran into the dragon
   */
  public static String getDragonEncounter() {
    return DRAGON_ENCOUNTER;
  }
}
