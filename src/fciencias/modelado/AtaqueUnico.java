/**
 * Clase que simula un <code>AtaqueUnico</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 * @see <code>Ataque</code>. 
 */

public abstract class AtaqueUnico extends Ataque {

    /** Nombre único del ataque */
    protected String nombreUnico;
    
    /**
     * Método para obtener el nombre único del Ataque.
     * @return <code>String</code> -- Nombre único del ataque.
     */
    public String getNombre(){
        return nombreUnico;
    }

    /**
     * Método para obtener el tipo del AtaqueUnico.
     * @return <code>String</code> -- Tipo del AtaqueUnico.
     */
    public abstract String getTipo();
}