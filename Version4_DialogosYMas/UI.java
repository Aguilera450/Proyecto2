import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.awt.FontFormatException;
import org.w3c.dom.Text;
import java.awt.GraphicsEnvironment;

public class UI {
    GamePanel gP;
    Graphics2D g2;
    public String mensaje = "";
    public boolean mensajeActivado = false;
    public String dialogoActual = "";
    String[] dialogos = new String[20];
    int contadorMensaje = 0;
    Font fuente;

    public UI(GamePanel gP){
        this.gP = gP;
        fuente = new Font("Arial", Font.PLAIN, 40);
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            InputStream is = UI.class.getResourceAsStream("/Fuentes/x12y16pxMaruMonica.ttf");
            fuente = Font.createFont(Font.TRUETYPE_FONT, is);
            fuente = fuente.deriveFont(Font.PLAIN, 40);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dibujar(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(fuente);
        if(gP.estadoActual == gP.estadoPlay){

        }

        if(gP.estadoActual == gP.estadoPausa){
            //dibujarVentanaPausa();
            dibujarVentanaDialogo("Pausa");
        }
        
        if(gP.estadoActual == gP.estadoDialogo){
            dibujarVentanaDialogo("Hola prro");
        }

        if(gP.estadoActual == gP.estadoPelea){
            dibujarVentanaPelea();
        }
    }

    public void dibujarVentanaDialogo(String texto){
        int x = gP.tileSize;
        int y = gP.tileSize/2;
        int ancho = gP.anchoPantalla - (gP.tileSize*2);
        int alto = gP.tileSize*4;
        dibujarSubventana(x,y,ancho,alto);
        //g2.setFont(new Font("x12y16pxMaruMonica", Font.PLAIN, 40));
        //g2.setFont(fuente);
        x += gP.tileSize;
        y += gP.tileSize;
        g2.drawString(texto, x, y);
    }

    public void dibujarSubventana(int x, int y, int ancho, int alto){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, ancho, alto, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, ancho-10, alto-10, 30, 30);
    }

    public void dibujarMensaje(String texto){
        mensaje = texto;
        mensajeActivado = true;
    }  

    public void dibujarVentanaPausa(){
        String texto = "Pausa";
        int x = obtenerXParaTextoCentrado(texto);
        int y = gP.altoPantalla/2;
        g2.drawString(texto, x, y);
    }

    public int obtenerXParaTextoCentrado(String texto){
        int longitud = (int) g2.getFontMetrics().getStringBounds(texto,g2).getWidth();
        int x = gP.anchoPantalla/2 - (longitud/2);
        return x;
    }

    public void dibujarVentanaPelea(){
        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRect(0, 0, gP.anchoPantalla/2, gP.altoPantalla);
        g2.fillRect(gP.anchoPantalla/2, 0, gP.anchoPantalla/2, gP.altoPantalla);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(5, 5, (gP.anchoPantalla/2)-10, gP.altoPantalla-10);
        g2.drawRect((gP.anchoPantalla/2)+5, 5, (gP.anchoPantalla/2)-10, gP.altoPantalla-10);
        g2.drawString("Jugador", 15, 50);
        g2.drawString("1000", 200, 50);
    }
}
