package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StartScreen extends ScreenAdapter {

    private final SuperGroovyPong game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;

    public StartScreen(final SuperGroovyPong game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400); // Adjust dimensions
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2.75f);
    }

    @Override
    public void show() {
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
        game.setScreen(new SuperGroovyGameScreen(game));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Welcome to Super Groovy Pong", 100, 300);
        font.draw(batch, "Tap the screen to start", 100, 250);
        batch.end();

        game.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        game.stage.draw();

        if (Gdx.input.isTouched()) {
            startGame();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
