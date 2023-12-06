package com.pong.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pong.game.Paddle.State;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * Main Super Groovy Pong Class, using the Application Adapter
 * Model: for the MVC
 * Design Pattern: Application Adapter
 * @author Marco Puig
 */
public class SuperGroovyPong extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int screenWidth, screenHeight;
    public Paddle paddle1, paddle2;
    public Ball ball;
    private BitmapFont font;
    Skin skin;

    //music instance
    private SGPSounds SGPSounds;

    //Flags
    private boolean isPaused;
    private boolean gameOver;

    public enum gameScreen {StartScreen, PlayScreen, PauseScreen, EndScreen}

    public gameScreen CurrentScreen;

    private StartScreen startScreen; 

    /**
     * Method to create the new game
     * Create method of Application Adapter
     * Initializes code, creating an instance of the game with resources
     */
    // On Start
    @Override
    public void create () {
        // Call needed dependencies
        libRequired();
        
        // Initialize the StartScreen instance
        startScreen = new StartScreen(this);

        //Set flags related to game states and screens
        CurrentScreen = gameScreen.StartScreen;
        gameOver = false;
        isPaused = false;

        // Initialize Objects once we go past Start Screen        
        paddle1 = new Paddle(20, screenHeight / 2 - 40, 20, 80, State.playerOne);
        ball = new Ball(screenWidth / 2, screenHeight / 2, 20, 20);

        //load and play the audio in the background
        SGPSounds = new SGPSounds("audio\\game-theme.mp3");
        SGPSounds.play();
    }

    /**
     * Method to render the screen
     * Render method of Application Adapter
     * Renders the code logic, updating the gaming world continuously
     */
    // Render on every frame
    @Override
    public void render () {

        // Graphics lib
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (CurrentScreen == gameScreen.StartScreen) 
        {
            startScreen.render(Gdx.graphics.getDeltaTime());
            CurrentScreen = gameScreen.StartScreen;
            if (startScreen.start) {
                CurrentScreen = gameScreen.PlayScreen;
                if (!startScreen.SinglePlayer()) {
                    paddle2 = new Paddle(screenWidth - 40, screenHeight / 2 - 40, 20, 80, State.playerTwo); // can also say State.playerAI
                } else {
                    paddle2 = new Paddle(screenWidth - 40, screenHeight / 2 - 40, 20, 80, State.playerAI); // can also say State.playerAI
                }
            }
        }

        // Update logic
        checkGameState();
        
        if (CurrentScreen == gameScreen.PlayScreen) 
        {
            paddle1.update();
            paddle2.update();
            paddle2.trackBall(ball);
            ball.update(paddle1, paddle2, screenWidth, screenHeight);
        }

        // Draw objects
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if (CurrentScreen == gameScreen.PlayScreen)
        {
            paddle1.render(shapeRenderer);
            paddle2.render(shapeRenderer);
            ball.render(shapeRenderer);

            // Draw Score
            font.draw(batch, Integer.toString(ball.getScorePlayerLeft()), screenWidth / 2 - 50, 50);
            font.draw(batch, Integer.toString(ball.getScorePlayerRight()), screenWidth / 2 + 25, 50);
        }

        // Needed calls for render
        batch.end();
        shapeRenderer.end();
    }


    /**
     * Method to dispose of the screen when the game is over
     * dispose method of Application Adapter
     * Disposes of the resources upon completion
     */
    // Dispose when completed
    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        SGPSounds.dispose();
    }

    /**
     * runs various game checks and load screen based on CurrentScreen value
     * @author Antonio Croissy
     */
    public void checkGameState() {

        gameOverCheck();
        pauseCheck();

        switch (CurrentScreen) {
            case StartScreen:
                startScreen.render(Gdx.graphics.getDeltaTime());
                break;
            case PauseScreen:   
                PauseScreen pauseScreen = new PauseScreen(this);
                pauseScreen.render(Gdx.graphics.getDeltaTime());
                break;
            case EndScreen:
                EndScreen endScreen = new EndScreen(this, ball.getScorePlayerLeft());
                endScreen.render(Gdx.graphics.getDeltaTime());
                break;
            default:
                break;
        }   
    }

    /**
     * checks score of both player to determine if game should be over
     * @author Antonio Croissy
     */
    private void gameOverCheck() {
        if (gameOverOnThreshold()) {
            gameOver = true;
            CurrentScreen = gameScreen.EndScreen;
        }
    }

    /**
     * Allows for for game to be paused by changing currentScreen to pauseScreen
     * @author Antonio Croissy
     */
    private void pauseCheck() {
        if (!gameOver) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                isPaused = !isPaused;
            }

            if (isPaused) {
                // Handle any pause-related logic (e.g., stop animations or background music)
                CurrentScreen = gameScreen.PauseScreen;
                SGPSounds.stop();
            } else {
                // Handle resume logic (e.g., restart animations or resume background music)
                if (CurrentScreen != gameScreen.StartScreen)
                    CurrentScreen = gameScreen.PlayScreen;
                SGPSounds.play();
            }
        }
    }
    
    public void showEndScreen(int finalScore) {
        // End screen should just display then have a button to redirect to menu screen
        //setScreen(new EndScreen(this, finalScore));
    }

    // Check to if a player has scored more than 10 points, if so, end the game
    public boolean gameOverOnThreshold()
    {
        if (ball.getScorePlayerLeft() >= 10)
        {
            return true;
        }
        else if (ball.getScorePlayerRight() >= 10)
        {
            return true;
        }

        return false;
    }

    /**
     * Needed requirements for the game
     * libRequired method of Application Adapter
     * Library setup method to initialize the components
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
