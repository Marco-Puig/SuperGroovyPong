package com.pong.game;
/**
 * The class that will play sounds
 * @author Laura Waldron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SGPSounds extends JFrame implements ActionListener {
    JButton button;
    JPanel panel;
    //Singleton instance
    private static SGPSounds instance = null;

    public SGPSounds() {
        this.setSize(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        button = new JButton("Play");
        button.addActionListener(this);
        panel.add(button);
        this.add(panel);
        this.setVisible(true);
    }
    public static SGPSounds getInstance(){
        if(instance == null){
            instance = new SGPSounds();
        }
        return instance;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("audio/stylist-rock-beat-trailer-116346").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException ex) {
                System.out.println("The specified audio file is not supported.");
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                System.out.println("Audio line for playing back is unavailable.");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println("Error playing the audio file.");
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        SGPSounds.getInstance();
    }
}

