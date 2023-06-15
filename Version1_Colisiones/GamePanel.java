import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    // Ajustes de Pantalla

    // Tamaño del tile (16 px)
    final int originalTileSize = 16;
    // Escala de los tiles 16*3 = 48
    final int escala = 3;
    //Tamaño final de los tiles
    public final int tileSize = originalTileSize*escala;
    // Tiles maximos en la pantalla a lo ancho (16 tiles)
    final int maxScreenCol = 16;
    // Tiles maximos en la pantalla a lo alto (12 tiles)
    final int maxScreenRow = 12;
    // Tamaño d ela pantalla a lo ancho
    final int anchoPantalla = maxScreenCol*tileSize;
    // Tamaño de la pantalla a lo alto
    final int altoPantalla = maxScreenRow*tileSize;

    //============== Parametros del mundo
    public final int maxMundoCol = 50;
    public final int maxMundoRow = 50;
    public final int mundoAncho = tileSize*maxMundoCol;
    public final int mundoAlto = tileSize*maxMundoRow;


    final int fps= 60;

    // Tile Manager
    TileManager tileM = new TileManager(this);
    // Manipular teclas:
    ManipularTeclas manipularTeclas = new ManipularTeclas();
    // Hilo del juego
    Thread hiloJuego;
    // Jugador
    public Jugador jugador = new Jugador(this, manipularTeclas);
    // Checador de colisiones
    public ChecadorColisiones checarColisiones = new ChecadorColisiones(this);

    /**
     * Creamos ajustes del jugador
     */
    int jugadorX = 100;
    int jugadorY = 100;
    int jugadorVel = 4;


    public GamePanel(){
        // establece las dimenciones predeterminadas de la pantalla
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        // Establece el fondo color negro.
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        // Agregamos el manipulador de teclas
        this.addKeyListener(manipularTeclas);

        // Hacemos que GampePanel este "enfocado" en recibir las teclas
        this.setFocusable(true);
    }

    public void iniciarHiloJuego(){
        //Crea el hilo del juego
        hiloJuego = new Thread(this);
        // Ejecuta el metodo run
        hiloJuego.start();

    }

    
    // Metodo que va a ser el loop del juego con metodo Sleep
    /*
    public void run(){
        //
        //Hay dos metodos para disminuir la velocidad de ejecución de
        //o que pasa entre actualizar() y repaint() ya que si lo dejamos
        //sin nada que regule la velocidad va a hacer las acciones millones
        //de veces. Por lo que hay dos metodos que funcionan bien para
        //regular la velocidad

        //
        //Metodo Sleep
        //Divide el 1000000000 entre los fps
        //
        double intervaloDibujado = 1000000000/fps; // 0.016666 seconds
        double sigIntervaloDibujado = System.nanoTime() + intervaloDibujado;
        // Tiempo exacto en nanosegundos
        long tiempoActual = System.nanoTime();
        
        while(hiloJuego != null){
            //System.out.println("Está ejecutandos el loop");


            // Actualizamos posiciones y eso
            actualizar();

            // Redibujamos los objetos
            repaint();

            // Esto es parte del metodo sleep
            try{
                double tiempoRestante = sigIntervaloDibujado-System.nanoTime();
                tiempoRestante = tiempoRestante/1000000;
                if(tiempoRestante < 0) {
                    tiempoRestante = 0;
                }
                Thread.sleep((long) tiempoRestante);
                sigIntervaloDibujado += intervaloDibujado;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    */
    // Metodo que va a ser el loop del juego con metodo Delta

    public void run(){
        double intervaloDibujado = 1000000000/fps;
        double delta= 0;
        long ultimaVez = System.nanoTime();
        long tiempoActual;
        long contador = 0;
        long conteoFPS = 0;
        while(hiloJuego != null){
            //System.out.println("Está ejecutandos el loop");

            tiempoActual = System.nanoTime();
            delta += (tiempoActual - ultimaVez)/intervaloDibujado;
            ultimaVez = tiempoActual;
            if(delta >= 1){
                // Actualizamos posiciones y eso
                actualizar();

                // Redibujamos los objetos
                repaint();

                delta --;
                conteoFPS ++;
            }

            if (contador >= 1000000000){
                System.out.println("FPS: "+ conteoFPS);
                conteoFPS = 0;
                contador = 0;
            }
            

        }
    }
    public void actualizar(){
        jugador.actualizar();
    }

    /**
     * Este es un metodo built in de java (jpanel) para dibujar cosas
     * @param g Un objeto de la clase Graphics ya implementada en java
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // se va a tratar a g como Graphics2D
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        jugador.draw(g2);

        // Se debe poner por buena practica, investigar mas este metodo
        // Pero basicamente lo prepara para cualquier cosa que lo vaya a usar
        g2.dispose();
    }
}
