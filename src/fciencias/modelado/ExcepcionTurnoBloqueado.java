/**
 * Clase que simula una excepción que es lanzada cuando un <code>Jefe</code>
 * trata de realizar un ataque y previamente el <code>Jugador</code> ha utilizado
 * el ataque de <code>AtaqueBloqueoTurno</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 * @see <code>Exception</code>.
 */

public class ExcepcionTurnoBloqueado extends Exception {

    /** Constructor de esta excepción por omisión. */
    public ExcepcionTurnoBloqueado() {}

    /**
     * Constructor por parámetro para esta excepción.
     * @param <code>msj</code> -- mensaje para especificar esta excepción.
     */
    public ExcepcionTurnoBloqueado(String msj) {
	super(msj);
    }
}
