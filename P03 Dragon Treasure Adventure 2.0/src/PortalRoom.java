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
import java.util.Random;
import processing.core.PImage;
import processing.core.PApplet;

/**
 * Class to represent the portal room of the dungeon as an object
 */
public class PortalRoom extends Room {
  private Random randGen; // random number generator for location picking

  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";

  private static final String TELEPORT_MESSAGE =
      "The space distortion teleported you to another room!\n";

  private static PImage portalImage; // image of a portal to be shown in all portal rooms

  /**
   * Constructor for a PortalRoom object. Initializes all instance data fields
   * 
   * @param ID          the ID that this PortalRoom should have
   * @param description the verbal description this PortalRoom should have
   * @param image       the image that should be used as a background when drawing this PortalRoom
   */
  public PortalRoom(int ID, String description, processing.core.PImage image) {
    super(ID, "You feel a distortion in space nearby.\n", image);
  }

  /**
   * Getter for PORTAL_WARNING
   * 
   * @return the string for warning about a portal being nearby
   */
  public static String getPortalWarning() {
    return PORTAL_WARNING;
  }

  /**
   * Getter for TELEPORT_MESSAGE
   * 
   * @return the string for letting the player know they were teleported
   */
  public static String getTeleportMessage() {
    return TELEPORT_MESSAGE;
  }

  /**
   * Picks an adjacent room at random for the player to teleport into
   * 
   * @return The room that player should immediately be moved to
   */
  public Room getTeleportLocation() {
    randGen = new Random();
    int randValue = randGen.nextInt(getAdjacentRooms().size());
    return getAdjacentRooms().get(randValue);
  }

  /**
   * Draws this PortalRoom to the window by drawing the background image, a rectangle, some text,
   * and the portal image
   * 
   */
  @Override
  public void draw() {
    super.draw();
    Room.processing.image(PortalRoom.portalImage, 325, 225);
  }

  /**
   * Sets the portal image for the PortalRoom class
   * 
   * @param portalImage the image to represent the portal
   */
  public static void setPortalImage(processing.core.PImage portalImage) {
    PortalRoom.portalImage = portalImage;
  }

}
