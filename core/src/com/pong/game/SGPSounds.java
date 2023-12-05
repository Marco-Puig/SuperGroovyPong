package com.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Class to handle the sounds of the game
 * Design Pattern: Singleton Pattern
 * @author Laura Waldron
 */
public class SGPSounds {
    private Music music; //create a private music instance

    /**
     * Method to find the music file path
     * @param musicFilePath the string of the file path
     */
    public SGPSounds(String musicFilePath) {
        music = Gdx.audio.newMusic(Gdx.files.internal(musicFilePath));
    }

    /**
     * Method to play the music
     * Static method
     */
    public void play() {
        if (music != null) {
            music.setLooping(true); //allow it to loop
            music.play();
        }
    }

    /**
     * Method to stop the music
     */
    public void stop() {
        if (music != null) {
            music.stop();
        }
    }

    /**
     * Method to dispose of the music
     */
    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
