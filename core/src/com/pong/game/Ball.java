package com.pong.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import java.util.Random;

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
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(x, y, width / 2);
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }
}
