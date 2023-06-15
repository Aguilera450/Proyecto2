import javax.swing.JFrame;

/**
 * Clase Principal del Proyecto 2.
 * @author Adrian Aguilera Moreno     - Aguiler450
 * @author Rosas Franco Diego Angel   - shikitimiau
 * @author Marco Antonio Rivera Silva - DONMARCORS
 * @version 1.0 - 22/05/2022
 */
public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Proyecto 2");
        PanelJuego panelJuego = new PanelJuego();
        ventana.add(panelJuego);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        panelJuego.iniciarHiloJuego();
    }
}
