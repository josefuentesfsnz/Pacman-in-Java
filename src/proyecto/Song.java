package proyecto;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Song
{
    private Proyecto game;
    
    private AudioInputStream a1 ;
    private Clip clip_Fant_comes = null;
    private BufferedInputStream gtFc;
    
    
    private AudioInputStream a2 ;       
    private Clip clip_pcn_comi = null;//
    private BufferedInputStream gtPc;
            
    private AudioInputStream a3 ;
    private Clip clip_pcn_emp = null;
    private BufferedInputStream gtPem;
    
    private AudioInputStream a4 ;
    private Clip pacman_intermission = null;
    private BufferedInputStream gtPi;
    
    private AudioInputStream a5 ;
    private Clip clip_muerte = null;
    private BufferedInputStream gtPm;
    
    public Song(Proyecto game) throws UnsupportedAudioFileException, IOException
    {
        this.game = game;
        try
        {
            
            this.gtFc = new BufferedInputStream(getClass().getResourceAsStream("songs/Fantasma_comestible.wav"));
            this.a1 = AudioSystem.getAudioInputStream(gtFc);
            this.clip_Fant_comes = AudioSystem.getClip();
            this.clip_Fant_comes.open(a1);
            
            this.gtPc = new BufferedInputStream(getClass().getResourceAsStream("songs/Fantasma_comiendo.wav"));
            this.a2 = AudioSystem.getAudioInputStream(gtPc);
            this.clip_pcn_comi = AudioSystem.getClip();
            this.clip_pcn_comi.open(a2);
            
            this.gtPem = new BufferedInputStream(getClass().getResourceAsStream("songs/Fantasma_empezar.wav"));
            this.a3 = AudioSystem.getAudioInputStream(gtPem);
            this.clip_pcn_emp = AudioSystem.getClip();
            this.clip_pcn_emp.open(a3);
            
            this.gtPi = new BufferedInputStream(getClass().getResourceAsStream("songs/Fantasma_intermission.wav"));
            this.a4 = AudioSystem.getAudioInputStream(gtPi);
            this.pacman_intermission = AudioSystem.getClip();
            this.pacman_intermission.open(a4);
            
            this.gtPm = new BufferedInputStream(getClass().getResourceAsStream("songs/Fantasma_muerte.wav"));
            this.a5 = AudioSystem.getAudioInputStream(gtPm);
            this.clip_muerte = AudioSystem.getClip();
            this.clip_muerte.open(a5);
            
        }catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {}
        }
    /*
    datos------------------------------------------
    Clip                    bigOvni;    rep_Fant_comes
    BufferedInputStream     bis1;       gtFc
    AudioInputStream        ais1;       a1
    CONSTRUCTOR--------------------------------------
    bis1 = new BufferedInputStream(getClass().getResourceAsStream("Fx/saucerBig.wav"));
    ais1 = AudioSystem.getAudioInputStream(bis1);
    bigOvni = AudioSystem.getClip();
    bigOvni.open(ais1);
    */
    public void comersnd ()
    {
        if (!this.game.fA.perseguir)
        {
            this.clip_Fant_comes.start();
        }
    }
}