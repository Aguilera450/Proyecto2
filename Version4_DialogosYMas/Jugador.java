import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Jugador extends Entidad{
    GamePanel gP;
    ManipularTeclas mT;
    // Coordenadas de la pantalla que serviran para la camara y
    // hacer una posicio relativa para el jugador y el mundo
    public final int pantallaX;
    public final int pantallaY;

    public Jugador(GamePanel gP, ManipularTeclas mT){
        this.gP = gP;
        this.mT = mT;
        pantallaX=gP.anchoPantalla/2 - (gP.tileSize/2);
        pantallaY=gP.altoPantalla/2 - (gP.tileSize/2);

        // HitBox del jugador con parametros (x,y,ancho, alto)
        areaSolida = new Rectangle(8,16,32,32);

        setValoresDefault();
        getPlayerImage();
    }

    public void setValoresDefault(){
        //Coordenadas del mundo
        mundoX = gP.tileSize*25;
        mundoY = gP.tileSize*25;
        vel = 4;
        direccion = "abajo";
    }

    public void getPlayerImage(){
        try{
            arriba1 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_up_1.png"));
            arriba2 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_up_2.png"));
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_down_1.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_down_2.png"));
            izq1 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_left_1.png"));
            izq2 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_left_2.png"));
            der1 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_right_1.png"));
            der2 = ImageIO.read(getClass().getResourceAsStream("/JugadorSprites/WalkingSprites/boy_right_2.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void actualizar(){
        if(mT.irArriba == true || mT.irAbajo == true || mT.irIzquierda == true || mT.irDerecha == true){
            if(mT.irArriba == true){
                direccion = "arriba";
            } else if(mT.irAbajo == true){
                direccion = "abajo";
            } else if(mT.irDerecha == true){
                direccion = "derecha";
            } else if(mT.irIzquierda == true){
                direccion = "izquierda";
            }

            // Checamos las colisiones
            colisionEncendida = false;
            gP.checarColisiones.checarTile(this);
            if(colisionEncendida == false){
                switch (direccion) {
                    case "arriba":
                        mundoY -= vel;
                        break;
                    
                    case "abajo":
                        mundoY += vel;
                        break;
        
                    case "derecha":
                        mundoX += vel;
                        break;
        
                    case "izquierda":
                        mundoX -= vel;
                        break;
                }
            }

            // Cambia los sprites
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        // Se le asigna color blanco
        //g2.setColor(Color.white);
        // Se hace un rectangulo rellando de tamaño de un tile*escala
        //g2.fillRect(x, y, gP.tileSize, gP.tileSize);;
        BufferedImage imagen = null;
        switch(direccion){
            case "arriba":
                if(spriteNum == 1){
                    imagen = arriba1;
                }
                if(spriteNum == 2){
                    imagen = arriba2;
                }
                break;
            case "derecha":
                if(spriteNum == 1){
                    imagen = der1;
                }
                if(spriteNum == 2){
                    imagen = der2;
                }
                break;
            case "abajo":
                if(spriteNum == 1){
                    imagen = abajo1;
                }
                if(spriteNum == 2){
                    imagen = abajo2;
                }
                break;
            case "izquierda":
                if(spriteNum == 1){
                    imagen = izq1;
                }
                if(spriteNum == 2){
                    imagen = izq2;
                }
                break;
        }

        g2.drawImage(imagen, pantallaX, pantallaY, gP.tileSize, gP.tileSize, null);
    }
}
