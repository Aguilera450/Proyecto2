/**
* Controlador para manejar las peleas del juego.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 */
public class ControladorPelea {
    /** Panel de juego asociado a la pelea */
    private PanelJuego panelJuego;
    /** Jefe asociado a la pelea */
    private Jefe jefe;
    /** Variable para controlar el turno del jugador */
    private boolean turnoJugador;
    /** Controlador de ataques del jefe */
    private ControladorAtaquesJefe controlAtaquesJefe;
    /** Controlador de ataques del jugador */
    private ControladorAtaquesJugador controlAtaquesJugador;
    /** Ataque que realizará el jefe */
    private Ataque ataqueJefe;
    /** Variable auxiliar para validar que el jugador haya escogido un ataque a realizar */
    private boolean validaAtaqueJugador;
    /** Valida el termino de la batalla */
    private boolean victoria;

    /** 
     * Constructor del controlador de pelea.
     */
    public ControladorPelea(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
        this.jefe = new Jefe(panelJuego.getControlJugador().getJugador().constructorAtaquesGeneral("Canekcianismo"), "Dummy");
        controlAtaquesJugador = new ControladorAtaquesJugador(panelJuego.getControlJugador().getJugador(), panelJuego);
        controlAtaquesJefe = new ControladorAtaquesJefe(jefe, panelJuego);
        controlAtaquesJugador.setControladorOponente(controlAtaquesJefe);
        controlAtaquesJefe.setControladorOponente(controlAtaquesJugador);
        turnoJugador = true;
    }

    /**
     * Método que se encarga de manejar el transcurso de la batalla.
     */
    public void transcursoBatalla(){
        if(panelJuego.getControlJugador().getJugador().getVida() != 0 && jefe.getVida() != 0){
            if(turnoJugador){
                ///Continua en controlTeclas, en la funcion teclasApretadasPelea. Cuando el jugador da Enter se
                // realiza el metodo this.ejecutarAtaque().
            } else {
                try {
                    setValidarAtaqueJugador(false);
                    ataqueJefe = jefe.ataqueAleatorio();
                    if(ataqueJefe instanceof AtaqueDebil){
                        panelJuego.getControlJugador().getJugador().perderVida(controlAtaquesJefe.ataqueDebil());
                        panelJuego.getControlIG().setTextoMensaje(jefe.nombre() + ": " + jefe.getAtaqueDebil().descripcion);
                        panelJuego.getControlIG().setMensajeBatalla(true);
                        cambioTurno();
                    } else if (ataqueJefe instanceof AtaqueFuerte){
                        panelJuego.getControlJugador().getJugador().perderVida(controlAtaquesJefe.ataqueFuerte());
                        panelJuego.getControlIG().setTextoMensaje(jefe.nombre() + ": " + jefe.getAtaqueFuerte().descripcion);
                            panelJuego.getControlIG().setMensajeBatalla(true);
                        cambioTurno();
                    } else if (ataqueJefe instanceof AtaqueUnico){
                        try {
                            panelJuego.getControlJugador().getJugador().perderVida(controlAtaquesJefe.ataqueUnico());
                            panelJuego.getControlIG().setTextoMensaje(jefe.nombre() + ": " + jefe.getAtaqueUnico().descripcion);
                            panelJuego.getControlIG().setMensajeBatalla(true);
                            cambioTurno();
                        } catch(ExcepcionTurnoBloqueado e){
                            panelJuego.getControlIG().setTextoMensaje("El Turno del Jefe fué Bloqueado.");
                            panelJuego.getControlIG().setMensajeBatalla(true);
                            cambioTurno();
                        } catch(ExcepcionAtaqueEnfriandose e){
                            panelJuego.getControlIG().setTextoMensaje("El Ataque Unico del Jefe está en enfriamiento. no puede usarse este turno.");
                            panelJuego.getControlIG().setMensajeBatalla(true);
                        }
                    }
                } catch(ExcepcionTurnoBloqueado e){
                    // Aqui se entra por los ataques debiles y fuertes.
                    panelJuego.getControlIG().mostrarMensajeNormal("El Turno del Jefe fué Bloqueado.");
                    cambioTurno();
                }
                
            }
        } else if(panelJuego.getControlJugador().getJugador().getVida() != 0 && jefe.getVida() == 0){
            panelJuego.getControlIG().setTextoMensaje("¡Ganaste!, Toma mi habilidad");
            panelJuego.getControlIG().setMensajeBatalla(true);
            panelJuego.getControlJugador().getJugador().aprenderAtaqueEspecial(jefe.getAtaqueUnico());
            victoria = true;
        } else if(panelJuego.getControlJugador().getJugador().getVida() == 0 && jefe.getVida() != 0){
            panelJuego.getControlIG().setTextoMensaje(jefe.nombre()+" ha ganado este enfrentamiento.");
            panelJuego.getControlIG().setMensajeBatalla(true);
            victoria = true;
        } else {
            panelJuego.getControlIG().setTextoMensaje(jefe.nombre()+" ha ganado este enfrentamiento.");
            panelJuego.getControlIG().setMensajeBatalla(true);
            victoria = true;
        }
    }

    /**
     * Método que se encarga de inicializar atributos usados para la batalla.
     */
    public void inicializarAtributos(){
        //Se inicializan Atributos, una vez temrinada la batalla
        turnoJugador = true;
        victoria = false;
        validaAtaqueJugador = false;
        panelJuego.getControlIG().setMensajeBatalla(false);
        if(panelJuego.getControlJugador().getJugador().getVida() < 1000){
            panelJuego.getControlJugador().getJugador().recuperarVida(1000-panelJuego.getControlJugador().getJugador().getVida());
        }
    }

    /**
     * Método para cambiar el turno de la batalla.
     */
    public void cambioTurno(){
        if(turnoJugador){
            turnoJugador = false;
        } else {
            turnoJugador = true;
        }
    }

    /**
     * Setter de la variable validaAtaqueJugador.
     * @param validaAtaqueJugador - Nuevo valor de validaAtaqueJugador.
     */
    public void setValidarAtaqueJugador(boolean validaAtaqueJugador){
        this.validaAtaqueJugador = validaAtaqueJugador;
    }

    /**
     * Método para obtener el valor de validaAtaqueJugador.
     * @return -- valor de validaAtaqueJugador.
     */
    public boolean getValidarAtaqueJugador(){
        return validaAtaqueJugador;
    }

    /**
     * Método para saber si es turno del jugador.
     * @return True si es el turno del jugador.
     */
    public boolean getTurnoJugador(){
        return turnoJugador;
    }

    /**
     * Método para saber si la batalla ha terminado.
     * @return True si ya termino.
     */
    public boolean getVictoria(){
        return victoria;
    }

    /**
     * Método para establecer el jefe de la batalla.
     * @param jefe -- Jefe de la batalla.
     */
    public void setJefe(Jefe jefe){
        this.jefe = jefe;
        controlAtaquesJefe = new ControladorAtaquesJefe(jefe, panelJuego);
        controlAtaquesJefe.setControladorOponente(controlAtaquesJugador);
        controlAtaquesJugador.setControladorOponente(controlAtaquesJefe);
    }

    /**
     * Método para obtener el jefe de la batalla.
     * @return -- Jefe de la batalla.
     */
    public Jefe getJefe(){
        return jefe;
    }

    /**
     * Método para obtener el controlador de ataques del jefe.
     * @return -- controlador de ataques del jefe.
     */
    public ControladorAtaquesJefe getControlAtaquesJefe(){
        return controlAtaquesJefe;
    }

    /**
     * Método para obtener el controlador de ataques del jugador.
     * @return -- controlador de ataques del jugador.
     */
    public ControladorAtaquesJugador getControlAtaquesJugador(){
        return controlAtaquesJugador;
    }

    /**
     * Va a seleccionar el ataque segun el valor en el menu de ataques.
     */
    public void ejecutarAtaque(){
        if(validaAtaqueJugador == false){
            try{
                if(panelJuego.getControlIG().getOpcionAtaque() == 0){
                    jefe.perderVida(controlAtaquesJugador.ataqueDebil());
                    panelJuego.getControlIG().setTextoMensaje(panelJuego.getControlJugador().getJugador().nombre() + ": " +panelJuego.getControlJugador().getJugador().getAtaqueDebil().getDescripcion());
                    panelJuego.getControlIG().setMensajeBatalla(true);
                    setValidarAtaqueJugador(true);
                } else if(panelJuego.getControlIG().getOpcionAtaque() == 1){
                    jefe.perderVida(controlAtaquesJugador.ataqueFuerte());
                    panelJuego.getControlIG().setTextoMensaje(panelJuego.getControlJugador().getJugador().nombre() + ": " +panelJuego.getControlJugador().getJugador().getAtaqueFuerte().getDescripcion());
                    panelJuego.getControlIG().setMensajeBatalla(true);
                    setValidarAtaqueJugador(true);
                } else if(panelJuego.getControlIG().getOpcionAtaque() == 2){
                    panelJuego.getControlIG().setTextoMensaje(panelJuego.getControlJugador().getJugador().nombre() + ": " +panelJuego.getControlJugador().getJugador().getAtaqueUnico().getDescripcion());
                    panelJuego.getControlIG().setMensajeBatalla(true);
                    
                    try {
                        jefe.perderVida(controlAtaquesJugador.ataqueUnico());
                        setValidarAtaqueJugador(true);
                    } catch(ExcepcionTurnoBloqueado e){
                        panelJuego.getControlIG().setTextoMensaje("El Turno del jugador fué Bloqueado.");
                        panelJuego.getControlIG().setMensajeBatalla(true);
                        cambioTurno();
                    } catch(ExcepcionAtaqueEnfriandose e){
                        panelJuego.getControlIG().setTextoMensaje("El Ataque Unico está en enfriamiento. no puede usarse este turno.");
                        panelJuego.getControlIG().setMensajeBatalla(true);
                    }
    
                } else if(panelJuego.getControlIG().getOpcionAtaque() == 3){
                    if(panelJuego.getControlJugador().getJugador().getAtaqueEspecial() != null){
                        panelJuego.getControlIG().setTextoMensaje(panelJuego.getControlJugador().getJugador().nombre() + ": " +panelJuego.getControlJugador().getJugador().getAtaqueEspecial().getDescripcion());
                        panelJuego.getControlIG().setMensajeBatalla(true);
                        try {
                            jefe.perderVida(controlAtaquesJugador.ataqueEspecial());
                            setValidarAtaqueJugador(true);
                        } catch(ExcepcionTurnoBloqueado e){
                            panelJuego.getControlIG().setTextoMensaje("El Turno del jugador fué Bloqueado.");
                            panelJuego.getControlIG().setMensajeBatalla(true);
                            cambioTurno();
                        } catch(ExcepcionAtaqueEnfriandose e){
                            panelJuego.getControlIG().setTextoMensaje("El Ataque Especial está en enfriamiento. no puede usarse este turno.");
                            panelJuego.getControlIG().setMensajeBatalla(true);
                        }
        
                    } else {
                        panelJuego.getControlIG().setTextoMensaje("¡Aun no has aprendido un Ataque Especial!");
                        panelJuego.getControlIG().setMensajeBatalla(true);
                    }
                } else {
                    panelJuego.getControlIG().setMensajeBatalla(false);
                }
            } catch(ExcepcionTurnoBloqueado e){
                // Aqui se entra por los ataques debiles y fuertes.
                panelJuego.getControlIG().mostrarMensajeNormal("El Turno del jugador fué Bloqueado.");
                cambioTurno();
            }
        }
    }
}
