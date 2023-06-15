import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        // Creamos Objeto JFrame
        JFrame ventana = new JFrame();
        // Definimos la forma en la que se va a cerrar.
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establecemos que el tamaño de la pantalla no se mueva
        ventana.setResizable(false);
        // Ponemos el Titulo de la ventana.
        ventana.setTitle("MiJuego 2D");

        GamePanel gamePanel = new GamePanel();
        ventana.add(gamePanel);
        ventana.pack();

        // Establecemos la pantalla en el centro.
        ventana.setLocationRelativeTo(null);
        // Hacemos que la pantalla sea visible
        ventana.setVisible(true);

        //Iniciamos el hilo de juego de GamePanel
        gamePanel.iniciarHiloJuego();
    }
}