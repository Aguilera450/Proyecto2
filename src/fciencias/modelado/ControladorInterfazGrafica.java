import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.imageio.ImageIO;
import java.util.Iterator;

public class ControladorInterfazGrafica{
    /** referencia al panel de juego */
    protected PanelJuego panelJuego;
    /** objeto grafico 2d para dibujar */
    protected Graphics2D grafico2D;
    /** Fuente MaruMonica */
    protected Font maruMonica;
    /** Numero de opcion del titulo */
    private int numOpcion = 0;
    /** Numero de opcion de la oantalla de combate */
    private int opcionAtaque = 0;
    /** Numero de opcion de la pantalla elegir carrera */
    private int opcionCarrera = 0;
    /** Imagen para dibujar */
    BufferedImage imagen;
    /** Determina si se muestra un mensaje normal */
    private boolean mensaje;
    /** Se muestra este texto en los mensajes */
    private String textoMensaje = "";
    /** Determina si se muestra un mensaje batalla */
    private boolean mensajeBatalla;
    /** Tienda de Ataques */
    private TiendaAtaquesEspeciales tiendita;
    /** Determina si se muestra el formulario */
    private boolean mostrarFormulario = true;

    /** 
     * Constructor del controlador de pelea.
     * @param panelJuego -- panel de juego
     */
    public ControladorInterfazGrafica(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        int tamanoFuente = 30;
        tiendita = new TiendaAtaquesEspeciales();
        tiendita.agregarAtaqueEspecialACatalogo(new AtaqueDanioExagerado("AtauqeTienda1", "El ataque golpea al contrincante "
        + " tan rápido que el usuario y el jugador no perciben estos golpes.", 150));
        tiendita.agregarAtaqueEspecialACatalogo(new AtaquePotenciado("AtaqueTienda2", "Este ataque detiene la respiración " 
        + "del contrincante por minutos y hace que el tiempo pase de manera más rápida para el atacante. "
        + "Así lo que puede ser una eternidad para el contrincante, es un pequeño instante para el atacante."
        + "El ataque persiste hasta 3 turnos y potencia en 40.", 65, 40, 3));
        tiendita.agregarAtaqueEspecialACatalogo(new AtaqueDrenarVida("AtaqueTienda3", "El ataque evapora la cantidad de agua en el contrincante. "
        + "Drena vida por 37 y en los próximos 3 turnos.", 24, 37, 3));
        try {
            InputStream is = getClass().getResourceAsStream("/Fuentes/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            maruMonica = maruMonica.deriveFont(Font.PLAIN, tamanoFuente);
            imagen = ImageIO.read(getClass().getResourceAsStream("/Fuentes/Shinx.png"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que dibujará las ventanas y todo lo demás
     * Este metodo es usado por el metodo que dibuja en panel Juego.
     * @param grafico2D -- objeto para dibujar en la interfaz
     */
    public void dibujar(Graphics2D grafico2D) {
        this.grafico2D = grafico2D;
        grafico2D.setFont(maruMonica);
        panelJuego.getEstadoActual().dibujar();
        if(mensaje){
            panelJuego.getControlIG().mostrarMensajeNormal(textoMensaje);
        } else if(mensajeBatalla) {
            panelJuego.getControlIG().mostrarMensajeBatlla(textoMensaje);
        }

        if(mostrarFormulario){
            final JFrame parent = new JFrame();
            JButton button = new JButton();
            button.setText("Haz click en mi para poner tu nombre");
            parent.add(button);
            parent.pack();
            parent.setVisible(true);

            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String nombre = JOptionPane.showInputDialog(parent,
                            "Introduce tu nombre", null);
                    panelJuego.getControlTeclas().setNombre(nombre);
                }
            });
            mostrarFormulario = false;
        } 
    }

    /**
     * Dibuja una subventana color negra con opacidad
     * @param x posicion en x
     * @param y posicion en y
     * @param ancho ancho de la subventana
     * @param alto alto de la subventana
     */
    public void dibujarSubVentana(int x, int y, int ancho, int alto){
        Color negroTransparente = new Color(0,0,0,210);
        int anchoLinea = 5;
        int redondeo1 = 35;
        int redondeo2 = 30;
        grafico2D.setColor(negroTransparente);
        grafico2D.fillRoundRect(x, y, ancho, alto, redondeo1, redondeo1);
        grafico2D.setColor(Color.white);
        grafico2D.setStroke(new BasicStroke(anchoLinea));
        grafico2D.drawRoundRect(x+5, y+5, ancho-10, alto-10, redondeo2, redondeo2);
    }

    /**
     * Dibuja la ventana del titulo si es que esta en estado Titulo el juego
     */
    public void dibujarVentanaTitulo(){
        // Titulo de la Ventana
        grafico2D.setColor(Color.black);
        grafico2D.fillRect(0, 0, panelJuego.getAnchoPantalla(), panelJuego.getAltoPantalla());
        grafico2D.setColor(Color.white);
        grafico2D.setFont(grafico2D.getFont().deriveFont(Font.BOLD, 96F));
        String texto = "CienciasException";
        int x = coordenadaXCentradaTexto(texto);
        int y = panelJuego.getTamanoTile()*3;
        grafico2D.setColor(new Color(0,0,100));
        grafico2D.drawString(texto, x-5, y-5);
        grafico2D.setColor(new Color(100,0,0));
        grafico2D.drawString(texto, x+5, y+5);
        grafico2D.setColor(Color.white);
        grafico2D.drawString(texto, x, y);

        //Opciones de la Ventana
        grafico2D.setFont(grafico2D.getFont().deriveFont(Font.BOLD, 48F));
        texto = "Nuevo Juego";
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()*4;
        grafico2D.drawString(texto, x, y);
        if (numOpcion == 0){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }

        texto = "Proximamente";
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile() + 10;
        grafico2D.drawString(texto, x, y);
        if (numOpcion == 1){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }

        texto = "Salir";
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile() + 10;
        grafico2D.drawString(texto, x, y);
        if (numOpcion == 2){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }
        
    }

    /**
     * Dibuja la ventana de Pelea si es que esta en estado Pelea el juego
     */
    public void dibujarVentanaPelea(){
        int x = 0;
        int y = 0;
        String texto= "";
        try{
            grafico2D.drawImage(ImageIO.read(getClass().getResourceAsStream("/Imagenes/Jefes/Salon_con_pizarron.png")), x,y,panelJuego.getAnchoPantalla(),panelJuego.getAltoPantalla(),null);
        } catch (IOException e){
            e.printStackTrace();
        }
        dibujarSubVentana(x,y,panelJuego.getAnchoPantalla()/2,panelJuego.getTamanoTile()*2);
        
        x += panelJuego.getAnchoPantalla()/2;
        dibujarSubVentana(x,y,panelJuego.getAnchoPantalla()/2,panelJuego.getTamanoTile()*2);

        x = 15;
        y += (panelJuego.getTamanoTile()+10);
        texto = panelJuego.getControlJugador().getJugador().nombre();
        grafico2D.drawString(texto,x,y);

        x += panelJuego.getAnchoPantalla()/2;
        texto = panelJuego.getControlPelea().getJefe().nombre();
        grafico2D.drawString(texto,x,y);
        x = 15;

        x += panelJuego.getTamanoTile()*4;
        texto = panelJuego.getControlJugador().getJugador().getVida()+"";
        grafico2D.drawString(texto,x, y);

        x += panelJuego.getAnchoPantalla()/2;
        texto = panelJuego.getControlPelea().getJefe().getVida()+"";
        grafico2D.drawString(texto,x,y);
        x = 15 + panelJuego.getTamanoTile()*4;

        x = 100;
        y = 140;
        grafico2D.drawImage(panelJuego.getControlJugador().getJugador().getSprites().get("Der2"), x, y, panelJuego.getTamanoTile()*3,panelJuego.getTamanoTile()*3,null);

        x += panelJuego.getAnchoPantalla()/2;
        grafico2D.drawImage(panelJuego.getControlPelea().getJefe().getSprite(), x, y,panelJuego.getTamanoTile()*3,panelJuego.getTamanoTile()*3,null);

        x = 0;
        y = panelJuego.getTamanoTile()*7;
        dibujarSubVentana(x,y,panelJuego.getAnchoPantalla(),panelJuego.getTamanoTile()*3);
        

        menuDeAtaques();
    }

    /**
     * Dibuja la ventana de opciones de ataques para la ventana de pelea.
     */
    public void menuDeAtaques(){
        int x = 0;
        int y = panelJuego.getTamanoTile()*10;
        dibujarSubVentana(x, y, panelJuego.getAnchoPantalla(), panelJuego.getTamanoTile()*2);
        x += panelJuego.getTamanoTile() - panelJuego.getTamanoTile()/2;
        y += panelJuego.getTamanoTile() +10;

        String texto = "Ataque Debil";
        grafico2D.drawString(texto, x, y);
        if(opcionAtaque == 0){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile()/3, y);
        }

        x += obtenerLongitudTexto(texto) + panelJuego.getTamanoTile();
        texto = "Ataque Fuerte";
        grafico2D.drawString(texto, x, y);
        if(opcionAtaque == 1){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile()/3, y);
        }

        x += obtenerLongitudTexto(texto) + panelJuego.getTamanoTile();
        texto = "Ataque Unico";
        grafico2D.drawString(texto, x, y);
        if(opcionAtaque == 2){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile()/3, y);
        }

        x += obtenerLongitudTexto(texto) + panelJuego.getTamanoTile();
        texto = "Ataque Especial";
        grafico2D.drawString(texto, x, y);
        if(opcionAtaque == 3){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile()/3, y);
        }
    }

    /**
     * Obtiene las coordenas para centrar un texto en x
     * @return int coordenada del texto centrada.
     */
    public int coordenadaXCentradaTexto(String texto){
        int longitud = (int) grafico2D.getFontMetrics().getStringBounds(texto, grafico2D).getWidth();
        int x = (panelJuego.getAnchoPantalla()/2) - (longitud/2);
        return x;
    }

    /**
     * obtiene la longitud de un texto en medidas de la pantalla
     * @param texto -- texto a obtener medida
     * @return int medidadel texto
     */
    public int obtenerLongitudTexto(String texto){
        return (int) grafico2D.getFontMetrics().getStringBounds(texto, grafico2D).getWidth();
    }

    /**
     * Dibuja la ventana de Pausa si es que se está en modo pausa
     */
    public void dibujarVentanaPausa(){
        dibujarJugador();
        grafico2D.setColor(Color.RED);
        grafico2D.drawString("PAUSA",coordenadaXCentradaTexto("PAUSA"),panelJuego.getAltoPantalla()/2);
    }

    /**
     * Dibuja la ventana de Eleccion Carrera si es que se está en modo EleccionCarrera.
     */
    public void dibujarVentanaEleccionCarrera(){
        int x = 0;
        int y = 0;
        grafico2D.setColor(Color.black);
        grafico2D.setFont(grafico2D.getFont().deriveFont(Font.BOLD, 48F));
        grafico2D.fillRect(x, y, panelJuego.getAnchoPantalla(), panelJuego.getAltoPantalla());
        grafico2D.setColor(Color.white);
        String texto = "Elige tu carrera:";
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()+30;
        grafico2D.drawString(texto, x, y);

        texto = "Actuaria";
        grafico2D.setColor(Color.cyan);
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()+30;
        grafico2D.drawString(texto, x, y);
        if (opcionCarrera == 0){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }

        texto = "Biologia";
        grafico2D.setColor(Color.green);
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()+30;
        grafico2D.drawString(texto, x, y);
        if (opcionCarrera == 1){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }

        texto = "Matematicas";
        grafico2D.setColor(Color.red);
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()+30;
        grafico2D.drawString(texto, x, y);
        if (opcionCarrera == 2){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }

        texto = "Ciencias de la Computacion";
        grafico2D.setColor(Color.gray);
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()+30;
        grafico2D.drawString(texto, x, y);
        if (opcionCarrera == 3){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }

        texto = "Fisica";
        grafico2D.setColor(Color.yellow);
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()+30;
        grafico2D.drawString(texto, x, y);
        if (opcionCarrera == 4){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }

        texto = "Matematicas Aplicadas";
        grafico2D.setColor(Color.gray);
        x = coordenadaXCentradaTexto(texto);
        y += panelJuego.getTamanoTile()+30;
        grafico2D.drawString(texto, x, y);
        if (opcionCarrera == 5){
            grafico2D.drawString(">", x-panelJuego.getTamanoTile(), y);
        }
    }

    /**
     * Dibuja la infromacion del jugador mientras se juega y esta en modo Jugar
     */
    public void dibujarPantallaJugar(){
        grafico2D.setFont(grafico2D.getFont().deriveFont(Font.BOLD, 48F));
        grafico2D.setColor(Color.black);
        int x = 12;
        int y = 45;
        grafico2D.drawString(panelJuego.getControlJugador().getJugador().nombre(), x, y);
        x += 500;
        grafico2D.drawString("Vida: " + panelJuego.getControlJugador().getJugador().getVida(), x, y);
        grafico2D.setColor(Color.white);
        x = 15;
        y = 48;
        grafico2D.drawString(panelJuego.getControlJugador().getJugador().nombre(), x, y);
        x += 500;
        grafico2D.drawString("Vida: " + panelJuego.getControlJugador().getJugador().getVida(), x, y);
    }

    /**
     * Dibuja al jugador
     */
    public void dibujarJugador(){
        BufferedImage imagen = null;
        switch(panelJuego.getControlJugador().getJugador().getDireccion()){
            case "arriba":
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 1){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Arr1");
                }
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 2){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Arr2");
                }
                break;
            case "derecha":
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 1){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Der1");
                }
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 2){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Der2");
                }
                break;
            case "abajo":
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 1){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Aba1");
                }
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 2){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Aba2");
                }
                break;
            case "izquierda":
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 1){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Izq1");
                }
                if(panelJuego.getControlJugador().getJugador().getNumSprite() == 2){
                    imagen = panelJuego.getControlJugador().getJugador().getSprites().get("Izq2");
                }
                break;
        }
    
        grafico2D.drawImage(imagen, panelJuego.getControlJugador().getPantallaX(), panelJuego.getControlJugador().getPantallaY(), panelJuego.getTamanoTile(), panelJuego.getTamanoTile(), null);
    }

    /**
     * Muesta el inventario/Tienda sie s que se está en modo Inventario
     */
    public void mostrarInventario(){
        int x = 15;
        int y = 15;
        int ancho = panelJuego.getAnchoPantalla() - x*2;
        int alto = panelJuego.getAltoPantalla() - y*2;
        dibujarSubVentana(x, y, ancho, alto);
        y += panelJuego.getTamanoTile()*1;
        grafico2D.setFont(grafico2D.getFont().deriveFont(Font.BOLD, 30F));
        String texto = "Ataques Especiales";
        grafico2D.drawString(texto, coordenadaXCentradaTexto(texto), y);

        x += 30;
        y += panelJuego.getTamanoTile()*1;
        texto = "Habilidades en Tienda: ";
        grafico2D.drawString(texto, x, y);

        int x2 = x + obtenerLongitudTexto(texto) + panelJuego.getTamanoTile()*2;
        int y2 = y;
        texto = "Tus Ataques Especiales: ";
        grafico2D.drawString(texto, x2, y2);
        
        Iterator iterador = panelJuego.getControlJugador().getJugador().getIteratorAtaquesEspeciales();
        Iterator iteradorTienda = tiendita.getIteradorAtaquesEspecialesCatalogo();

        while (iterador.hasNext()) {
            Object ataque =  iterador.next();
            if(ataque instanceof AtaqueUnico){
                String strAtaque =  ((AtaqueUnico) ataque).getNombre();
                y2+= panelJuego.getTamanoTile()*2;
                grafico2D.drawString(strAtaque, x2, y2);
            }
        }
        while(iteradorTienda.hasNext()){
            AtaqueUnico ataqueTienda = (AtaqueUnico) iteradorTienda.next();
            y += panelJuego.getTamanoTile()*2;
            grafico2D.drawString(ataqueTienda.getNombre(), x, y);

        }
    }

    /**
     * Muestra un mensaje en la pantalla de juego.
     * @param texto -- texto a mostrar
     */
    public void mostrarMensajeNormal(String texto){
        int x = 0;
        int y = 0;
        int ancho = panelJuego.getAnchoPantalla();
        int alto = panelJuego.getAltoPantalla();
        dibujarSubVentana(x, y, ancho, panelJuego.getTamanoTile()*3);
        grafico2D.drawString(texto, x+20, y+50);
    }

    /**
     * Muestra un mensaje en la pantalla de pelea.
     * @param texto -- texto a mostrar
     */
    public void mostrarMensajeBatlla(String texto){
        String restoTexto = "";
        String restoTexto2 = "";
        String restoTexto3 = "";
        String textoActual = texto;
        int x = 0;
        int y = panelJuego.getTamanoTile()*7;
        if(texto.length() > 65){
            textoActual = texto.substring(0, 64);
            restoTexto = texto.substring(64,texto.length());
            if(restoTexto.length() > 65){
                restoTexto2 = restoTexto.substring(0, 64);
                restoTexto3 = restoTexto.substring(64,restoTexto.length());

                grafico2D.drawString(textoActual, x+20, y+50);
                y += 40; 
                grafico2D.drawString(restoTexto2, x+20, y+50);
                y += 40; 
                grafico2D.drawString(restoTexto3, x+20, y+50);
            } else {
                grafico2D.drawString(textoActual, x+20, y+50);
                y += 40; 
                grafico2D.drawString(restoTexto, x+20, y+50);
                y += 40;
            }
        } else {
            grafico2D.drawString(textoActual, x+20, y+50);
        }
    }

    /**
     * Metodo que establece si el mensaje normal se puede ver
     * @param mensaje -- true si se puede ver, false si no
     */
    public void setMensaje(boolean mensaje){
        this.mensaje = mensaje;
    }

    /**
     * Devuelve sise puede ver el mensaje normal
     * @return boolean -- true si se puede ver, false si no
     */
    public boolean getMensaje(){
        return mensaje;
    }

    /**
     * Metodo que establece si el mensaje batalla se puede ver
     * @param mensajeBatalla -- true si se puede ver, false si no
     */
    public void setMensajeBatalla(boolean mensajeBatalla){
        this.mensajeBatalla = mensajeBatalla;
    }

/**
     * Devuelve sise puede ver el mensaje batalla
     * @return boolean -- true si se puede ver, false si no
     */
    public boolean getMensajeBatalla(){
        return mensajeBatalla;
    }

    /**
     * Metoco que nos regresa que que opcion del titulo está el jugador
     * @return int -- numero de la opcion
     */
    public int getNumOpcion(){
        return numOpcion;
    }

    /**
     * Establece el numero de la opcion del menu del titulo
     * @param numOpcion -- nueva opcion
     */    
    public void setNumOpcion(int numOpcion){
        this.numOpcion = numOpcion;
    }

    /**
     * Metoco que nos regresa que que opcion del menu de ataques está el jugador
     * @return int -- numero de la opcion
     */
    public int getOpcionAtaque(){
        return opcionAtaque;
    }

    /**
     * Establece el numero de la opcion del menu de la pelea
     * @param numAtaque -- nueva opcion
     */    
    public void setOpcionAtaque(int opcionAtaque){
        this.opcionAtaque = opcionAtaque;
    }

    /**
     * Metoco que nos regresa que que opcion del la pantalla elegir carrera está el jugador
     * @return int -- numero de la opcion
     */
    public int getOpcionCarrera(){
        return opcionCarrera;
    }

    /**
     * Establece el numero de la opcion del menu del la eleccionde carrera
     * @param numCarrera -- nueva opcion carrera
     */    
    public void setOpcionCarrera(int opcionCarrera){
        this.opcionCarrera = opcionCarrera;
    }

    /**
     * extablece el texto a mostros en los mensajes
     * @param textoMensaje -- mensaje a definir
     */
    public void setTextoMensaje(String textoMensaje){
        this.textoMensaje = textoMensaje;
    }

    /**
     * cambia si se puede mostrar el formulario para ingresar nombre
     * @param mostrarFormulario -- true si se muestra, false si no.
     */
    public void setMostrarFormulario(boolean mostrarFormulario){
        this.mostrarFormulario = mostrarFormulario;
    }

}
