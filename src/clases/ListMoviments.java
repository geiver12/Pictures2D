package clases;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.JButton;

/*esta verifica si la matriz de mov es distinta, si es distina añade un nuevo mov*/
public class ListMoviments {

    Square Movimientos[][];

    public ListMoviments(Square Movimientos[][]) {

        this.Movimientos = new Square[Movimientos.length][Movimientos[0].length];
        for (int i = 0; i < Movimientos.length; i++) {
            for (int j = 0; j < Movimientos[i].length; j++) {
                this.Movimientos[i][j]= new Square(new Color(0,0,0,0));
                this.Movimientos[i][j].color = Movimientos[i][j].color;
                this.Movimientos[i][j].Active = Movimientos[i][j].Active;
            }
        }

    }

    public boolean MovimientoDistinto(Square Movimientos[][]) {
        Square Moov[][];
        Moov = new Square[Movimientos.length][Movimientos[0].length];
        for (int i = 0; i < Movimientos.length; i++) {
            for (int j = 0; j < Movimientos[i].length; j++) {
                Moov[i][j] = Movimientos[i][j];
            }
        }

        for (int i = 0; i < Movimientos.length; i++) {
            for (int j = 0; j < Movimientos[i].length; j++) {
                if (Moov[i][j] != this.Movimientos[i][j]) {
                    return true;
                }
            }
        }
        return false;

    }

}
