/**
 * Clase que simula un <code>AtaqueDanioExagerado</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 * @see <code>AtaqueUnico</code>. 
 */
public class AtaqueDanioExagerado extends AtaqueUnico{
    
    /**
     * Constructor de clase.
     * Crea un ataque de tipo daño exagerado.
     * @param nombre -- Nombre del ataque.
     * @param descripcion -- Descripcion de ataque.
     * @param danioBaseExagerado -- Daño exagerado que hará.
     */
    AtaqueDanioExagerado(String nombre, String descripcion, int danioBaseExagerado){
        nombreUnico = nombre;
        super.descripcion = descripcion;
        super.danio = danioBaseExagerado;
    }

    /**
     * Método para obtener el tipo del AtaqueUnico.
     * @return <code>String</code> -- Tipo del AtaqueUnico.
     */
    @Override
    public String getTipo(){
        return "Danio Exagerado";
    }

    /**
     * Método para obtener el daño exagerado del ataque.
     * @return Daño exagerado del ataque.
     */
    public int getDanioExagerado(){
        return super.getDanio();
    }

}
