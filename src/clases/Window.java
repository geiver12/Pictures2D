package clases;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Window extends JFrame {

    PanelInitConfig panelInitConfig;
    PanelImages panelImages, panelAnimation;
    PanelDashboard panelDashboard;
    PanelColors panelCurrentColor;
    PanelName panelName;
    ArrayList<ImageWork> ImagesWork;
    ImageWork CurrentImage;
    Square CopySquare[][];


    /*1=crear raya---2=pintarcuadro ---3=btnborrar---4=obtnercolor---5=invertir---6=rotar----7=cambiarcolor*/
    // int Boton_Seleccionado;//1=pintarraya,2=pintarcuadro,3=botton borrar,4=recuperar color,5=cambiar el color por sectores
    Timer Actualizador;

    public Window() throws HeadlessException {

        super();
        LoadWindow();
        InitComponents();
        setVisible(true);
        UpdateTimer();

    }

    private void UpdateTimer() {

        Actualizador = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CheckDashboard();//Check tool Active
                SelectCurrentImage(); //Select a panel of Work
                if (CurrentImage.ToolSelect == 7) {
                    panelCurrentColor.ShowCurrentColor.setBackground(CurrentImage.color);
                }
                CurrentImage.UpdateComponents(panelCurrentColor.ShowCurrentColor.getBackground()); //Load the color y update components.
                // System.out.println(CurrentImage.Identificador - 1);
                panelImages.UpdateComponents(CurrentImage.imagen, CurrentImage.Identificador - 1);
                panelAnimation.UpdateComponents(CurrentImage.imagen, CurrentImage.Identificador - 1);

                panelImages.repaint();
                panelAnimation.repaint();

                //Get the color while it swear the color of some square.
            }

        });

        Actualizador.start();
    }

    private void CheckDashboard() {
        switch (panelDashboard.CurrentButton) {
            case 1: {

                JFrame Miniventana = new JFrame();
                Miniventana.setBounds(910, 410, 120, 120);
                Miniventana.setResizable(false);//para que no se pueda cambiar de tamaño la ventana
                Miniventana.setBackground(Color.pink);
                Miniventana.setContentPane(new JLabel(new ImageIcon(CurrentImage.imagen)));
                Miniventana.setLayout(null);
                Miniventana.setVisible(true);//para que la ventana visible
                CurrentImage.ToolSelect = 1;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 2: {

                CopySquare = new Square[this.CurrentImage.CuadrosHoja.length][this.CurrentImage.CuadrosHoja[0].length];

                for (int i = 0; i < CurrentImage.CuadrosHoja.length; i++) {
                    for (int j = 0; j < CurrentImage.CuadrosHoja[0].length; j++) {
                        CopySquare[i][j] = new Square(new Color(0, 0, 0, 0));
                        CopySquare[i][j].Active = CurrentImage.CuadrosHoja[i][j].Active;
                        CopySquare[i][j].color = CurrentImage.CuadrosHoja[i][j].color;
                    }
                }
                CurrentImage.ToolSelect = 2;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 3: {

                int ancho, alto;
                if (CurrentImage.imagen.getHeight() <= CopySquare.length) {
                    alto = CurrentImage.imagen.getHeight();
                } else {
                    alto = CopySquare.length;
                }
                if (CurrentImage.imagen.getWidth() <= CopySquare[0].length) {
                    ancho = CurrentImage.imagen.getWidth();
                } else {
                    ancho = CopySquare[0].length;
                }

                for (int i = 0; i < alto; i++) {
                    for (int j = 0; j < ancho; j++) {
                        CurrentImage.CuadrosHoja[i][j].color = CopySquare[i][j].color;
                        CurrentImage.CuadrosHoja[i][j].Active = CopySquare[i][j].Active;
                    }
                }

                CurrentImage.updateUI();
                CurrentImage.ToolSelect = 3;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 4: {
                CurrentImage.ToolSelect = 4; //function for the pen.
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 5: {
                CurrentImage.ToolSelect = 5;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 6: {
                CurrentImage.ToolSelect = 6;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 7: {
                CurrentImage.ToolSelect = 7;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 8: {

                int seleccion = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Opciones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Vertical", "Horizontal"}, "opcion 1");
                System.out.println(seleccion);
                if (-1 != seleccion) {
                    CurrentImage.InversionBotones(seleccion);
                    CurrentImage.CambioMovimiento();
                }

                panelDashboard.CurrentButton = 0;
                break;
            }
            case 9: {
                CurrentImage.RotarBotones();
                CurrentImage.CambioMovimiento();
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 10: {
                CurrentImage.ToolSelect = 10;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 11: {
                CurrentImage.Movimientos(-1);//z Deshacer Movimiento
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 12: {
                CurrentImage.Movimientos(1);//y Añadir movimiento
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 13: {
                if (JOptionPane.showConfirmDialog(new JFrame(), "Desea guardar con fondo transparente", "Guardar Archivo", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    // CurrentImage.ColorFondo = true;
                } else {
                    //CurrentImage.ColorFondo = false;
                }
                GuardandoImagen();
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 14: {
                if (CurrentImage.Identificador - 1 == ImagesWork.size() - 1) {
                    CurrentImage.setVisible(false);
                    panelInitConfig.setVisible(false);

                    panelInitConfig = new PanelInitConfig(10, 160, 600, 500);
                    add(panelInitConfig);
                }
                if (CurrentImage.Identificador - 1 < ImagesWork.size() - 1) {
                    CambiarPanel(CurrentImage.Identificador);
                }
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 15: {
                if (ImagesWork.size() != 0) {
                    panelInitConfig.setVisible(false);
                    CurrentImage.setVisible(true);
                }
                if (CurrentImage.Identificador - 1 > 0) {
                    CambiarPanel(CurrentImage.Identificador - 2);
                }
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 16: {
                CurrentImage.ToolSelect = 16;
                panelDashboard.CurrentButton = 0;
                break;
            }
            case 17: {

                panelDashboard.CurrentButton = 0;
                break;
            }
        }
    }

    private void LoadWindow() {
        setTitle("Crear Imagen-2D");
        setSize(820, 720);
        this.getContentPane().setBackground(Color.pink);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Dibujo.png"));
        setIconImage(icon);
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void InitComponents() {

        panelImages = new PanelImages(10, 10, 600, 150, Color.yellow, 0);
        add(panelImages);

        panelAnimation = new PanelImages(610, 10, 200, 150, Color.WHITE, 50);
        add(panelAnimation);

        panelInitConfig = new PanelInitConfig(10, 160, 600, 500);
        add(panelInitConfig);

        panelCurrentColor = new PanelColors(610, 160, 200, 300);
        add(panelCurrentColor);

        panelDashboard = new PanelDashboard(610, 460, 200, 150);
        add(panelDashboard);

        ImagesWork = new ArrayList<ImageWork>();
        CurrentImage = new ImageWork(10, 160, 600, 500, Color.yellow, 32, 32);

        panelName = new PanelName(10, 660, 600, 50);
        add(panelName);

        CopySquare = new Square[CurrentImage.CuadrosHoja.length][CurrentImage.CuadrosHoja[0].length];

        add(CurrentImage);
        CurrentImage.setVisible(false);

    }

    private void GuardandoImagen() {

        File fichero;
        System.out.println(panelName.TxfName.getText().contains(".png"));

        if (panelName.TxfName.getText().contains(".png") || panelName.TxfName.getText().contains(".jpg")) {
            fichero = new File(panelName.TxfName.getText());
        } else {
            fichero = new File(panelName.TxfName.getText() + ".png");
        }

        String formato = "png";
        try {
            ImageIO.write(CurrentImage.imagen, formato, fichero);
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

    }

    private void CambiarPanel(int id) {
        CurrentImage.setVisible(false);
        CurrentImage = ImagesWork.get(id);
        panelName.LoadAtributes(id);
        CurrentImage.setVisible(true);
        CurrentImage.updateUI();
        add(CurrentImage);
    }

    private void SelectCurrentImage() {

        if (panelInitConfig.CerrarPanel == 1) {
            if (panelInitConfig.Bool_Img_No_Creada) {
                ImagesWork.add(new ImageWork(ImagesWork.size() + 1, panelInitConfig.Image, 160, 600, 500));
            } else {
                ImagesWork.add(new ImageWork(ImagesWork.size() + 1, 160, 600, 500, Color.white, panelInitConfig.width, panelInitConfig.height));
            }

            panelImages.addImage(CurrentImage.imagen); //Add Image to panel Images
            panelAnimation.addImage(CurrentImage.imagen);

            panelName.addImage(panelInitConfig.TextName.getText(), panelInitConfig.TextAncho.getText(), panelInitConfig.TextAlto.getText(), ImagesWork.size() - 1);

            CurrentImage = ImagesWork.get(ImagesWork.size() - 1);
            panelInitConfig.CerrarPanel = 2;
        }

        if (panelInitConfig.CerrarPanel == 2 && ImagesWork.size() != 0) {
            panelInitConfig.setVisible(false);
            CurrentImage.updateUI();
            CurrentImage.setVisible(true);
            add(CurrentImage);
            panelInitConfig.CerrarPanel = 3;

        }
    }

}
