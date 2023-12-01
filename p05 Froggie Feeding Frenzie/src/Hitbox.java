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
// Online Sources: Used the Hitbox javadocs to help create the method for this part of the project.
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p05/doc/Hitbox.html)
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PApplet;

/**
 * An instantiable class maintains data about Hitboxes used for different objects in the Froggie
 * Feeding Frenzie game. Hitboxes in this application are rectangles.
 * 
 * @author Michelle & Dylan Zulkosky
 */
public class Hitbox {
  /** the 2D coordinates of the center of this hitbox [x,y] */
  private float[] coordinates;
  /** the width of the box */
  private float width;
  /** the height of the box */
  private float height;
  /** the PApplet that the hitbox can draw on */
  private static PApplet processing;

  /**
   * Creates a new Hitbox object based on the given parameters.
   * 
   * @param x,      the x-coordinate of the center of the hitbox
   * @param y,      the y-coordinate of the center of the hitbox
   * @param width,  the width of the hitbox
   * @param height, the height of the hitbox
   * @throws IllegalStateException if processing is null
   * @author Michelle
   */
  public Hitbox(float x, float y, float width, float height) {
    if (Hitbox.processing == null)
      throw new IllegalStateException("Processing is null. setProcessing() must be called before "
          + "creating any Hitbox objects.");
    this.coordinates = new float[] {x, y};
    this.width = width;
    this.height = height;
  }

  /**
   * Changes the coordinates of the center of this Hitbox
   * 
   * @param x, the new x-coordinate of the Hitbox's center
   * @param y, the new y-coordinate of the Hitbox's center
   * @author Michelle
   */
  public void setPosition(float x, float y) {
    this.coordinates[0] = x;
    this.coordinates[1] = y;
  }

  /**
   * Detects if this Hitbox and another collide by overlapping.
   * 
   * @param other, the Hitbox to check for if it collides with this one
   * @return true if the 2 Hitboxes overlap, false otherwise
   * @author Dylan Zulkosky
   */
  public boolean doesCollide(Hitbox other) {
    // Calculate the coordinates of the endpoints of this hitbox
    float thisLeft = this.coordinates[0] - this.width / 2;
    float thisRight = this.coordinates[0] + this.width / 2;
    float thisTop = this.coordinates[1] - this.height / 2;
    float thisBottom = this.coordinates[1] + this.height / 2;

    // Calculate the coordinates of the endpoints of the other hitbox
    float otherLeft = other.coordinates[0] - other.width / 2;
    float otherRight = other.coordinates[0] + other.width / 2;
    float otherTop = other.coordinates[1] - other.height / 2;
    float otherBottom = other.coordinates[1] + other.height / 2;

    // Check for overlap of endpoints
    boolean isOverlapping = !(thisRight < otherLeft || thisLeft > otherRight
        || thisBottom < otherTop || thisTop > otherBottom);

    // Return true if endpoints are overlapping, false if they are not
    return isOverlapping;
  }

  /**
   * Sets the processing for all Hitboxes
   * 
   * @param processing, the instance of a PApplet to draw onto
   * @author Michelle
   */
  public static void setProcessing(PApplet processing) {
    Hitbox.processing = processing;
  }

  /**
   * Change both the width and height of this Hitbox
   * 
   * @param width,  the new width for the Hitbox
   * @param height, the new height for the Hitbox
   * @author Michelle
   */
  public void changeDimensions(float width, float height) {
    this.height = height;
    this.width = width;
  }

  /**
   * Draws the Hitbox to the screen. Provided solely for visualization and debugging purposes.
   * 
   * @author Michelle
   */
  public void visualizeHitbox() {
    processing.noFill(); // make the inside of the rectangle clear
    processing.strokeWeight(3); // make the lines thicker
    processing.stroke(255, 0, 0); // Set the stroke color to red (you can choose any color)

    // draw a rectangle to the screen
    processing.rect(coordinates[0], coordinates[1], width, height);
  }
}
