package com.pong.game;

import com.badlogic.gdx.math.Circle;
//libGDX Intersector class
import com.badlogic.gdx.math.Intersector; //use intersector class for collision detection

public class CollisionUtils {
    public static boolean collides(Paddle paddle, Ball ball) {
        // Add logic for detecting collisions between the paddle and the ball
        //return false;
        //according to Chatgpt can use intersector to check this
        // Convert Rectangle to Circle for collision check
        Circle paddleCircle = new Circle(paddle.getBoundingBox().getX() + paddle.getBoundingBox().getWidth() / 2,
        paddle.getBoundingBox().getY() + paddle.getBoundingBox().getHeight() / 2,
        Math.max(paddle.getBoundingBox().getWidth(), paddle.getBoundingBox().getHeight()) / 2);

        // Check for collision between the converted Circle and the Ball's Circle
        return Intersector.overlaps(paddleCircle, ball.getBoundingCircle());
    }

    public static void handleCollision(Paddle paddle, Ball ball) {
        // Add logic for handling collisions between the paddle and the ball
        //reverse the ball's velocity upon a collision
        ball.reverseVelocityX();
    }

    public static boolean isOutOfBounds(Ball ball, int screenWidth, int screenHeight) {
        // Add logic for checking if the ball is out of bounds
        //return false;
        return ball.getX()-ball.getWidth()/2 < 0 || 
        ball.getX() + ball.getWidth() / 2 > screenWidth ||
        ball.getY() - ball.getWidth() / 2 < 0 ||
        ball.getY() + ball.getWidth() / 2 > screenHeight;
    }

    public static void resetBall(Ball ball, int screenWidth, int screenHeight) {
        // Add logic for resetting the ball to the center of the screen
        ball.setPosition(screenWidth/2, screenHeight/2);
    }
}
