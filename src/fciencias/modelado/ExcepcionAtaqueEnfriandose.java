/**
 * Clase que simula la excepción que se da cuando existe un ataque de
 * tipo único en enfriamiento.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 * @see <code>Exception</code>.
 */

public class ExcepcionAtaqueEnfriandose extends Exception {

    /** Constructor de esta excepción por omisión. */
    public ExcepcionAtaqueEnfriandose() {}
    
    /**
     * Constructor por parámetro para esta excepción.
     * @param <code>msj</code> -- mensaje para especificar esta excepción.
     */
    public ExcepcionAtaqueEnfriandose(String msj) {
	super(msj);
    }
}
