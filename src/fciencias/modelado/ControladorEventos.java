import java.awt.Rectangle;
/**
 * Clase para controlar los eventos del juego.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class ControladorEventos {

    private PanelJuego panelJuego;
    private Rectangle eventoRec;
    private int eventoRectDefaultX, eventoRectDefaultY;

    /**
     * Constructor del Controlador
     * @param <code>panelJuego</code> -- Panel de Juego
     */
    public ControladorEventos(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
        eventoRec = new Rectangle();
        eventoRec.x = 23;
        eventoRec.y = 23;
        eventoRec.width = 2;
        eventoRec.height = 2;
        eventoRectDefaultX = eventoRec.x;
        eventoRectDefaultY = eventoRec.y;
    }

    /**
     * checa si el jugador entra en la zona de un evento y lo activa.
     */
    public void checarEvento(){
        if(panelJuego.getControlTiles().getNombreMapaActual().equals(panelJuego.getControlTiles().getMapa1())){
            if(colision(38, 18, "cualquiera") == true){
                panelJuego.getControlTiles().setNombreMapaActual(panelJuego.getControlTiles().getMapa2());
                panelJuego.getControlTiles().cargarMapa(panelJuego.getControlTiles().getMapa2(), 51, 44);
                panelJuego.getControlJugador().getJugador().setCoordX(panelJuego.getTamanoTile()*9);
                panelJuego.getControlJugador().getJugador().setCoordY(panelJuego.getTamanoTile()*26);
            }

            if(colision(23, 25, "cualquiera") == true){
                panelJuego.getControlIG().setTextoMensaje("Esta es la entrada del Darwin");
                panelJuego.getControlIG().setMensaje(true);
            } else if(colision(20, 28, "cualquiera") == true){
                panelJuego.getControlIG().setTextoMensaje("Aqui está el Darwin (Proximamente)");
                panelJuego.getControlIG().setMensaje(true);
            } else if(colision(13, 11, "cualquiera") == true){
                panelJuego.getControlIG().setTextoMensaje("Todo está cerrado, ve por el Tlahuiz");
                panelJuego.getControlIG().setMensaje(true);
            } else {
                panelJuego.getControlIG().setMensaje(false);
            }

            

            if(colision(21, 25, "cualquiera") == true){
                panelJuego.getControlJugador().getJugador().setCoordX(panelJuego.getTamanoTile()*22);
                panelJuego.getControlJugador().getJugador().setCoordY(panelJuego.getTamanoTile()*25);
                panelJuego.getControlTeclas().desactivarMovimiento();
                panelJuego.getControlPelea().setJefe(new Jefe(panelJuego.getControlJugador().getJugador().constructorAtaquesGeneral("Canekcianismo"), "Canek"));
                panelJuego.setEstadoActual(panelJuego.getEstadoPelea());
            }
        } else if(panelJuego.getControlTiles().getNombreMapaActual().equals(panelJuego.getControlTiles().getMapa2())){
            if(colision(8, 26, "cualquiera") == true){
                panelJuego.getControlTiles().setNombreMapaActual(panelJuego.getControlTiles().getMapa1());
                panelJuego.getControlTiles().cargarMapa(panelJuego.getControlTiles().getMapa1(), 48, 50);
                panelJuego.getControlJugador().getJugador().setCoordX(panelJuego.getTamanoTile()*37);
                panelJuego.getControlJugador().getJugador().setCoordY(panelJuego.getTamanoTile()*18);
            }

            if(colision(35, 25, "cualquiera") == true){
                panelJuego.getControlJugador().getJugador().setCoordX(panelJuego.getTamanoTile()*34);
                panelJuego.getControlJugador().getJugador().setCoordY(panelJuego.getTamanoTile()*24);
                panelJuego.getControlPelea().setJefe(new Jefe(panelJuego.getControlJugador().getJugador().constructorAtaquesGeneral("EfectoPHP"), "Rosa"));
                panelJuego.setEstadoActual(panelJuego.getEstadoPelea());
                //16
            }

            if(colision(18, 10, "cualquiera") == true){
                panelJuego.getControlJugador().getJugador().setCoordX(panelJuego.getTamanoTile()*18);
                panelJuego.getControlJugador().getJugador().setCoordY(panelJuego.getTamanoTile()*11);
                panelJuego.setEstadoActual(panelJuego.getEstadoInventario());
            }

            if(colision(18, 25, "cualquiera") == true){
                panelJuego.getControlIG().setTextoMensaje("Mira! una Magnolia Invisible!");
                panelJuego.getControlIG().setMensaje(true);
            } else if(colision(13, 8, "cualquiera") == true){
                panelJuego.getControlIG().setTextoMensaje("No se puede subir");
                panelJuego.getControlIG().setMensaje(true);
            } else {
                panelJuego.getControlIG().setMensaje(false);
            }
        }
    }

    /**
     * Metodo que checa si el jugador esta en la columna, fila y con la direccion
     * @param <code>colEvento</code> -- Columna de la zona
     * @param <code>filEvento</code> -- Fila de la zona
     * @param <code>reqDireccion</code> -- direccion del personaje
     * @return <code>boolean</code> -- true si está en la zona, false si no.
     */
    public boolean colision(int colEvento, int filEvento, String reqDireccion){
        boolean colision = false;

        int auxX = panelJuego.getControlJugador().getJugador().getHitBox().x;
        int auxY = panelJuego.getControlJugador().getJugador().getHitBox().y;

        panelJuego.getControlJugador().getJugador().getHitBox().x = panelJuego.getControlJugador().getJugador().getCordX() + panelJuego.getControlJugador().getJugador().getHitBox().x;
        panelJuego.getControlJugador().getJugador().getHitBox().y = panelJuego.getControlJugador().getJugador().getCordY() + panelJuego.getControlJugador().getJugador().getHitBox().y;
        eventoRec.x = colEvento*panelJuego.getTamanoTile() + eventoRec.x;
        eventoRec.y = filEvento*panelJuego.getTamanoTile() + eventoRec.y;

        if(panelJuego.getControlJugador().getJugador().getHitBox().intersects(eventoRec)){
            if(panelJuego.getControlJugador().getJugador().direccion.contentEquals(reqDireccion) || reqDireccion.contentEquals("cualquiera")){
                colision = true;
            }
        }

        panelJuego.getControlJugador().getJugador().getHitBox().x = auxX;
        panelJuego.getControlJugador().getJugador().getHitBox().y = auxY;
        eventoRec.x = eventoRectDefaultX;
        eventoRec.y = eventoRectDefaultY;

        return colision;
    }


}
