/**
 * Clase que simula un <code>Jefe</code> en este proyecto.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 * @see <code>Personaje</code>.
 */

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
 //import java.util.UnsupportedOperationException;

 public class Jefe extends Personaje {
    /* Atributos de clase. */
    
    /** Sprite del Jefe */
    private BufferedImage sprite;

    /** Variable auxiliar para decidir que ataque regresará el jefe en ataqueAleatorio() */
    private static int contadorJefeAtaques = 0;

    /**
     * Método constructor por parámetros.
     * @param <code>ataqueUnico</code> -- ataque específico del <code>Jefe</code>.
     * @param <code>nombre</code> -- nombre del <code>Jefe</code>.
     */
    public Jefe(AtaqueUnico ataqueUnico, String nombre) {
        super(ataqueUnico, nombre);
        try{
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Jefes/Gris.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método de acceso indirecto.
     * @return <code>Ataque</code> -- Un ataque aleatorio del <code>Jefe</code>
     */
    public Ataque ataqueAleatorio() {
        Ataque ataque = null;
        if(contadorJefeAtaques < 5){
            int eleccion = generaRandom(2);
            switch(eleccion) {
                case 0:
                    ataque = getAtaqueDebil();
                case 1:
                    ataque = getAtaqueFuerte();
            }
            contadorJefeAtaques++;
        } else {
            ataque = getAtaqueUnico();
            contadorJefeAtaques = 0;
        }
        return ataque;
    }

    /**
     * Método que genera un número aleatorio del 0 a n, donde n es el parámetro recibido.
     * @param <code>max</code> -- <code>int</code> con el valor máximo aleatorio.
     * @return <code>int</code> -- con un número aleatorio de 0 a n.
     */
    protected int generaRandom(int max) {
	    return (int) (Math.random() * max);
    }


    /**
     * Método modificador.
     * Establece la coordenada X del Personaje.
     * @throws UnsupportedOperationException.
     */
    @Override
    public void setCoordX(int coordenadaX) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("A los Jefes no se les cambia sus coordenadas.");
    }

    /**
     * Método modificador.
     * Establece la coordenada Y del Personaje.
     * @throws UnsupportedOperationException.
     */
    @Override
    public void setCoordY(int coordenadaY) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("A los Jefes no se les cambia sus coordenadas.");
    }

    /**
     * Método para obtener el sprite del jefe.
     * @return Sprite del jefe.
     */
    public BufferedImage getSprite(){
        return sprite;
    }

 }