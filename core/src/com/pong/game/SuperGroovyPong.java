package com.pong.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pong.game.Paddle.State;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


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
    //screen instance
    // private StartScreen startScreen;

    boolean isPaused = false;

    public enum gameScreen {StartScreen, PlayScreen, PauseScreen, EndScreen}

    public gameScreen CurrentScreen;

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
        // startScreen = new StartScreen(this);
        //setScreen(startScreen);

        CurrentScreen = gameScreen.PlayScreen;

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

        checkGameState();
        // Update logic
        if (CurrentScreen == gameScreen.PlayScreen) {
            paddle1.update();
            paddle2.update();
            paddle2.trackBall(ball);
            ball.update(paddle1, paddle2, screenWidth, screenHeight);
        }

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
    
    public void startGame(){
        //setScreen(new SuperGroovyPong(this));
    }

    // Inside your SuperGroovyPong class
    public void showEndScreen(int finalScore) {
        //setScreen(new EndScreen(this, finalScore));
    }

    public void checkGameState() {

        gameOverCheck();
        pauseCheck();

        switch (CurrentScreen) {
            case PauseScreen:   
                
                break;
            case EndScreen:
                EndScreen endScreen = new EndScreen(this, ball.getScorePlayerLeft());
                endScreen.render(60);
                break;
            default:
                break;
        }
       
    }

    private void gameOverCheck() {
        if (ball.getScorePlayerLeft() < ball.getScorePlayerRight()-3) {
            CurrentScreen = gameScreen.EndScreen;
        }
    }

    private void pauseCheck() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            isPaused = !isPaused;
            System.out.println(isPaused);
        }

        if (isPaused) {
            // Handle any pause-related logic (e.g., stop animations or background music)
            CurrentScreen = gameScreen.PauseScreen;
        } else {
            // Handle resume logic (e.g., restart animations or resume background music)
            CurrentScreen = gameScreen.PlayScreen;
        } 
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
        //skin initialization
        //skin = new Skin(Gdx.files.internal("path/to/your/skin.json"));
    }
}
