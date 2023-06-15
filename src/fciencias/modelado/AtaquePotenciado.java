/**
 * Clase que simula un <code>AtaquePotenciado</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 * @see <code>AtaqueUnico</code>. 
 */
public class AtaquePotenciado extends AtaqueUnico{
    
    /** Ataque extra que se le sumará al ataque */
    private int potenciaDanio = 0;
    /** Número de turnos en los que los ataques estarán potenciados. */
    private int turnosPotenciados = 0;

    /**
     * Constructor de clase.
     * Permite crear un ataque de tipo potenciado.
     * @param nombre -- Nombre del ataque.
     * @param descripcion -- Descripción del ataque
     * @param danio -- Daño que realiza el ataque.
     * @param potenciaDanio -- Ataque extra que se le sumará al ataque.
     * @param turnosPotenciados -- Número de turnos en los que los ataques estarán potenciados.
     */
    AtaquePotenciado(String nombre, String descripcion, int danio, int potenciaDanio, int turnosPotenciados){
        nombreUnico = nombre;
        super.descripcion = descripcion;
        super.danio = danio;
        this.potenciaDanio = potenciaDanio;
        this.turnosPotenciados = turnosPotenciados;
    }

    /**
     * Método para obtener el tipo del AtaqueUnico.
     * @return <code>String</code> -- Tipo del AtaqueUnico.
     */
    @Override
    public String getTipo(){
        return "Ataque Potenciado";
    }

    /**
     * Método que nos permite saber el ataque extra que se le sumará al ataque.
     * @return -- ataque extra que se le sumará al ataque.
     */
    public int getPotenciaDanio(){
        return potenciaDanio;
    }

    /**
     * Método para obtener el número de turnos afectados por el ataque.
     * @return -- Turnos afectados por el ataque.
     */
    public int getTurnosPotenciados(){
        return turnosPotenciados;
    }

}
