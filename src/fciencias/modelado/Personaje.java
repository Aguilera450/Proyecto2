import java.awt.Rectangle;

/**
 * Clase abstracta que simula un <code>Personaje</code> en nuestro juego.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 29/05/2022
 */

public abstract class Personaje {
    /* Atributos de clase. */
    /** Nombre del personaje. */
    private String nombre;
    /** Ruta hacia la imagen del jugador cuando esta en batalla. */
    private String rutaImgJugBatalla;
    /** Medidor de vida para el personaje. */
    private int vida;
    /** Coordenadda de posicionamiento en X (1er entrada). */  
    protected int coordenadaX;
    /** Coordenadda de posicionamiento en Y (2da entrada). */
    protected int coordenadaY;
    /** Ataque débil del personaje. */
    private AtaqueDebil ataqueDebil;
    /** Ataque fuerte del personaje. */
    private AtaqueFuerte ataqueFuerte;
    /** Ataque representativo del Jefe o especial para el Jugador. */
    protected AtaqueUnico ataqueUnico;
    /** HitBox del Personaje */
    protected Rectangle hitBox;
    /** Direccion del Personaje*/
    protected String direccion;
    /** Velocidad del Personaje */
    private int velocidad;
    /** Colision Encendida */
    private boolean colisionEncendida;
    

    /**
     * Constructor por parámetros de un personaje.
     * @param <code>ataqueDebil</code> -- correspondiente al ataque de tipo Débil del personaje.
     * @param <code>ataqueFuerte</code> -- correspondiente al ataque de tipo Fuerte del personaje.
     * @param <code>ataqueUnico</code> -- correspondiente al ataque de tipo Único. 
     * @param <code>nombre</code> -- nombre del personaje.
     */
    public Personaje(AtaqueDebil ataqueDebil, AtaqueFuerte ataqueFuerte, AtaqueUnico ataqueUnico, String nombre) {
        this.ataqueDebil = ataqueDebil;
        this.ataqueFuerte = ataqueFuerte;
        this.ataqueUnico = ataqueUnico;
        this.nombre = nombre;
        direccion = "abajo";
        velocidad = 4;
        vida = 1000;
    }

    /**
     * Constructor por copia.
     * @param <code>ataqueUnico</code> -- correspondiente al ataque de tipo Único. 
     * @param <code>nombre</code> -- nombre del personaje.
     */
    public Personaje(AtaqueUnico ataqueUnico, String nombre) {
        this(new AtaqueDebil("Golpe a puño cerrado."), new AtaqueFuerte("Patada al aire  y contra contrincante."), ataqueUnico, nombre);
    }

    /**
     * Método de acceso.
     * @return <code>String</code> -- nombre del personaje.
     */
    public String nombre() {
	    return nombre;
    }

    /**
     * Método modificador.
     * @param <code>vida</code> -- descuento a la vida del personaje.
     */
    public void perderVida(int vida) {
	    if (this.vida >= vida)
            this.vida -= vida;
        else
            this.vida = 0;
    }

    /**
     * Método modificador.
     * @param <code>vida</code> -- aumento en la vida del personaje.
     */
    public void recuperarVida(int vida) {
        this.vida += vida; // Falta validación.
    }

    /**
     * Método de acceso.
     * @return <code>AtaqueDebil</code> -- devuelve el ataque Débil del personaje.
     */
    public AtaqueDebil getAtaqueDebil() {
	    return ataqueDebil;
    }

    /**
     * Método de acceso.
     * @return <code>AtaqueFuerte</code> -- devuelve el ataque Fuerte del personaje.
     */
    public AtaqueFuerte getAtaqueFuerte() {
	    return ataqueFuerte;
    }

    /**
     * Método de acceso.
     * @return <code>AtaqueUnico</code> -- devuelve el ataque Único del personaje.
     */
    public AtaqueUnico getAtaqueUnico() {
	    return ataqueUnico;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- coordenada del personaje en X.
     */
    public int getCordX() {
	    return coordenadaX;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- coordenada del personaje en Y.
     */
    public int getCordY() {
	    return coordenadaY;
    }

    /**
     * Método de modificación.
     * @param <code>coordenadaX</code> -- nueva coordenada en Y.
     */
    public abstract void setCoordX(int coordenadaX);

    /**
     * Método de modificación.
     * @param <code>coordenadaY</code> -- nueva coordenada en Y.
     */
    public abstract void setCoordY(int coordenadaY);
        
    /**
     * Método de acceso.
     * @return <code>String</code> -- ruta hacia la imagen del personaje en batalla.
     */
    public String getRutaImgJugBatalla() {
	    return rutaImgJugBatalla;
    }

    /**
     * Método de modificación.
     * @param <code>rutaNueva</code> -- nueva ruta de la imagen en batalla para el personaje.
     */
    public void setRutaImgJugBatalla(String rutaNueva) {
        rutaImgJugBatalla = rutaNueva;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- vida del personaje.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método de acceso.
     * @return <code>Rectangle</code> -- Objeto de tipo Rectangle que representa el hitBox del Personaje.
     */
    public Rectangle getHitBox(){
        return hitBox;
    }

    /**
     * Método de modificación.
     * @param <code>hitBox</code> -- Nuevo HitBox del Personaje.
     */
    public void setHitBox(Rectangle hitBox){
        this.hitBox = hitBox;
    }

    /**
     * Método de acceso.
     * @return <code>String</code> -- Dirección del personaje.
     */
    public String getDireccion(){
        return direccion;
    }

    /**
     * Método de modificación
     * @param <code>direccion</code> -- Nueva dirección del personaje.
     */
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    /**
     * Método de acceso.
     * @return <code>int</code> -- Velocidad del personaje.
     */
    public int getVelocidad(){
        return velocidad;
    }

    /**
     * Método de acceso.
     * @return <code>boolean</code> -- Indica si la colision del personaje está encendida.
     */
    public boolean getColisionEncendida(){
        return colisionEncendida;
    }

    /**
     * Método de modificación
     * @param <code>colisionEncendida</code> -- Indica el valor del atributo colisionEncendida.
     */
    public void setColisionEncendida(boolean colisionEncendida){
        this.colisionEncendida = colisionEncendida;
    }
}
