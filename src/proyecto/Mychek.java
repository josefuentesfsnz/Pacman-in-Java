package proyecto;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mychek {
    Proyecto juego ;
    Image win = null;
    Image lose = null;
    Reproductor sonido = new Reproductor();

    public Mychek(Proyecto juego) {
        this.juego = juego;
        try
        {
            if (this.win == null)
            {
                this.win = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\win!.png"));
            }
        }catch (IOException ex)
        {
            System.out.println("error imagen GANAR");;
        }
        try
        {
            if (this.lose == null)
            {
                this.lose = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\perder.png"));
            }
        }catch (IOException ex)
        {
            System.out.println("error imagen PERDER");;
        }
    }
    
    boolean gane()
    {
        for (int i = 0; i < this.juego.mapa.map.length; i++)
        {
            for (int j = 0; j < this.juego.mapa.map[i].length; j++)
            {
                if (this.juego.pacmn.vida > 0)
                {
                    if (this.juego.mapa.map[i][j] == 2 || this.juego.mapa.map[i][j] == 3)
                    {
                        return true;
                        
                    }
                }
            }
        }
        
        return false;
    }
    
    void perder() throws InterruptedException
    {
        //FANTAMA ROJO
        if (this.juego.fR.perseguir)
        {
            if (this.juego.fR.x +29 >= this.juego.pacmn.x && this.juego.fR.x <= this.juego.pacmn.x + 29
                && this.juego.fR.y +29 >= this.juego.pacmn.y && this.juego.fR.y <= this.juego.pacmn.y + 29)
            {
                this.juego.pacmn.audPac.playFcomestible();
                this.juego.pacmn.muerte();
                this.juego.fR.reinicio();
                this.juego.fA.reinicio();
                this.juego.fAz.reinicio();
            }
        }
        if (!this.juego.fR.perseguir)
        {
            if (this.juego.fR.x +29 >= this.juego.pacmn.x && this.juego.fR.x <= this.juego.pacmn.x + 29
                && this.juego.fR.y +29 >= this.juego.pacmn.y && this.juego.fR.y <= this.juego.pacmn.y + 29)
            {
                this.juego.pacmn.audPac.playFcomestible();
                this.juego.fR.reinicio();
                this.juego.pacmn.puntuacion += 150;
                Thread.sleep(1000);
            }
        }
        //FANTASMA AMARILLO
        if (this.juego.fA.perseguir)
        {
            if (this.juego.fA.x +29 >= this.juego.pacmn.x && this.juego.fA.x <= this.juego.pacmn.x + 29
                && this.juego.fA.y +29 >= this.juego.pacmn.y && this.juego.fA.y <= this.juego.pacmn.y + 29)
            {
                this.juego.pacmn.muerte();
                this.juego.fR.reinicio();
                this.juego.fA.reinicio();
                this.juego.fAz.reinicio();
            }
        }
        if (!this.juego.fA.perseguir)
        {
            if (this.juego.fA.x +29 >= this.juego.pacmn.x && this.juego.fA.x <= this.juego.pacmn.x + 29
                && this.juego.fA.y +29 >= this.juego.pacmn.y && this.juego.fA.y <= this.juego.pacmn.y + 29)
            {
                this.juego.pacmn.audPac.playFcomestible();
                this.juego.fA.reinicio();
                this.juego.pacmn.puntuacion += 150;
                Thread.sleep(1000);
            }
        }
        //FANTASMA AZUL
        if (this.juego.fAz.perseguir)
        {
            if (this.juego.fAz.x +29 >= this.juego.pacmn.x && this.juego.fAz.x <= this.juego.pacmn.x + 29
                && this.juego.fAz.y +29 >= this.juego.pacmn.y && this.juego.fAz.y <= this.juego.pacmn.y + 29)
            {
                this.juego.pacmn.muerte();
                this.juego.fR.reinicio();
                this.juego.fA.reinicio();
                this.juego.fAz.reinicio();
                
            }
        }
        if (!this.juego.fAz.perseguir)
        {
            if (this.juego.fAz.x +29 >= this.juego.pacmn.x && this.juego.fAz.x <= this.juego.pacmn.x + 29
                && this.juego.fAz.y +29 >= this.juego.pacmn.y && this.juego.fAz.y <= this.juego.pacmn.y + 29)
            {
                this.juego.pacmn.audPac.playFcomestible();
                this.juego.fAz.reinicio();
                this.juego.pacmn.puntuacion += 150;
                Thread.sleep(1000);
                
            }
        }
    }
    void puntuar()
    {
        //puntos
        if (this.juego.pacmn.y % 30 == 0 && this.juego.pacmn.x % 30 == 0)
        {
            if (this.juego.mapa.map[this.juego.pacmn.y / this.juego.pacmn.alto][this.juego.pacmn.x / this.juego.pacmn.ancho] == 2)
            {//bolas pequeñas
                this.juego.mapa.map[this.juego.pacmn.y / this.juego.pacmn.alto][this.juego.pacmn.x / this.juego.pacmn.ancho] = 0;
                this.juego.pacmn.puntuacion += 50;
                this.juego.pacmn.audPac.playcomiendo();
            }
            if (this.juego.mapa.map[this.juego.pacmn.y / this.juego.pacmn.alto][this.juego.pacmn.x / this.juego.pacmn.ancho] == 3)
            {//bolas grandes
                this.juego.mapa.map[this.juego.pacmn.y / this.juego.pacmn.alto][this.juego.pacmn.x / this.juego.pacmn.ancho] = 0;
                this.juego.pacmn.puntuacion += 100;
                this.juego.fR.perseguir = false;
                this.juego.fAz.perseguir = false;
                this.juego.fA.perseguir = false;
                this.juego.fA.espacios = 0;
                this.juego.fAz.espacios = 0;
                this.juego.fR.espacios = 0;
                
            }
        }
    }
}