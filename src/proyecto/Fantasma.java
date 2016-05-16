package proyecto;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Fantasma {

    int alto = 30;
    int ancho = 30;
    int x = 390;
    int xa = 0;
    int y = 240;
    int ya = 0;
    int veloc = 2;
    String dire = "izq";
    private Proyecto game;
    int espacios = 0;
    
    boolean perseguir = true;
    Image imgfantasma=null;
    Image normal = null;
    Image comible = null;
    public Fantasma(Proyecto game, String color)
    {
        this.game = game;
        if (color.equals("rojo"))
        {
            try
            {
                if(this.imgfantasma == null)
                {
                    this.imgfantasma = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\fan_rojo.png"));
                    //
                    this.normal = imgfantasma;
                    this.comible = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\fan_rojo_comible.png"));
                }
            }catch (IOException ex)
            {
                System.out.println("error imagen FANTASMA rojo");;
            }
        }
        if (color.equals("amarillo"))
        {
            try
            {
                if(this.imgfantasma == null)
                {
                    this.imgfantasma = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\fan_amarillo.png"));
                    this.normal = imgfantasma;
                    this.comible = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\fan_amarillo_comible.png"));
                }
            }catch (IOException ex)
            {
                System.out.println("error imagen FANTASMA amarillo");;
            }
        }
        if (color.equals("azul"))
        {
            try
            {
                if(this.imgfantasma == null)
                {
                    this.imgfantasma = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\fan_azul.png"));
                    this.normal = imgfantasma;
                    this.comible = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\fan_azul_comible.png"));
                }
            }catch (IOException ex)
            {
                System.out.println("error imagen FANTASMA amarillo");;
            }
        }
    }

    public boolean puedoCircular(int xx, int yy)
    {//dado un x e y va a la matriz y ve si puedo circular
        int nx = (int) (xx / this.ancho);
        int ny = (int) (yy / this.alto);
        if (this.game.mapa.map[ny][nx] != 1)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void move()
    {
        if (this.perseguir)
        {
            this.veloc = 2;
        }else
        {
            this.veloc = 1;
        }
        if (this.y % 30 == 0 && this.x % 30 == 0)
        {
            
            double decide=Math.random();
            
            if(decide > 0.5f)
            {
                this.dondeVoy();
            }else
            {
                this.deci_direc();
            }
            
            if (this.dire.equals("der") && this.puedoCircular(this.x + this.ancho, this.y))
            {
                this.xa = this.veloc;
                this.ya = 0;
            }
            
            if (this.dire.equals("izq") && this.puedoCircular(this.x - this.veloc, this.y))
            {
                this.xa = -veloc;
                this.ya = 0;
            }
            
            if (this.dire.equals("arr") && this.puedoCircular(this.x, this.y - this.veloc))
            {
                this.xa = 0;
                this.ya = -this.veloc;
            }
            
            if (this.dire.equals("aba") && this.puedoCircular(this.x, this.y + this.alto ))
            {
                this.xa = 0;
                this.ya = this.veloc;
            }
        }

        if (this.y % 30 == 0)
        {
            if (this.xa > 0 && this.puedoCircular(this.x + this.ancho , this.y)
                    || this.xa < 0 && this.puedoCircular(this.x - this.veloc, this.y))
             {
                this.x = this.x + this.xa;
             }
        }
        if (this.x % 30 == 0)
        {
            if (ya > 0 && this.puedoCircular(x, y + alto )
                    || ya < 0 && this.puedoCircular(x, y - veloc))
            {
                y = y + ya;
            }
        }
        if (this.dire.equals("der") && this.x == 690)
        {
            this.dire = "izq";
        }
        if (this.x == 0 && this.dire.equals("izq"))
        {
            this.dire = "der";
        }
        if (!this.perseguir && this.y % 30 == 0 && this.x % 30 == 0)
        {
            this.espacios++;
        }
        if (this.espacios == 15)
        {
            this.perseguir = true;
            this.espacios = 0;
        }
        
    }

    public void paint(Graphics2D g)
    {
        if (this.perseguir)
        {
            this.imgfantasma = this.normal;
        }
        if (!this.perseguir)
        {
            this.imgfantasma = this.comible;
        }
        
        g.drawImage(imgfantasma, this.x, this.y, this.alto, this.ancho, this.game);
        /*
        g.setColor(Color.RED);
        g.drawRect(x+ancho+veloc, y+16, 1, 1);
        g.drawRect(x-veloc, y+16, 1, 1);
        g.drawRect(x+16, y+alto+veloc, 1, 1);
        g.drawRect(x+16, y-veloc, 1, 1);
                */
    }

    public void deci_direc()
    {
        if (this.y % 30 == 0 && this.x % 30 == 0 && this.perseguir)
        {
            double ran = (Math.random()*10);
            
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 3)
            {
                if (ran <= 5)
                {
                    this.dire = "der";
                }else
                {
                    this.dire = "aba";
                }
                ran = 0;
            }
            
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 4)
            {
                if (ran <= 5)
                {
                    this.dire = "izq";
                }else
                {
                    this.dire = "aba";
                }
                ran = 0;
            }
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 5)
            {
                if (ran <= 5)
                {
                    this.dire = "izq";
                }else
                {
                    this.dire = "arr";
                }
                ran = 0;
            }
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 6)
            {
                if (ran <= 5)
                {
                    this.dire = "der";
                }else
                {
                    this.dire = "arr";
                }
                ran = 0;
            }
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 7)
            {
                this.dire = "aba";
                ran = 0;
            }
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 8)
            {
                if (ran <= 5)
                {
                    this.dire = "der";
                }else
                {
                    this.dire = "izq";
                }
                ran = 0;
            }
            
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 9)
            {
                this.dire ="izq";
                ran = 0;
            }
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 10)
            {
                this.dire = "der";
                ran = 0;
            }
            if (this.game.mapa.mapaFant[this.y / this.alto][this.x / this.ancho] == 11)
            {
                this.dondeVoy();
            }
        }
    }
    void dondeVoy ()
    {
    ArrayList <String> movepse = new ArrayList ();
    
    {
        if (!this.dire.equals("izq") && this.puedoCircular(this.x + this.ancho, this.y))
            {
                movepse.add("der");
            }
            
            if (!this.dire.equals("der") &&this.puedoCircular(this.x - this.veloc, this.y))
            {
                movepse.add("izq");
                //System.out.println(this.puedoCircular(x - veloc, y));
            }
            
            if (!this.dire.equals("aba") &&this.puedoCircular(this.x, this.y - this.veloc))
            {
                movepse.add("arr");
                //System.out.println(this.puedoCircular(x, y - veloc));
            }
            
            if (!this.dire.equals("arr") &&this.puedoCircular(this.x, this.y + this.alto ))
            {
                movepse.add("aba");
                
            }
            int mat=(int) Math.round(Math.random() * (movepse.size()-1));
            //System.out.println("mat:"+mat+","+movepse.size()+" "+movepse);
            this.dire = movepse.get(mat);
            
    }
}
    void reinicio()
    {
        this.perseguir = true;
        this.x = 390;
        this.y = 240;
        this.dire = "izq";
        this.espacios = 0;
        
    }
}