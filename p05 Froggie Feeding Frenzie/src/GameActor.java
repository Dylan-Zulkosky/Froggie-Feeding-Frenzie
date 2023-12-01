//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Creating a Froggie Feeding Frenzie Game
// Course: CS 300 Fall 2023
//
// Author: Dylan Zulkosky
// Email: dzulkosky@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: I used the GameActor javadocs to help create this class
///////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p05/doc/GameActor.html)
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PApplet;
import processing.core.PImage;

/**
 * An instantiable class for all game actors in the Froggie Feeding Frenzie game. Game actors are
 * images that are drawn the screen that also have hitboxes.
 */
public class GameActor {
  // the x,y-coordinates of the center stored as [x,y]
  private float[] coordinates;
  // the hitbox associated with this GameActor
  private Hitbox hitbox;
  // the image associated with this GameActor
  protected processing.core.PImage image;
  // PApplet to use to draw GameActors to the screen
  protected static processing.core.PApplet processing;

  /**
   * Constructor for a new GameActor object by setting the coordinates, loading the image, and
   * creating the hitbox.
   * 
   * @param x       - the x-coordinate for the center of this object and its hitbox
   * @param y       - the y-coordinate for the center of this object and its hitbox
   * @param imgPath - the path to the image that will be loaded in Throws: IllegalStateException -
   *                with a descriptive message if processing is null
   */
  public GameActor(float x, float y, String imgPath) {
    if (processing == null) {
      throw new IllegalStateException("Processing cannot be null to proceed with GameActor.");
    }

    // Creating the GameActor
    this.coordinates = new float[] {x, y};
    this.image = processing.loadImage(imgPath);
    this.hitbox = new Hitbox(x, y, this.image.width, this.image.height);
  }

  /**
   * Sets the processing for all GamActors
   * 
   * @param processing - the instance of a PApplet to draw onto
   */
  public static void setProcessing(processing.core.PApplet processing) {
    GameActor.processing = processing;
  }

  /**
   * Getter for the x-coordinate.
   * 
   * @return - the x-coordinate of center of this GameActor
   */
  public float getX() {
    return coordinates[0];
  }

  /**
   * Getter for the y-coordinate.
   * 
   * @return - the y-coordinate of center of this GameActor
   */
  public float getY() {
    return coordinates[1];
  }

  /**
   * Setter for the x-coordinate.
   * 
   * @param newX - the new x-coordinate for the center of this GameActor
   */
  public void setX(float newX) {
    coordinates[0] = newX;
  }

  /**
   * Setter for the y-coordinate.
   * 
   * @param newY - the new y-coordinate for the center of this GameActor
   */
  public void setY(float newY) {
    coordinates[1] = newY;
  }

  /**
   * Getter for the Hitbox.
   * 
   * @return - the Hitbox of this GameActor
   */
  public Hitbox getHitbox() {
    return hitbox;
  }

  /**
   * Moves this GameActors Hitbox to the provided x,y-coordinates
   * 
   * @param x - the new x-coordinate for the center of the GameActor's hitbox
   * @param y - the new y-coordinate for the center of the GameActor's hitbox
   */
  public void moveHitbox(float x, float y) {
    hitbox.setPosition(x, y);
  }

  /**
   * Draws the image of the GameActor to the screen.
   */
  public void draw() {
    processing.image(image, coordinates[0] - image.width / 2, coordinates[1] - image.height / 2);
  }
}