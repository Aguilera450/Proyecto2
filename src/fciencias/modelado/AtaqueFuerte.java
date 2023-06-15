/**
 * Clase que simula un <code>AtaqueFuerte</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 * @see <code>Ataque</code>. 
 */

 public class AtaqueFuerte extends Ataque {

     /**
     * Método constructor.
     * @param <code>descripcion</code> -- del <code>AtaqueFuerte</code>.
     */
    public AtaqueFuerte(String descripcion) {
        super.descripcion = descripcion;
        danio = 30; 
    }
}