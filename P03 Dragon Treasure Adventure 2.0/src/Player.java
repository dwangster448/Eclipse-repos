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

import java.util.ArrayList;

/**
 * Class to represent the player of the dungeon as an object
 */
public class Player extends Character implements Moveable {

  private boolean hasKey;

  /**
   * Constructor for player object. The label should be "PLAYER" and not have a key by default
   * 
   * @param currentRoom the room that the player should start in
   * @throws IllegalArgumentException if the currentRoom is not a StartRoom
   */
  public Player(Room currentRoom) {
    super(currentRoom, "PLAYER");
    if (getCurrentRoom().equals(new TreasureRoom(1)) == false)
      throw new IllegalArgumentException("Current Room not Start Room");
    hasKey = false;
    if (!(getCurrentRoom() instanceof StartRoom))
      throw new IllegalArgumentException("PLAYER DOES NOT BEGIN IN START ROOM");
  }

  /**
   * Determines if the player has the key
   * 
   * @return true if the player has the key, false otherwise
   */
  public boolean hasKey() {
    return this.hasKey;
  }

  /**
   * Gives player the key
   */
  public void obtainKey() {
    this.hasKey = true;
  }

  /**
   * Moves the Player to the destination room
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
   * Checks if the player can move to the given destination. A valid move is the destination is a
   * room adjacent to the player
   * 
   * @param destination the room to check if the player can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    if (getCurrentRoom().isAdjacent(destination))
      return true;
    return false;
  }

  /**
   * Checks if the player needs to teleport and move them if needed
   * 
   * @return true if a teleport occurred, false otherwise
   */
  public boolean teleport() {
    if (getCurrentRoom().getID() == 3) {
      return true;
    }
    return false;
  }

  /**
   * Determines whether or not the given dragon is nearby. A dragon is considered nearby if it is in
   * one of the adjacent rooms
   * 
   * @param d the dragon to check if nearby
   * @return true if the dragon is nearby, false otherwise
   */
  public boolean isDragonNearby(Dragon d) {
    return getCurrentRoom().isAdjacent(d.getCurrentRoom());
  }

  /**
   * Determines whether or not a portal room is nearby. A portal room is considered nearby if it is
   * one of the adjacent rooms
   * 
   * @return true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby() {
    ArrayList<Room> room = getAdjacentRooms();
    for (int i = 0; i < room.size(); i++) {
      if (room.get(i).equals(new PortalRoom(i, getLabel(), null))) {
        System.out.println("There is a portal nearby");
        // return true;
      }
    }
    return false;
  }

  /**
   * Determines whether or not the treasure room is nearby. The treasure room is considered nearby
   * if it is one of the adjacent rooms
   * 
   * @return true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby() {
    ArrayList<Room> room = getAdjacentRooms();
    for (int i = 0; i < room.size(); i++) {
      if (room.get(i).equals(new Room(9, "dummy", null))) {
        System.out.println("There is a treasure nearby");
        return true;
      }
    }
    return false;
  }
}
