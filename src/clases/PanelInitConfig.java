package clases;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelInitConfig extends JPanel {

    JButton Boton_CrearImagen, Boton_CargarImagen, Boton_OK, Boton_Cancelar;
    JScrollBar SbAlto, SbAncho;
    JTextField TextAlto, TextAncho, TextName;
    JLabel Label_Alto, Label_Ancho, Label_Imagen, Label_Name;
    int height, width, CerrarPanel;
    boolean Bool_Img_No_Creada;
    Square Image[][];

    public PanelInitConfig(int x, int y, int width, int heigth) {
        super();
        setLayout(null);
        setBounds(x, y, width, heigth);
        setBackground(Color.red);
        setVisible(true);
        CargarComponentes();

    }

    private void CargarComponentes() {
        CerrarPanel = 0;
        Bool_Img_No_Creada = false;
        this.height = 32;
        this.width = 32;
        BotonNuevaImagen();
        BotonCargarImagen();
        LoadAlto();
        LoadAncho();
        LoadName();
        Lbl_Imagen();
        Boton_Ok();
        Boton_Cancelar();
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public int RetornarNumeroCorrecto(int n) {
        if (n > 120) {
            n = 120;
        }
        if (n < 10) {
            n = 10;
        }
        return n;

    }

    private void BotonNuevaImagen() {

        Boton_CrearImagen = new JButton("Crear Imagen");
        Boton_CrearImagen.setForeground(Color.pink);
        Boton_CrearImagen.setBackground(Color.black);
        Boton_CrearImagen.setBounds(10, 10, 200, 30);
        add(Boton_CrearImagen);
        Boton_CrearImagen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ChangeState(true);
                Boton_OK.setVisible(true);
                Boton_CargarImagen.setVisible(false);
                Boton_Cancelar.setVisible(true);

            }
        });
    }

    private void BotonCargarImagen() {
        Boton_CargarImagen = new JButton("Subir Imagen");
        Boton_CargarImagen.setForeground(Color.pink);
        Boton_CargarImagen.setBackground(Color.black);
        Boton_CargarImagen.setBounds(300, 10, 200, 30);
        add(Boton_CargarImagen);
        Boton_CargarImagen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.setCurrentDirectory(null);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
                chooser.setFileFilter(filter);
                int status = chooser.showDialog(null, "Seleccionar directorio");
                if (status == JFileChooser.APPROVE_OPTION) {
                    File Directorio = chooser.getSelectedFile();
                    try {
                        BufferedImage img = ImageIO.read(Directorio);
                        if (img.getWidth() >= 10 && img.getHeight() >= 10 && img.getWidth() <= 120 && img.getHeight() <= 120) {
                            Image = new Square[img.getHeight()][img.getWidth()];

                            int Alto = img.getHeight();
                            int Ancho = img.getWidth();

                            for (int i = 0, y = 0; i < Alto; i++, y += 500 / Alto) {
                                for (int j = 0, x = 0; j < Ancho; j++, x += 600 / Ancho) {

                                    int clr = img.getRGB(j, i);
                                    Color c = new Color(clr, true);
                                    if (c.getAlpha() == 0) {
                                        Image[i][j] = new Square(new Color(0, 0, 0, 0), x, y, 600 / Ancho, 500 / Alto);
                                    } else {
                                        Image[i][j] = new Square(c, x, y, 600 / Ancho, 500 / Alto);
                                        Image[i][j].Active = true;
                                    }

                                }
                            }

                            TextName.setText(Directorio.getName());
                            TextName.setVisible(true);
                            TextAlto.setText("" + img.getHeight());
                            TextAncho.setText("" + img.getWidth());
                            Label_Name.setVisible(true);
                            Bool_Img_No_Creada = true;
                            ImageIcon icon = new ImageIcon(img);
                            Label_Imagen.setIcon(icon);
                            Label_Imagen.setVisible(true);
                            Label_Imagen.updateUI();
                            Boton_OK.setVisible(true);
                            Boton_Cancelar.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Imagen muy grande o muy pequeña");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PanelInitConfig.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void LoadAlto() {
        Label_Alto = new JLabel("Alto");
        Label_Alto.setLayout(null);
        Label_Alto.setBounds(30, 50, 100, 30);
        Label_Alto.setOpaque(true);
        Label_Alto.setBackground(Color.black);
        Label_Alto.setForeground(Color.pink);
        Label_Alto.setVisible(false);
        add(Label_Alto);

        TextAlto = new JTextField();
        TextAlto.setBounds(30, 100, 100, 30);
        add(TextAlto);
        TextAlto.setVisible(false);
        TextAlto.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (isNumeric(TextAlto.getText())) {
                    SbAlto.setValue(RetornarNumeroCorrecto(Integer.parseInt(TextAlto.getText())));
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (isNumeric(TextAlto.getText())) {
                    SbAlto.setValue(RetornarNumeroCorrecto(Integer.parseInt(TextAlto.getText())));
                }
            }
        });

        TextAlto.setText("" + this.height);

        SbAlto = new JScrollBar();
        SbAlto.setBounds(150, 50, 30, 100);
        SbAlto.setMinimum(10);
        SbAlto.setMaximum(130);
        SbAlto.setVisible(false);
        SbAlto.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                TextAlto.setText("" + SbAlto.getValue());
            }
        });
        SbAlto.setValue(height);
        add(SbAlto);
    }

    private void LoadAncho() {
        Label_Ancho = new JLabel(" Ancho");
        Label_Ancho.setLayout(null);
        Label_Ancho.setBounds(200, 50, 100, 30);
        Label_Ancho.setOpaque(true);
        Label_Ancho.setBackground(Color.black);
        Label_Ancho.setForeground(Color.pink);
        Label_Ancho.setVisible(false);
        add(Label_Ancho);

        TextAncho = new JTextField();
        TextAncho.setBounds(200, 100, 100, 30);
        add(TextAncho);
        TextAncho.setVisible(false);
        TextAncho.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (isNumeric(TextAncho.getText())) {
                    SbAncho.setValue(RetornarNumeroCorrecto(Integer.parseInt(TextAncho.getText())));
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (isNumeric(TextAncho.getText())) {
                    SbAncho.setValue(RetornarNumeroCorrecto(Integer.parseInt(TextAncho.getText())));
                }
            }
        });
        TextAncho.setText("" + width);

        SbAncho = new JScrollBar();
        SbAncho.setBounds(310, 50, 30, 100);
        SbAncho.setMinimum(10);
        SbAncho.setMaximum(130);
        SbAncho.setVisible(false);
        SbAncho.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                TextAncho.setText("" + SbAncho.getValue());
            }
        });
        SbAncho.setValue(width);
        add(SbAncho);
    }

    private void ChangeState(boolean state) {
        Label_Alto.setVisible(state);
        TextName.setVisible(state);
        SbAlto.setVisible(state);
        TextAlto.setVisible(state);
        Label_Ancho.setVisible(state);
        SbAncho.setVisible(state);
        TextAncho.setVisible(state);
        Label_Name.setVisible(state);
    }

    private void Boton_Ok() {
        Boton_OK = new JButton("OK");
        Boton_OK.setForeground(Color.pink);
        Boton_OK.setBackground(Color.black);
        Boton_OK.setBounds(150, 180, 200, 30);
        add(Boton_OK);
        Boton_OK.setVisible(false);

        Boton_OK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                width = SbAncho.getValue();
                height = SbAlto.getValue();
                CerrarPanel = 1;
            }
        }
        );
    }

    private void Boton_Cancelar() {
        Boton_Cancelar = new JButton("Cancel");
        Boton_Cancelar.setForeground(Color.pink);
        Boton_Cancelar.setBackground(Color.black);
        Boton_Cancelar.setBounds(350, 180, 200, 30);
        add(Boton_Cancelar);
        Boton_Cancelar.setVisible(false);

        Boton_Cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangeState(false);
                Boton_OK.setVisible(false);
                Boton_Cancelar.setVisible(false);
                Label_Imagen.setVisible(false);
                Boton_CargarImagen.setVisible(true);
                CerrarPanel = 2;

            }
        });
    }

    private void Lbl_Imagen() {
        Label_Imagen = new JLabel();
        Label_Imagen.setLayout(null);
        Label_Imagen.setBounds(150, 90, 120, 120);
        Label_Imagen.setVisible(false);
        add(Label_Imagen);
    }

    private void LoadName() {

        Label_Name = new JLabel("Name");
        Label_Name.setLayout(null);
        Label_Name.setBounds(350, 50, 100, 30);
        Label_Name.setOpaque(true);
        Label_Name.setBackground(Color.black);
        Label_Name.setForeground(Color.pink);
        Label_Name.setVisible(false);
        add(Label_Name);

        TextName = new JTextField();
        TextName.setBounds(350, 100, 100, 30);
        add(TextName);
        TextName.setVisible(false);
        TextName.setText("Canvas");

    }

}
