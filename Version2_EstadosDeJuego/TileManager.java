import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gP;
    Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gP){
        this.gP = gP;
        tile = new Tile[10];
        mapTileNum = new int[gP.maxMundoCol][gP.maxMundoRow];
        getTileImagen();
        cargarMapa("MapaMundo.txt");
    }

    public void cargarMapa(String ruta){
        try{
            InputStream is =getClass().getResourceAsStream(ruta);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col < gP.maxMundoCol && row < gP.maxMundoRow){
                String linea = br.readLine();
                while(col < gP.maxMundoCol){
                    String[] numeros = linea.split(" ");
                    int num = Integer.parseInt(numeros[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gP.maxMundoCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImagen(){
        try{
            tile[0] = new Tile();
            tile[0].imagen = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].imagen = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            tile[1].colision = true;

            tile[2] = new Tile();
            tile[2].imagen = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
            tile[2].colision = true;

            tile[3] = new Tile();
            tile[3].imagen = ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].imagen = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
            tile[4].colision = true;

            tile[5] = new Tile();
            tile[5].imagen = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int mundoCol = 0;
        int mundoRow = 0;

        while(mundoCol < gP.maxMundoCol && mundoRow < gP.maxMundoRow){
            int tileNum = mapTileNum[mundoCol][mundoRow];

            int mundoX = mundoCol * gP.tileSize;
            int mundoY = mundoRow * gP.tileSize;
            int pantallaX = mundoX -gP.jugador.mundoX + gP.jugador.pantallaX;
            int pantallaY = mundoY -gP.jugador.mundoY + gP.jugador.pantallaY;
            if(mundoX + gP.tileSize > gP.jugador.mundoX - gP.jugador.pantallaX &&
                mundoX - gP.tileSize < gP.jugador.mundoX + gP.jugador.pantallaX &&
                mundoY + gP.tileSize > gP.jugador.mundoY - gP.jugador.pantallaY &&
                mundoY - gP.tileSize < gP.jugador.mundoY + gP.jugador.pantallaY){
                    g2.drawImage(tile[tileNum].imagen,pantallaX,pantallaY,gP.tileSize,gP.tileSize,null);
                }
            
            mundoCol++;
            if(mundoCol == gP.maxMundoCol){
                mundoCol = 0;
                mundoRow++;
            }
        }
    }
}
