/**
 * Clase para controlar las colisiones.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 */
public class ControladorColisiones {

    /** Referencia para el panel del juego */
    private PanelJuego panelJuego;

    /**
     * Constructor del COntrolador
     * @param <code>panelJuego</code> -- panel de Juego
     */
    public ControladorColisiones(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
    }

    /**
     * Checa si los tiles cercanos al jugador tienen colision para frenar su movimiento.
     * @param <code>personaje</code> -- Personaje al que se le van a checar colisiones
     */
    public void checarTile(Personaje personaje){
        // cordenadas de la hitbox
        int personajeIzqMundoX = personaje.getCordX() + personaje.getHitBox().x;
        int personajeDerMundoX = personaje.getCordX() + personaje.getHitBox().x + personaje.getHitBox().width;
        int personajeArrMundoY = personaje.getCordY() + personaje.getHitBox().y;
        int personajeAbjMundoY = personaje.getCordY() + personaje.getHitBox().y + personaje.getHitBox().height;

        // Filas y columnas
        int personajeColIzq = personajeIzqMundoX/panelJuego.getTamanoTile();
        int personajeColDer = personajeDerMundoX/panelJuego.getTamanoTile();
        int personajeFilArr = personajeArrMundoY/panelJuego.getTamanoTile();
        int personajeFilAbj = personajeAbjMundoY/panelJuego.getTamanoTile();

        // Tiles
        int tileNum1, tileNum2;

        switch (personaje.getDireccion()) {
            case "arriba":
                personajeFilArr = (personajeArrMundoY - personaje.getVelocidad())/panelJuego.getTamanoTile();
                tileNum1 = panelJuego.getControlTiles().getMapTileNum()[personajeColIzq][personajeFilArr];
                tileNum2 = panelJuego.getControlTiles().getMapTileNum()[personajeColDer][personajeFilArr];
                if(panelJuego.getControlTiles().getTiles()[tileNum1].getColision() == true || panelJuego.getControlTiles().getTiles()[tileNum2].getColision() == true){
                    personaje.setColisionEncendida(true);
                }
                break;
            
            case "abajo":
                personajeFilAbj = (personajeAbjMundoY + personaje.getVelocidad())/panelJuego.getTamanoTile();
                tileNum1 = panelJuego.getControlTiles().getMapTileNum()[personajeColIzq][personajeFilAbj];
                tileNum2 = panelJuego.getControlTiles().getMapTileNum()[personajeColDer][personajeFilAbj];
                if(panelJuego.getControlTiles().getTiles()[tileNum1].getColision() == true || panelJuego.getControlTiles().getTiles()[tileNum2].getColision() == true){
                    personaje.setColisionEncendida(true);
                }
                break;

            case "derecha":
                personajeColDer = (personajeDerMundoX + personaje.getVelocidad())/panelJuego.getTamanoTile();
                tileNum1 = panelJuego.getControlTiles().getMapTileNum()[personajeColDer][personajeFilArr];
                tileNum2 = panelJuego.getControlTiles().getMapTileNum()[personajeColDer][personajeFilAbj];
                if(panelJuego.getControlTiles().getTiles()[tileNum1].getColision() == true || panelJuego.getControlTiles().getTiles()[tileNum2].getColision() == true){
                    personaje.setColisionEncendida(true);
                }
                break;

            case "izquierda":
                personajeColIzq = (personajeIzqMundoX - personaje.getVelocidad())/panelJuego.getTamanoTile();
                tileNum1 = panelJuego.getControlTiles().getMapTileNum()[personajeColIzq][personajeFilArr];
                tileNum2 = panelJuego.getControlTiles().getMapTileNum()[personajeColIzq][personajeFilAbj];
                if(panelJuego.getControlTiles().getTiles()[tileNum1].getColision() == true || panelJuego.getControlTiles().getTiles()[tileNum2].getColision() == true){
                    personaje.setColisionEncendida(true);
                }
                break;
        }
    }
}
