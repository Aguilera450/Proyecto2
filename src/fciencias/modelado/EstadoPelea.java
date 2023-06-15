/**
 * Clase que representa el estado de pelea.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class EstadoPelea implements EstadosInterfaz{
    /** Referencia a la Interfaz Grafica */
    private PanelJuego panelJuego;

    /**
     * Constructor del Estado Pelea.
     * @param <code>panelJuego</code> -- La referencia a la interfaz gráfica.
     */
    public EstadoPelea(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    /**
     * Metodo que sobreescribe al metodo dibujar de la interfaz 
     * <code>EstadosInterfaz</code>.
     */
    @Override
    public void dibujar() {
        panelJuego.getControlIG().dibujarVentanaPelea();
        panelJuego.getControlJugador().getJugador().setNuevoEstado(panelJuego.getControlJugador().getJugador().getEstadoEnBatalla());
    }

    /**
     * Metodo que sobreescribe al metodo teclasApretadas de la interfaz 
     * <code>EstadosInterfaz</code>.
     * @param <code>code</code> -- codigo de la tecla pulsada.
     */
    @Override
    public void teclasApretadas(int code) {
        panelJuego.getControlTeclas().teclasPantallaPelea(code);
    }

    /**
     * Metodo que sobreescribe al metodo teclasSueltas de la interfaz 
     * <code>EstadosInterfaz</code>.
     * @param <code>code</code> -- codigo de la tecla suelta.
     */
    @Override
    public void teclasSueltas(int code) {
    
    }

    @Override
    public void hiloJuego(){
        //controlJugador.actualizar();
        //controlEventos.checarEvento();
        panelJuego.getControlPelea().transcursoBatalla();
    }
}
