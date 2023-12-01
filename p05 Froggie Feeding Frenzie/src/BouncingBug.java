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
// Online Sources: I used the BouncingBug javadocs to help create this method
/////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p05/doc/BouncingBug.html)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;

/**
 * A subclass of Bug that is Movable. These bugs bounce around the screen like a DVD player logo.
 */
public class BouncingBug extends Bug implements Moveable {
  // keeps track if bug is moving up or down
  private boolean goDown;
  // keeps track if bug is moving left or right
  private boolean goLeft;
  // a constant, number of points ALL BouncingBugs are worth, 100
  private static final int POINTS = 100;
  // a random generator to determine the initial directions
  private Random randGen;
  // the number of pixels to move horizontally and vertically, formatted [dx,dy]
  private int[] speedNums;

  /**
   * Creates a new Bouncing Bug object using the given parameters. Randomly determines if the bug
   * will initially move left or right. The same applies to if the bug will move up or down.
   * 
   * @param x  - the x-coordinate for the center of this BouncingBug
   * @param y  - the y-coordinate for the center of this BouncingBug
   * @param dx - the number of pixels to move horizontally
   * @param dy - the number of pixels to move vertically
   */
  public BouncingBug(float x, float y, int dx, int dy) {
    super(x, y, dx); 
    randGen = new Random();

    // Randomly determine if the bug will move left or right
    goLeft = randGen.nextBoolean();

    // Randomly determine if the bug will move up or down
    goDown = randGen.nextBoolean();

    // Set the speed values
    speedNums = new int[]{dx, dy};
  }

  /**
   * Moves this BouncingBug dx pixels left or right (depending on the current horizontal direction)
   * and dy pixels up or down (depending on the current vertical direction). The Bug's Hitbox should
   * also move with the BouncingBug. The bug only changes its xy-coordinates if it should move. If
   * the center of the Bouncing Bug hits an end of the window it will switch directions. Ex. The Bug
   * is going down and left and hits the left side of the screen then the Bug will be going down and
   * right.
   */
  public void move() {
    if (shouldMove()) {
      // Calculate the new coordinates
      float newX = getX();
      float newY = getY();

      // Update the x-coordinate based on the current horizontal direction
      if (goLeft) {
          newX -= speedNums[0];
      } else {
          newX += speedNums[0];
      }

      // Update the y-coordinate based on the current vertical direction
      if (goDown) {
          newY += speedNums[1];
      } else {
          newY -= speedNums[1];
      }

      // Update the bug's position
      setX(newX);
      setY(newY);

      // Manually update the Hitbox's coordinates
      getHitbox().setPosition(newX, newY);

      // Check if the bug hits the window boundaries and change direction if needed
      if (newX < 0 || newX > processing.width) {
          goLeft = !goLeft;
      }
      if (newY < 0 || newY > processing.height) {
          goDown = !goDown;
      }
  }
  }

  /**
   * Reports if the BouncingBug needs to move.
   * 
   * @return - true, this Bug ALWAYS moves
   */
  public boolean shouldMove() {
    return true;
  }

}