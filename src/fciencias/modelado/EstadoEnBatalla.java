/**
 * Clase que simula el estado <code>En Batalla</code>
 * del Jugador.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class EstadoEnBatalla implements EstadoJugador{
    /** Jugador asociado al estado actual */
    private Jugador jugador;

    /**
     * Constructor de el estado En Batalla.
     * @param jugador - <code>Jugador</code> asociado al estado actual.
     */
	public EstadoEnBatalla(Jugador jugador){
		this.jugador = jugador;
	}

    /** Método que inicia el Estado Modo Detenido. 
     * Si el jugador ya terminó la batalla puede pasar a este estado.
    */
    public void detenido(){
        if(jugador.getEstadoActual().equals(jugador.getEstadoEnBatalla()))
            jugador.setNuevoEstado(jugador.getEstadoDetenido());
    }
    
    /** 
     * Método que inicia el Estado Caminando. 
     * @throws UnsupportedOperationException.
     */
    public void caminando()throws UnsupportedOperationException {
        // El jugador no puede salir de una batalla caminando, primero tiene que detenerse.
        throw new UnsupportedOperationException("No existe una transición del estado batalla al estado caminando. Verifique la instrucción, por favor.");
    }

    /** Método que inicia el Estado En Batalla. */
    public void enBatalla(){
        // El jugador ya se encuentra en este estado
    }

}