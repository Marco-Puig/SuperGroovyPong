package com.pong.game;

//import libraries to handle the screen, graphics, exc
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen  implements Screen{
    //define the game variable
    private final SuperGroovyPong game;
    //define the camera?
    private OrthographicCamera camera;
    //define the batch?
    private SpriteBatch batch;
    //define the font
    private BitmapFont font;

    public StartScreen(final SuperGroovyPong game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400); //adjust dimensions
        batch = new SpriteBatch();
        font = new BitmapFont();
    }
    
    @Override
    public void show(){
        //setup screen when shown
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Welcome to SUPER GROOVY PONG!!!!", 100, 150);
        font.draw(batch, "Please tap the screen to begin!", 100, 100);
        batch.end();
        //following code for switching between screens
        if (Gdx.input.isTouched()) {
            //game.setScreen(new SuperGroovyPong(game)); // Switch to the game screen when touched
            dispose(); // Dispose of resources used by this screen
        }
    }

    @Override
    public void resize(int width, int height){
        //code to handle screen resizing
    }

    @Override
    public void pause(){
        //could handle if the game is paused
    }

    @Override
    public void resume(){
        //handle when the game is resumed
    }

    @Override
    public void hide(){
        //clean up screen when it is no longer active
    }

    @Override
    public void dispose(){
        //dispose of resources when screen no longer shown
        batch.dispose();
        font.dispose();
    }

}
