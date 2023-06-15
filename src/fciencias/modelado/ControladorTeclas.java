import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 * Clase para controlar la entrada del teclado.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 */
public class ControladorTeclas implements KeyListener {

    /** Referencia al panelJuego */
    private PanelJuego panelJuego;
    /** Nombre del Jugador */
    private String nombre = "";
    /** direcciones del jugador */
    private boolean arriba,derecha,izquierda,abajo;

    /**
     * Constructor del Controlador
     * @param <code>panelJuego</code> -- Panel de Juego
     */
    public ControladorTeclas(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
    }

    /**
     * Metodo tecla pulsada Necesario para implementar KeyListener
     * @param e evento de tecla de tipo KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e){
        
    }

    /**
     * Metodo tecla apretada
     * @param <code>e</code> evento de tecla de tipo KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        panelJuego.getEstadoActual().teclasApretadas(code);
    }

    /**
     * Metodo tecla 
     * @param <code>e</code> evento de tecla de tipo KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        panelJuego.getEstadoActual().teclasSueltas(code);
    }

    /**
     * Metodo para establecer el comportamiento de las teclas en estado titulo.
     * @param <code>code</code> -- codigo de la tecla usada.
     */
    public void teclasPantallaTitulo(int code){
        if(code == KeyEvent.VK_W){
            panelJuego.getControlIG().setNumOpcion(panelJuego.getControlIG().getNumOpcion()-1);
            if(panelJuego.getControlIG().getNumOpcion() < 0){
                panelJuego.getControlIG().setNumOpcion(2);
            }
        }
        if(code == KeyEvent.VK_S){
            panelJuego.getControlIG().setNumOpcion(panelJuego.getControlIG().getNumOpcion()+1);
            if(panelJuego.getControlIG().getNumOpcion() > 2){
                panelJuego.getControlIG().setNumOpcion(0);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(panelJuego.getControlIG().getNumOpcion() == 0){
                panelJuego.setEstadoActual(panelJuego.getEstadoEleccionCarrera());
            } else if(panelJuego.getControlIG().getNumOpcion() == 2){
                System.exit(0);
            }
        }
    }

    /**
     * Metodo para establecer el comportamiento de las teclas en estado pelea.
     * @param <code>code</code> -- codigo de la tecla usada.
     */
    public void teclasPantallaPelea(int code){
        if(panelJuego.getControlPelea().getTurnoJugador()){
            if(code == KeyEvent.VK_A){
                panelJuego.getControlIG().setOpcionAtaque(panelJuego.getControlIG().getOpcionAtaque()-1);
                if(panelJuego.getControlIG().getOpcionAtaque() <0){
                    panelJuego.getControlIG().setOpcionAtaque(3);
                }
            }
            if(code == KeyEvent.VK_D){
                panelJuego.getControlIG().setOpcionAtaque(panelJuego.getControlIG().getOpcionAtaque()+1);
                if(panelJuego.getControlIG().getOpcionAtaque() >3){
                    panelJuego.getControlIG().setOpcionAtaque(0);
                }
            }
            if(code == KeyEvent.VK_ENTER){
                panelJuego.getControlPelea().ejecutarAtaque();
            }

            if(code == KeyEvent.VK_SPACE){
                if(panelJuego.getControlPelea().getValidarAtaqueJugador()){
                    panelJuego.getControlPelea().cambioTurno();
                }
                if(panelJuego.getControlPelea().getVictoria()){
                    panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
                    panelJuego.getControlIG().setMensajeBatalla(false);
                    panelJuego.getControlPelea().inicializarAtributos();
                    desactivarMovimiento();
                }
            }
        }
    }

    /**
     * Metodo para establecer el comportamiento de las teclas aoretadas en estado jugar.
     * @param <code>code</code> -- codigo de la tecla usada.
     */
    public void teclasApretadas(int code){
        if(code == KeyEvent.VK_W){
            arriba = true;
        }
        if(code == KeyEvent.VK_S){
            abajo = true;
        }
        if(code == KeyEvent.VK_A){
            izquierda = true;
        }
        if(code == KeyEvent.VK_D){
            derecha = true;
        }
        if(code == KeyEvent.VK_P){
            panelJuego.setEstadoActual(panelJuego.getEstadoPausa());
        }
    }

    /**
     * Metodo para establecer el comportamiento de las teclas sueltas en estado jugar.
     * @param <code>code</code> -- codigo de la tecla usada.
     */
    public void teclasSueltas(int code){
        if(code == KeyEvent.VK_W){
            arriba = false;
        }
        if(code == KeyEvent.VK_S){
            abajo = false;
        }
        if(code == KeyEvent.VK_A){
            izquierda = false;
        }
        if(code == KeyEvent.VK_D){
            derecha = false;
        }
    }

    /**
     * Metodo para establecer el comportamiento de las teclas en estado pausa.
     * @param <code>code</code> -- codigo de la tecla usada.
     */
    public void teclasApretadasPausa(int code){
        if(code == KeyEvent.VK_P){
            panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
        }
    }

    /**
     * Metodo para establecer el comportamiento de las teclas en estado EleccionCarrera.
     * @param <code>code</code> -- codigo de la tecla usada.
     */
    public void teclasApretadasEleccionCarrera(int code){
        if(code == KeyEvent.VK_W){
            panelJuego.getControlIG().setOpcionCarrera(panelJuego.getControlIG().getOpcionCarrera()-1);
            if(panelJuego.getControlIG().getOpcionCarrera() < 0){
                panelJuego.getControlIG().setOpcionCarrera(5);
            }
        }
        if(code == KeyEvent.VK_S){
            panelJuego.getControlIG().setOpcionCarrera(panelJuego.getControlIG().getOpcionCarrera()+1);
            if(panelJuego.getControlIG().getOpcionCarrera() > 5){
                panelJuego.getControlIG().setOpcionCarrera(0);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            switch (panelJuego.getControlIG().getOpcionCarrera()) {
                case 0:
                    panelJuego.getControlJugador().setJugador(new Jugador(nombre, "Actuaria"));
                    panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
                    break;
                case 1:
                    panelJuego.getControlJugador().setJugador(new Jugador(nombre, "Biologia"));
                    panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
                    break;
                case 2:
                    panelJuego.getControlJugador().setJugador(new Jugador(nombre, "Matematicas"));
                    panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
                    break;
                case 3:
                    panelJuego.getControlJugador().setJugador(new Jugador(nombre, "Ciencias de la Computacion"));
                    panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
                    break;
                case 4:
                    panelJuego.getControlJugador().setJugador(new Jugador(nombre, "Fisica"));
                    panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Metodo para establecer el comportamiento de las teclas en estado Inventario.
     * @param <code>code</code> -- codigo de la tecla usada.
     */
    public void teclasInventario(int code){
        if(code == KeyEvent.VK_ESCAPE){
            panelJuego.setEstadoActual(panelJuego.getEstadoJugar());
            arriba = false;
            abajo = false;
            izquierda = false;
            derecha = false;
        }
    }

    /**
     * Metodo que regresa si se están usando teclas en direccion arriba
     * @return <code>boolean</code> -- True si es esa direccion, false otro caso.
     */
    public boolean getArriba(){
        return arriba;
    }

    /**
     * Metodo que regresa si se están usando teclas en direccion abajo
     * @return <code>boolean</code> -- True si es esa direccion, false otro caso.
     */
    public boolean getAbajo(){
        return abajo;
    }

    /**
     * Metodo que regresa si se están usando teclas en direccion derecha
     * @return <code>boolean</code> -- True si es esa direccion, false otro caso.
     */
    public boolean getDerecha(){
        return derecha;
    }

    /**
     * Metodo que regresa si se están usando teclas en direccion izquierda
     * @return <code>boolean</code> -- True si es esa direccion, false otro caso.
     */
    public boolean getIzquierda(){
        return izquierda;
    }

    /**
     * Metodo que establece la direccion arriba
     * @param <code>arriba</code> -- direccion
     */
    public void setArriba(boolean arriba){
        this.arriba = arriba;
    }

    /**
     * Metodo que establece la direccion abajo
     * @param <code>abajo</code> -- direccion
     */
    public void setAbajo(boolean abajo){
        this.abajo = abajo;
    }

    /**
     * Metodo que establece la direccion izquierda
     * @param <code>izquierda</code> -- direccion
     */
    public void setIzquierda(boolean izquierda){
        this.izquierda = izquierda;
    }

    /**
     * Metodo que establece la direccion derecha
     * @param <code>derecha</code> -- direccion
     */
    public void setDerecha(boolean derecha){
        this.derecha = derecha;
    }

    /**
     * Metodo que establece el nombre del jugador
     * @param <code>nombre</code> -- nombre
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Metodo que establece todas las direcciones en false, es decir
     * desactiva el movimiento.
     */
    public void desactivarMovimiento(){
        arriba = false;
        derecha = false;
        izquierda = false;
        abajo = false;
    }
}
