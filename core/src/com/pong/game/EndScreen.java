package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class to build an ending screen
 * @author Laura Waldron, Hana Segura, Antonio Croissy
 */
public class EndScreen extends ScreenAdapter {

    private final SuperGroovyPong game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private int score;

    public EndScreen(final SuperGroovyPong game, int score) {
        this.game = game;
        this.score = score;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400); // Adjust dimensions
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2.75f);
    }

    @Override
    public void show() {
        // Setup screen when shown
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Game Over", 100, 300);
        font.draw(batch, "Score: " + score, 100, 250);
        font.draw(batch, "Tap the screen to play again", 100, 200);
        batch.end();

        if (Gdx.input.isTouched()) {
            // Reset the game and switch back to the main screen
            game.dispose();
            game.create();
            dispose();
        }
    }

    @Override
    public void dispose() {
        // Dispose of resources when the screen is no longer shown
        batch.dispose();
        font.dispose();
    }
}
