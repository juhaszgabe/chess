package chess;

import javax.swing.*;
import java.util.ArrayList;

public class Queen extends Piece {

    /** Konstruktor */
    public Queen(int col){
        color = col;
        if(col == 0){
            this.icon = new ImageIcon("src/res/whitequeen.png");
        } else {
            this.icon = new ImageIcon("src/res/blackqueen.png");
        }
        tile = null;
        type = 4;
        hasMoved = false;
        specialMove = false;
        moved2 = false;
    }

    @Override
    public ArrayList<Tile> checkPossibleTiles(Tile[][] tiles) {
        ArrayList<Tile> possibleTiles = new ArrayList<>();

        for(int i = this.getPosy() + 1; i < 8; i++){
            if(tiles[i][this.getPosx()].isEmpty()){
                possibleTiles.add(tiles[i][this.getPosx()]);
            } else if (tiles[i][this.getPosx()].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[i][this.getPosx()]);
                break;
            } else {
                break;
            }
        }

        for(int i = this.getPosy() - 1; i >= 0; i--){
            if(tiles[i][this.getPosx()].isEmpty()){
                possibleTiles.add(tiles[i][this.getPosx()]);
            } else if (tiles[i][this.getPosx()].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[i][this.getPosx()]);
                break;
            } else {
                break;
            }
        }

        for(int i = this.getPosx() + 1; i < 8; i++){
            if(tiles[this.getPosy()][i].isEmpty()){
                possibleTiles.add(tiles[this.getPosy()][i]);
            } else if (tiles[this.getPosy()][i].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[this.getPosy()][i]);
                break;
            } else {
                break;
            }
        }

        for(int i = this.getPosx() - 1; i >= 0; i--){
            if(tiles[this.getPosy()][i].isEmpty()){
                possibleTiles.add(tiles[this.getPosy()][i]);
            } else if (tiles[this.getPosy()][i].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[this.getPosy()][i]);
                break;
            } else {
                break;
            }
        }

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
