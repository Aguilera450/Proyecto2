import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Clase PanelJuego, esta clase básicamente es el nucle de la interfaz
 * gráfica, por lo que instanciará y usará los controladores, además
 * extiende de la clase JPanel (para generar ventanas) e implementa
 * Runnable (para el uso de hilos).
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class PanelJuego extends JPanel implements Runnable {
    /** Constantes de los Ajustes de la Pantalla */
    /** Tamaño original de los Tile utilizados (16 pixeles). */
    private final int tamanoOriginalTile = 16;
    /** Es el numero de veces que se va a aescalar el Tile original. */
    private final int escala = 3;
    /** Tamano de los Tiles que se van a mostrar en el juego. */
    private final int tamanoTile = tamanoOriginalTile*escala;
    /** Columnas de Tiles maximas que va a tener la pantalla */
    private final int maxColPantalla = 16;
    /** Filas de Tiles maximas que va a tener la pantalla */
    private final int maxFilPantalla = 12;
    /** Ancho de la Pantalla */
    private final int anchoPantalla = maxColPantalla*tamanoTile;
    /** Alto de la Pantalla */
    private final int altoPantalla = maxFilPantalla*tamanoTile;
    /** Numero maximo de columnas del Mundo */
    private final int maxColMundo = 50;
    /** Numero maximo de filas del Mundo */
    private final int maxFilMundo = 50;
    /** Ancho del Mundo */
    private final int anchoMundo = tamanoTile*maxColMundo;
    /** Alto del Mundo */
    private final int altoMundo = tamanoTile*maxFilMundo;

    /** Hilo del Juego */
    private Thread hiloJuego;
    /** FPS del Juego */
    private final int fps = 60;

    /** CONTROLADORES =========== */
    /** Controlador de Teclas */
    /** Controlador de Tiles */
    private ControladorTiles controlTiles = new ControladorTiles(this);
    /** Controlador de la Interfaz gráfica será abreviado como controlIG */
    private ControladorInterfazGrafica controlIG = new ControladorInterfazGrafica(this);
    /** Controlador de Teclas */
    private ControladorTeclas controlTeclas = new ControladorTeclas(this);
    /** Controlador de Jugador */
    private ControladorJugador controlJugador = new ControladorJugador(this, controlTeclas);
    /** Controlador de Colisiones */
    private ControladorColisiones controlColisiones = new ControladorColisiones(this);
    /** Controlador de Eventos */
    private ControladorEventos controlEventos = new ControladorEventos(this);
    /** Controlador de Peleas */
    private ControladorPelea controlPelea = new ControladorPelea(this);

    /** ESTADOS =================*/
    /** Estado que muestra la pantalla del titulo */
    private EstadosInterfaz estadoTitulo = new EstadoTitulo(this);
    /** Estado que muestra el modo juego */
    private EstadosInterfaz estadoJugar = new EstadoJugar(this);
    /** Estado que muestra la pantalla de Pelea */
    private EstadosInterfaz estadoPelea = new EstadoPelea(this);
    /** Estado que muestra la pantalla de Pausa */
    private EstadosInterfaz estadoPausa = new EstadoPausa(this);
    /** Estado que muestra la pantalla de Eleccion Carrera */
    private EstadosInterfaz estadoEleccionCarrera = new EstadoEleccionCarrera(this);
    /** Estado que muestra la pantalla del Inventario/Tienda */
    private EstadosInterfaz estadoInventario = new EstadoInventario(this);
    /** Estado actual del juego */
    private EstadosInterfaz estadoActual = estadoTitulo;

    /** GETTERS =============== */
    /**
     * Metodo que regresa el estado Titulo de la Interfaz.
     * @return <code>EstadosInterfaz</code> -- Estado Titulo.
     */
    public EstadosInterfaz getEstadoTitulo(){
        return estadoTitulo;
    }

    /**
     * Metodo que regresa el estado Jugar de la Interfaz.
     * @return <code>EstadosInterfaz</code> -- Estado Jugar.
     */
    public EstadosInterfaz getEstadoJugar(){
        return estadoJugar;
    }

    /**
     * Metodo que regresa el estado Pelea de la Interfaz.
     * @return <code>EstadosInterfaz</code> -- Estado Pelea.
     */
    public EstadosInterfaz getEstadoPelea(){
        return estadoPelea;
    }

    /**
     * Metodo que regresa el estado Pausa de la Interfaz.
     * @return <code>EstadosInterfaz</code> -- Estado Pausa.
     */
    public EstadosInterfaz getEstadoPausa(){
        return estadoPausa;
    }

    /**
     * Metodo que regresa el estado EleccionCarrera de la Interfaz.
     * @return <code>EstadosInterfaz</code> -- Estado EleccionCarrera.
     */
    public EstadosInterfaz getEstadoEleccionCarrera(){
        return estadoEleccionCarrera;
    }

    /**
     * Metodo que regresa el estado Inventario de la Interfaz.
     * @return <code>EstadosInterfaz</code> -- Estado Inventario.
     */
    public EstadosInterfaz getEstadoInventario(){
        return estadoInventario;
    }

    /**
     * Metodo que regresa el estado Actual de la Interfaz.
     * @return <code>EstadosInterfaz</code> -- Estado Actual.
     */
    public EstadosInterfaz getEstadoActual(){
        return estadoActual;
    }

    /**
     * Metodo que regresa el ancho de la pantalla del juego.
     * @return <code>int</code> -- Ancho de la Pantalla.
     */
    public int getAnchoPantalla(){
        return anchoPantalla;
    }

    /**
     * Metodo que regresa el alto de la pantalla del juego.
     * @return <code>int</code> -- Alto de la Pantalla.
     */
    public int getAltoPantalla(){
        return altoPantalla;
    }

    /**
     * Metodo que regresa el tamaño de los tiles del juego.
     * @return <code>int</code> -- Tamaño del Tile.
     */
    public int getTamanoTile(){
        return tamanoTile;
    }

    /**
     * Metodo que regresa el maximo de columnas de tiles de la pantalla del juego.
     * @return <code>int</code> -- maximo de columnas de la Pantalla.
     */
    public int getMaxColMundo(){
        return maxColMundo;
    }

    /**
     * Metodo que regresa el maximo de filas de tiles de la pantalla del juego.
     * @return <code>int</code> -- Ancho de la Pantalla.
     */
    public int getMaxFilMundo(){
        return maxFilMundo;
    }

    /**
     * Metodo que regresa el ancho del mundo (Mapa) del juego.
     * @return <code>int</code> -- Ancho del Mundo.
     */
    public int getAnchoMundo(){
        return anchoMundo;
    }

    /**
     * Metodo que regresa el alto del mundo (Mapa) del juego.
     * @return <code>int</code> -- Alto del Mundo.
     */
    public int getAltoMundo(){
        return altoMundo;
    }

    /**
     * Metodo que regresa el controlador de la InterfazGrafica.
     * @return <code>ControladorInterfazCrafica</code> -- controlador de la InterfazGrafica.
     */
    public ControladorInterfazGrafica getControlIG(){
        return controlIG;
    }

    /**
     * Metodo que regresa el controlador de las teclas.
     * @return <code>ControladorTeclas</code> -- controlador de las Teclas.
     */
    public ControladorTeclas getControlTeclas(){
        return controlTeclas;
    }

    /**
     * Metodo que regresa el controlador del jugador.
     * @return <code>ControladorJugador</code> -- controlador del Jugador.
     */
    public ControladorJugador getControlJugador(){
        return controlJugador;
    }

    /**
     * Metodo que regresa el controlador de los Tiles (Imagenes del mapa).
     * @return <code>ControladorTiles</code> -- controlador de los Tiles.
     */
    public ControladorTiles getControlTiles(){
        return controlTiles;
    }

    /**
     * Metodo que regresa el controlador de las colisiones.
     * @return <code>ControladorColisiones</code> -- controlador de las COlisiones.
     */
    public ControladorColisiones getControlColisiones(){
        return controlColisiones;
    }

    /**
     * Metodo que regresa el controlador de  Eventod por zona.
     * @return <code>ControladorEventos</code> -- controlador de las Eventos.
     */
    public ControladorEventos getControlEventos(){
        return controlEventos;
    }

    /**
     * Metodo que regresa el controlador de las Peleas.
     * @return <code>ControladorPelea</code> -- controlador de las Peleas.
     */
    public ControladorPelea getControlPelea(){
        return controlPelea;
    }

    /** SETTERS =============== */
    /**
     * Metodo que establece un nuevo estado actual
     * @param <code>estadoActual</code>
     */
    public void setEstadoActual(EstadosInterfaz estadoActual){
        this.estadoActual = estadoActual;
    }

    /** METODOS ================= */
    /**
     * Constructor del Panel de Juego
     */
    public PanelJuego(){
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(controlTeclas);
        this.setFocusable(true);
    }

    /**
     * Metodo que inicia el Hilo del juego.
     */
    public void iniciarHiloJuego(){
        hiloJuego = new Thread(this);
        hiloJuego.start();
    }

    /**
     * Metodo que ejecuta el hilo del juego.
     * Utiliza la metodología delta para hacer un dibujado
     * más estable y constante.
     */
    @Override
    public void run(){
        final double nanoSegundos = 1000000000;
        double intervaloDibujado = nanoSegundos/fps;
        double delta= 0;
        long ultimaVez = System.nanoTime();
        long tiempoActual;
        while(hiloJuego != null){
            tiempoActual = System.nanoTime();
            delta += (tiempoActual - ultimaVez)/intervaloDibujado;
            ultimaVez = tiempoActual;
            if(delta >= 1){
                // Actualizamos posiciones y eso
                /*
                controlJugador.actualizar();
                controlEventos.checarEvento();
                controlPelea.transcursoBatalla();
                */
                estadoActual.hiloJuego();
                // Redibujamos los objetos
                repaint();
                delta --;
            }
        }
    }

    /** 
     * Metodo paint component, sirve para poder usar el metodo Repaint y dibujar todo lo
     * necesario en la intefaz.
     * @param <code>grafico</code> -- Objeto para dibujar figuras, ventanas, etc. 
    */
    public void paintComponent(Graphics grafico){
        super.paintComponent(grafico);
        Graphics2D grafico2D = (Graphics2D) grafico;
        controlTiles.dibujar(grafico2D);
        controlIG.dibujar(grafico2D);
        grafico2D.dispose();
    }
}
