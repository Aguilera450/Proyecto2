/**
 * Clase Estado Inventario que implementa la interfaz <code>EstadosInterfaz</code>.
 * Es uno de los estados del patron de diseño state de la interfaz gráfica.
 * Se usa para entrar en el Estado Inventario y mostrar el inventario.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class EstadoInventario implements EstadosInterfaz {
    /** Referencia a la Interfaz Grafica */
    PanelJuego panelJuego;

    /**
     * Constructor del Estado Inventario.
     * @param <code>panelJuego</code> -- La referencia a la interfaz gráfica.
     */
    public EstadoInventario(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }
    
    /**
     * Metodo que sobreescribe al metodo dibujar de la interfaz 
     * <code>EstadosInterfaz</code>.
     */
    @Override
    public void dibujar() {
        panelJuego.getControlIG().dibujarJugador();
        panelJuego.getControlTeclas().desactivarMovimiento();
        panelJuego.getControlJugador().getJugador().setNuevoEstado(panelJuego.getControlJugador().getJugador().getEstadoDetenido());
        panelJuego.getControlIG().mostrarInventario();
    }

    /**
     * Metodo que sobreescribe al metodo teclasApretadas de la interfaz 
     * <code>EstadosInterfaz</code>.
     * @param <code>code</code> -- codigo de la tecla pulsada.
     */
    @Override
    public void teclasApretadas(int code) {
        panelJuego.getControlTeclas().teclasInventario(code);
    }

    /**
     * Metodo que sobreescribe al metodo teclasSueltas de la interfaz 
     * <code>EstadosInterfaz</code>.
     * @param <code>code</code> -- codigo de la tecla suelta.
     */
    @Override
    public void teclasSueltas(int code) {
        //panelJuego.getControlTeclas().teclasSueltas(code);
    }

    @Override
    public void hiloJuego(){
        
    }
}
