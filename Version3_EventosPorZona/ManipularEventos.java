import java.awt.Rectangle;

public class ManipularEventos {
    GamePanel gP;
    Rectangle eventoRec;
    int eventoRectDefaultX, eventoRectDefaultY;
    //boolean colision = false;

    public ManipularEventos(GamePanel gP){
        this.gP = gP;
        eventoRec = new Rectangle();
        eventoRec.x = 23;
        eventoRec.y = 23;
        eventoRec.width = 2;
        eventoRec.height = 2;
        eventoRectDefaultX = eventoRec.x;
        eventoRectDefaultY = eventoRec.y;
    }

    public void checarEvento(){
        if(colision(39, 26, "cualquiera") == true){
            gP.jugador.mundoX = gP.tileSize*30;
            gP.jugador.mundoY = gP.tileSize*26;
        }
    }

    public boolean colision(int colEvento, int filEvento, String reqDireccion){
        boolean colision = false;

        int auxX = gP.jugador.areaSolida.x;
        int auxY = gP.jugador.areaSolida.y;

        gP.jugador.areaSolida.x = gP.jugador.mundoX + gP.jugador.areaSolida.x;
        gP.jugador.areaSolida.y = gP.jugador.mundoY + gP.jugador.areaSolida.y;
        eventoRec.x = colEvento*gP.tileSize + eventoRec.x;
        eventoRec.y = filEvento*gP.tileSize + eventoRec.y;

        if(gP.jugador.areaSolida.intersects(eventoRec)){
            if(gP.jugador.direccion.contentEquals(reqDireccion) || reqDireccion.contentEquals("cualquiera")){
                colision = true;
            }
        }

        gP.jugador.areaSolida.x = auxX;
        gP.jugador.areaSolida.y = auxY;
        eventoRec.x = eventoRectDefaultX;
        eventoRec.y = eventoRectDefaultY;

        return colision;
    }


}
