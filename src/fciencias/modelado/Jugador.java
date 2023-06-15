import java.util.Iterator;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Clase que simula un <code>Jugador</code>.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 29/05/2022
 * @see <code>Personaje</code>.
 */
 public class Jugador extends Personaje {
    /* Atributos de clase. */
    /** Carrera de procedencia del Jugador. */
    private String carrera;                                      
    /** Ataque especial en acción. */
    private AtaqueUnico ataqueEspecial;
    /** Conjunto de ataques especiales a los que tiene disponibilidad el jugador (adquiridos). */                         
    private HashMap<String, AtaqueUnico> ataquesEspeciales = new HashMap<>();
    /** Conjunto de Sprites */
    private HashMap<String, BufferedImage> sprites = new HashMap<>();
    /** Contador de Sprites para determinar el tiempo en el que que el sprite se va dibujar */
    private int contadorSprite = 0;
    /** Numero de Sprite actual que se va a dibujar */
    private int numSprite = 1;
    
    /** Estado en el que se encuentra */
    private EstadoJugador estadoActual;
    /** Estado detinido del jugador. El jugador no camina ni esta en batalla. */
    private EstadoJugador estadoDetenido;
    /** Estado caminando del jugador. El jugador se encuentra en movimiento por el mapa. */
    private EstadoJugador estadoCaminando;
    /** Estado en batalla del jugador. El jugador esta enfrenatando a algún <code>Jefe</code>. */
    private EstadoJugador estadoEnBatalla;

    /**
     * Constructor por parámetros de un Jugador.
     * @param <code>nombre</code> -- nombre del jugador.
     * @param <code>carrera</code> -- carrera a la que pertenece el jugador
     */
    public Jugador(String nombre, String carrera) {
        super(null, nombre);
        this.carrera = carrera;
        ataqueUnico = constructorAtaqueCarrera(carrera);
        ataqueEspecial = null;
        estadoDetenido = new EstadoDetenido(this);
        estadoCaminando = new EstadoCaminando(this);
        estadoEnBatalla = new EstadoEnBatalla(this);
        estadoActual = estadoDetenido;
        cargarSprites();
    }

    /**
     * Constructor por copia que reconstruye un <code>Jugador</code>.
     * @param <code>nombre</code> -- nombre del jugador.
     * @param <code>carrera</code> -- carrera de la que proviene el jugador.
     * @param <code>ataquesEspeciales</code> -- conjunto de ataques a recuperar.
     */
    public Jugador(String nombre, String carrera, String ataquesEspeciales) {
        this(nombre, carrera);
        String[] ataquesSeparados = ataquesEspeciales.split(",");
        for (String ataque : ataquesSeparados) {
            AtaqueUnico ataqueRecuperado = constructorAtaquesGeneral(ataque);
            aprenderAtaqueEspecial(ataqueRecuperado);
        }
    }
    
    /**
     * Método que constuye los ataques disponibles que el jugador puede adquirir.
     * @param <code>ataque</code> -- nombre del ataque requerido.
     * @return <code>AtaqueUnico</code> -- ataque requerido.
     */
    public AtaqueUnico constructorAtaquesGeneral(String ataque) {
        switch(ataque) {
            case "AtaqueTienda1":
                return new AtaqueDanioExagerado("AtauqeTienda1", "El ataque golpea al contrincante "
                + " tan rápido que el usuario y el jugador no perciben estos golpes.", 150);
            case "AtaqueTienda2":
                return new AtaquePotenciado("AtaqueTienda2", "Este ataque detiene la respiración " 
                + "del contrincante por minutos y hace que el tiempo pase de manera más rápida para el atacante. "
                + "Así lo que puede ser una eternidad para el contrincante, es un pequeño instante para el atacante."
                + "El ataque persiste hasta 3 turnos y potencia en 40.", 65, 40, 3);
            case "AtaqueTienda3":
                return new AtaqueDrenarVida("AtaqueTienda3", "El ataque evapora la cantidad de agua en el contrincante. "
                + "Drena vida por 37 y en los próximos 3 turnos.",
                 24, 37, 3);
            case "Canekcianismo":
                return new AtaqueDanioExagerado("Canekcianismo", "Este ataque busca saturar de estres a su contrincante, es una ataque muy "
                + "poderoso, pues se considera exagerado.", 130);
            case "EfectoPHP":
                return new AtaquePotenciado("EfectoPHP", "Este ataque esta fuera de lo común, pues lejos de ser un "
                + "ataque, es un trauma que va consumiendo al contrincante con miedo y coraje, este dura 4 turnos y se potencia en 27.",
                 90, 27, 4);
            default:
                return null;
        }
    }

    /*
     * Método auxiliar que clasifica los ataques únicos y por carrera.
     * @param carrera -- carrera del jugador.
     * @return <code>AtaqueUnico</code> -- ataque correspondiente al jugador por carrera. 
     */
    private AtaqueUnico constructorAtaqueCarrera(String carrera) {
        switch(carrera) {
        	case "Fisica":
	            return new AtaqueDrenarVida("HiperFisismo.", "Este ataque le permite a Fisico intoxicar a su oponente, "
                + "los rasgos mas caracteristicos del ataque son: falta de energia y emorragia interna, por al menos 3 turnos y drena vida por 20.",
                 48, 20, 3);
	        case "Actuaria":
	            return new AtaqueBloqueoTurno("Apartismo.", "Este ataque permite apartar un turno frente a su oponente.", 35);
	        case "Biologia":
	            return new AtaqueDrenarVida("Naturalismo.", "Este ataque parece inofensivo, pero tiene un gran daño por detras, "
                + "consiste en disipar internamente al jugador, esto permite que el contrincante se desgaste conforme recorren 2 turnos.",
                60, 37, 2);
            case "Matematicas":
                return new AtaqueDanioExagerado("Calculista.", "El ataque consiste en darle migraña a su contrincante", 500);
	        case "Ciencias de la Computacion":
	            return new AtaquePotenciado("Polimorfo.", "Este ataque no tiene una forma en específico, solo sabemos que hace daño por 2 turnos. "
                + "Entonces, el contrincante no vera venir los guamazos. El ataque dura 2 turnos y potencia por 32.", 32, 32, 2);
	        default :
	            return null;
        }
    }

     /**
      * Carga los sprites del jugador acorde a su carrera.
      * Por el momento solo están disponibles las carreras de: Actuaria, Biología, Ciencias de la Computación,
      * Física y Matemáticas.
      */
    private void cargarSprites(){
        if(carrera!=null){
            try {
                sprites.put("Arr1", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera +"/up_1.png")));
                sprites.put("Arr2", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera + "/up_2.png")));
                sprites.put("Aba1", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera + "/down_1.png")));
                sprites.put("Aba2", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera + "/down_2.png")));
                sprites.put("Der1", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera + "/right_1.png")));
                sprites.put("Der2", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera + "/right_2.png")));
                sprites.put("Izq1", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera + "/left_1.png")));
                sprites.put("Izq2", ImageIO.read(getClass().getResourceAsStream("/Imagenes/" + carrera + "/left_2.png")));
            } catch (IOException e) {
               System.out.println("No fue posible cargar los sprites de un jugador de " + carrera +". El archivo no fue encontrado.");
            }
        }
    }

     /**
      * Método que anexa un nuevo <code>Ataque</code> al conjunto de ataques.
      * @param <code>ataqueEspecial</code> -- ataque a añadir.
      */
    public void aprenderAtaqueEspecial(AtaqueUnico ataqueEspecial) {
        if(ataquesEspeciales.isEmpty()){
            System.out.println("Poniendo ataque Especial como principal.");
            this.ataqueEspecial = ataqueEspecial;
        }
        // Solo se agregará si el ataque no ha sido aprendido
        if(!ataquesEspeciales.containsKey(ataqueEspecial.getNombre()))
            ataquesEspeciales.put(ataqueEspecial.getNombre(), ataqueEspecial);
    }

    /**
     * Método que cambia el <code>Ataque</code> temporal del jugador.
     * @param <code>ataqueEspecial</code> -- si el ataque no es parte del repertorio del jugador
     *                                       el ataque se vuelve <code>null</code>, en otro caso
     *                                       se asigna.     
     */
    public void cambiarAtaqueEspecialEnUso(String ataqueEspecial) {
        // Si el jugador no conoce el ataqueEspecial solicitado se quedará
        // con el que ya tenía.
        if(ataquesEspeciales.containsKey(ataqueEspecial))
            this.ataqueEspecial = ataquesEspeciales.get(ataqueEspecial);
    }
      
    
    /**
     * Método de Modificación.
     * @param <code>coordenadaX</code> -- nueva coordenada en X.
     */
    @Override
    public void setCoordX(int coordenadaX) {
	    this.coordenadaX = coordenadaX;
    }

    /**
     * Método de modificación.
     * @param <code>coordenadaY</code> -- nueva coordenada en Y.
     */
    @Override
    public void setCoordY(int coordenadaY) {
	    this.coordenadaY = coordenadaY;
    }
    
    /**
     * Método de acceso.
     * @return <code>Iterator<></code> -- regresa un iterador para el conjunto de ataques especiales.
     */
    public Iterator getIteratorAtaquesEspeciales() {
        return ataquesEspeciales.values().iterator();
    }

    /**
     * Método modificador de estado.
     * Manda el<code>estadoActual</code> a un estado detenido.
     */
    public void detenerse() {
        estadoActual.detenido();
    }

    /**
     * Método modificador de estado.
     * Manda el<code>estadoActual</code> a un estado caminando.
     */
    public void caminar() {
        estadoActual.caminando();
    }

    /**
     * Método modificador de estado.
     * Manda el<code>estadoActual</code> a un estado en batalla.
     */
    public void entrarEnBatalla() {
        estadoActual.enBatalla();
    }

    /**
     * Método de acceso.
     * @return <code>EstadoJugador</code> -- se regresa el estado actual del jugador.
     */
    public EstadoJugador getEstadoActual() {
        return estadoActual;        
    }

    /**
     * Método de acceso.
     * @return <code>EstadoJugador</code> -- se regresa el estado detenido del jugador
     */
    public EstadoJugador getEstadoDetenido() {
        return estadoDetenido;
    }

    /**
     * Método de acceso.
     * @return <code>EstadoJugador</code> -- se regresa el estado caminando del jugador
     */
    public EstadoJugador getEstadoCaminando() {
        return estadoCaminando;
    }

    /**
     * Método de acceso.
     * @return <code>EstadoJugador</code> -- se regresa el estado en batalla del jugador
     */
    public EstadoJugador getEstadoEnBatalla() {
        return estadoEnBatalla;
    }

    /**
     * Método de modificación.
     * @param <code>estado</code>  -- Nuevo estado actual del jugador.
     */
    public void setNuevoEstado(EstadoJugador estado) {
        estadoActual = estado;
    }
     
    /**
     * Método de acceso.
     * @return <code>String</code> -- carrera del <code>Jugador</code>.
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Método de acceso.
     * @return <code>AtaqueUnico</code> -- Regresa el ataqueEspecial en uso del jugador.
     */
    public AtaqueUnico getAtaqueEspecial() {
        return ataqueEspecial;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- Contador de Sprite.
     */
    public int getContadorSprite(){
        return contadorSprite;
    }

    /**
     * Método de moficicación.
     * @param <code>contadorSprite</code> -- Asigna un contadorSprite.
     */
    public void setContadorSprite(int contadorSprite){
        this.contadorSprite = contadorSprite;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- Número de sprite.
     */
    public int getNumSprite(){
        return numSprite;
    }

    /**
     * Método de modificación.
     * @param <code>numSprite</code>  -- Nuevo número de sprite.
     */
    public void setNumSprite(int numSprite){
        this.numSprite = numSprite;
    }

    /**
     * Método de acceso.
     * @return <code>HashMap<String,BufferedImage></code> -- HashMap con los sprites del jugador. 
     */
    public HashMap<String,BufferedImage> getSprites(){
        return sprites;
    }
}
