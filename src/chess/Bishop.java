package chess;

import javax.swing.*;
import java.util.ArrayList;

public class Bishop extends Piece {

    /** Konstruktor */
    public Bishop(int col){
        color = col;
        if(col == 0){
            this.icon = new ImageIcon("src/res/whitebishop.png");
        } else {
            this.icon = new ImageIcon("src/res/blackbishop.png");
        }
        tile = null;
        type = 3;
        hasMoved = false;
        specialMove = false;
        moved2 = false;
    }

    @Override
    public ArrayList<Tile> checkPossibleTiles(Tile[][] tiles) {
        ArrayList<Tile> possibleTiles = new ArrayList<>();

        int j = this.getPosx() + 1;
        for(int i = this.getPosy() + 1; i < 8 && j < 8; i++){

            if(tiles[i][j].isEmpty()){
                possibleTiles.add(tiles[i][j]);
            } else if (tiles[i][j].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[i][j]);
                break;
            } else {
                break;
            }

            j++;
        }

        j = this.getPosx() - 1;
        for(int i = this.getPosy() + 1; i < 8 && j >= 0; i++){

            if(tiles[i][j].isEmpty()){
                possibleTiles.add(tiles[i][j]);
            } else if (tiles[i][j].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[i][j]);
                break;
            } else {
                break;
            }

            j--;
        }

        j = this.getPosx() + 1;
        for(int i = this.getPosy() - 1; i >= 0 && j < 8; i--){

            if(tiles[i][j].isEmpty()){
                possibleTiles.add(tiles[i][j]);
            } else if (tiles[i][j].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[i][j]);
                break;
            } else {
                break;
            }

            j++;
        }

        j = this.getPosx() - 1;
        for(int i = this.getPosy() - 1; i >= 0 && j >= 0; i--){

            if(tiles[i][j].isEmpty()){
                possibleTiles.add(tiles[i][j]);
            } else if (tiles[i][j].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[i][j]);
                break;
            } else {
                break;
            }

            j--;
        }

        return possibleTiles;
    }

    @Override
    public Tile hasSpecialMove(Tile[][] tiles) {
        return null;
    }
}
