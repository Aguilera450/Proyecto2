import java.awt.image.BufferedImage;
import java.awt.Rectangle;
public class Entidad {
    public int mundoX,mundoY;
    public int x,y;
    public int vel; 
    public BufferedImage arriba1,arriba2,abajo1,abajo2,izq1,izq2,der1,der2;
    public String direccion;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle areaSolida;
    public boolean colisionEncendida = false;
}
