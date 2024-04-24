package chess;

import javax.swing.*;
import java.io.Serializable;

public class Tile implements Serializable {
    JButton button;
    boolean enabled;
    private Piece piece;
    int posx;
    int posy;

    /** Konstruktor */
    public Tile(int x, int y){
        button = new JButton();
        enabled = true;
        piece = null;
        posx = x;
        posy = y;
    }

    /** Figura (piece) hozzáadása a mezőhöz */
    public void addPiece(Piece p){
        piece = p;
        button.setIcon(p.getIcon());
        piece.setPosx(this.posx);
        piece.setPosy(this.posy);
        piece.setTile(this);
    }

    /** Figura eltávolítása a mezőről */
    public void removePiece(){
        piece = null;
        button.setIcon(null);
    }

    /** Getterek és setterek */
    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public JButton getButton(){
        return button;
    }

    public Piece getPiece(){
        return piece;
    }

    public boolean isEmpty(){
        return piece == null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
