package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.SuperGroovyPong.gameScreen;

/**
 * Class to render the pausing of the game
 * Design Pattern: Singleton Pattern
 * @author Antonio Croissy
 */
public class PauseScreen extends ScreenAdapter {

    private final SuperGroovyPong game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;

    public PauseScreen(final SuperGroovyPong game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400); // Adjust dimensions
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2.75f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Game Paused", 100, 300);
        font.draw(batch, "Press Esc or Enter to unpause", 100, 200);
        batch.end();

        if (game.CurrentScreen != gameScreen.PauseScreen) {
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
