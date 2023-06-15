/**
 * Clase que simula el estado <code>Detenido</code>
 * del Jugador.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class EstadoDetenido implements EstadoJugador{
    /** Jugador asociado al estado actual */
    private Jugador jugador;

    /**
     * Constructor de el estado Detenido.
     * @param jugador - <code>Jugador</code> asociado al estado actual.
     */
	public EstadoDetenido(Jugador jugador){
		this.jugador = jugador;
	}

    /** Método que inicia el Estado Modo Detenido. */
    public void detenido(){
        // El jugador ya se encuentra en este estado
    }

    /**
     *  Método que inicia el Estado Caminando. 
     * Al presionarse una tecla pasará a este modo.
    */
    public void caminando(){
        if(jugador.getEstadoActual().equals(jugador.getEstadoDetenido()))
           jugador.setNuevoEstado(jugador.getEstadoCaminando());
    }

    /** 
     * Método que inicia el Estado En Batalla. 
     * Se cambiará a este estado cuando el jugador acepte enfrentarse a un jefe y esté detenido.
    */
    public void enBatalla(){
        if(jugador.getEstadoActual().equals(jugador.getEstadoDetenido()))
            jugador.setNuevoEstado(jugador.getEstadoEnBatalla());
    }
}