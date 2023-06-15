public class ControladorJefe {
    PanelJuego panelJuego;
    Jefe jefe;
    private final int hitBoxX;
    private final int hitBoxY;
    private final int hitBoxAncho;
    private final int hitBoxAlto;
    
    public ControladorJefe(PanelJuego panelJuego, Jefe jefe){
        this.panelJuego = panelJuego;
        this.jefe = jefe;
        hitBoxX = 8;
        hitBoxY = 16;
        hitBoxAncho = 32;
        hitBoxAlto = 32;
        ajustesDefaultJefe();
    }

    public ControladorJefe(PanelJuego panelJuego){
        this(panelJuego, new Jefe(null, "Canek"));
    }

    public void ajustesDefaultJefe(){
        //jefe.setCoordX(panelJuego.getTamanoTile()*0);
        //jefe.setCoordY(panelJuego.getTamanoTile()*0);
    }
    
    public Jefe getJefe(){
        return jefe;
    }
}