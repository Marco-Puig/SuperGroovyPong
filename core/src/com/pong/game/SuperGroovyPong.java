package com.pong.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pong.game.Paddle.State;


/**
 * Main Super Groovy Pong Class, using the Application Adapter
 * @author Marco Puig
 */
public class SuperGroovyPong extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int screenWidth, screenHeight;
    private Paddle paddle1, paddle2;
    private Ball ball;
    private BitmapFont font;
    //music instance
    private SGPSounds SGPSounds;


    /**
     * Method to create the new game
     */
    // On Start
    @Override
    public void create () {
        //set the starting screen
        //setScreen(new StartScreen(this));

        // Call needed dependencies
        libRequired();

        // Initialize Objects once we go past Start Screen        
        paddle1 = new Paddle(20, screenHeight / 2 - 40, 20, 80, State.playerOne);
        paddle2 = new Paddle(screenWidth - 40, screenHeight / 2 - 40, 20, 80, State.playerAI); // can also say State.playerAI
        ball = new Ball(screenWidth / 2, screenHeight / 2, 20, 20);

        //load and play the audio in the background
        SGPSounds = new SGPSounds("audio\\game-theme.mp3");
        SGPSounds.play();
    }

    /**
     * Method to render the screen
     */
    // Render on every frame
    @Override
    public void render () {

        // Graphics lib
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update logic
        paddle1.update();
        paddle2.update();
        paddle2.trackBall(ball);
        ball.update(paddle1, paddle2, screenWidth, screenHeight);

        // Draw objects
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        paddle1.render(shapeRenderer);
        paddle2.render(shapeRenderer);
        ball.render(shapeRenderer);

        // Draw Score
        font.draw(batch, Integer.toString(ball.getScorePlayerLeft()), screenWidth / 2 - 50, 50);
        font.draw(batch, Integer.toString(ball.getScorePlayerRight()), screenWidth / 2 + 25, 50);

        // Needed calls for render
        batch.end();
        shapeRenderer.end();
    }


    /**
     * Method to dispose of the screen when the game is over
     */
    // Dispose when completed
    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }

    // Inside your SuperGroovyPong class
    public void showEndScreen(int finalScore) {
        //setScreen(new EndScreen(this, finalScore));
    }

    /**
     * Needed requirements for the game
     * @author Marco Puig
     */
    public void libRequired()
    {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }
}
