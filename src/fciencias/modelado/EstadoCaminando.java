/**
 * Clase que simula el estado <code>Caminando</code>
 * del Jugador.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class EstadoCaminando implements EstadoJugador{
    /** Jugador asociado al estado actual */
    private Jugador jugador;

    /**
     * Constructor de el estado Caminando.
     * @param jugador - <code>Jugador</code> asociado al estado actual.
     */
	public EstadoCaminando(Jugador jugador) {
		this.jugador = jugador;
	}

    /** Método que inicia el Estado Modo Detenido. 
     * Cuándo el jugador esté en modo caminando y se deje de presionar una tecla
     * se pasará a modo detenido.
    */
    public void detenido() {
        if(jugador.getEstadoActual().equals(jugador.getEstadoCaminando()))
            jugador.setNuevoEstado(jugador.getEstadoDetenido());
    }
    
    /** Método que inicia el Estado Caminando. */
    public void caminando() {
        // El jugador ya se encuentra en este estado        
    }

    /** 
     * Método que inicia el Estado En Batalla. 
     * Se cambiará a este estado cuando el jugador acepte enfrentarse a un jefe.
     * @throws UnsupportedOperationException.
    */
    public void enBatalla() throws UnsupportedOperationException {
        // El jugador esta caminando, necesita estár detenido para poder entrar en este estado.
        throw new UnsupportedOperationException("No existe una transición del estado caminando al estado batalla. Verifique la instrucción, por favor.");
    }

}
