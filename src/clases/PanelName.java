package clases;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Geiver Botello
 */
public class PanelName extends JPanel {

    JTextField TxfName;
    JTextField TxfWidth, TxfHeight;
    JButton Button_Ok;
    ArrayList<String> listname, listwidth, listheight;

    public PanelName(int x, int y, int width, int height) {
        super();
        setLayout(null);
        setBounds(x, y, width, height);
        setBackground(Color.black);
        setVisible(true);
        InitComponents();
    }

    public void InitComponents() {
        listheight = new ArrayList<>();
        listwidth = new ArrayList<>();
        listname = new ArrayList<>();

        TxfName = new JTextField();
        TxfName.setBounds(10, 10, 100, 30);
        add(TxfName);
        TxfName.setVisible(true);

        TxfName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });

        TxfWidth = new JTextField();
        TxfWidth.setBounds(120, 10, 30, 30);
        add(TxfWidth);
        TxfWidth.setVisible(true);

        TxfWidth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });

        TxfHeight = new JTextField();
        TxfHeight.setBounds(160, 10, 30, 30);
        add(TxfHeight);
        TxfHeight.setVisible(true);

        TxfHeight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        Button_Ok = new JButton("OK");
        Button_Ok.setForeground(Color.pink);
        Button_Ok.setBackground(Color.black);
        Button_Ok.setBounds(200, 10, 60, 20);
        Button_Ok.setVisible(true);
        add(Button_Ok);
        Button_Ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void LoadAtributes(int cont) {

        TxfHeight.setText("" + listheight.get(cont));
        TxfWidth.setText("" + listwidth.get(cont));
        TxfName.setText(listname.get(cont));
    }

    void addImage(String text, String text0, String text1, int lim) {
        listname.add(text);
        listwidth.add(text0);
        listheight.add(text1);
        LoadAtributes(lim);

    }

}
