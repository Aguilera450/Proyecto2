/**
 * Clase abstracta que simula un <code>Ataque</code> de parte de un
 * objeto de tipo <code>Personaje</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */

 public abstract class Ataque {
    /* Atributos de clase. */
    /** Poder de daño por <code>Ataque</code>. */
    protected int danio;
    /** Descripción del <code>Ataque</code>. */
    protected String descripcion;

    /**
     * Método de acceso.
     * @return <code>int</code> -- valor de daño de este <code>Ataque</code>.
     */
    public int getDanio() {
        return danio;
    }

    /**
     * Método modificador.
     * @param <code>danio</code> -- aumenta el daño de este <code>Ataque</code>.
     */
    public void aumentarDanio(int danio) {
        this.danio += danio;
    }

    /**
     * Método modificador.
     * @param <code>danio</code> -- disminuye el daño de este <code>Ataque</code>.
     */
    public void disminuirDanio(int danio) {
        if(this.danio >= danio) this.danio -= danio;
    }

    /**
     * Método de acceso.
     * @return <code>String</code> -- descripción del <code>Ataque</code>.
     */
    public String getDescripcion() {
        return descripcion;
    }

 }