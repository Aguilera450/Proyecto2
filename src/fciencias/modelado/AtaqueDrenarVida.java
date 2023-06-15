/**
 * Clase que simula un <code>AtaqueDrenarVida</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 * @see <code>AtaqueUnico</code>. 
 */
public class AtaqueDrenarVida extends AtaqueUnico{
    
    /** Vida que se le drenará al oponente cada turno */
    private int vidaDrenada = 0;
    /** Número de turnos en los que se le drenara vida al oponente */
    private int turnosAfectados = 0;

    /**
     * Constructor de clase.
     * Crea un ataque de tipo drenar vida.
     * @param nombre -- Nombre del ataque.
     * @param descripcion -- Descripción del ataque.
     * @param danio -- Daño del ataque.
     * @param vidaDrenada -- Vida drenada por el efecto del ataque.
     * @param turnosAfectados -- Número de turnos en los que se drenará vida.
     */
    AtaqueDrenarVida(String nombre, String descripcion, int danio, int vidaDrenada, int turnosAfectados){
        nombreUnico = nombre;
        super.descripcion = descripcion;
        super.danio = danio;
        this.vidaDrenada = vidaDrenada;
        this.turnosAfectados = turnosAfectados;
    }

    /**
     * Método para obtener el tipo del AtaqueUnico.
     * @return <code>String</code> -- Tipo del AtaqueUnico.
     */
    @Override
    public String getTipo(){
        return "Drenar Vida";
    }

    /**
     * Método para obtener la cantidad de vida que se drenará.
     * @return vida drenada.
     */
    public int getVidaDrenada(){
        return vidaDrenada;
    }

    /**
     * Método para obtener el número de turnos donde se drenará vida.
     * @return número de turnos donde se drenará vida.
     */
    public int getTurnosAfectados(){
        return turnosAfectados;
    }
}
