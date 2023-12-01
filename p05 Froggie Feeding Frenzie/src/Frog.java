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
// Online Sources: I used the Frog class javadocs to help create this class
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p05/doc/Frog.html)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * 
 * An instantiable class maintains data about a Frog in the Froggie Feeding Frenzie game. They an be
 * drawn to the screen, dragged around by the mouse, and attack Bugs with its Tongue.
 */
public class Frog extends GameActor implements Moveable {
  // how much health the frog has
  private int health;
  // path to the image to use for the frog
  private static final String IMG_PATH = "images/frog.png";
  // keeps track of if the Frog is being dragged by the mouse
  private boolean isDragging;
  // the previous x-coordinate of the mouse
  private float oldMouseX;
  // the previous y-coordinate of the mouse
  private float oldMouseY;
  // the tongue belonging to this frog
  private Tongue tongue;

  /**
   * Constructor for a new Frog object using the provided parameters. The Frog is NOT dragging by
   * default.
   * 
   * @param x      - the x-coordinate for the center of the Frog and starting point of the tongue
   * @param y      - the y-coordinate for the center of the Frog and starting point of the tongue
   * @param health - the initial health of this Frog Throws: IllegalArgumentException - with a
   *               descriptive message if health is less than 1
   */
  public Frog(float x, float y, int health) {
    super(x, y, IMG_PATH);
    if (health < 1) {
      throw new IllegalArgumentException("Health must be greater than or equal to 1.");
    }
    this.health = health;
    this.isDragging = false;
    this.oldMouseX = 0;
    this.oldMouseY = 0;
    this.tongue = new Tongue(x - 50, y - 70);
  }

  /**
   * Draws the image of the Frog to the screen. If the Frog's tongue is active: (1)draw the tongue
   * and (2) extend the tongue by moving it's x-coordinate to the Frog's x-coordinate and up 2
   * pixels.
   */
  @Override
  public void draw() {
    // starts drawing
    this.tongue.draw();
    super.draw();
    
    // Extends frog tongue
    if (tongue.isActive()) {
      tongue.extend(this.getX(), -2);
    }
  }

  /**
   * Moves the Frog by dragging it by the mouse, if it should be dragging. (See write-up for more
   * details on implementing the dragging functionality.) The starting point of the tongue and the
   * Hitbox need to move along with the Frog. If the Frog's tongue is NOT active, move the tongue's
   * endpoint along with the Frog as well. The Frog only moves if it should move.
   */
  @Override
  public void move() {
 // Check to see if it should move
    if (isMouseOver() && shouldMove()) {
        float dx = processing.mouseX - this.oldMouseX;
        float dy = processing.mouseY - this.oldMouseY;
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);

        // Update the starting point of the tongue
        this.tongue.updateStartPoint(this.getX() - 50, this.getY() - 50);
        this.tongue.getHitbox().setPosition(this.getX() - 50, this.getY() - 50);

        // Update the hitbox position to match the frog
        this.moveHitbox(this.getX(), this.getY());
        
    }

    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  /**
   * Determines whether or not this Frog has run into a Bug.
   * 
   * @param b - the Bug to check that if it collides with the Frog
   * @return - true if the Bug's Hitbox and Frog's Hitbox overlap, false otherwise
   */
  public boolean isHitBy(Bug b) {
    if (this.getHitbox().doesCollide(b.getHitbox())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the Hitbox for this Frog's tongue.
   * 
   * @return - this Frog's tongue's hitbox Throws: IllegalStateException - if the tongue is
   *         currently inactive
   */
  public Hitbox getTongueHitbox() {
    if (!tongue.isActive()) {
      throw new IllegalStateException();
    }
    return tongue.getHitbox();
  }

  /**
   * Decreases the health of this Frog by 1.
   */
  public void loseHealth() {
    this.health--;
  }

  /**
   * Gets the current health of the Frog
   * 
   * @return - the current health of this Frog
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Reports if the Frog needs to move on the screen.
   * 
   * @return - true if the Frog is being dragged, false otherwise
   */
  @Override
  public boolean shouldMove() {
    if (isDragging) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Determines if this frog is dead.
   * 
   * @return - true if this Frog's health is 0 or lower, false otherwise
   */
  public boolean isDead() {
    if (this.health <= 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Changes this Frog so it is now being dragged. This method should only be called externally when
   * the mouse is over this frog and has been clicked.
   */
  public void mousePressed() {
    if (isDragging = isMouseOver()) {
      this.isDragging = true;
    }
  }

  /**
   * Changes this Frog so it is no longer being dragged. This method should only be called
   * externally when the mouse has been released.
   */
  public void mouseReleased() {
    this.isDragging = false;
  }

  /**
   * Determines if the mouse is over the Frog's image
   * 
   * @return - true, if the mouse is inside the Frog's bounding box of the image, false otherwise
   */
  public boolean isMouseOver() {
    float xMi = (this.getX()) - image.width / 2;
    float xMa = (this.getX()) + image.width / 2;
    float yMi = (this.getY()) - image.height / 2;
    float yMa = (this.getY()) + image.height / 2;
    if ((processing.mouseX > xMi && processing.mouseX < xMa)
        && (processing.mouseY > yMi && processing.mouseY < yMa)) {
      return true;
    }
    return false;
  }

  /**
   * Starts an attack by resetting the tongue to it's default state and activating the tongue.
   */
  public void startAttack() {
    tongue.reset();
    tongue.activate();
    // Stops if tongue hits top of screen
    if (tongueHitBoundary()) {
      // stops attacking
      this.stopAttack();
    }
  }

  /**
   * Stops the attack by deactivating the tongue.
   */
  public void stopAttack() {
    // stop attacking
    tongue.deactivate();
  }

  /**
   * Reports if this Frog's tongue has hit the top of the screen.
   * 
   * @return - true if the tongue has hit the top of the screen, false otherwise
   */
  public boolean tongueHitBoundary() {
    return tongue.hitScreenBoundary();
  }
}