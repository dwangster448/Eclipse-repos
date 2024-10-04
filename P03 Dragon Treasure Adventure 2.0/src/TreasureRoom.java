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

import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class to represent the ending treasure room of the dungeon as an object
 */
public class TreasureRoom extends Room {
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";

  private static PImage treasureBackground; // the image ALWAYS used for treasure rooms

  /**
   * Constructor for a TresureRoom object and have a description of "In the back of this room, you
   * spot a treasure chest. It is locked...". Initializes all instance data fields
   * 
   * @param ID the unique id number for this room
   */
  public TreasureRoom(int ID) {
    super(ID, "In the back of this room, you spot a treasure chest. It is locked...",
        treasureBackground);
  }

  /**
   * Getter for TREASURE_WARNING.
   * 
   * @return the string for warning about treasure being nearby
   */
  public static String getTreasureWarning() {
    return TreasureRoom.TREASURE_WARNING;
  }

  /**
   * Determines whether or not the player can open the treasure chest in the room
   * 
   * @param p the Player to check if they can open the chest
   * @return true if the player has the key and is in this TreasureRoom, false otherwise
   */
  public boolean playerCanGrabTreasure(Player p) {
    if (p.getCurrentRoom().getID() == 9 && p.hasKey())
      return true;
    return false;
  }

  /**
   * Sets the background image for the TreasureRoom class
   * 
   * @param treasureBackground the image to be the background
   */
  public static void setTreasureBackground(processing.core.PImage treasureBackground) {
    TreasureRoom.treasureBackground = treasureBackground;
  }
}
