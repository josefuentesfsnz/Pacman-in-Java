package proyecto;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pacman 
{

    int alto = 30;
    int ancho = 30;
    int x = this.ancho*11;
    int xa = 0;
    int y = this.alto*6;
    int ya = 0;
    int veloc = this.alto/15;
    String dire = "";
    //para poder jugar
    int vida = 3;
    int puntuacion = 0;
    //imagenes
    Image imgPersonaje_der = null;
    Image imgPersonaje_izq = null;
    Image imgPersonaje_arr = null;
    Image imgPersonaje_aba = null;
    Image imgPersonaje_cerra = null;
    Image imgmostrar = null;
    int soni;
    
    Reproductor audPac = new Reproductor();
    private Proyecto game;
    
    public Pacman(Proyecto game)
    {
        this.game = game;
        try
        {
            if(this.imgPersonaje_der ==null)
            {
                String a = System.getProperty("user.dir");
                this.imgPersonaje_der = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\pacman_der.png"));
                this.imgmostrar = this.imgPersonaje_der;
            }
        }catch (IOException ex)
        {
            System.out.println("error imagen pacman der");;
        }
        
        try
        {
            if(this.imgPersonaje_izq ==null)
            {
                this.imgPersonaje_izq = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\pacman_izq.png"));
                
            }
        }catch (IOException ex)
        {
            System.out.println("error imagen pacman izq");;
        }
        try
        {
            if(this.imgPersonaje_arr ==null)
            {
                this.imgPersonaje_arr = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\pacman_arr.png"));
                
            }
        }catch (IOException ex)
        {
            System.out.println("error imagen pacman arr");;
        }
        try
        {
            if(this.imgPersonaje_aba ==null)
            {
                this.imgPersonaje_aba = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\pacman_abajo.png"));
                
            }
        }catch (IOException ex)
        {
            System.out.println("error imagen pacman aba");;
        }
        try
        {
            if(this.imgPersonaje_cerra ==null)
            {
                this.imgPersonaje_cerra = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\pacman_cerrado.png"));
                
            }
        }catch (IOException ex)
        {
            System.out.println("error imagen pacman aba");;
        }
    }

    public boolean doblar(int xx, int yy)
    {//dado un x e y va a la matriz y ve si puedo circular
        int nx = (int) (xx / this.ancho);
        int ny = (int) (yy / this.alto);
        
        if (this.game.mapa.map[ny][nx] != 1 && this.game.mapa.mapaFant[ny][nx] != 7)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void move() throws InterruptedException
    {
        if (this.dire.equals("der") && this.x > 685 && this.y == 210)
        {
            this.x = 0;
        }
        if (this.x == 0 && this.dire.equals("izq")&& this.y == 210)
        {
            this.x = 690;
        }
        if (this.y % 30 == 0 && this.x % 30 == 0)
        {
            if (this.dire.equals("der") && this.doblar(this.x + this.ancho, this.y))
            {
                this.xa = this.veloc;
                this.ya = 0;
                this.imgmostrar = this.imgPersonaje_der;
            }
            if (this.dire.equals("izq") && this.doblar(this.x - this.veloc, this.y))
            {
                this.xa = -veloc;
                this.ya = 0;
                this.imgmostrar = this.imgPersonaje_izq;
            }
            if (this.dire.equals("arr") && this.doblar(this.x, this.y - this.veloc))
            {
                this.xa = 0;
                this.ya = -this.veloc;
                this.imgmostrar = this.imgPersonaje_arr;
            }
            if (this.dire.equals("aba") && this.doblar(this.x, this.y + this.alto ))
            {
                this.xa = 0;
                this.ya = this.veloc;
                this.imgmostrar = this.imgPersonaje_aba;
            }
        }
        if (this.y % 30 == 0)
        {
            if (this.xa > 0 && this.doblar(this.x + this.ancho , this.y)
                    || this.xa < 0 && this.doblar(this.x - this.veloc, this.y))
            {
                this.x = this.x + this.xa;
            }
        }
        if (this.x % 30 == 0)
        {
            if (ya > 0 && this.doblar(x, y + alto )
                    || ya < 0 && doblar(x, y - veloc))
            {
                y = y + ya;
            }
        }
        
        //portales
        
        this.game.chek.puntuar();
        this.game.chek.perder();
    }

    public void paint(Graphics2D g) throws InterruptedException
    {
        if (this.x % 30 == 0 && this.y % 10 == 0)
        {
            g.drawImage(this.imgPersonaje_cerra, this.x, this.y, this.alto, this.ancho, this.game);
        }else if (this.y % 30 == 0 && this.x % 10 == 0)
        {
            g.drawImage(this.imgPersonaje_cerra, this.x, this.y, this.alto, this.ancho, this.game);
        }else
        {
            g.drawImage(this.imgmostrar, this.x, this.y, this.alto, this.ancho, this.game);
        }
        
    }

    public void keyPressed(KeyEvent e)
    {
        if(this.game.chek.gane() && this.vida > 0)
        {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.dire = "izq";
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.dire = "der";
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.dire = "arr";
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.dire = "aba";
        }
        }
    }

    public void keyReleased(KeyEvent e) {
        //     xa = 0;
        //    ya = 0;
    }
    
    void muerte ()
    {
        this.x = 330;
        this.y = 180;
        this.vida--;
        this.puntuacion -= 300;
        try
        {
            Thread.sleep(2000);
        }catch(Exception e)
        {
            System.out.println("error al morir");
        }
        this.dire = "";
    }
}
