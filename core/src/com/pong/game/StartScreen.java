package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class to handle the start of the game
 * Design Pattern: Singleton Pattern
 * @author Hana Segura, Antonio Croissy
 */
public class StartScreen extends ScreenAdapter {
    private final SuperGroovyPong game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    public Boolean start = false;
    private boolean singlePlayer;

    public StartScreen(final SuperGroovyPong game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2.15f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Welcome to Super Groovy Pong", 100, 300);
        font.draw(batch, "Press '1' for Single Player or '2' for Two Players", 100, 250);
        batch.end();


        // DO THE CHANGE PLAYER STATE HERE:::::::::::
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            singlePlayer = true;
            start = true;
            dispose();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            singlePlayer = false;
            start = true;
            dispose();
        }
    }

    public boolean SinglePlayer() {
        return singlePlayer;
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
