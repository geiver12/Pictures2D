package clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*este metodo es solo para colores y de aca lo
unico que interesa  es el color activo con el que se pinta que son los 3 valores enteros rgb que seran usados para pintar la pantalla con el color que indiquen estos*/
public class PanelColors extends JPanel {

    JSlider rojo, azul, verde;
    JTextField TextRed, TextBlue, TextGreen;
    JLabel Label_Red, Label_Blue, Label_Green;
    JPanel ShowCurrentColor;
    int N_rojo, N_azul, N_verde;
    JButton ListaColor[][];
    ArrayList<JButton> RecentColors;

    public PanelColors(int x, int y, int width, int height) {
        super();
        setBounds(x, y, width, height);
        setLayout(null);
        setBackground(Color.blue);
        InitComponents();

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
        if (n > 255) {
            n = 255;
        }
        if (n < -255) {
            n = 0;
        }
        if (n >= 0) {
            return n;
        } else {
            return -n;
        }
    }

    public void LoadColor(Color color) {
        boolean ver = true;
        if (RecentColors.size() > 0 && RecentColors.size() < 11) {
            for (JButton RecentColor : RecentColors) {
                if (color.equals(RecentColor.getBackground())) {
                    ver = false;
                }
            }
            if (ver) {
                RecentColors.add(new JButton());
                JButton cont = RecentColors.get(RecentColors.size() - 1);
                cont.setBounds(RecentColors.get(RecentColors.size() - 2).getX() + 20, 220, 20, 20);
                cont.setBackground(color);
                add(cont);
                cont.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Object fuente = ae.getSource();
                        for (JButton RecentColor : RecentColors) {
                            if (fuente == RecentColor) {
                                N_rojo = RecentColor.getBackground().getRed();
                                N_azul = RecentColor.getBackground().getBlue();
                                N_verde = RecentColor.getBackground().getGreen();

                            }
                            TextRed.setText("" + N_rojo);
                            TextGreen.setText("" + N_verde);
                            TextBlue.setText("" + N_azul);
                            rojo.setValue(N_rojo);
                            verde.setValue(N_verde);
                            azul.setValue(N_azul);
                            ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                        }
                    }
                });
            }
        }

    }

    private void InitComponents() {
        N_azul = N_verde = N_rojo = 125;

        RecentColors = new ArrayList<>();
        RecentColors.add(new JButton());
        RecentColors.get(0).setBounds(10, 260, 20, 20);
        RecentColors.get(0).setBackground(Color.GRAY);
        add(RecentColors.get(0));
        RecentColors.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object fuente = ae.getSource();
                for (JButton RecentColor : RecentColors) {
                    if (fuente == RecentColor) {
                        N_rojo = RecentColor.getBackground().getRed();
                        N_azul = RecentColor.getBackground().getBlue();
                        N_verde = RecentColor.getBackground().getGreen();

                    }
                    TextRed.setText("" + N_rojo);
                    TextGreen.setText("" + N_verde);
                    TextBlue.setText("" + N_azul);
                    rojo.setValue(N_rojo);
                    verde.setValue(N_verde);
                    azul.setValue(N_azul);
                    ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                }
            }
        });
        LoadRed();
        LoadBlue();
        LoadGreen();
        LoadColorsPickers();

        ShowCurrentColor = new JPanel();
        ShowCurrentColor.setBounds(5, 220, 90, 30);
        ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
        add(ShowCurrentColor);
    }

    private void LoadRed() {
        Label_Red = new JLabel("R");
        Label_Red.setBounds(14, 10, 10, 10);
        add(Label_Red);

        rojo = new JSlider(JSlider.VERTICAL, 0, 255, 125);
        rojo.setBounds(10, 30, 20, 150);
        add(rojo);
        rojo.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                N_rojo = rojo.getValue();
                ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                ShowCurrentColor.updateUI();
                TextRed.setText("" + N_rojo);
                TextRed.updateUI();

            }
        });
        TextRed = new JTextField("" + N_rojo);
        TextRed.setBounds(5, 190, 30, 20);
        add(TextRed);
        TextRed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (isNumeric(TextRed.getText())) {
                    N_rojo = RetornarNumeroCorrecto(Integer.parseInt(TextRed.getText()));
                }
                rojo.setValue(N_rojo);
                ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                ShowCurrentColor.updateUI();
            }
        });
    }

    private void LoadBlue() {
        Label_Blue = new JLabel("B");
        Label_Blue.setBounds(45, 10, 10, 10);
        add(Label_Blue);

        azul = new JSlider(JSlider.VERTICAL, 0, 255, 125);
        azul.setBounds(40, 30, 20, 150);
        add(azul);
        azul.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                N_azul = azul.getValue();
                ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                ShowCurrentColor.updateUI();
                TextBlue.setText("" + N_azul);
                TextBlue.updateUI();
            }
        });

        TextBlue = new JTextField("" + N_azul);
        TextBlue.setBounds(35, 190, 30, 20);
        add(TextBlue);

        TextBlue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (isNumeric(TextBlue.getText())) {
                    N_azul = RetornarNumeroCorrecto(Integer.parseInt(TextBlue.getText()));
                }
                azul.setValue(N_azul);
                ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                ShowCurrentColor.updateUI();
            }
        });
    }

    private void LoadGreen() {

        Label_Green = new JLabel("V");
        Label_Green.setBounds(75, 10, 10, 10);
        add(Label_Green);

        verde = new JSlider(JSlider.VERTICAL, 0, 255, 125);
        verde.setBounds(70, 30, 20, 150);
        add(verde);
        verde.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                N_verde = verde.getValue();
                ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                ShowCurrentColor.updateUI();
                TextGreen.setText("" + N_verde);
                TextGreen.updateUI();
            }
        });

        TextGreen = new JTextField("" + N_verde);
        TextGreen.setBounds(65, 190, 30, 20);
        add(TextGreen);

        TextGreen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (isNumeric(TextGreen.getText())) {
                    N_verde = RetornarNumeroCorrecto(Integer.parseInt(TextGreen.getText()));
                }
                verde.setValue(N_verde);
                ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                ShowCurrentColor.updateUI();
            }
        });
    }

    private void LoadColorsPickers() {
        ListaColor = new JButton[11][4];
        int y2 = -10, x2, r = 0, g = 0, b = 0, cont = 0;
        for (int i = 0; i < ListaColor.length; i++) {
            x2 = 90;
            y2 += 20;
            if (i == 0) {
                g = b = r = 0;//Black
            }
            if (i == 1) {
                g = b = r = 255;//White
            }
            if (i == 2) {
                g = b = 0;//Red
                r = 255;
            }
            if (i == 3) {
                r = b = 0;//Green
                g = 255;
            }
            if (i == 4) {
                g = r = 0;//Blue
                b = 255;
            }
            if (i == 5) {
                b = 0; //Yellow
                g = r = 255;
            }
            if (i == 6) { //Cyan
                g = b = 255;
                r = 0;
            }
            if (i == 7) { //Magenta
                b = r = 255;
                g = 0;
            }
            if (i == 8) {
                g = 0;
                b = 0;
                r = 100;//Brown
            }
            if (i == 9) {
                g = b = 125;//Gray
                r = 25;
            }
            if (i == 10) {
                g = 0;
                b = 80;
                r = 80;//Purple
            }
            for (int j = 0; j < ListaColor[0].length; j++) {
                if (i == 0 && j > 0) {
                    r = b = g += 20;
                }
                if (i == 1 && j > 0) {
                    r = b = g -= 20;
                }
                if (i == 2 && j > 0) {
                    b += 40;
                }
                if (i == 3 && j > 0) {
                    g -= 50;
                }
                if (i == 4 && j > 0) {
                    g += 50;
                }
                if (i == 5 && j > 0) {
                    g -= 35;
                }
                if (i == 6 && j > 0) {
                    b -= 50;
                }
                if (i == 7 && j > 0) {
                    r -= 50;
                }
                if (i == 8 && j > 0) {
                    g += 50;
                }
                if (i == 9 && j > 0) {
                    r += 50;
                }
                if (i == 10 && j > 0) {
                    b += 40;
                }
                ListaColor[i][j] = new JButton();
                ListaColor[i][j].setBounds(x2 += 20, y2, 20, 20);
                ListaColor[i][j].setBackground(new Color(r, g, b));
                add(ListaColor[i][j]);

                ListaColor[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Object fuente = e.getSource();
                        for (int i = 0; i < ListaColor.length; i++) {
                            for (int j = 0; j < ListaColor[0].length; j++) {
                                if (fuente == ListaColor[i][j]) {
                                    N_rojo = ListaColor[i][j].getBackground().getRed();
                                    N_azul = ListaColor[i][j].getBackground().getBlue();
                                    N_verde = ListaColor[i][j].getBackground().getGreen();
                                }
                            }

                        }
                        TextRed.setText("" + N_rojo);
                        TextGreen.setText("" + N_verde);
                        TextBlue.setText("" + N_azul);
                        rojo.setValue(N_rojo);
                        verde.setValue(N_verde);
                        azul.setValue(N_azul);
                        ShowCurrentColor.setBackground(new Color(N_rojo, N_verde, N_azul));
                    }
                });
            }
        }
    }

}
