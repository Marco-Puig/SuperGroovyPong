package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.audio.Sound;
//libGDX Intersector class
import com.badlogic.gdx.math.Intersector; //use intersector class for collision detection

/**
 * Class to handle collisions between the paddle and the ball
 * @author Laura Waldron, Marco Puig, Antonio Croissy
 */
public class CollisionUtils {
    //sounds instance
    private static Sound hitSound;

    /**
     * Method to initialize the sound when the ball hits
     * @author Laura Waldron
     */
    public static void initializeSound(){
        hitSound = Gdx.audio.newSound(Gdx.files.internal("assets\\scored.wav"));
    }

    /**
     * Method to handle a collision between a paddle and the ball
     * @author Laura Waldron
     * @param paddle the paddle
     * @param ball the ball
     * @return a collision between the two circles (true or false)
     */
    public static boolean collides(Paddle paddle, Ball ball) {
        //use intersector classs to check this
        // Convert Rectangle to Circle for collision check
        Circle paddleCircle = new Circle(paddle.getBoundingBox().getX() + paddle.getBoundingBox().getWidth() / 2,
        paddle.getBoundingBox().getY() + paddle.getBoundingBox().getHeight() / 2,
        Math.max(paddle.getBoundingBox().getWidth(), paddle.getBoundingBox().getHeight()) / 2);

        // Check for collision between the converted Circle and the Ball's Circle
        return Intersector.overlaps(paddleCircle, ball.getBoundingCircle());
    }

    /**
     * Method to handle a collision detected before
     * @author Laura Waldron
     * @param paddle the paddle
     * @param ball the ball
     */
    public static void handleCollision(Paddle paddle, Ball ball) {
        //reverse the ball's velocity upon a collision
        ball.reverseVelocityX();
        playHitSound();
    }

    /**
     * Method to handle if the ball goes out of bounds
     * @author Marco Puig
     * @param ball the ball
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @return false if ball is out of bounds
     */
    public static boolean isOutOfBounds(Ball ball, int screenWidth, int screenHeight) {
        return ball.getX()-ball.getWidth()/2 < 0 || 
        ball.getX() + ball.getWidth() / 2 > screenWidth ||
        ball.getY() - ball.getWidth() / 2 < 0 ||
        ball.getY() + ball.getWidth() / 2 > screenHeight;
    }

    /**
     * Method to reset the ball to the middle of the screen
     * @author Antoinio Croissy
     * @param ball the ball
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public static void resetBall(Ball ball, int screenWidth, int screenHeight) {
        //reset the ball to the center of the screen
        ball.setPosition(screenWidth/2, screenHeight/2);
    }

    /**
     * Method to play the sound when the ball hits
     * @author Laura Waldron
     */
    private static void playHitSound(){
        //method to play the sound
        if(hitSound != null){
            hitSound.play();
        }
    }
}
