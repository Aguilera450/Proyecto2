import java.awt.Font;
import java.awt.Graphics2D;

import org.w3c.dom.Text;
public class UI {
    GamePanel gP;
    Graphics2D g2;
    public String mensaje = "";
    public boolean mensajeActivado = false;
    int contadorMensaje = 0;
    Font fuente;

    public UI(GamePanel gP){
        this.gP = gP;
        fuente = new Font("Arial", Font.PLAIN, 40);
    }

    public void dibujar(Graphics2D g2){
        this.g2 = g2;

        if(gP.estadoActual == gP.estadoPlay){

        }

        if(gP.estadoActual == gP.estadoPausa){
            dibujarVentanaPausa();
        }
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
}
