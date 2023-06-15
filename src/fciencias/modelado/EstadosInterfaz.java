/**
 * Interfaz EstadosInterfaz que van implementar todos los estados de la 
 * interfaz gráfica. 
 * Con estos estados se usa el patron de diseño state.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public interface EstadosInterfaz {
    /** Metodo para dibujar cosas en la interfaz. */
    public void dibujar();

    /**
     * Metodo para usar las teclas del <code>ControladorTeclas</code>-
     * @param <code>code</code> -- Codigo de la tecla pulsada.
     */
    public void teclasApretadas(int code);

    /**
     * Metodo para usar las teclas del <code>ControladorTeclas</code>-
     * @param <code>code</code> -- Codigo de la tecla suelta.
     */
    public void teclasSueltas(int code);

    /**
     * Metodo para ejecutar metodos en el hilo de juego del <code>PanelJuego</code>.
     */
    public void hiloJuego();
}
