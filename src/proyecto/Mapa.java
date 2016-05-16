package proyecto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Mapa {
    Proyecto game;
    Image bloc = null;
    int map[/*Y*/][/*X*/] = {
        //1= pared
        //2 = pelota normal
        //3 = pelota especial
      //  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//0
        { 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},//1
        { 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1},//2
        { 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1},//3
        { 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1},//4
        { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1},//5
        { 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1},//6
        { 0, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 0},//7
        { 1, 1, 1, 2, 1, 1, 1, 1, 3, 1, 0, 0, 0, 0, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1},//8
        { 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 0, 0, 0, 0, 1, 3, 1, 2, 1, 2, 2, 2, 2, 1},//9
        { 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 0, 0, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1},//10
        { 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 1},//11
        { 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1},//12
        { 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},//13
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//14
    };
    
    int mapaFant[/*Y*/][/*X*/] = {
        //1 = pared
        //2 = pelota normal
        //3 = abajo o derecha
        //4 = izquierda o abajo
        //5 = izquierda o arriba
        //6 = arriba o derecha
        //7 = abajo
        //8 = izquierda o derecha
        //9 = izquierda
       //10 = derecha
      //   0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 
        {  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//0
        {  1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1},//1
        {  1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1},//2
        {  1, 0, 1, 6, 4, 1, 6, 0, 0, 4, 1, 3, 0, 0, 0, 4, 0, 4, 1, 0, 1, 1, 0, 1},//3
        {  1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},//4
        {  1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1},//5
        {  1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1},//6
        { 10, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 3, 0, 0, 11, 0, 0, 9},//7
        {  1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 3, 7, 7, 4, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1},//8
        {  1, 3, 0, 0, 1, 1, 0, 1, 0, 1, 6, 7, 7, 5, 1, 0, 1, 0, 0, 0, 0, 0, 4, 1},//9
        {  1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 7, 7, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1},//10
        {  1, 0, 1, 6, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0,11, 0, 5, 1, 3, 5, 1, 0, 1},//11
        {  1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},//12
        {  1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1},//13
        {  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//14
    };
    
    public Mapa(Proyecto a)
    {
        game = a;
        if (this.bloc == null)
        {
            try
            {
                this.bloc=ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\proyecto\\imagen\\block.png"));
            }catch (IOException ex)
            {
                System.out.println("error imagen BLOKE");
            }
        }
    }
    void paint(Graphics2D g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect (0, 0, this.game.mapa.map[0].length*30, this.game.mapa.map.length*30, 0, 0);
        for (int i = 0; i < this.map.length; i++)
        {
            for (int j = 0; j <this.map[i].length; j++)
            {
                if (this.map[i][j] == 1)
                {
                    g2d.drawImage(this.bloc, j*30, i*30, 30, 30, null);
                }
                if (this.map[i][j] == 2)
                {
                    g.setColor(Color.RED);
                    g.fillOval((j*30)+14, (i*30)+14, 5, 5);
                }
                if (this.map[i][j] == 3)
                {
                    g.setColor(Color.blue);
                    g.fillOval((j*30)+11, (i*30)+11, 10, 10);
                }
            }
        }
        
        g2d.drawString(this.vidas(), 30, 480);
        g2d.drawString(this.points(), 30, 500);
        
        if (!this.game.chek.gane())
        {
            g2d.drawImage(this.game.chek.win, 0, 0, 735, 600, game);
            
        }
        if (this.game.pacmn.vida == 0)
        {
            
            g2d.drawImage(this.game.chek.lose, 0, 0, 735, 600, game);
        }
    }
    
    String vidas ()
    {
        String a = "Vidas = "+this.game.pacmn.vida;
        return a;
    }
    String points ()
    {
        String b = "Puntos = "+this.game.pacmn.puntuacion;
        return b;
    }
}