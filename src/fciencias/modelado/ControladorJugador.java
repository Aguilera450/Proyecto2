/**
 * Clase controladora del jugador.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 29/05/2022
 */
import java.awt.Rectangle;

public class ControladorJugador {
    /* Atributos de clase. */

    /** Jugador a manipular. */
    protected Jugador jugador;
   
    /** Controlador de teclas. */
    protected ControladorTeclas controlTeclas;
   
    /** Panel de Juego. */
    protected PanelJuego panelJuego;
   
    /* Coordenada X de la pantalla. */
    private final int pantallaX;

    /* Coordenada Y de la pantalla. */
    private final int pantallaY;

    /* Coordenada X del hitBox. */
    private final int hitBoxX;

    /* Coordenada Y del hitBox */
    private final int hitBoxY;

    /* Ancho del hitBox. */
    private final int hitBoxAncho;

    /* Alto del hitBox. */
    private final int hitBoxAlto;

    /* Jugador centrado en X. */
    private final int jugadorCentradoX;

    /* Jugador centrado en Y. */
    private final int jugadorCentradoY;

    /**
     * Constructor por parámetros.
     * @param <code>panelJuego</code> -- panel de juego a asignar para este controlador.
     * @param <code>controlTeclas</code> -- controlador de teclas para este controlador.
     * @param <code>jugador</code> -- jugador en cuestión.
     */
    public ControladorJugador(PanelJuego panelJuego, ControladorTeclas controlTeclas, Jugador jugador){
        this.panelJuego = panelJuego;
        this.controlTeclas = controlTeclas;
        pantallaX = (panelJuego.getAnchoPantalla()/2) - (panelJuego.getTamanoTile()/2);
        pantallaY = (panelJuego.getAltoPantalla()/2) - (panelJuego.getTamanoTile()/2);
        hitBoxX = 8;
        hitBoxY = 26;
        hitBoxAncho = 32;
        hitBoxAlto = 22;
        jugadorCentradoX = panelJuego.getTamanoTile()*25;
        jugadorCentradoY = panelJuego.getTamanoTile()*25;
        this.jugador = jugador;
        ajustesDefaultJugador();
    }

    /**
     * Constructor por parámetros.
     * @param <code>panelJuego</code> -- panel de juego a asignar para este controlador.
     * @param <code>controlTeclas</code> -- controlador de teclas para este controlador.
     */
    public ControladorJugador(PanelJuego panelJuego, ControladorTeclas controlTeclas){
        //this(panelJuego, controlTeclas, new Jugador("Marco", "Ciencias de la Computacion"));
        //this(panelJuego, controlTeclas, new Jugador("Marco", "Actuaria"));
        //this(panelJuego, controlTeclas, new Jugador("Marco", "Fisica"));
        this(panelJuego, controlTeclas, new Jugador("Marco", "Matematicas"));
        //this(panelJuego, controlTeclas, new Jugador("Marco", "Biologia"));
    }

    /**
     * Método modificador.
     * Asignación de coordenadas en X y Y por omisión. 
     * Asignación del HitBox por omisión..
     */
    public void ajustesDefaultJugador() {
        jugador.setHitBox(new Rectangle(hitBoxX, hitBoxY, hitBoxAncho, hitBoxAlto));
        jugador.setCoordX(jugadorCentradoX);
        jugador.setCoordY(jugadorCentradoY);
    }

    /**
     * Método modificador.
     * Asigna la dirección en la que el jugador se mueve.
     */
    public void actualizar(){
        if(controlTeclas.getArriba() == true || controlTeclas.getAbajo() == true || controlTeclas.getIzquierda() == true || controlTeclas.getDerecha() == true){
            if(controlTeclas.getArriba()){
                jugador.setDireccion("arriba");
            } else if(controlTeclas.getAbajo()){
                jugador.setDireccion("abajo");
            } else if(controlTeclas.getDerecha()){
                jugador.setDireccion("derecha");
            } else if(controlTeclas.getIzquierda()){
                jugador.setDireccion("izquierda");
            }

            jugador.setColisionEncendida(false);
            panelJuego.getControlColisiones().checarTile(jugador);
            if(jugador.getColisionEncendida() == false){
                switch (jugador.getDireccion()) {
                    case "arriba":
                        jugador.setCoordY(jugador.getCordY() - jugador.getVelocidad());
                        //panelJuego.getControlJefe().getJefe().setCoordY(panelJuego.getControlJefe().getJefe().getCordY() + jugador.getVelocidad());
                        break;
                    
                    case "abajo":
                        jugador.setCoordY(jugador.getCordY() + jugador.getVelocidad());
                        //panelJuego.getControlJefe().getJefe().setCoordY(panelJuego.getControlJefe().getJefe().getCordY() - jugador.getVelocidad());
                        break;
        
                    case "derecha":
                        jugador.setCoordX(jugador.getCordX() + jugador.getVelocidad());
                        //panelJuego.getControlJefe().getJefe().setCoordX(panelJuego.getControlJefe().getJefe().getCordX() - jugador.getVelocidad());
                        break;
        
                    case "izquierda":
                        jugador.setCoordX(jugador.getCordX() - jugador.getVelocidad());
                        //panelJuego.getControlJefe().getJefe().setCoordX(panelJuego.getControlJefe().getJefe().getCordX() + jugador.getVelocidad());
                        break;
                }
            }

            // Cambia los sprites
            jugador.setContadorSprite(jugador.getContadorSprite()+1);
            if(jugador.getContadorSprite() > 10){
                if(jugador.getNumSprite() == 1){
                    jugador.setNumSprite(2);
                } else if(jugador.getNumSprite() == 2){
                    jugador.setNumSprite(1);
                }
                jugador.setContadorSprite(0);
            }
        }
    }

    /**
     * Método que nos regresa el jugador con el cual se esta trabajando en este controlador.
     * @return <code>Jugador</code> -- jugador del cual es atributo en esta clase.
     */
    public Jugador getJugador(){
        return jugador;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- coordenada en X de la pantalla.
     */
    public int getPantallaX(){
        return pantallaX;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- coordenada en Y de la pantalla.
     */
    public int getPantallaY(){
        return pantallaY;
    }

    /**
     * Método de modificación.
     * @param <code>jugador</code> -- asignamos un nuevo jugador al controlador.
     */
    public void setJugador(Jugador jugador){
        this.jugador = jugador;
        ajustesDefaultJugador();
    }
}
