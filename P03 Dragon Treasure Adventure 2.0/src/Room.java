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
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class to represent each room of the dungeon as an object
 */
public class Room extends PApplet {

  private String description; // verbal description of the room

  private ArrayList<Room> adjRooms; // list of all rooms directly connect

  private final int ID; // a "unique" identifier for each room

  protected static PApplet processing; // PApplet object which the rooms will use to
  // draw stuff to the GUI

  private PImage image; // stores the image that corresponds to the background of a room

  /**
   * Parent constructor for the room object. Assigns values to the non-static fields.
   * 
   * @param ID          the unique id number for this room
   * @param description a brief description of this room
   * @param image       the display image of the unique room
   * @param adjRooms    list of adjacent rooms listed by mapInfo File
   */
  public Room(int ID, String description, processing.core.PImage image) {
    this.ID = ID;
    this.description = description;
    this.image = image;
    this.adjRooms = new ArrayList<Room>();
  }

  /**
   * Default constructor for the room object. Assigns room's ID to 0.
   */
  public Room() {
    this.ID = 0;
  }

  /**
   * Getter for the id instance field
   * 
   * @return this object's id
   */
  public int getID() {
    return this.ID;
  }

  /**
   * Getter for the description instance field
   * 
   * @return this object's description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for the adjRooms instance field
   * 
   * @return this object's list of rooms adjacent to it
   */
  public ArrayList<Room> getAdjacentRooms() {
    return this.adjRooms;
  }

  /**
   * Sets the processing for the class
   * 
   * @param processing the PApplet that this room will use to draw to the window
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Room.processing = processing;
  }

  /**
   * Takes the given room and adds it to this object's list of adjacent rooms
   * 
   * @param toAdd room to be added to the adjacent rooms list
   */
  public void addToAdjacentRooms(Room toAdd) {
    this.adjRooms.add(toAdd);
  }

  /**
   * Checks whether this given room is adjacent to this one or not.
   * 
   * @param r The room that you are seeing if it is adjacent to this.
   * @return true if they are adjacent, false otherwise
   */
  public boolean isAdjacent(Room r) {
    if (adjRooms.contains(r))
      return true;
    else
      return false;
  }

  /**
   * Checks whether or not this Room is equal to the other.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Room) {
      Room otherRoom = (Room) other;
      return this.ID == otherRoom.ID;
    }

    return false;
  }

  /**
   * Returns a String representation of this Room.
   */
  @Override
  public String toString() {
    String s = this.ID + ": " + this.getDescription() + "\n Adjacent Rooms: ";

    for (int i = 0; i < adjRooms.size(); i++)
      s += adjRooms.get(i).getID() + " ";

    return s;
  }

  /**
   * Draws this Room to the window by drawing the background image, a rectangle, and some text
   */
  public void draw() {
    processing.image(image, 0, 0);
    processing.fill(-7028);
    processing.rect(0, 500, 800, 600);
    processing.fill(0);
    processing.text(toString(), 300, 525);
  }
}
