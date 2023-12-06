package com.pong.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class to handle the paddle
 * Design Pattern: Observer Pattern
 * @author Marco Puig
 */
public class Paddle {
    private int x, y, width, height; //box width height
    private static int speed = 5;
    private Rectangle boundingBox;
    public State playerState;
    private int ballYposition;

    /**
     * Method to define a paddle
     * Subject of Observer Pattern
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width of the paddle
     * @param height the height of the paddle
     * @param playerState the state of the player
     */
    public Paddle(int x, int y, int width, int height, State playerState) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.playerState = playerState;
    }

    /**
     * Method to update the state
     * Design Pattern: State Pattern
     * State can be two players or an ai player
     */
    public void update() {
        // Movement code based on state

        // If main player
        if (playerState == State.playerOne) moveInputWASD();
        if (playerState == State.playerTwo) moveInputKeys();
        
        // call ai for State.playerAI
        if (playerState == State.playerAI) moveAI();

        // Update the bounding box position
        boundingBox.setPosition(x, y);
    }
    /**
     * Method to create a paddle (rectangle)
     * @param shapeRenderer create the paddle
     */
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, width, height);
    }

    /**
     * Method to create a bounding box
     * @return the bounding box
     */
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    /**
     * Method to create a rectangle
     * @return the rectangle
     */
    public Rectangle getBoundingRectangle(){
        return boundingBox;
    }

    /**
     * Method to move the input keys (WASD)
     * Observer for Observer Pattern
     * ConcreteState1 for State Pattern
     * @author Antoinio Croissy
     */
    public void moveInputKeys()
    {
        // Get Input
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed;
        }   
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed;
        } 

        if (x < (2*(Gdx.graphics.getWidth() / 3))) {
            x = 2*(Gdx.graphics.getWidth() / 3);
        }
        
        checkCollide();
    }
    
    /**
     * Method to move the WASD keys
     * ConcreteObserver for Observer Pattern
     * ConcreteState2 for State Pattern
     * @author Marco Puig
     */
    public void moveInputWASD()
    {
        // Get Input
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= speed;
        }   
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= speed;
        } 

        if (x > Gdx.graphics.getWidth() / 3) {
            x = Gdx.graphics.getWidth() / 3;
        }
        
        checkCollide();
    }

    /**
     * Method to move the AI Player
     * attach() method for Observer pattern
     * ConcreteState3 for State pattern
     * @author Antoinio Croissy
     */
    public void moveAI() {
        if (y < ballYposition) {
            y += speed;
        }
        if (y > ballYposition) {
            y -= speed;
        }
          
        checkCollide();
    }

    /**
     * Method to track the ball
     * @author Marco Puig
     * @param ball the moving ball
     */
    public void trackBall(Ball ball) {
        ballYposition = ball.getBallY();
    }

    /**
     * Method to check for a collision
     * notify() for Observer pattern
     * @author Marco Puig
     */
    public void checkCollide() {
        // Add boundary checking to prevent the paddle from going off-screen (Collision)
        
        // Y-axis boundary checking
        if (y < 0) {
            y = 0;
        }
        if (y > Gdx.graphics.getHeight() - height) {
            y = Gdx.graphics.getHeight() - height;
        }
    
        // X-axis boundary checking (limit movement to one-third of the screen)
        if (x < 0) {
            x = 0;
        }
        if (x > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - width;
        }
    } 

    /**
     * Set Position
     * @author Marco Puig
     */
    void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X position
     * @author Marco Puig
     */
    int getX()
    {
        return this.x;
    }

    /**
     * Get X position
     * @author Marco Puig
     */
    int getY()
    {
        return this.y;
    }


    /**
     * State declaration (3 states)
     * States for State Pattern
     * @author Marco Puig
     */
    public enum State {
        playerOne, //state 1 -- player one
        playerTwo, //state 2 -- player two
        playerAI //state 3 -- AI player
    }
}
