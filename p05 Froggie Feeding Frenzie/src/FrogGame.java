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
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * This class is the start up of the FrogGame and is responsible for all of the methods that affect
 * how it is run
 */
public class FrogGame extends PApplet {
  private ArrayList<GameActor> gameActors; // array list of the gameActors in the game
  private int score; // the player's current score
  private PImage backgroundImg; // the image to use for the background
  private boolean isGameOver; // keeps track if the game is over, is true if the game is over
  private Random randGen; // random number generator
  private static final int BUG_COUNT = 5; // how many bugs should be on the screen at all times

  public static void main(String[] args) {
    PApplet.main("FrogGame");
  }

  /**
   * Is how big the size of the screen is
   */
  @Override
  public void settings() {
    // Sets width and height
    size(800, 600);
  }

  /**
   * Has everything needed to setup the parameters of the game
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Froggie Feeding Frenzie"); // set title of window
    this.imageMode(PApplet.CENTER); // images when drawn will use x,y as their center
    this.rectMode(PApplet.CENTER); // rectangles when drawn will use x,y as their center
    this.focused = true; // window is "active" upon start up
    this.textAlign(PApplet.CENTER); // text written to screen will have center alignment
    this.textSize(30); // text is 30 pt
    GameActor.setProcessing(this);
    Tongue.setProcessing(this);
    Hitbox.setProcessing(this);
    randGen = new Random();
    backgroundImg = loadImage("images/background.jpg");
    gameActors = new ArrayList<GameActor>();

    initGame();
  }

  /**
   * Responsible for drawing everything onto the screen
   */
  @Override
  public void draw() {
    image(backgroundImg, 400, 300);

    if (!isGameOver) {
      Frog frog = null;

      for (int i = 0; i < gameActors.size(); i++) {
        GameActor actor = gameActors.get(i);

        actor.draw();
        if (actor instanceof Moveable) {
          ((Moveable) actor).move();
        }

        if (actor instanceof Frog) {
          frog = (Frog) actor;
        }
      }

      runGameLogicChecks();

      if (frog != null) {
        fill(255); // Set text color to white
        text("Health: " + frog.getHealth(), 80, 40);
        text("Score: " + score, 240, 40);
      }
    } else {
      fill(255); // Set text color to white
      textSize(50); // Increase text size for "GAME OVER"
      text("GAME OVER", width / 2, height / 2);
    }
  }

  /**
   * Determines what new bug is added to screen
   */
  private void addNewBug() {
    int bugType = (int) (Math.random() * 4);
    int x = (int) (Math.random() * 800);
    int y = (int) (Math.random() * 600);

    switch (bugType) {
      case 0:
        gameActors.add(new Bug(x, y, 25));
        break;
      case 1:
        gameActors.add(new BouncingBug(x, y, 2, 5));
        break;
      case 2:
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        gameActors.add(new CirclingBug(x, y, 25.0, new int[] {red, green, blue}));
        break;
      case 3:
        gameActors.add(new StrongBug(x, y, 3));
        break;
    }
  }

  /**
   * Calls the mousePressed function for the frog
   */
  @Override
  public void mousePressed() {
    for (int i = 0; i < gameActors.size(); i++) {
      GameActor actor = gameActors.get(i);
      if (actor instanceof Frog) {
        Frog frog = (Frog) actor;
        if (frog.isMouseOver()) {
          frog.mousePressed();
          break;
        }
      }
    }
  }

  /**
   * Calls the mouseReleased fucntion for the frog
   */
  @Override
  public void mouseReleased() {
    for (int i = 0; i < gameActors.size(); i++) {
      GameActor actor = gameActors.get(i);
      if (actor instanceof Frog) {
        Frog frog = (Frog) actor;
        frog.mouseReleased();
      }
    }
  }

  /**
   * Resets the game or starts the frog attack depending on what is pressed
   */
  @Override
  public void keyPressed() {
    if (key == ' ') {
      for (int i = 0; i < gameActors.size(); i++) {
        GameActor actor = gameActors.get(i);
        if (actor instanceof Frog) {
          Frog frog = (Frog) actor;
          frog.startAttack();
          break;
        }
      }
    } else if (key == 'r') {
      initGame();
    }
  }

  /**
   * creates the frog to start the game
   */
  public void initGame() {
    score = 0;
    isGameOver = false;
    gameActors.clear();

    // Create and add a Frog with 100 health to the list
    int frogX = 400;
    int frogY = 500;
    Frog frog = new Frog(frogX, frogY, 100);
    gameActors.add(frog);

    for (int i = 0; i < BUG_COUNT; i++) {
      addNewBug();
    }
  }

  /**
   * Runs the logic basics of the game
   */
  private void runGameLogicChecks() {
    Frog frog = null; // Initialize the Frog reference

    // Find the Frog in the gameActors list
    for (int i = 0; i < gameActors.size(); i++) {
      GameActor actor = gameActors.get(i);

      if (actor instanceof Frog) {
        frog = (Frog) actor;
        break; // Found the Frog, exit the loop
      }
    }

    if (frog == null) {
      throw new IllegalStateException("Frog not found in gameActors.");
    }

    // (1) Check if the Frog's tongue hits the edge of the screen
    try {
      if (frog.tongueHitBoundary()) {
        frog.stopAttack();
      }
    } catch (IllegalStateException e) {
      // Handle the IllegalStateException (e.g., log an error message)
    }

    // (2) Check every bug to see if it has been hit by the Frog's tongue
    for (int i = gameActors.size() - 1; i >= 0; i--) {
      GameActor actor = gameActors.get(i);

      if (actor instanceof Bug) {
        Bug bug = (Bug) actor;

        // Check if the Frog's tongue hits the bug (outside of the try-catch block)
        try {
          if (frog.getTongueHitbox().doesCollide(bug.getHitbox())) {
            // (2a1) Stop the frog's attack
            frog.stopAttack();

            // Remove the bug from the game
            gameActors.remove(i);

            // (2a3) Update the score
            score += 10;

            // (2a4) Add a new bug (of a random variety) to the game
            addNewBug();
          }
        } catch (IllegalStateException e) {
          // Handle the IllegalStateException (e.g., log an error message)
        }
      }

      if (actor instanceof StrongBug) {
        StrongBug strongBug = (StrongBug) actor;

        // Check if the Frog's tongue hits the StrongBug (outside of the try-catch block)
        try {
          if (frog.getTongueHitbox().doesCollide(strongBug.getHitbox())) {
            // (2b1) Stop the frog's attack
            frog.stopAttack();

            // The StrongBug takes damage and loses health
            strongBug.loseHealth();

            // (2b3) If the StrongBug is dead, do steps 2a1 - 2a4
            if (strongBug.isDead()) {
              // (2a1) Stop the frog's attack
              frog.stopAttack();

              // Remove the bug from the game
              gameActors.remove(i);

              // (2a3) Update the score
              score += 10;

              // (2a4) Add a new bug (of a random variety) to the game
              addNewBug();
            }
          }
        } catch (IllegalStateException e) {
          // Handle the IllegalStateException (e.g., log an error message)
        }
      }
    }

    // Check if the frog is hit by any of the bugs
    for (int i = 0; i < gameActors.size(); i++) {
      GameActor actor = gameActors.get(i);

      if (actor instanceof Bug) {
        Bug bug = (Bug) actor;

        if (frog.isHitBy(bug)) {
          // If the frog is hit by any of the bugs, it takes damage and loses health
          frog.loseHealth();
        }
      }
    }

    // If the Frog is dead, update the game so it is over
    if (frog.isDead()) {
      isGameOver = true;
    }
  }
}