package chess;

import javax.swing.*;
import java.util.ArrayList;

public class Pawn extends Piece{

    /** Konstruktor */
    public Pawn(int col){
        color = col;
        if(col == 0){
            this.icon = new ImageIcon("src/res/whitepawn.png");
        } else {
            this.icon = new ImageIcon("src/res/blackpawn.png");
        }
        tile = null;
        type = 0;
        hasMoved = false;
        specialMove = false;
        moved2 = false;
        promotionTiles = new ArrayList<>();
    }

    @Override
    public ArrayList<Tile> checkPossibleTiles(Tile[][] tiles) {
        ArrayList<Tile> possibleTiles = new ArrayList<>();

        if(this.getColor() == 0){

            if(this.getPosy() > 0){
                if(tiles[this.getPosy() - 1][this.posx].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.posx]);
                }

                if(this.getPosy() == 6 && tiles[this.getPosy() - 2][this.posx].isEmpty() && tiles[this.getPosy() - 1][this.posx].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 2][this.posx]);
                }

                //ütés

                if(this.getPosx() < 7  && !tiles[this.getPosy() - 1][this.getPosx() + 1].isEmpty() && tiles[this.getPosy() - 1][this.getPosx() + 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() + 1]);
                }

                if(this.getPosx() > 0  && !tiles[this.getPosy() - 1][this.getPosx() - 1].isEmpty() && tiles[this.getPosy() - 1][this.getPosx() - 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() - 1]);
                }
            }

        } else {
            if(this.getPosy() < 7){
                if(tiles[this.getPosy() + 1][this.posx].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.posx]);
                }

                if(this.getPosy() == 1 && tiles[this.getPosy() + 2][this.posx].isEmpty() && tiles[this.getPosy() + 1][this.posx].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 2][this.posx]);
                }

                //ütés

                if(this.getPosx() < 7  && !tiles[this.getPosy() + 1][this.getPosx() + 1].isEmpty() && tiles[this.getPosy() + 1][this.getPosx() + 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() + 1]);
                }

                if(this.getPosx() > 0  && !tiles[this.getPosy() + 1][this.getPosx() - 1].isEmpty() && tiles[this.getPosy() + 1][this.getPosx() - 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() - 1]);
                }
            }
        }
        return possibleTiles;
    }

    @Override
    public Tile hasSpecialMove(Tile[][] tiles) {

        if(this.getColor() == 0 && this.getPosy() == 3){

            if(this.getPosx() > 0){
                if(!tiles[3][this.getPosx() - 1].isEmpty() && tiles[3][this.getPosx() - 1].getPiece().getType() == 0 && tiles[3][this.getPosx() - 1].getPiece().hasMoved2()){
                    return tiles[2][this.getPosx() - 1];
                }
            }

            if(this.getPosx() < 7){
                if(!tiles[3][this.getPosx() + 1].isEmpty() && tiles[3][this.getPosx() + 1].getPiece().getType() == 0 && tiles[3][this.getPosx() + 1].getPiece().hasMoved2()){
                    return tiles[2][this.getPosx() + 1];
                }
            }
        }

        if(this.getColor() == 1 && this.getPosy() == 4){
            if(this.getPosx() > 0){
                if(!tiles[4][this.getPosx() - 1].isEmpty() && tiles[4][this.getPosx() - 1].getPiece().getType() == 0 && tiles[4][this.getPosx() - 1].getPiece().hasMoved2()){
                    return tiles[5][this.getPosx() - 1];
                }
            }

            if(this.getPosx() < 7){
                if(!tiles[4][this.getPosx() + 1].isEmpty() && tiles[4][this.getPosx() + 1].getPiece().getType() == 0 && tiles[4][this.getPosx() + 1].getPiece().hasMoved2()){
                    return tiles[5][this.getPosx() + 1];
                }
            }
        }
        return null;
    }
}
