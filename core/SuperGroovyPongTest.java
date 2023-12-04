import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Test method for the super groovy pong
 * @author Marco Puig
 */
public class SuperGroovyPongTest {

    private SuperGroovyPong game;

    @Before
    public void setUp() {
        game = new SuperGroovyPong();
        game.create(); // Initialize the game
    }

    @Test
    public void testPaddle1Update() {
        // Move paddle1 to a new position
        game.paddle1.setPosition(50, 100);

        // Trigger the update method
        game.render();

        // Check if the paddle1's position has been updated
        assertEquals(50, game.paddle1.getX(), 0);
        assertEquals(100, game.paddle1.getY(), 0);
    }

    @Test
    public void testPaddle2Update() {
        // Move paddle2 to a new position
        game.paddle2.setPosition(500, 200);

        // Trigger the update method
        game.render();

        // Check if the paddle2's position has been updated
        assertEquals(500, game.paddle2.getX(), 0);
        assertEquals(200, game.paddle2.getY(), 0);
    }

    @Test
    public void testBallUpdate() {
        // Move the ball to a new position
        game.ball.setPosition(300, 400);

        // Trigger the update method
        game.render();

        // Check if the ball's position has been updated
        assertEquals(300, game.ball.getX(), 0);
        assertEquals(400, game.ball.getY(), 0);
    }

    @Test
    public void testStartGame() {
        // Call the startGame method
        game.startGame();
    }

    @Test
    public void testShowEndScreen() {
        // Call the showEndScreen method with a mock finalScore
        game.showEndScreen(100);
    }

}
