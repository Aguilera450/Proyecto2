import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
/**
 * CLase para controlar Tiles.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 31/05/2022
 */
public class ControladorTiles {
    private PanelJuego panelJuego;
    /** Arreglo que guarda todos los Tiles para dibujar el mapa */
    private Tile[] tiles;
    /** Arreglo de 2 dimensiones para hacer el mapa */
    private int[][] mapTileNum;
    
    /** Mapa 2 */
    private String mapa2 = "/Mapas/Sotano_Tlahuiz_44x51.txt";
    /** Mapa 1 */
    private String mapa1 = "/Mapas/Entrada_Darwin_50x48.txt";
    /** Mapa Actual */
    private String mapaActual = mapa1;

    /**
     * Constructor del Controlador
     * @param <code>panelJuego</code> -- Panel de Juego
     */
    public ControladorTiles(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
        this.tiles = new Tile[100];
        getTileImagen();
        cargarMapa(mapa1, 48, 50);
    }

    /**
     * Metodo que dado una ruta, columnas del mapa
     * y filas del mapa lee un txt con el mapa.
     * @param ruta  -- ruta del mapa
     * @param colMax -- columnas del mapa
     * @param filMax -- filas del mapa
     */
    public void cargarMapa(String ruta, int colMax, int filMax){
        mapTileNum = new int[colMax][filMax];
        try{
            InputStream is =getClass().getResourceAsStream(ruta);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < colMax && row < filMax){
                String linea = br.readLine();
                while(col < colMax){
                    String[] numeros = linea.split(" ");
                    int num = Integer.parseInt(numeros[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == colMax){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que lee los numeros del mapa y los pasa a Tiles.
     */
    public void getTileImagen(){
        try{
            tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/00.png")));
            tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/01.png")));
            tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/02.png")));
            tiles[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/03.png")));
            tiles[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/04.png")));
            tiles[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/05.png")));
            tiles[6] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/06.png")));
            tiles[7] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/07.png")));
            tiles[8] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/08.png")));
            tiles[9] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/09.png")));
            tiles[10] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/10.png")));
            tiles[11] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/11.png")),true);
            tiles[12] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/12.png")),true);
            tiles[13] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/13.png")),true);
            tiles[14] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/14.png")),true);
            tiles[15] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/15.png")),true);
            tiles[16] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/16.png")),true);
            tiles[17] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/17.png")),true);
            tiles[18] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/18.png")),true);
            tiles[19] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/19.png")),true);
            tiles[20] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/20.png")),true);
            tiles[21] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/21.png")));
            tiles[22] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/22.png")));
            tiles[23] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/23.png")));
            tiles[24] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/24.png")));
            tiles[25] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/25.png")));
            tiles[26] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/26.png")),true);
            tiles[27] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/27.png")),true);
            tiles[28] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/28.png")),true);
            tiles[29] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/29.png")));
            tiles[30] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/30.png")));
            tiles[31] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/31.png")));
            tiles[32] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/32.png")));
            tiles[33] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/33.png")));
            tiles[34] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/34.png")));
            tiles[35] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/35.png")));
            tiles[36] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/36.png")),true);
            tiles[37] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Tiles/37.png")),true);
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("Error Tile no encontrado");
        }
    }

    /**
     * DIbuja el mapa en pantalla
     * @param grafico2D -- objeto 2d para dibujar el mapa
     */
    public void dibujar(Graphics2D grafico2D){
        int mundoCol = 0;
        int mundoRow = 0;

        while(mundoCol < mapTileNum.length && mundoRow < mapTileNum[0].length){
            int tileNum = mapTileNum[mundoCol][mundoRow];
            int mundoX = mundoCol * panelJuego.getTamanoTile();
            int mundoY = mundoRow * panelJuego.getTamanoTile();
            //int pantallaX = mundoX -panelJuego.jugador.mundoX + panelJuego.jugador.pantallaX;
            int pantallaX = mundoX -panelJuego.getControlJugador().getJugador().getCordX() + panelJuego.getControlJugador().getPantallaX();
            int pantallaY = mundoY -panelJuego.getControlJugador().getJugador().getCordY() + panelJuego.getControlJugador().getPantallaY();
            if(mundoX + panelJuego.getTamanoTile() > panelJuego.getControlJugador().getJugador().getCordX() - panelJuego.getControlJugador().getPantallaX() &&
                mundoX - panelJuego.getTamanoTile() < panelJuego.getControlJugador().getJugador().getCordX() + panelJuego.getControlJugador().getPantallaX() &&
                mundoY + panelJuego.getTamanoTile() > panelJuego.getControlJugador().getJugador().getCordY() - panelJuego.getControlJugador().getPantallaY() &&
                mundoY - panelJuego.getTamanoTile() < panelJuego.getControlJugador().getJugador().getCordY() + panelJuego.getControlJugador().getPantallaY()){
                    grafico2D.drawImage(tiles[tileNum].getImagen(),pantallaX,pantallaY,panelJuego.getTamanoTile(),panelJuego.getTamanoTile(),null);
                }
            
            mundoCol++;
            if(mundoCol == mapTileNum.length){
                mundoCol = 0;
                mundoRow++;
            }
        }
    }

    /**
     * Devuelve el arreglo que contiene a los tiles.
     * @return Tile[] Arreglo de Tiles con los Tiles para dibujar
     */
    public Tile[] getTiles(){
        return tiles;
    }

    /**
     * Devuelve el arreglo que contiene al mapa.
     * @return int[]][] Arreglo de numeros que representa el mapa
     */
    public int[][] getMapTileNum(){
        return mapTileNum;
    }

    /**
     * Devuelve el nombre del mapa actual
     * @return String -- Nombre del mapa actual.
     */
    public String getNombreMapaActual(){
        return mapaActual;
    }

    /**
     * Cambia el nombre del mapa actual
     * @param mapaActual Nuevo nombre del mapa actual.
     */
    public void setNombreMapaActual(String mapaActual){
        this.mapaActual = mapaActual;
    }

    /**
     * Devuelve el nombre del mapa 1
     * @return String
     */
    public String getMapa1(){
        return mapa1;
    }

    /**
     * Devuelve el nombre del mapa 2
     * @return String
     */
    public String getMapa2(){
        return mapa2;
    }
}
