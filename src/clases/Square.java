package clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class Square {

    Color color;
    int width, height, x, y;
    boolean Active;

    public boolean Colision(int y, int x) {
        return (((y >= this.y && y < this.y + height))
                && (x >= this.x && x < this.x + width));
    }

    public void ActiveSquare(Color color, boolean state) {
        this.Active = state;
        this.color = color;
    }

    public Square(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.y = y;
        this.x = x;
        Active = false;
    }

    public Square(Color color) {
        color = color;
        Active = false;
    }

    public void PainterSquare(Graphics g) {
        if (Active) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }
}
