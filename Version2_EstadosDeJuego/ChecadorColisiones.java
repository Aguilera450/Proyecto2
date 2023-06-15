public class ChecadorColisiones {

    GamePanel gP;

    public ChecadorColisiones(GamePanel gP){
        this.gP = gP;
    }

    public void checarTile(Entidad entidad){
        // cordenadas de la hitbox
        int entidadIzqMundoX = entidad.mundoX + entidad.areaSolida.x;
        int entidadDerMundoX = entidad.mundoX + entidad.areaSolida.x + entidad.areaSolida.width;
        int entidadArrMundoY = entidad.mundoY + entidad.areaSolida.y;
        int entidadAbjMundoY = entidad.mundoY + entidad.areaSolida.y + entidad.areaSolida.height;

        // Filas y columnas
        int entidadColIzq = entidadIzqMundoX/gP.tileSize;
        int entidadColDer = entidadDerMundoX/gP.tileSize;
        int entidadFilArr = entidadArrMundoY/gP.tileSize;
        int entidadFilAbj = entidadAbjMundoY/gP.tileSize;

        // Tiles
        int tileNum1, tileNum2;

        switch (entidad.direccion) {
            case "arriba":
                entidadFilArr = (entidadArrMundoY - entidad.vel)/gP.tileSize;
                tileNum1 = gP.tileM.mapTileNum[entidadColIzq][entidadFilArr];
                tileNum2 = gP.tileM.mapTileNum[entidadColDer][entidadFilArr];
                if(gP.tileM.tile[tileNum1].colision == true || gP.tileM.tile[tileNum2].colision == true){
                    entidad.colisionEncendida = true;
                }
                break;
            
            case "abajo":
                entidadFilAbj = (entidadAbjMundoY + entidad.vel)/gP.tileSize;
                tileNum1 = gP.tileM.mapTileNum[entidadColIzq][entidadFilAbj];
                tileNum2 = gP.tileM.mapTileNum[entidadColDer][entidadFilAbj];
                if(gP.tileM.tile[tileNum1].colision == true || gP.tileM.tile[tileNum2].colision == true){
                    entidad.colisionEncendida = true;
                }
                break;

            case "derecha":
                entidadColDer = (entidadDerMundoX + entidad.vel)/gP.tileSize;
                tileNum1 = gP.tileM.mapTileNum[entidadColDer][entidadFilArr];
                tileNum2 = gP.tileM.mapTileNum[entidadColDer][entidadFilAbj];
                if(gP.tileM.tile[tileNum1].colision == true || gP.tileM.tile[tileNum2].colision == true){
                    entidad.colisionEncendida = true;
                }
                break;

            case "izquierda":
                entidadColIzq = (entidadIzqMundoX - entidad.vel)/gP.tileSize;
                tileNum1 = gP.tileM.mapTileNum[entidadColIzq][entidadFilArr];
                tileNum2 = gP.tileM.mapTileNum[entidadColIzq][entidadFilAbj];
                if(gP.tileM.tile[tileNum1].colision == true || gP.tileM.tile[tileNum2].colision == true){
                    entidad.colisionEncendida = true;
                }
                break;
        }
    }
}
