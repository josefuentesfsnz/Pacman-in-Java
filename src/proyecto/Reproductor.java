package proyecto;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Reproductor {

    ArrayList<File> mySounds = new ArrayList();
    private Clip clip1 = null;
    private Clip clip2 = null;
    private Clip clip3 = null;
    private Clip clip4 = null;
    private Clip clip5 = null;
    

    Reproductor()
    {
        mySounds.add( new File(System.getProperty("user.dir")+"\\src\\proyecto\\songs\\fan_paracomer.wav"));
        mySounds.add( new File(System.getProperty("user.dir")+"\\src\\proyecto\\songs\\pacman_comiendo.wav"));
        mySounds.add( new File(System.getProperty("user.dir")+"\\src\\proyecto\\songs\\pacman_empezar.wav"));
        mySounds.add( new File(System.getProperty("user.dir")+"\\src\\proyecto\\songs\\pacman_intermission.wav"));
        mySounds.add( new File(System.getProperty("user.dir")+"\\src\\proyecto\\songs\\pacman_muerte.wav"));
    }
    
    
    public void playcomiendo()
    {
        try
        {
            AudioInputStream ie=AudioSystem.getAudioInputStream(mySounds.get(1));
            clip1 = AudioSystem.getClip();
            if(clip1.isRunning())
            {
                clip1.stop();
            }
                
            if(clip1.isOpen())
            {
                clip1.close();
            }
            
            clip1.open(ie);
            clip1.setFramePosition(0); // rewind to the beginning
            clip1.start();
            
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex)
        {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playFcomestible()
    {
        try
        {
            AudioInputStream ie=AudioSystem.getAudioInputStream(mySounds.get(0));
            clip2 = AudioSystem.getClip();
            if(!clip2.isRunning())
            {
                clip2.open(ie);
                clip2.setFramePosition(0); // rewind to the beginning
                clip2.start();
            }
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex)
        {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}