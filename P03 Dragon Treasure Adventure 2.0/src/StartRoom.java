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
 * Class to represent the starting room of the dungeon as an object
 */
public class StartRoom extends Room {

  /**
   * Constructor for a StartRoom object. Initializes all instance data fields
   * 
   * @param ID    the ID that this PortalRoom should have
   * @param image the image that should be used as a background when drawing this PortalRoom
   */
  public StartRoom(int ID, processing.core.PImage image) {
    super(ID, "You find yourself in the entrance to a cave holding treasure.", image);
  }
}
