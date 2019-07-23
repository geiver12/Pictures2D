package clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.java2d.pipe.DrawImage;

/**
 *
 * @author Geiver Botello this panel show the images create and show the
 * sebquent of images how a gif.
 */
class PanelImages extends JPanel {

    ArrayList<BufferedImage> ListImages;
    int Temporizador, CountTemp, ValCurrent;

    public PanelImages(int x, int y, int width, int height, Color c, int Temporizador) {
        super();
        setLayout(null);
        setBounds(x, y, width, height);
        setBackground(c);
        setVisible(true);
        ListImages = new ArrayList<BufferedImage>();
        this.Temporizador = Temporizador;
        CountTemp = 0;
        ValCurrent = 0;

    }

    public void addImage(BufferedImage image) {
        ListImages.add(image);
    }

    public void paint(Graphics g) {

        super.paint(g);
        if (Temporizador == 0) {
            int y = 10, x = 10;
            for (BufferedImage ListImage : ListImages) {
                g.drawImage(ListImage, x, y, null);
                x += ListImage.getWidth();
            }
        } else {
            if (CountTemp > Temporizador) {
                ValCurrent++;
                CountTemp = 0;
                if (ValCurrent >= ListImages.size()) {
                    ValCurrent = 0;
                }
            }

            if (ListImages.size() > 0) {
                g.drawImage(ListImages.get(ValCurrent), 20, 20, null);
            }
            CountTemp++;
        }

    }

    void UpdateComponents(BufferedImage imagen, int Identificador) {
        //System.out.println(ListImages.size() + "," + Identificador);
        if (ListImages.size() > 0) {
            this.ListImages.set(Identificador, imagen);
        }
    }

}
