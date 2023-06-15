import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ManipularTeclas implements KeyListener {

    GamePanel gP;

    boolean irArriba,irAbajo,irDerecha,irIzquierda;

    public ManipularTeclas(GamePanel gP){
        this.gP = gP;
    }

    /**
     * Metodo tecla pulsada
     * @param e evento de tecla de tipo KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e){

    }

    /**
     * Metodo tecla apretada
     * @param e evento de tecla de tipo KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            irArriba = true;
        }

        if(code == KeyEvent.VK_S){
            irAbajo = true;
        }

        if(code == KeyEvent.VK_D){
            irDerecha = true;
        }

        if(code == KeyEvent.VK_A){
            irIzquierda = true;
        }

        if(code == KeyEvent.VK_P){
            if(gP.estadoActual == gP.estadoPlay){
                gP.estadoActual = gP.estadoPausa;
            }else if(gP.estadoActual == gP.estadoPausa){
                gP.estadoActual = gP.estadoPlay;
            }
        }

    }

    /**
     * Metodo tecla 
     * @param e evento de tecla de tipo KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            irArriba = false;
        }

        if(code == KeyEvent.VK_S){
            irAbajo = false;
        }

        if(code == KeyEvent.VK_D){
            irDerecha = false;
        }

        if(code == KeyEvent.VK_A){
            irIzquierda = false;
        }
    }
}
