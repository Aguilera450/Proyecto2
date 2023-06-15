/**
 * Interfaz que se usara para crear los Estados de un Jugador.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public interface EstadoJugador{
    /** Método que inicia el Modo Detenido. */
    public void detenido();

    /** Método que inicia el Modo Caminando. */
    public void caminando();

    /** Método que inicia el Modo En Batalla. */
    public void enBatalla();
}