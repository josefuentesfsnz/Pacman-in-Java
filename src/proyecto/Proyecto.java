package proyecto;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Proyecto extends JPanel{
    Pacman pacmn = new Pacman (this);
    Fantasma fR = new Fantasma (this, "rojo");
    Fantasma fA = new Fantasma (this, "amarillo");
    Fantasma fAz = new Fantasma (this, "azul");
    Mapa mapa = new Mapa(this);
    Mychek chek = new Mychek(this);
    
    Proyecto () throws HeadlessException, MalformedURLException
    {
        Buttom btn = new Buttom(this);
        addKeyListener(btn);
        setFocusable(true);
    }
    
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.mapa.paint(g2d);
       
        if (this.pacmn.vida > 0 && this.chek.gane())
        {
            try
            {
                this.pacmn.paint(g2d);
                this.fR.paint(g2d);
                this.fA.paint(g2d);
                this.fAz.paint(g2d);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    void move() throws InterruptedException
    {
        //this.sonido.sonar();
        this.pacmn.move();
        this.fR.move();
        this.fA.move();
        this.fAz.move();
        
    }
    
    
    
    public static void main(String[] args) throws InterruptedException, HeadlessException, MalformedURLException {
        JFrame frame = new JFrame("pacman");
        Proyecto juego = new Proyecto();
        Mychek win = new Mychek(juego);
        frame.add(juego);
        frame.setSize(735, 600);
        frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (win.gane())
        {
            juego.move();
            frame.repaint();
            Thread.sleep(20);
        }
    }
    
}
