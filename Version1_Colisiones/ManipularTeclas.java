import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ManipularTeclas implements KeyListener {
    boolean irArriba,irAbajo,irDerecha,irIzquierda;
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
