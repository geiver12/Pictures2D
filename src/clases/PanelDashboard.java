package clases;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Geiver Botello Este panel se encaga de todos los intrumentos o
 * opciones que tiene el codigo disponible,linea,raya,bote,borrar,etc
 */
public class PanelDashboard extends JPanel {

    JButton Btn_Hoja_Siguiente, Btn_Hoja_Atras, Btn_Guardar, Btn_MostrarProceso, Btn_CopiarArchivo, Btn_PegarArchivo;
    JButton Btn_CrearLapiz, Btn_RellenarCuadro, Btn_Borrar, Btn_ObtenerColor, Btn_DevolverMov, Btn_SiguienteMov, Btn_Invertir, Btn_Rotar, Btn_CambiarColor, Btn_ZoomDown, Btn_ZoomUp;
    JButton Lbl_BotonSeleccionado;
    int CurrentButton;

    public PanelDashboard(int x, int y, int width, int heigth) {
        super();
        setLayout(null);
        setBounds(x, y, width, heigth);
        setBackground(Color.CYAN);
        setVisible(true);
        InitComponents();
    }

    private void Btn_MostrarProceso() {
        Btn_MostrarProceso = new JButton();
        Btn_MostrarProceso.setBounds(10, 30, 30, 30);
        Btn_MostrarProceso.setIcon(new ImageIcon("mostrar.png"));
        add(Btn_MostrarProceso);
        Btn_MostrarProceso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Lbl_BotonSeleccionado.setIcon(new ImageIcon("mostrar.png"));
                CurrentButton = 1;
            }
        });
    }

    private void Btn_CopiarArchivo() {
        Btn_CopiarArchivo = new JButton();
        Btn_CopiarArchivo.setBounds(40, 30, 30, 30);
        Btn_CopiarArchivo.setIcon(new ImageIcon("copiar.png"));
        add(Btn_CopiarArchivo);
        Btn_CopiarArchivo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Lbl_BotonSeleccionado.setIcon(new ImageIcon("copiar.png"));
                CurrentButton = 2;
            }
        });
    }

    private void Btn_PegarArchivo() {
        Btn_PegarArchivo = new JButton();
        Btn_PegarArchivo.setBounds(70, 30, 30, 30);
        Btn_PegarArchivo.setIcon(new ImageIcon("pegar.png"));
        add(Btn_PegarArchivo);
        Btn_PegarArchivo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Lbl_BotonSeleccionado.setIcon(new ImageIcon("pegar.png"));
                CurrentButton = 3;
            }
        });
    }

    private void Btn_CrearLapiz() {
        Btn_CrearLapiz = new JButton();
        Btn_CrearLapiz.setBounds(100, 30, 30, 30);
        Btn_CrearLapiz.setIcon(new ImageIcon("lapiz.png"));
        add(Btn_CrearLapiz);
        Btn_CrearLapiz.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Lbl_BotonSeleccionado.setIcon(new ImageIcon("lapiz.png"));
                CurrentButton = 4;

            }
        });
    }

    private void Btn_RellenarCuadro() {

        Btn_RellenarCuadro = new JButton();
        Btn_RellenarCuadro.setBounds(130, 30, 30, 30);
        Btn_RellenarCuadro.setIcon(new ImageIcon("pintar.png"));
        add(Btn_RellenarCuadro);
        Btn_RellenarCuadro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Lbl_BotonSeleccionado.setIcon(new ImageIcon("pintar.png"));
                CurrentButton = 5;

            }
        });
    }

    private void Btn_Borrar() {

        Btn_Borrar = new JButton();
        Btn_Borrar.setBounds(160, 30, 30, 30);
        Btn_Borrar.setIcon(new ImageIcon("borrar.png"));
        add(Btn_Borrar);
        Btn_Borrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Lbl_BotonSeleccionado.setIcon(new ImageIcon("borrar.png"));
                CurrentButton = 6;

            }
        });
    }

    private void Btn_ObtenerColor() {

        Btn_ObtenerColor = new JButton();
        Btn_ObtenerColor.setBounds(10, 60, 30, 30);
        Btn_ObtenerColor.setIcon(new ImageIcon("obtener.png"));
        add(Btn_ObtenerColor);
        Btn_ObtenerColor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                CurrentButton = 7;
                Lbl_BotonSeleccionado.setIcon(new ImageIcon("obtener.png"));
            }
        });
    }

    private void Btn_Invertir() {
        Btn_Invertir = new JButton();
        Btn_Invertir.setBounds(40, 60, 30, 30);
        Btn_Invertir.setIcon(new ImageIcon("invertir.png"));
        add(Btn_Invertir);
        Btn_Invertir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrentButton = 8;
                Lbl_BotonSeleccionado.setIcon(new ImageIcon("invertir.png"));

            }
        });
    }

    private void Btn_Rotar() {
        Btn_Rotar = new JButton();
        Btn_Rotar.setBounds(70, 60, 30, 30);
        Btn_Rotar.setIcon(new ImageIcon("rotar.png"));
        add(Btn_Rotar);
        Btn_Rotar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrentButton = 9;
                Lbl_BotonSeleccionado.setIcon(new ImageIcon("rotar.png"));

            }
        });
    }

    private void Btn_Bote_Pintura() {
        Btn_CambiarColor = new JButton();
        Btn_CambiarColor.setBounds(100, 60, 30, 30);
        Btn_CambiarColor.setIcon(new ImageIcon("cambiarcolor.png"));
        add(Btn_CambiarColor);
        Btn_CambiarColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrentButton = 10;
                Lbl_BotonSeleccionado.setIcon(new ImageIcon("cambiarcolor.png"));
            }
        });
    }

    private void Btn_DevolverMov() {
        Btn_DevolverMov = new JButton("<<");
        Btn_DevolverMov.setBounds(10, 120, 50, 30);
        Btn_DevolverMov.setForeground(Color.pink);
        Btn_DevolverMov.setBackground(Color.black);
        add(Btn_DevolverMov);
        Btn_DevolverMov.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentButton = 11;
            }
        });

    }

    private void Btn_SiguienteMov() {
        Btn_SiguienteMov = new JButton(">>");
        Btn_SiguienteMov.setForeground(Color.pink);
        Btn_SiguienteMov.setBackground(Color.black);
        Btn_SiguienteMov.setBounds(60, 120, 50, 30);
        add(Btn_SiguienteMov);
        Btn_SiguienteMov.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentButton = 12;

            }
        });
    }

    private void Btn_Guardar() {
        Btn_Guardar = new JButton();
        Btn_Guardar.setBounds(160, 120, 30, 30);
        Btn_Guardar.setIcon(new ImageIcon("guardar.png"));
        add(Btn_Guardar);
        Btn_Guardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CurrentButton = 13;
            }
        });
    }

    private void Btn_Hoja_Siguiente() {
        Btn_Hoja_Siguiente = new JButton("New Img");
        Btn_Hoja_Siguiente.setBounds(110, 90, 100, 30);
        Btn_Hoja_Siguiente.setForeground(Color.pink);
        Btn_Hoja_Siguiente.setBackground(Color.black);
        add(Btn_Hoja_Siguiente);

        Btn_Hoja_Siguiente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentButton = 14;
            }

        });
    }

    private void Btn_Hoja_Atras() {
        Btn_Hoja_Atras = new JButton("Img back");
        Btn_Hoja_Atras.setForeground(Color.pink);
        Btn_Hoja_Atras.setBackground(Color.black);
        Btn_Hoja_Atras.setBounds(10, 90, 100, 30);
        add(Btn_Hoja_Atras);

        Btn_Hoja_Atras.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentButton = 15;
            }
        });
    }

    private void BtnZoom() {
        Btn_ZoomUp = new JButton();
        Btn_ZoomUp.setBounds(130, 60, 30, 30);
        Btn_ZoomUp.setIcon(new ImageIcon("guardar.png"));
        add(Btn_ZoomUp);
        Btn_ZoomUp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CurrentButton = 16;
            }
        });

        Btn_ZoomDown = new JButton();
        Btn_ZoomDown.setBounds(160, 60, 30, 30);
        Btn_ZoomDown.setIcon(new ImageIcon("guardar.png"));
        add(Btn_ZoomDown);
        Btn_ZoomDown.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CurrentButton = 17;
            }
        });
    }

    private void Lbl_BotonSeleccionado() {
        Lbl_BotonSeleccionado = new JButton();
        Lbl_BotonSeleccionado.setBounds(10, 0, 30, 30);
        Lbl_BotonSeleccionado.setBackground(Color.white);
        add(Lbl_BotonSeleccionado);
    }

    private void InitComponents() {

        Btn_MostrarProceso();
        Btn_CopiarArchivo();
        Btn_PegarArchivo();
        Btn_CrearLapiz();
        Btn_RellenarCuadro();
        Btn_Borrar();
        Btn_ObtenerColor();
        Btn_Invertir();
        Btn_Rotar();
        Btn_Bote_Pintura();
        Btn_DevolverMov();
        Btn_SiguienteMov();
        Btn_Hoja_Siguiente();
        Btn_Hoja_Atras();
        Btn_Guardar();
        Lbl_BotonSeleccionado();
        BtnZoom();
    }

}
