package clases;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/*el rango de las imagenes podra estar entre 10 y 150 pixeles*/
public class MainPrincipal {

    public MainPrincipal() {
        // La ventana 
        JFrame ventana = new JFrame("Imagen");

        // El panel de scroll 
        JScrollPane scroll = new JScrollPane();

        // La etiqueta. 
        JLabel etiqueta = new JLabel();

        // Se carga la imagen, con path absoluto para evitar problemas y debe 
        // ser un gif. 
        Icon imagen = new ImageIcon(
                "D:/youtube/imagenes/9.jpg");

        // Se mete la imagen en el label 
        etiqueta.setIcon(imagen);

        // Se mete el scroll en la ventana 
        ventana.getContentPane().add(scroll);

        // Se mete el label en el scroll 
        scroll.setViewportView(etiqueta);

        // Y se visualiza todo. 
        ventana.pack();
        ventana.setVisible(true);
    }

    public static void main(String args[]) {
        Window object = new Window();
        //new MainPrincipal();

    }
}
