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
 * Class to represent all the characters of the dungeon as an object
 */
public class Character {

  private Room currentRoom; // current room the character is in

  private String label; // a label giving a basic description of the character

  /**
   * Constructor for a Character object. Initializes all instance fields
   * 
   * @param currentRoom the room that the Character is located in
   * @param label       a descriptive label of this Character
   */
  public Character(Room currentRoom, String label) {
    this.currentRoom = currentRoom;
    this.label = label;
  }

  /**
   * Getter for the current room of this Character
   * 
   * @return the room where the character is
   */
  public Room getCurrentRoom() {
    return this.currentRoom;
  }

  /**
   * Getter for the label of this Character
   * 
   * @return this Character's descriptive label
   */
  public String getLabel() {
    return this.label;
  }

  /**
   * Gets the list of rooms adjacent to this Character
   * 
   * @return an ArrayList of rooms adjacent to this character
   */
  public ArrayList<Room> getAdjacentRooms() {
    return currentRoom.getAdjacentRooms();
  }

  /**
   * Sets the current room to the one given
   * 
   * @param newRoom the room that should become the current room
   */
  public void setCurrentRoom(Room newRoom) {
    this.currentRoom = newRoom;
  }

}
