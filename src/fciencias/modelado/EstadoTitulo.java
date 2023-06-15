/**
 * Clase Estado Titulo que implementa la interfaz <code>EstadosInterfaz</code>.
 * Es uno de los estados del patron de diseño state de la interfaz gráfica.
 * Se utiliza para entrar en Estado Titulo y dibujar el titulo.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class EstadoTitulo implements EstadosInterfaz{
    /** Referencia a la Interfaz Grafica */
    private PanelJuego panelJuego;

    /**
     * Constructor del Estado Titulo.
     * @param <code>panelJuego</code> -- La referencia a la interfaz gráfica.
     */
    public EstadoTitulo(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    /**
     * Metodo que sobreescribe al metodo dibujar de la interfaz 
     * <code>EstadosInterfaz</code>.
     */
    @Override
    public void dibujar() {
        panelJuego.getControlIG().dibujarVentanaTitulo();
    }

    /**
     * Metodo que sobreescribe al metodo teclasApretadas de la interfaz 
     * <code>EstadosInterfaz</code>.
     * @param <code>code</code> -- codigo de la tecla pulsada.
     */
    @Override
    public void teclasApretadas(int code) {
        panelJuego.getControlTeclas().teclasPantallaTitulo(code);
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
        
    }
}
