import java.util.Iterator;
import java.util.LinkedList;

/**
 * Clase que simula una <code>Tienda</code>.
 * En esta tienda se podrán comprar ataquesEspeciales y otras cosas a futuro.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 29/05/2022
 */
public class TiendaAtaquesEspeciales{
    /** Lista de los ataques especiales en venta */
    private LinkedList<AtaqueUnico> ataquesEspecialesCatalogo;
    
    /** Constructor de clase.
     * Se inicializa la tienda con ciertos productos.
     */
    public TiendaAtaquesEspeciales(){
        ataquesEspecialesCatalogo = new LinkedList<>();
    }

    /**
     * Método de modificación.
     * Agrega un ataqueEspecial al catalogo de la tienda.
     * Si el ataque ya se encuentra en la tienda no se agregará para evitar duplicidad.
     * @param <code>ataqueEspecial</code> -- Ataque especial a agregar.
     */
    public void agregarAtaqueEspecialACatalogo(AtaqueUnico ataqueEspecial){
        if(!ataquesEspecialesCatalogo.contains(ataqueEspecial))
            ataquesEspecialesCatalogo.add(ataqueEspecial);
    }
    
    /**
     * Método de acceso indirecto.
     * @return <code>Iterator</code> -- Iterador para recorrer los ataques especiales en el catalogo.
     */
    public Iterator getIteradorAtaquesEspecialesCatalogo(){
        return ataquesEspecialesCatalogo.iterator();
    }
}
