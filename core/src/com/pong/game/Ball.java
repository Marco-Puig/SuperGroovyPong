package com.pong.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;
//Laura needs to work on the ball
public class Ball {
    private int x, y, width, height;
    private int speed = 5;
    private int velocityX, velocityY;
    private Circle boundingCircle;
    private int score1 = 0;
    private int score2 = 0;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingCircle = new Circle(x, y, width / 2);
        Random random = new Random();
        velocityX = speed * (random.nextBoolean() ? 1 : -1);
        velocityY = speed * (random.nextBoolean() ? 1 : -1);
    }

    public void update(Paddle paddle1, Paddle paddle2, int screenWidth, int screenHeight) {
        // Add logic for updating the ball's position and handling collisions
        //update the ball position based on the velocity
        x+= velocityX;
        x+= velocityY;

        //check to see if it collided with a wall
        if( x-width/2 <0 || x+ width/2 > screenWidth){
            //reverse the horizontal velocity
            velocityX = -velocityX;
        }

        //check to see if it collided with a paddle
        // Check for collisions with paddles
        Rectangle ballRectangle = new Rectangle(x - width / 2, y - width / 2, width, height);

        if (Intersector.overlaps(ballRectangle, paddle1.getBoundingRectangle()) ||
            Intersector.overlaps(ballRectangle, paddle2.getBoundingRectangle())) {
            // Reverse the horizontal velocity if the ball hits a paddle
            velocityX = -velocityX;
        }

        //check for a collsision in the top and bottom walls
        if (y - width / 2 < 0 || y + width / 2 > screenHeight) {
            // Reverse the vertical velocity if the ball hits the top or bottom wall
            velocityY = -velocityY;
        }
        //update the BoundingCircle.setPosition
        boundingCircle.setPosition(x,y);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(x, y, width / 2);
    }

    public int getBallY() {
        return y;
    }
    
    public int getScorePlayerLeft() {
        return score1;
    }

    public int getScorePlayerRight() {
        return score2;
    }
}
