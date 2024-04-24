package chess;

import javax.swing.*;
import java.util.ArrayList;

public class Rook extends Piece {

    /** Konstruktor */
    public Rook(int col){
        color = col;
        if(col == 0){
            this.icon = new ImageIcon("src/res/whiterook.png");
        } else {
            this.icon = new ImageIcon("src/res/blackrook.png");
        }
        tile = null;
        type = 1;
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


        return possibleTiles;
    }

    @Override
    public Tile hasSpecialMove(Tile[][] tiles) {
        if(this.getColor() == 0){
            if(!this.isHasMoved() && !tiles[7][4].isEmpty() && !tiles[7][4].getPiece().isHasMoved()){
                if(this.getPosx() < 4){
                    for(int i = this.getPosx() + 1; i < 4; i++){
                        if(!tiles[7][i].isEmpty()){
                            return null;
                        }
                    }
                    this.setSpecialMove(true);
                    return tiles[7][4];
                } else {
                    for(int i = 5; i < this.getPosx(); i++){
                        if(!tiles[7][i].isEmpty()){
                            return null;
                        }
                    }
                    this.setSpecialMove(true);
                    return tiles[7][4];
                }
            }
        } else {
            if(!this.isHasMoved() && !tiles[0][4].isEmpty() && !tiles[0][4].getPiece().isHasMoved()){
                if(this.getPosx() < 4){
                    for(int i = this.getPosx() + 1; i < 4; i++){
                        if(!tiles[0][i].isEmpty()){
                            return null;
                        }
                    }
                    this.setSpecialMove(true);
                    return tiles[0][4];
                } else {
                    for(int i = 5; i < this.getPosx(); i++){
                        if(!tiles[0][i].isEmpty()){
                            return null;
                        }
                    }
                    this.setSpecialMove(true);
                    return tiles[0][4];
                }
            }
        }
        return null;
    }
}
