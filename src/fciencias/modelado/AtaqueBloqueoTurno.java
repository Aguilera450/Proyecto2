/**
 * Clase que simula un <code>AtaqueBloqueoTurno</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 * @see <code>AtaqueUnico</code>. 
 */
public class AtaqueBloqueoTurno extends AtaqueUnico{

    /**
     * Constructor de clase.
     * Permite crear un ataque de tipo bloqueo de turno.
     * @param nombre -- Nombre del ataque.
     * @param descripcion -- Descripción del ataque
     * @param danio -- Daño que realiza el ataque.
     */
    AtaqueBloqueoTurno(String nombre, String descripcion, int danio){
        nombreUnico = nombre;
        super.descripcion = descripcion;
        super.danio = danio;
    }
    
    /**
     * Método para obtener el tipo del AtaqueUnico.
     * @return <code>String</code> -- Tipo del AtaqueUnico.
     */
    @Override
    public String getTipo(){
        return "Bloqueo Turno";
    }
    
}
