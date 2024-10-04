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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class that sets up the Dragon Treasure Adventure Game and handles game logic and game-related
 * objects
 */
public class DragonTreasureGame extends PApplet {

  private ArrayList<Room> roomList; // list of rooms in the cave

  private File roomInfo; // file of each room's properties

  private File mapInfo; // file of map containing room connections

  private ArrayList<Character> characters; // list of key characters

  private boolean isDragonTurn = false; // instance of the dragon's turn

  private int gameState; // keeps track whether to keep playing or not

  /**
   * This sets up the game window
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Sets up the game by populating the rooms, player, dragon, and initial game state
   */
  @Override
  public void setup() {

    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); // Images are drawn using the x,y-coordinate
    // as the top-left corner
    this.rectMode(PApplet.CORNERS); // When drawing rectangles interprets args
    // as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); // sets the font size for the text
    roomInfo = new File("roominfo.txt");
    mapInfo = new File("map.txt");
    roomList = new ArrayList<Room>();
    characters = new ArrayList<Character>();
    TreasureRoom.setTreasureBackground(loadImage("images" + File.separator + "treasure.jpg"));
    PortalRoom.setPortalImage(loadImage("images" + File.separator + "portal.png"));
    gameState = 0;
    Room.setProcessing(this);
    loadRoomInfo();
    loadMap();
    loadCharacters();


  }

  /**
   * Loops gameplay loop and displays current room image
   */
  public void draw() {
    if (gameState == 1 || gameState == 2)
      return;
    Player player = (Player) playerStatus(characters, "PLAYER");
    Character keyHolder = playerStatus(characters, "KEYHOLDER");
    player.getCurrentRoom().draw();
    Dragon dragon = (Dragon) playerStatus(characters, "DRAGON");;
    if (dragon.getCurrentRoom().isAdjacent(player.getCurrentRoom()))
      System.out.println(Dragon.getDragonWarning());
    if (getRoomByID(3).isAdjacent(player.getCurrentRoom()))
      System.out.println(PortalRoom.getPortalWarning());
    if (getRoomByID(9).isAdjacent(player.getCurrentRoom()))
      System.out.println(TreasureRoom.getTreasureWarning());

    if (player.getCurrentRoom().getID() == keyHolder.getCurrentRoom().getID()
        && player.hasKey() == false) {
      System.out.println("KEY OBTAINED");
      player.obtainKey();
    }

    if (gameState == 0 && isDragonTurn == true) {
      dragon.changeRoom(dragon.pickRoom());
      isDragonTurn = false;
    }
    if (player.getCurrentRoom().equals(getRoomByID(3)))
      System.out.println(PortalRoom.getPortalWarning());

    if (player.getCurrentRoom().equals(getRoomByID(9)) && player.hasKey() && gameState == 0)
      gameState = 1;

    if (player.teleport())
      System.out.println(PortalRoom.getTeleportMessage());
    if (player.getCurrentRoom().getID() == 3)
      player.changeRoom(((PortalRoom) player.getCurrentRoom()).getTeleportLocation());

    if (player.getCurrentRoom().getID() == 9) {
      gameState = 1;
      System.out.println("YOU WON!");
    }
    if (dragon.getCurrentRoom().equals(player.getCurrentRoom())) {
      gameState = 2;
      System.out.print(Dragon.getDragonEncounter());
      System.out.println("YOU LOSE!");
    }
  }

  /**
   * Returns the selected character in the characters list
   * 
   * @param characters list containing player, dragon, and keyholder
   * @param character  the selected character type
   */
  private Character playerStatus(ArrayList<Character> characters, String character) {
    for (int i = 0; i < characters.size(); i++)
      if (characters.get(i).getLabel().equals(character))
        return characters.get(i);
    return null;
  }

  /**
   * Loads in room info using the file stored in roomInfo
   * 
   * @author Michelle
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {

      // scanner to read from file
      fileReader = new Scanner(roomInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();

        // parse info and create new room
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); // get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;

        if (parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);

        }

        if (parts.length == 4) {
          description = parts[3].trim(); // get the room description
        }

        switch (parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }

        if (newRoom != null) {
          roomList.add(newRoom);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally {
      if (fileReader != null)
        fileReader.close(); // close scanner regardless of what happened for security reasons :)
    }
  }

  /**
   * Loads in room connections using the file stored in mapInfo
   * 
   * @author Michelle
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
      // scanner to read from file
      fileReader = new Scanner(mapInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {

        // parse info
        String nextLine = fileReader.nextLine();
        String parts[] = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);

        Room toEdit = getRoomByID(id); // get the room we need to update info for adjacent rooms

        // add all the rooms to the adj room list of toEdit
        for (int i = 1; i < parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally { // close scanner regardless of what happened for security reasons :)
      if (fileReader != null)
        fileReader.close();
    }
  }

  /**
   * Get the room objected associated with the given ID.
   * 
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id) {
    int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));
    Room toEdit = roomList.get(indexToEdit);
    return toEdit;
  }

  private void loadCharacters() {
    System.out.println("Adding characters...");
    characters.add(new Character(getRoomByID(5), "KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
  }

  /**
   * Specific keys (1-9) dictates the player movement when prompted
   */
  @Override
  public void keyPressed() {
    Player player = (Player) playerStatus(characters, "PLAYER");
    if (!(gameState == 0))
      return;
    if (player.canMoveTo(getRoomByID(Integer.parseInt(String.valueOf(key))))) {
      player.changeRoom(getRoomByID(Integer.parseInt(String.valueOf(key))));
      isDragonTurn = true;
      draw();
    } else {
      System.out.println("not a valid room to move to. Pick a valid one");
    }
  }

  /**
   * Begins the game application software
   * 
   * @param args none
   */
  public static void main(String[] args) {
    PApplet.main("DragonTreasureGame");
  }


}

