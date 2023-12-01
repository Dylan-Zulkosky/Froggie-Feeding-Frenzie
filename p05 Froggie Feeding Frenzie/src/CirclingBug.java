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
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////



/**
 * A subclass of Bug that is Movable and moves in a circle. When drawn to the screen they also have
 * a tint applied to the image
 */
public class CirclingBug extends Bug implements Moveable {
  // the x,y-coordinates for the center of the circle path
  private float[] circleCenter;
  // constant, number of points ALL CirclingBugs are worth 200
  private static final int POINTS = 200;
  // the radius of the circle path this bug moves on
  private double radius;
  // keeps track of how long the bug has been moving
  private double ticks;
  // color used to tint the image when being drawn [Red,Green,Blue]
  private int[] tintColor;

  /**
   * Creates a new CirclingBug object using the provided parameters. By default the number of ticks
   * for a new CirclingBug should be 0.0. The x,y-coordinates for the initial position of the Bug
   * must be calculated by using the equations given in the write-up for ticks = 0.0.
   * 
   * @param circleX   - the x-coordinate for the center of the circle path
   * @param circleY   - the y-coordinate for the center of the circle path
   * @param radius    - the radius of this CirclingBug's circle path
   * @param tintColor - an array containing the Red,Green, and Blue values for the tint color You
   *                  can assume that this array is ALWAYS length 3.
   */
  public CirclingBug(float circleX, float circleY, double radius, int[] tintColor) {
    super(circleX, circleY, 0);
    this.circleCenter = new float[] {circleX, circleY};
    this.radius = radius;
    this.ticks = 0.0;
    this.tintColor = tintColor;
  }

  /**
   * Draws the image to the screen, tinting it with the tintColor before drawing it. After the image
   * is drawn to the screen the tinting effect will need to done undone by tinting it again with
   * white. (255, 255, 255)
   */
  public void draw() {
    processing.tint(tintColor[0], tintColor[1], tintColor[2]);

    // Draw the image at the current position
    processing.image(image, getX(), getY());

    // Reset the tint to white to undo the effect
    processing.tint(255, 255, 255);
  }

  /**
   * Moves this CirclingBug to the next position on its circular path. (See write-up for more
   * details on how to calculate this path.) The Hitbox should move with the CirclingBug. The bug
   * only changes its xy-coordinates if it should move. Ticks will advance by 0.05 whenever this
   * method is called.
   */
  public void move() {
    if (shouldMove()) {
      // Advance the ticks first
      ticks += 0.05;

      // Calculate the new position based on ticks and radius
      double x = circleCenter[0] + radius * Math.cos(ticks);
      double y = circleCenter[1] + radius * Math.sin(ticks);

      // Set the new position for the Bug
      setX((float) x);
      setY((float) y);

      // Move the hitbox with the Bug
      moveHitbox((float) x, (float) y);
    }
  }

  /**
   * Reports if the CirclingBug needs to move.
   */
  public boolean shouldMove() {
    return true; // true, this bug ALWAYS moves
  }
}