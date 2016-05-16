package proyecto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Buttom implements KeyListener {
    Proyecto pacman;
    
    Buttom (Proyecto a)
    {
        this.pacman = a;
    }
    
    @Override
    public void keyTyped(KeyEvent ke)
    {
        
    }
    
    @Override
    public void keyPressed(KeyEvent ke)
    {
        pacman.pacmn.keyPressed(ke);
    }
    
    @Override
    public void keyReleased(KeyEvent ke)
    {
        
    }
    
}
