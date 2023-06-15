/**
 * Clase controladora de los ataques de los personajes.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 29/05/2022
 */
public abstract class ControladorAtaques{
    /* Atributos de clase. */

    /** Jugador o Jefe en combate. */
    protected Personaje personaje;

    /** Objeto de esta clase. */
    protected ControladorAtaques controlOponente;

    /** Panel del Juego para controlar los ataques de los Jugadores/Jefes por medio de este objeto. */
    protected PanelJuego interfazBatalla;
    
    // Atributos de apoyo para el manejo de turnos
    /** Indica si el turno del Personaje fue bloqueado por su oponente */
    protected boolean turnoBloqueado = false;
    
    /** Contador que indica los turnos (del personaje) donde sus ataques se verán potenciados */
    protected int contadorPotenciaDanio = 0;
    
    /** Contador que indica los turnos (del personaje) donde estos ataques drenarán la vida del contrincante. */
    protected int contadorVidaDrenada = 0;

    /** Variable que indica si el ataque de derenado de vida esta activo (nos sirve como referencia para saber cuantos turnos estará vigente este ataque). */
    protected boolean afectadoVidaDrenada = false;
      
    /** Contador que indica los turnos (del personaje) restantes para poder volver a usar el ataque único */
    protected int enfriamientoAtaqueUnico = 0;

    /** Contador que indica los turnos (del personaje) restantes para poder volver a usar el ataque especial */
    protected int enfriamientoAtaqueEspecial = 0;

    // -------- Métodos relacionados al ataque tipo: Bloqueo Turno --------
    /**
     * Método de acceso.
     * Nos dice si el turno del personaje contrincante ha sido bloqueado.
     * @return <code>boolean</code> -- <code>True</code> si en efecto el turno del personaje
     * contrincante ha sido bloqueado por algún tipo de ataque con bloqueo. <code>False</code> 
     * en otro caso.
     */
    public boolean getTurnoBloqueado(){
        return this.turnoBloqueado;
    }

    /**
     * Método modificador.
     * Cambia el valor que nos dice si el personaje ha sido bloqueado o no.
     * @param <code>turnoBloqueado</code> -- valor de activasión para el ataque con bloque.
     */
    public void setTurnoBloqueado(boolean turnoBloqueado){
        this.turnoBloqueado = turnoBloqueado;
    }

    /** Método que bloquea el turno del personaje. */
    protected void bloquearTurnoOponente(){
        controlOponente.setTurnoBloqueado(true);
    }

    /** 
     * Método para saber si el turno del oponente está bloqueado
     * Si es así significa que este Personaje le bloqueó en el turno anterior.
     */
    protected boolean oponenteTurnoBloqueado(){
        return controlOponente.getTurnoBloqueado();
    }

    /**
     * Si el oponente tiene su turno bloqueado todavía significa que en el turno anterior
     * del actual personaje lo bloqueó, por lo que sin importar el ataque que se haga deberá ser desbloqueado
     */
    protected void quitarBloqueoTurnoOponente(){
        if(oponenteTurnoBloqueado())
            controlOponente.setTurnoBloqueado(false);
    }

    /**
     * Método para realizar el ataque de tipo bloqueo turno.
     * @param ataqueUnico -- Ataque unico a realizar.
     * @return -- Daño realizado.
     */
    protected int ataqueBloqueoTurno(AtaqueUnico ataqueUnico){
        bloquearTurnoOponente();
        //return personaje.getAtaqueUnico().getDanio() + obtenerPotenciaAtaque();
        return ataqueUnico.getDanio() + obtenerPotenciaAtaque();
    }

    // -------- Métodos relacionados al ataque tipo: Ataque Potenciado --------
    /**
     * Nos permite obtener la potencia del ataque en caso de que se haya realizado un ataque potenciado
     * y siga activo su efecto.
     * Si no hay algun efecto activo se regresará 0.
     * @return -- Daño realizado.
     */
    protected int obtenerPotenciaAtaque(){
        if(contadorPotenciaDanio > 0){
            contadorPotenciaDanio--;
            return ((AtaquePotenciado) personaje.getAtaqueUnico()).getPotenciaDanio();
        }
        else 
            return 0;
    }

    /**
     * Método que potencia un ataque.
     * @param ataqueUnico -- Ataque unico a realizar.
     * @return <code>int</code> -- valor en el que se potencia el ataque. 
     */
    protected int ataquePotenciaDanio(AtaqueUnico ataqueUnico){
        // contadorPotenciaDanio = ((AtaquePotenciado) personaje.getAtaqueUnico()).getTurnosPotenciados();
        contadorPotenciaDanio = ((AtaquePotenciado) ataqueUnico).getTurnosPotenciados();

        // return personaje.getAtaqueUnico().getDanio() + ((AtaquePotenciado) personaje.getAtaqueUnico()).getPotenciaDanio();
        return ataqueUnico.getDanio() + ((AtaquePotenciado) ataqueUnico).getPotenciaDanio();

    }

    // -------- Métodos relacionados al ataque tipo: Daño Exagerado --------
    /**
     * Método que exagera los daños del ataque en cuestión.
     * @param ataqueUnico -- Ataque unico a realizar.
     * @return <code>int</code> poder de ataque después de exagerarlo.
     */
    protected int ataqueDanioExagerado(AtaqueUnico ataqueUnico){
        // return ((AtaqueDanioExagerado) personaje.getAtaqueUnico()).getDanioExagerado() +  obtenerPotenciaAtaque();
        return ((AtaqueDanioExagerado) ataqueUnico).getDanioExagerado() +  obtenerPotenciaAtaque();
    }

    // -------- Métodos relacionados al ataque tipo: Drenar Vida --------
    /** Método abstracto que calcula la vida drenada después de aplicar un ataque de drenado. */
    protected abstract void calcularVidaDrenada();

    /**
     * Método que drena la vida de quién recibe un ataque de drenado.
     * @param ataqueUnico -- Ataque unico a realizar.
     * @return <code>int</code> -- cantidad en la que se drena la vida del personaje contrincante.
     */
    protected int ataqueDrenarVida(AtaqueUnico ataqueUnico){
        // contadorVidaDrenada = ((AtaqueDrenarVida) personaje.getAtaqueUnico()).getTurnosAfectados();
        contadorVidaDrenada = ((AtaqueDrenarVida) ataqueUnico).getTurnosAfectados();
        controlOponente.contadorVidaDrenada = this.contadorVidaDrenada;

        // return personaje.getAtaqueUnico().getDanio();
        return ataqueUnico.getDanio();
    }

    // -------- Métodos generales de los ataques --------
    /** Método que se encarga de ir disminuyendo el enfriamento de los ataques de tipo Unico y por carrera. */
    protected void disminuirEnfriamientoAtaqueUnico(){
        if(enfriamientoAtaqueUnico > 0)
            enfriamientoAtaqueUnico--;
    }

    /** Método que se encarga de ir disminuyendo el enfriamiento de los ataques de tipo Unico adquiridos (o de origen en un Jefe). */
    protected void disminuirEnfriamientoAtaqueEspecial(){
        if(enfriamientoAtaqueEspecial > 0)
            enfriamientoAtaqueEspecial--;
    }
    
    /**
     * Indica el daño del ataque debil del personaje.
     * @return -- Daño del ataque.
     * @throws <code>ExcepcionTurnoBloqueado</code>
     */
    public int ataqueDebil() throws ExcepcionTurnoBloqueado{  
        if(getTurnoBloqueado())
            throw new ExcepcionTurnoBloqueado("El turno de: " + personaje.nombre() + " fue bloqueado");
        quitarBloqueoTurnoOponente();
        calcularVidaDrenada();
        disminuirEnfriamientoAtaqueUnico();
        disminuirEnfriamientoAtaqueEspecial();

        return personaje.getAtaqueDebil().getDanio() + obtenerPotenciaAtaque();
    }

    /**
     * Indica el daño del ataque fuerte del personaje.
     * @return <code>int</code> -- regresa el valor de un ataque fuerte después de potenciarse.
     * @throws <code>ExcepcionTurnoBloqueado</code>.
     */
    public int ataqueFuerte() throws ExcepcionTurnoBloqueado{
        if(getTurnoBloqueado())
            throw new ExcepcionTurnoBloqueado("El turno de: " + personaje.nombre() + " fue bloqueado");
        quitarBloqueoTurnoOponente();
        calcularVidaDrenada();
        disminuirEnfriamientoAtaqueUnico();
        disminuirEnfriamientoAtaqueEspecial();

        return personaje.getAtaqueFuerte().getDanio() + obtenerPotenciaAtaque();
    }

    /** 
     * Indica el daño del ataque único.
     * @return <code>int<code> -- regresa el valor de un ataque único después de aplicar otros ataques en mezcla.
     * trows <code>ExcepcionAtaqueEnfriandose</code>, <code>ExcepcionTurnoBloqueado</code>.
     */
    public int ataqueUnico() throws ExcepcionAtaqueEnfriandose, ExcepcionTurnoBloqueado{
        if(getTurnoBloqueado())
            throw new ExcepcionTurnoBloqueado("El turno de: " + personaje.nombre() + " fue bloqueado");
        quitarBloqueoTurnoOponente(); 
        calcularVidaDrenada();
        disminuirEnfriamientoAtaqueEspecial();

        if(enfriamientoAtaqueUnico == 0){
            int ataque = 0;
            if(personaje.getAtaqueUnico() instanceof AtaqueBloqueoTurno)
                ataque = ataqueBloqueoTurno(personaje.getAtaqueUnico());
            else if(personaje.getAtaqueUnico() instanceof AtaquePotenciado)
                ataque = ataquePotenciaDanio(personaje.getAtaqueUnico());
            else if(personaje.getAtaqueUnico() instanceof AtaqueDanioExagerado)
                ataque = ataqueDanioExagerado(personaje.getAtaqueUnico());
            else if(personaje.getAtaqueUnico() instanceof AtaqueDrenarVida)
                ataque = ataqueDrenarVida(personaje.getAtaqueUnico());
                
            enfriamientoAtaqueUnico = 4;
            return ataque;
        }
        else
	    throw new ExcepcionAtaqueEnfriandose("El ataque único se encuentra en enfriamiento, no puede usarse ahora");
    }
    
    /**
     * Método modificador.
     * Modifica en controlador de pelea del oponente.
     * @param <code>controlOponente</code> -- nuevo controlador a asignar.
     */
    public abstract void setControladorOponente(ControladorAtaques controlOponente);
}

