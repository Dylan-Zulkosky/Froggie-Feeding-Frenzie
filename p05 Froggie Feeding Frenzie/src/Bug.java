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

import java.io.File;
import processing.core.PImage;

/*
 * An instantiable class maintains data about a Bug in the Froggie Feeding Frenzie game. These bugs
 * do not move, can be drawn to the screen, and detect if it has been hit (eaten) by a Frog.
 */
public class Bug extends GameActor {
  // path to the image used for bugs, all bugs use the same image
  private static final String IMG_PATH = "images/bug.png";
  // how many points this bug gives for being eaten
  private int points;

  /**
   * Creates a new Bug object with the provided information.
   * 
   * @param x      - the x-coordinate for the center of this bug
   * @param y      - the y-coordinate for the center of this bug
   * @param points - the number of points the Bug is worth
   */
  public Bug(float x, float y, int points) {
    super(x, y, IMG_PATH);
    this.points = points;
  }

  /**
   * Gets how many points this Bug is worth
   * 
   * @return - the number of points this Bug is worth
   */
  public int getPoints() {
    return this.points;
  }

  /**
   * Determines whether or not this bug has been eaten by the Frog.
   * 
   * @param f - the frog that has possibly eaten this bug
   * @return - true if this Bug's Hitbox overlaps that Frog's Tongue's Hitbox, false otherwise note
   *         this method should also return false if the tongue is inactive
   */
  public boolean isEatenBy(Frog f) {
    try {
      if (f.getTongueHitbox().doesCollide(this.getHitbox())) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }
}

