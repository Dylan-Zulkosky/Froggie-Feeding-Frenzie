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
// Online Sources: I used the StrongBug javadocs to help create this method
///////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p05/doc/StrongBug.html)
//
///////////////////////////////////////////////////////////////////////////////


/**
 * A subclass of Bug that is Movable. These bugs only move when they are not at max health. When hit
 * they become smaller and start moving horizontally across the screen.
 */
public class StrongBug extends Bug implements Moveable {
  // the current health of this StringBug, updates when Bug takes damage
  private int currentHealth;
  // the max health of this StrongBug
  private final int MAX_HEALTH;
  // the number of points ALL StrongBugs are worth, 500
  private static final int POINTS = 500;

  /**
   * Creates a new StrongBug object at full health using the provided parameters.
   * 
   * @param x      - the x-coordinate for the center of this StrongBug
   * @param y      - the y-coordinate for the center of this StrongBug
   * @param health - the max health for this StrongBug Throws: IllegalArgumentException - with a
   *               descriptive message if health is below one
   */
  public StrongBug(float x, float y, int health) {
    super(x, y, health);
    if (health < 1) {
      throw new IllegalArgumentException("Health must be at least 1");
    }

    this.MAX_HEALTH = health;
    this.currentHealth = MAX_HEALTH;

  }

  /**
   * Moves this StrongBug 3 units to the right, wrapping around the screen when the center hits the
   * edge of the window. The Hitbox should move with the StrongBug. The x,y-coordinate of only
   * changes if the StrongBug should move.
   */
  @Override
  public void move() {
    if (shouldMove()) {
      // Calculate the new x-coordinate
      float newX = getX() + 3;

      // Wrap around the screen when the center hits the edge of the window
      if (newX > processing.width) {
        newX = 0; // Wrap to the left edge
      }

      // Update the x-coordinate
      setX(newX);

      // Update the hitbox position
      getHitbox().setPosition(newX, getY());
    }
  }

  /**
   * Reports if this StrongBug is dead.
   * 
   * @return - true if its currentHealth is 0 or less, false otherwise
   */
  public boolean isDead() {
    return currentHealth <= 0;
  }

  /**
   * Decreases the health of this StrongBug by 1.
   */
  public void loseHealth() {
    if (currentHealth > 0) {
      currentHealth--;
    }
  }

  /**
   * Reports if the StrongBug needs to move.
   * 
   * @return - true if the bug is NOT at full health, false otherwise
   */
  @Override
  public boolean shouldMove() {
    return currentHealth < MAX_HEALTH;
  }

  /**
   * Determines whether or not this bug has been eaten by the Frog.
   * 
   * @param f - the frog that has possibly eaten this bug
   * @return - true if this Bug's Hitbox overlaps that Frog's Tongue's Hitbox, false otherwise
   */
  @Override
  public boolean isEatenBy(Frog f) {
    Hitbox bugHitbox = getHitbox();
    Hitbox tongueHitbox = f.getTongueHitbox();

    if (bugHitbox != null && tongueHitbox != null) {
      if (bugHitbox.doesCollide(tongueHitbox)) {
        loseHealth(); // Decrease the health of the StrongBug

        // Calculate the new dimensions based on the initial dimensions
        float newX = getX() * 0.75f; // Keep the same X-coordinate
        float newY = getY() * 0.75f;

        // Update the dimensions of the bug's hitbox
        bugHitbox.changeDimensions(newX, newY);

        return true; // The bug is eaten
      }
    }

    return false;
  }

}