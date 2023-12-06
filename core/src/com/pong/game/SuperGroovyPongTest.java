package com.pong.game;

/**
 * Test method for the super groovy pong
 * @author Marco Puig
 */

public class SuperGroovyPongTest {

    private SuperGroovyPong game;

    public void setUp() {
        game = new SuperGroovyPong();
        game.create(); // Initialize the game
    }

    public void testPaddle1Update() {
        // Move paddle1 to a new position
        game.paddle1.setPosition(50, 100);

        // Trigger the update method
        game.render();

        // Check if the paddle1's position has been updated
        assertEqual(50, game.paddle1.getX(), 0);
        assertEqual(100, game.paddle1.getY(), 0);
    }

    public void testPaddle2Update() {
        // Move paddle2 to a new position
        game.paddle2.setPosition(500, 200);

        // Trigger the update method
        game.render();

        // Check if the paddle2's position has been updated
        assertEqual(500, game.paddle2.getX(), 0);
        assertEqual(200, game.paddle2.getY(), 0);
    }

    public void testBallUpdate() {
        // Move the ball to a new position
        game.ball.setPosition(300, 400);

        // Trigger the update method
        game.render();

        // Check if the ball's position has been updated
        assertEqual(300, game.ball.getX(), 0);
        assertEqual(400, game.ball.getY(), 0);
    }
    
    // Helper method to mimic JUnit's assertEquals
    private void assertEqual(double expected, double actual, double delta) {
        if (Math.abs(expected - actual) > delta) {
            throw new AssertionError("Expected: " + expected + ", Actual: " + actual);
        }
    }

    // Run the tests
    public static void main(String[] args) {
        SuperGroovyPongTest test = new SuperGroovyPongTest();
        test.setUp();
        test.testPaddle1Update();
        test.testPaddle2Update();
        test.testBallUpdate();
    }
}