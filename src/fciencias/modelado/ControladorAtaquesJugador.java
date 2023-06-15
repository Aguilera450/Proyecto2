/**
 * Clase controlador de los ataques del <code>Jugador</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 29/05/2022
 * @see <code>ControladorAtaques</code>.
 */

import java.security.InvalidParameterException;

public class ControladorAtaquesJugador extends ControladorAtaques{

    /**
     * Consturctor por parámetros.
     * @param <code>jugador</code> -- a controlar.
     * @param <code>interfazBatalla</code> -- panel a modificar.
     */
    ControladorAtaquesJugador(Jugador jugador, PanelJuego interfazBatalla){
        personaje = jugador;
        super.interfazBatalla = interfazBatalla;
    }

    /** Método que calcula la cantidad de vida drenada siempre que esta sea natural. */
    @Override
    protected void calcularVidaDrenada() {
        if(contadorVidaDrenada > 0){
            contadorVidaDrenada--;
            controlOponente.contadorVidaDrenada--;
            interfazBatalla.getControlPelea().getJefe().perderVida(((AtaqueDrenarVida) personaje.getAtaqueUnico()).getVidaDrenada());
        }  
    }

    /**
     * Método que obtiene la potencia del ataque en cuestión.
     * @return <code>int</code> -- cantidad de potencia.
     */
    @Override
    protected int obtenerPotenciaAtaque(){
        if(contadorPotenciaDanio > 0){
            contadorPotenciaDanio--;
            if(((Jugador) personaje).getAtaqueEspecial() instanceof AtaquePotenciado)
                return ((AtaquePotenciado) ((Jugador) personaje).getAtaqueEspecial()).getPotenciaDanio();
            return ((AtaquePotenciado) personaje.getAtaqueUnico()).getPotenciaDanio();
        }
        else 
            return 0;
    }
    
    /**
     * Método que accede o no al ataque especial (dependiendo del rango de enfriamiento).
     * @return <code>int</code> -- valor del ataque dependiendo el tipo.
     * @throws ExcepcionAtaqueEnfriandose
     * @throws ExcepcionTurnoBloqueado
     */
    public int ataqueEspecial() throws ExcepcionAtaqueEnfriandose, ExcepcionTurnoBloqueado{
        if(getTurnoBloqueado())
            throw new ExcepcionTurnoBloqueado("El turno de: " + personaje.nombre() + " fue bloqueado");
        quitarBloqueoTurnoOponente(); 
        calcularVidaDrenada();
        disminuirEnfriamientoAtaqueEspecial();

        if(super.enfriamientoAtaqueEspecial == 0){
            //ERROR EN OBTENER DANIO DE ATAQUE
            int ataque = 0;
            if(((Jugador) personaje).getAtaqueEspecial() instanceof AtaqueBloqueoTurno)
            ataque = ataqueBloqueoTurno(((Jugador) personaje).getAtaqueEspecial());
            else if(((Jugador) personaje).getAtaqueEspecial() instanceof AtaquePotenciado)
            ataque = ataquePotenciaDanio(((Jugador) personaje).getAtaqueEspecial());
            else if(((Jugador) personaje).getAtaqueEspecial() instanceof AtaqueDanioExagerado){
                ataque = ataqueDanioExagerado(((Jugador) personaje).getAtaqueEspecial());
            } else if(((Jugador) personaje).getAtaqueEspecial() instanceof AtaqueDrenarVida)
            ataque = ataqueDrenarVida(((Jugador) personaje).getAtaqueEspecial());
            
            super.enfriamientoAtaqueEspecial = 4;
            return ataque;
        }
        else 
            throw new ExcepcionAtaqueEnfriandose("El ataque especial se encuentra en enfriamiento, no puede usarse ahora");
    }
    
    /** Método modificador. Asigna una nueva referencia al control del oponente. */
    @Override
    public void setControladorOponente(ControladorAtaques controlOponente) throws InvalidParameterException{
        if(controlOponente instanceof ControladorAtaquesJefe)
            super.controlOponente = controlOponente;
        else
            throw new InvalidParameterException("El controlOponente debe ser de tipo ControladorAtaquesJefe");
    }


}
