package clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;
import sun.awt.image.PixelConverter;
import static sun.net.www.http.HttpClient.New;

public class ImageWork extends JPanel implements MouseListener {

    int Identificador;
    Square[][] CuadrosHoja;
    BufferedImage imagen;
    Color color;
    int ToolSelect, ActiveTool;//tolselect:Tool of moment while activetool is the time active
    int initSquareX, initSquareY, finishSquareY, finishSquareX, CantMov;
    ArrayList<Integer> Listay, Listax;
    ArrayList<ListMoviments> ListMoviments;


    /*Constructor para imagen por defecto*/
    ImageWork(int id, int y, int width, int height, Color color, int squareWidth, int squareHeight) {
        super();
        LoadPanel(y, width, height);
        InitComponents(id, width, height);
        imagen = new BufferedImage(squareWidth, squareHeight, BufferedImage.TYPE_INT_ARGB);
        CargarCuadrosDefecto(squareWidth, squareHeight, color);

    }

    /*Constructor para imagen cargada*/
    ImageWork(int id, Square imagen[][], int y, int width, int height) {
        super();
        LoadPanel(y, width, height);
        InitComponents(id, width, height);
        this.imagen = new BufferedImage(imagen[0].length, imagen.length, BufferedImage.TYPE_INT_ARGB);
        CargarCuadrosImagen(imagen);
    }

    public void InitComponents(int id, int width, int height) {

        CantMov = 0;
        Identificador = id;
        ToolSelect = 0;
        ActiveTool = 0;
        Listay = new ArrayList<>();
        Listax = new ArrayList<>();
        ListMoviments = new ArrayList<>();
    }

    public void CargarCuadrosDefecto(int ancho, int alto, Color color) {

        CuadrosHoja = new Square[alto][ancho];
        for (int i = 0, y = 0; i < alto; i++, y += 500 / alto) {
            for (int j = 0, x = 0; j < ancho; j++, x += 600 / ancho) {
                CuadrosHoja[i][j] = new Square(Color.white, x, y, 600 / ancho, 500 / alto);
            }
        }
        ListMoviments.add(new ListMoviments(CuadrosHoja));
    }

    private void CargarCuadrosImagen(Square imagen[][]) {
        int Alto = imagen.length;
        int Ancho = imagen[0].length;
        CuadrosHoja = new Square[Alto][Ancho];

        for (int i = 0, y = 0; i < Alto; i++, y += 500 / Alto) {
            for (int j = 0, x = 0; j < Ancho; j++, x += 600 / Ancho) {

                CuadrosHoja[i][j] = new Square(imagen[i][j].color, x, y, 600 / Ancho, 500 / Alto);
                CuadrosHoja[i][j].color = imagen[i][j].color;
                CuadrosHoja[i][j].Active = imagen[i][j].Active;

            }
        }

        ListMoviments.add(new ListMoviments(CuadrosHoja));

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 255, 255, 0)); //Pintamos el area de juego en color vacio.
        PaintBoard(g);
        LoadImage();
        SelectTool();

    }

    private void PaintBoard(Graphics g) {
        for (int i = 0; i < CuadrosHoja.length; i++) {
            for (int j = 0; j < CuadrosHoja[i].length; j++) {
                CuadrosHoja[i][j].PainterSquare(g);
            }
        }
    }

    private void LoadPanel(int y, int width, int height) {
        setBounds(10, y, width, height);
        setLayout(null);
        setVisible(true);
        addMouseListener(this);
    }

    public void ChangeColor(Color color) {
        this.color = color;
    }

    public int BuscarBotonClickeado2(Square imagen[][]) {
        int Cont = 0;
        for (int i = 0; i < imagen.length; i++) {
            for (int j = 0; j < imagen[0].length; j++) {
                if (imagen[i][j].Active) {
                    Cont++;//este aumenta si consigue un boton clickeado
                }
            }
        }
        return Cont;
    }

    public void CargarPosicionInicial() {
        //cargando la posicion inicial .
        for (int i = 0; i < imagen.getHeight(); i++) {
            for (int j = 0; j < imagen.getWidth(); j++) {
                if (CuadrosHoja[i][j].Active) {
                    boolean dat = true;
                    for (int k = 0; k < Listay.size(); k++) {
                        if (i == Listay.get(k) && j == Listax.get(k)) {
                            dat = false;
                        }
                    }
                    if (dat) {
                        Listay.add(i);
                        Listax.add(j);
                    }
                }
            }
        }
    }

    public int RetornarPositivo(int n, int n2) {
        int num = n - n2;
        if (num >= 0) {
            return num;
        } else {
            return -num;
        }
    }

    public void CambioMovimiento() {
        boolean juega = true;
        for (int i = 0; i < ListMoviments.size() - 1; i++) {
            if (!ListMoviments.get(i).MovimientoDistinto(CuadrosHoja)) {
                juega = false;
            }
        }
        if (ListMoviments.get(ListMoviments.size() - 1).MovimientoDistinto(CuadrosHoja) && juega) {
            if (CantMov < ListMoviments.size() - 1) {
                for (int i = CantMov + 1; i < ListMoviments.size();) {
                    ListMoviments.remove(i);
                }
            }
            ListMoviments.add(new ListMoviments(CuadrosHoja));
            CantMov++;
        }

    }

    public void Movimientos(int Dato) {
        CantMov += Dato;
        if (CantMov < 0) {
            CantMov = 0;
        }
        if (CantMov > ListMoviments.size() - 1) {
            CantMov = ListMoviments.size() - 1;
        }

        for (int i = 0; i < CuadrosHoja.length; i++) {
            for (int j = 0; j < CuadrosHoja[0].length; j++) {
                if (ListMoviments.size() >= 1) {
                    CuadrosHoja[i][j].color = ListMoviments.get(CantMov).Movimientos[i][j].color;
                    CuadrosHoja[i][j].Active = ListMoviments.get(CantMov).Movimientos[i][j].Active;

                }
            }
        }

    }

    public void RotarBotones() {
        int Alto = CuadrosHoja.length;
        int Ancho = CuadrosHoja[0].length;
        if (CuadrosHoja.length == CuadrosHoja[0].length) {
            Square Matriz[][] = new Square[Alto][Ancho];
            for (int i = 0; i < Alto; i++) {
                for (int j = 0, k = Ancho - 1; j < Ancho; j++, k--) {
                    Matriz[i][j] = new Square(color);
                    Matriz[i][j].color = CuadrosHoja[k][i].color;
                    Matriz[i][j].Active = CuadrosHoja[k][i].Active;
                }
            }

            for (int i = 0; i < Alto; i++) {
                for (int j = 0; j < Ancho; j++) {
                    CuadrosHoja[i][j].color = Matriz[i][j].color;
                    CuadrosHoja[i][j].Active = Matriz[i][j].Active;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los pixeles de anchura y altura son distintos");
        }
    }

    private boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void InversionBotones(int seleccion) {

        int Alto = CuadrosHoja.length;
        int Ancho = CuadrosHoja[0].length;
        Square Matriz[][] = new Square[Alto][Ancho];
        if (seleccion == 1) {
            for (int i = 0; i < Alto; i++) {
                for (int j = 0, k = Ancho - 1; j < Ancho; j++, k--) {
                    Matriz[i][j] = new Square(color);
                    Matriz[i][j].color = CuadrosHoja[i][k].color;
                    Matriz[i][j].Active = CuadrosHoja[i][k].Active;
                }
            }
        } else {
            for (int i = 0, k = Alto - 1; i < Alto; i++, k--) {
                for (int j = 0; j < Ancho; j++) {
                    Matriz[i][j] = new Square(color);
                    Matriz[i][j].color = CuadrosHoja[k][j].color;
                    Matriz[i][j].Active = CuadrosHoja[k][j].Active;
                }
            }
        }

        for (int i = 0; i < Alto; i++) {
            for (int j = 0; j < Ancho; j++) {
                CuadrosHoja[i][j].color = Matriz[i][j].color;
                CuadrosHoja[i][j].Active = Matriz[i][j].Active;
            }
        }

    }

    public void CambiandoColores(Color Current, int y, int x, Color Future, int cont) {
        /*las funciones recursivas en java desbordan la maquina virtual de java porque desbordan la pila, mejor funciones iterativas*/
 /* Ojo hacer uso del print en java pone lenta la aplicacion.*/
        int Alto = CuadrosHoja.length;
        int Ancho = CuadrosHoja[0].length;

        ArrayList<Integer> listy = new ArrayList<>();
        ArrayList<Integer> listx = new ArrayList<>();

        listy.add(y);
        listx.add(x);
        listy.add(y);
        listx.add(x);
        // System.out.println(listy.size()); 

        if (!Current.equals(Future)) {
            for (int i = listy.get(0), j = listx.get(0); listy.size() > 1;) {
                CuadrosHoja[i][j].ActiveSquare(Future, true);
                if (j + 1 < Ancho) {
                    if (CuadrosHoja[i][j + 1].color.equals(Current)) {
                        listy.add(i);
                        listx.add(j + 1);
                        CuadrosHoja[i][j + 1].ActiveSquare(Future, true);
                    }
                }

                if (j - 1 >= 0) {
                    if (CuadrosHoja[i][j - 1].color.equals(Current)) {
                        listy.add(i);
                        listx.add(j - 1);
                        CuadrosHoja[i][j - 1].ActiveSquare(Future, true);
                    }
                }
                if (i + 1 < Alto) {
                    if (CuadrosHoja[i + 1][j].color.equals(Current)) {
                        listy.add(i + 1);
                        CuadrosHoja[i + 1][j].ActiveSquare(Future, true);
                        listx.add(j);
                    }
                }

                if (i - 1 >= 0) {
                    if (CuadrosHoja[i - 1][j].color.equals(Current)) {
                        listy.add(i - 1);
                        listx.add(j);
                        CuadrosHoja[i - 1][j].ActiveSquare(Future, true);
                    }
                }
                listy.remove(0);
                listx.remove(0);
                i = listy.get(0);
                j = listx.get(0);
            }
        }

    }

    void sumar(int i) {
        System.out.println("" + i);
        if (i < 10) {
            sumar(++i);
        }
    }

//    private void Txf_MostrarHoja(String NombreHoja) {
//        Txf_MostrarHoja = new JTextField(NombreHoja);
//        Txf_MostrarHoja.setBounds(300, 600, 250, 20);
//        add(Txf_MostrarHoja);
//
//    }
    public int RetornarNumeroCorrecto(int n) {
        if (n > 150) {
            n = 150;
        }
        if (n < 10) {
            n = 10;
        }
        return n;

    }

//    private void Txf_Ancho() {
//        Txf_Ancho = new JTextField();
//        Txf_Ancho.setBounds(100, 600, 100, 20);
//        add(Txf_Ancho);
//        Txf_Ancho.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                if (isNumeric(Txf_Ancho.getText())) {
//                    int Ancho = RetornarNumeroCorrecto(Integer.parseInt(Txf_Ancho.getText()));
//                }
//            }
//        });
//    }
//    private void Txf_Alto() {
//        Txf_Alto = new JTextField();
//        Txf_Alto.setBounds(200, 600, 100, 20);
//        add(Txf_Alto);
//        Txf_Alto.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                if (isNumeric(Txf_Alto.getText())) {
//                    int Alto = RetornarNumeroCorrecto(Integer.parseInt(Txf_Alto.getText()));
//                }
//            }
//        });
//    }
    @Override
    public void mouseClicked(java.awt.event.MouseEvent me) {

        switch (this.ToolSelect) {
            case 4: {
                ActiveTool = (ActiveTool == 4) ? 0 : 4;
                break;
            }
            case 5: {
                ActiveTool = (ActiveTool == 5) ? 0 : 5;
                initSquareY = (int) me.getY();
                initSquareX = (int) me.getX();
                finishSquareY = (int) me.getY();
                finishSquareX = (int) me.getX();
                break;
            }
            case 6: {
                ActiveTool = (ActiveTool == 6) ? 0 : 6;
                initSquareY = (int) me.getY();
                initSquareX = (int) me.getX();
                finishSquareY = (int) me.getY();
                finishSquareX = (int) me.getX();
                for (int i = 0; i < CuadrosHoja.length; i++) {
                    for (int j = 0; j < CuadrosHoja[i].length; j++) {
                        if (CuadrosHoja[i][j].Colision(me.getY(), me.getX())) {
                            CuadrosHoja[i][j].ActiveSquare(Color.white, false);
                        }
                    }
                }
                break;
            }
            case 7: {
                for (int i = 0; i < CuadrosHoja.length; i++) {
                    for (int j = 0; j < CuadrosHoja[i].length; j++) {
                        if (CuadrosHoja[i][j].Colision(me.getY(), me.getX())) {
                            color = CuadrosHoja[i][j].color;
                        }
                    }
                }
                break;
            }
            case 10: {
                for (int i = 0; i < CuadrosHoja.length; i++) {
                    for (int j = 0; j < CuadrosHoja[0].length; j++) {
                        if (CuadrosHoja[i][j].Colision(me.getY(), me.getX())) {
                            CambiandoColores(CuadrosHoja[i][j].color, i, j, color, 0);
                        }
                    }
                }
                break;
            }
        }

        for (int i = 0; i < CuadrosHoja.length; i++) {
            for (int j = 0; j < CuadrosHoja[i].length; j++) {
                if (CuadrosHoja[i][j].Colision(me.getY(), me.getX()) && ToolSelect != 6) {
                    CuadrosHoja[i][j].ActiveSquare(color, true);
                }
            }
        }
        CambioMovimiento();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent me) {
        // System.out.println("presionado");
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent me) {
        //System.out.println("oprimido");

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent me) {
        //  System.out.println("entra");

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent me) {
        //  System.out.println("sale");
        // System.out.println(me.getX() + "," + me.getY());
    }

    public void UpdateComponents(Color color) {
        this.color = color;
        this.repaint();  //Pintar all.
    }

    private void LoadImage() {
        for (int i = 0; i < CuadrosHoja.length; i++) {
            for (int j = 0; j < CuadrosHoja[i].length; j++) {
                this.imagen.setRGB(j, i, new Color(0, 0, 0, 0).getRGB());
                if (CuadrosHoja[i][j].Active) {
                    this.imagen.setRGB(j, i, CuadrosHoja[i][j].color.getRGB());
                }
            }
        }
    }

    private void SelectTool() {
        Point punto = this.getMousePosition();//get position of mouse

        switch (ActiveTool) {
            case 4: {
                if (punto != null && ToolSelect == 4) {
                    PainterPen(punto);
                }
                break;
            }
            case 5: {
                if (punto != null && ToolSelect == 5) {
                    PainterSquare(punto, color, true);
                }
                break;
            }
            case 6: {
                if (punto != null && ToolSelect == 6) {
                    Eraserquare(punto);
                }
                break;
            }
        }
    }

    public void PainterPen(Point punto) {

        for (int i = 0; i < CuadrosHoja.length; i++) {
            for (int j = 0; j < CuadrosHoja[i].length; j++) {
                if (CuadrosHoja[i][j].Colision((int) punto.getY(), (int) punto.getX())) {
                    CuadrosHoja[i][j].ActiveSquare(color, true);
                }
            }
        }
    }

    private void PainterSquare(Point punto, Color color, boolean state) {

        if (initSquareY >= punto.getY()) {
            initSquareY = (int) punto.getY();
        }
        if (finishSquareY < punto.getY()) {
            finishSquareY = (int) punto.getY();
        }
        if (initSquareX >= punto.getX()) {
            initSquareX = (int) punto.getX();
        }
        if (finishSquareX < punto.getX()) {
            finishSquareX = (int) punto.getX();
        }

        // System.out.println(initSquareY + "," + finishSquareY + ";" + initSquareX + "," + finishSquareX);
        for (int i = initSquareY / CuadrosHoja[0][0].height; i <= finishSquareY / CuadrosHoja[0][0].height; i++) {
            for (int j = initSquareX / CuadrosHoja[0][0].width; j <= finishSquareX / CuadrosHoja[0][0].width; j++) {
                if (i < CuadrosHoja.length && j < CuadrosHoja[0].length) {
                    CuadrosHoja[i][j].ActiveSquare(color, state);
                }
            }
        }
    }

    private void Eraserquare(Point punto) {
        PainterSquare(punto, Color.white, false);
    }

}
