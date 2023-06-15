import java.awt.image.BufferedImage;

/**
 * Clase Tile, la cual sirve para hacer los Tiles del juego
 * (los cuadritos con las imagenes del mapa).
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class Tile {
    /** Imagen del Tile */
    protected BufferedImage imagen;
    /** Colision del Tile */
    protected boolean colision;

    /**
     * Constructor del Tile.
     * @param <code>imagen</code> -- Imagen del Tile (De tipo BufferedImage).
     * @param <code>colision</code> -- Determina si se puede atravezar el Tile.
     */
    public Tile(BufferedImage imagen, boolean colision){
        this.imagen = imagen;
        this.colision = colision;
    }

    /**
     * Constructor del Tile.
     * @param <code>imagen</code> -- Imagen del Tile (De tipo BufferedImage).
     */
    public Tile(BufferedImage imagen){
        this.imagen = imagen;
        this.colision = false;
    }

    /**
     * Metodo que regresa la imagen del Tile.
     * @return <code>BufferedImage</code> -- Imagen del Tile.
     */
    public BufferedImage getImagen(){
        return imagen;
    }

    /**
     * Metodo que regresa la colision del Tile.
     * @return <code>boolean</code> -- true si se hace colision con el Tile, en otro caso false.
     */
    public boolean getColision(){
        return colision;
    }

    /**
     * Metodo que establece la colision por el valor dado.
     * @param <code>colision</code> -- Nuevo valor de colision a establecer.
     */
    public void setColision(boolean colision){
        this.colision = colision;
    }
}
