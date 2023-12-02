package com.pong.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
//import libraries to control the ball
import com.badlogic.gdx.scenes.scene2d.ui.List;
import java.util.ArrayList;
import java.util.Random; //use the random library to make the ball move randomly
import java.util.Iterator;

import com.badlogic.gdx.Gdx;


/**
 * Class handling the Ball's definition
 * @author Marco Puig
 */
public class Ball {
    //initialize variables to handle the ball
    private int x, y, width, height;
    private int speed = 5;
    private int velocityX, velocityY;
    private Circle boundingCircle;
    private int score1 = 0;
    private int score2 = 0;

    /**
     * Method to declare a ball
     * @param x the x position of the ball
     * @param y the y position of the ball
     * @param width the width of the ball
     * @param height the height of the ball
     */
    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingCircle = new Circle(x, y, width / 2);
        resetSpeed();
    }

    /**
     * Method to reverse the ball's x velocity
     * @author Laura Waldron
     */
    public void reverseVelocityX(){
        velocityX = -velocityX;
    }
    /**
     * Method to reverse the ball's y velocity
     * @author Laura Waldron
     */
    public void reverseVelocityY(){
        velocityY = -velocityY;
    }
    /**
     * Method to get the X value of the ball
     * @return the x value of the ball
     */
    public float getX(){
        return x;
    }
    /**
     * Method to get the Y value of the ball
     * @return the y value of the ball
     */
    public float getY(){
        return y;
    }
    /**
     * Method to get the width of the ball
     * @return the width of the ball
     */
    public float getWidth(){
        return width;
    }
    /**
     * Method to set the position of the ball
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setPosition(float x, float y){
        this.x = (int)x;
        this.y = (int)y;
        boundingCircle.setPosition(x,y);
    }
    /**
     * Method to get the circle (ball)
     * @return the circle
     */
    public Circle getBoundingCircle(){
        return boundingCircle;
    }

    /**
     * Method to update the ball's position upon collision
     * @author Laura Waldron
     * @param paddle1 the first paddle
     * @param paddle2 the second paddle
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public void update(Paddle paddle1, Paddle paddle2, int screenWidth, int screenHeight) {
        //update the ball position based on the velocity
        x += velocityX;
        y += velocityY;

        //check to see if it collided with a wall and on which side to update the score
        if ((x + width / 2) > screenWidth){
            //reverse the horizontal velocity 
            reverseVelocityX();
            //give the right player a point 
            resetSpeed();
            // play sound on scored
            Gdx.audio.newSound(Gdx.files.internal("audio/scored.wav")).play();
            score1++;
            //reset the ball position to the center again
            resetBall();
        }
        else if((x - width / 2) < 0){
            //reverse the horizontal velocity
            reverseVelocityX();
            //give the left player a point
            resetSpeed();
            // play sound on scored
            Gdx.audio.newSound(Gdx.files.internal("audio/scored.wav")).play();
            score2++;
            //reset the ball position to the center
            resetBall();
        }

        // Check for collisions with paddles
        Rectangle ballRectangle = new Rectangle(x - width / 2, y - width / 2, width, height);

        if (Intersector.overlaps(ballRectangle, paddle1.getBoundingRectangle()) || 
            Intersector.overlaps(ballRectangle, paddle2.getBoundingRectangle())) {
            // Reverse the horizontal velocity if the ball hits a paddle
            reverseVelocityX();
            // play sound on hit
            Gdx.audio.newSound(Gdx.files.internal("audio/hit.wav")).play();
            increaseSpeed();
        }

        //check for a collsision in the top and bottom walls
        if (y - width / 2 < 0 || y + width / 2 > screenHeight) {
            // Reverse the vertical velocity if the ball hits the top or bottom wall
            reverseVelocityY();
        }
        
        //update the BoundingCircle.setPosition
        boundingCircle.setPosition(x, y);
    }

    /**
     * Method to create the ball
     * @param shapeRenderer the shapeRenderer option to make the ball
     */
    public void render(ShapeRenderer shapeRenderer) {
        // Render the ball

        // Calculate color based on which player has last hit the ball
        float red = -velocityX;
        float blue = velocityX;
        float green = 0.0f;
        
        shapeRenderer.setColor(red, green, blue, 1);
        shapeRenderer.circle(x, y, width / 2);
    }
    
    /**
     * Get the y coordinate of the ball
     * @return the y coordinate
     */
    public int getBallY() {
        return y;
    }

    /**
     * Get the y coordinate of the ball
     * @return the y coordinate
     */
    public int getBallX() {
        return x;
    }

    /**
     * Method to get the score of the left player
     * @return the score of the left player
     */
    public int getScorePlayerLeft() {
        return score1;
    }

    /**
     * Method to get the score of the right player
     * @return the score of the right player
     */
    public int getScorePlayerRight() {
        return score2;
    }

    /**
     * Method to increase the speed of the ball
     * @author Marco Puig
     */
    public void increaseSpeed()
    {
        velocityX *= 1.2;
        velocityY *= 1.2;
    }

    /**
     * Method to reset the speed of the ball
     */
    public void resetSpeed()
    {
        Random random = new Random();
        velocityX = speed * (random.nextBoolean() ? 1 : -1);
        velocityY = speed * (random.nextBoolean() ? 1 : -1);
    }

    
    /**
     * Method to reset the position of the ball
     */
    private void resetBall()
    {
        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
    }

}
