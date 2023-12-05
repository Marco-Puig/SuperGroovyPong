package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pong.game.SuperGroovyPong.gameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class StartScreen extends ScreenAdapter {
    private final SuperGroovyPong game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Skin skin;
    private Stage stage;

    public StartScreen(final SuperGroovyPong game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2.75f);

        skin = new Skin(Gdx.files.internal("path/to/your/skin.json"));
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.add(startButton).pad(20);
        stage.addActor(table);
    }

    private void startGame() {
        // Switch to the game screen
        game.CurrentScreen = gameScreen.PlayScreen;
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

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if (Gdx.input.isTouched()) {
            startGame();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        skin.dispose();
        stage.dispose();
    }
}
