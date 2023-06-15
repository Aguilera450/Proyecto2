/**
 * Clase controlador de los ataques del <code>Jefe</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 29/05/2022
 * @see <code>ControladorAtaques</code>.
 */
import java.security.InvalidParameterException;

public class ControladorAtaquesJefe extends ControladorAtaques{
    
    /**
     * Constructor por parámetros.
     * @param <code>jefe</code> -- a controlar.
     * @param <code>interfazBatalla</code> -- panel a modificar.
     */
    ControladorAtaquesJefe(Jefe jefe, PanelJuego interfazBatalla){
        personaje = jefe;
        super.interfazBatalla = interfazBatalla;
    }

    /**
     * Método modificador.
     * Valida si hay algo que drenar y si es el caso drena la vida de este jefe.
     */
    @Override
    protected void calcularVidaDrenada() {
        if(contadorVidaDrenada > 0){
            contadorVidaDrenada--;
            controlOponente.contadorVidaDrenada--;
            interfazBatalla.getControlJugador().getJugador().perderVida(((AtaqueDrenarVida) personaje.getAtaqueUnico()).getVidaDrenada());
        }  
    }
    
    /** Disminuye en 1 al ataque especial en cuestión. */
    @Override
    protected void disminuirEnfriamientoAtaqueEspecial(){
        disminuirEnfriamientoAtaqueUnico();
    }
    
    /** 
     * Si el controlador que se pasa como parámetros es instancia de <code>ControladorAtaques</code>  
     * entonces se asigan como nuevo controlador. En otro caso, la operación es inválida.
     * @throws <code>InvalidParameterException</code>.
     */
    @Override
    public void setControladorOponente(ControladorAtaques controlOponente) throws InvalidParameterException{
        if(controlOponente instanceof ControladorAtaquesJugador)
            super.controlOponente = controlOponente;
        else
            throw new InvalidParameterException("El controlOponente debe ser de tipo ControladorAtaquesJugador");
    }
}
