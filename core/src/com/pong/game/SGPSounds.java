package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SGPSounds {
    private Music music;

    public SGPSounds(String musicFilePath) {
        music = Gdx.audio.newMusic(Gdx.files.internal(musicFilePath));
    }

    public void play() {
        if (music != null) {
            music.setLooping(true);
            music.play();
        }
    }

    public void stop() {
        if (music != null) {
            music.stop();
        }
    }

    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
