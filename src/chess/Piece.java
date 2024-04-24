package chess;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable {

    boolean hasMoved;
    boolean wm;
    boolean specialMove;
    ImageIcon icon;
    Tile tile;
    boolean moved2;
    int posx;
    int posy;
    int color; // 0 == white, 1 == black (could be replaced by an enum)
    ArrayList<Tile> promotionTiles;
    int type; // 0 == pawn, 1 == rook, 2 == knight, 3 == bishop, 4 == queen, 5 == king (could be replaced with an enum)

    /** Default konstruktor */
    public Piece(){}

    /** Getterek és setterek */
    public boolean isWm() {
        return wm;
    }

    public void setWm(boolean wm) {
        this.wm = wm;
    }

    public boolean hasMoved2() {
        return moved2;
    }

    public void setMoved2(boolean moved2) {
        this.moved2 = moved2;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getColor(){
        return color;
    }

    public int getType() {
        return type;
    }

    public void setSpecialMove(boolean specialMove) {
        this.specialMove = specialMove;
    }

    /** A lehetséges mezők felderítése, ahova az adott figura léphet a táblán */
    public abstract ArrayList<Tile> checkPossibleTiles(Tile[][] tiles);

    /** A lehetséges különleges lépések felderítése */
    public abstract Tile hasSpecialMove(Tile[][] tiles);
}
