package com.pong.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {
    private int x, y, width, height; //box width height
    private static int speed = 5;
    private Rectangle boundingBox;
    public State playerState;
    private int ballYposition;

    public Paddle(int x, int y, int width, int height, State playerState) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.playerState = playerState;
    }

    public void update() {
        // Movement code based on state

        // If main player
        if (playerState == State.playerOne) moveInputKeys();
        if (playerState == State.playerTwo) moveInputWASD();
        
        // call ai for State.playerAI
        if (playerState == State.playerAI) moveAI();

        // Update the bounding box position
        boundingBox.setPosition(x, y);
    }
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, width, height);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public Rectangle getBoundingRectangle(){
        return boundingBox;
    }

    public void moveInputKeys()
    {
        // Get Input
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed;
        }   
        
        checkCollide();
    }
    
    public void moveInputWASD()
    {
        // Get Input
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= speed;
        }   
        
        checkCollide();
    }

    public void moveAI() {
        if (y < ballYposition) {
            y += speed;
        }
        if (y > ballYposition) {
            y -= speed;
        }
        
        checkCollide();
    }

    public void trackBall(Ball ball) {
        ballYposition = ball.getBallY();
    }


    public void checkCollide()
    {
        // Add boundary checking to prevent the paddle from going off-screen (Collision)
        if (y < 0) {
                y = 0;
        }
        if (y > Gdx.graphics.getHeight() - height) {
            y = Gdx.graphics.getHeight() - height;
        } 
    }

    public enum State {
        playerOne,
        playerTwo,
        playerAI
    }
}
