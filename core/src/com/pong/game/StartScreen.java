package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StartScreen implements Screen {

    private final SuperGroovyPong game;

    public StartScreen(final SuperGroovyPong game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Set up your UI components here
        TextButton startButton = new TextButton("Start Game", game.skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.add(startButton).pad(20);
        game.stage.addActor(table);
    }

    private void startGame() {
        // Switch to the game screen
        //game.setScreen(new SuperGroovyGameScreen(game));
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the stage
        game.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        game.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Resize the stage
        game.stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Handle pause if needed
    }

    @Override
    public void resume() {
        // Handle resume if needed
    }

    @Override
    public void hide() {
        // Clean up when the screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose of resources when the screen is no longer needed
    }
}

// package com.pong.game;

// //import libraries to handle the screen, graphics, exc
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Screen;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.OrthographicCamera;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Table;
// import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
// import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
// import com.badlogic.gdx.utils.Array;
// import com.badlogic.gdx.utils.viewport.FitViewport;

// /**
//  * Class to create a start screen
//  * @author Laura Waldron, Hana Segura
//  */
// public class StartScreen implements Screen{
//     //define the game variable
//     private final SuperGroovyPong game;
//     //define the camera?
//     private OrthographicCamera camera;
//     //define the batch?
//     private SpriteBatch batch;
//     //define the font
//     private BitmapFont font;
//     private Stage stage;

//     public StartScreen(final SuperGroovyPong game) {
//         this.game = game;
//         camera = new OrthographicCamera();
//         camera.setToOrtho(false, 800, 400);
//         batch = new SpriteBatch();
//         font = new BitmapFont();
//         stage = new Stage(new FitViewport(800, 400));
//     }

//     @Override
//     public void show(){
//         //setup screen when shown
//         initializeUI();
//         initializeGameObjects();
//     }

//     private void initializeUI(){
//         TextButton startButton = new TextButton("Start Game", game.skin);
//         startButton.addListener(new ClickListener(){
//             @Override
//             public void clicked(InputEvent event, float x, float y) {
//                 startGame();
//             }
//         });

//         Table table = new Table();
//         table.setFillParent(true);
//         table.add(startButton).pad(20);
//         stage.addActor(table);
//     }

//     private void initializeGameObjects(){
//         // Initialize paddles and ball, set up game world
//         Paddle playerPaddle = new Paddle(new Texture("player_paddle.png"), 20, 200);
//         Paddle enemyPaddle = new Paddle(new Texture("enemy_paddle.png"), 760, 200);
//         Ball ball = new Ball(new Texture("ball.png"), 400, 200);

//         // Add game objects to the game world
//         game.gameWorld.addPaddle(playerPaddle);
//         game.gameWorld.addPaddle(enemyPaddle);
//         game.gameWorld.setBall(ball);
//     }

//     private void startGame(){
//         game.setScreen(new GameScreen(game));
//     }

//     @Override
//     public void render(float delta){
//         Gdx.gl.glClearColor(0, 0, 0, 1);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//         camera.update();
//         batch.setProjectionMatrix(camera.combined);

//         batch.begin();
//         font.draw(batch, "Welcome to SUPER GROOVY PONG!!!!", 100, 150);
//         font.draw(batch, "Please tap the screen to begin!", 100, 100);
//         batch.end();
//         //following code for switching between screens

//         if (Gdx.input.isTouched()) {
//             //game.setScreen(new SuperGroovyPong(game)); // Switch to the game screen when touched
//             dispose(); // Dispose of resources used by this screen
//         }

//         stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//         stage.draw();
//     }

//     @Override
//     public void resize(int width, int height) {
//         stage.getViewport().update(width, height, true);
//     }

//     @Override
//     public void pause(){
//         //could handle if the game is paused
//     }

//     @Override
//     public void resume(){
//         //handle when the game is resumed
//         game.gameWorld.resume();
//     }

//     @Override
//     public void hide(){
//         //clean up screen when it is no longer active
//         disposeScreenResources();
//         stopScreenAnimations();
//     }

//     @Override
//     public void dispose(){
//         //dispose of resources when screen no longer shown
//         disposeScreenResources();
//         stage.dispose();
//     }

//     private void disposeScreenResources(){
//         batch.dispose();
//         font.dispose();
//     }

//     private void stopScreenAnimations(){
//         Array<com.badlogic.gdx.scenes.scene2d.Action> screenActions = new Array<>();

//         for (com.badlogic.gdx.scenes.scene2d.Actor actor : stage.getActors()) {
//             if (actor.hasActions()) {
//                 screenActions.addAll(actor.getActions());
//             }
//             actor.clearActions();
//         }

//         for (com.badlogic.gdx.scenes.scene2d.Action action : screenActions) {
//             action.finish();
//         }
//         screenActions.clear();
//     }
// }
