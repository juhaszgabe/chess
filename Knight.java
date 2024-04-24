package chess;

import javax.swing.*;
import java.util.ArrayList;

public class Knight extends Piece {

    /** Konstruktor */
    public Knight(int col){
        color = col;
        if(col == 0){
            this.icon = new ImageIcon("src/res/whiteknight.png");
        } else {
            this.icon = new ImageIcon("src/res/blackknight.png");
        }
        tile = null;
        type = 2;
        hasMoved = false;
    }

    @Override
    public ArrayList<Tile> checkPossibleTiles(Tile[][] tiles) {
        ArrayList<Tile> possibleTiles = new ArrayList<>();

        if(this.getPosy() > 1){
            if(this.getPosx() > 0){
                if(tiles[this.getPosy() - 2][this.getPosx() - 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 2][this.getPosx() - 1]);
                } else if (tiles[this.getPosy() - 2][this.getPosx() - 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 2][this.getPosx() - 1]);
                }
            }

            if(this.getPosx() < 7){
                if(tiles[this.getPosy() - 2][this.getPosx() + 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 2][this.getPosx() + 1]);
                } else if (tiles[this.getPosy() - 2][this.getPosx() + 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 2][this.getPosx() + 1]);
                }
            }
        }

        if(this.getPosy() > 0){
            if(this.getPosx() > 1){
                if(tiles[this.getPosy() - 1][this.getPosx() - 2].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() - 2]);
                } else if (tiles[this.getPosy() - 1][this.getPosx() - 2].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() - 2]);
                }
            }

            if(this.getPosx() < 6){
                if(tiles[this.getPosy() - 1][this.getPosx() + 2].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() + 2]);
                } else if (tiles[this.getPosy() - 1][this.getPosx() + 2].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() + 2]);
                }
            }
        }

        if(this.getPosy() < 7){
            if(this.getPosx() > 1){
                if(tiles[this.getPosy() + 1][this.getPosx() - 2].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() - 2]);
                } else if (tiles[this.getPosy() + 1][this.getPosx() - 2].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() - 2]);
                }
            }

            if(this.getPosx() < 6){
                if(tiles[this.getPosy() + 1][this.getPosx() + 2].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() + 2]);
                } else if (tiles[this.getPosy() + 1][this.getPosx() + 2].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() + 2]);
                }
            }
        }

        if(this.getPosy() < 6){
            if(this.getPosx() > 0){
                if(tiles[this.getPosy() + 2][this.getPosx() - 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 2][this.getPosx() - 1]);
                } else if (tiles[this.getPosy() + 2][this.getPosx() - 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 2][this.getPosx() - 1]);
                }
            }

            if(this.getPosx() < 7){
                if(tiles[this.getPosy() + 2][this.getPosx() + 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 2][this.getPosx() + 1]);
                } else if (tiles[this.getPosy() + 2][this.getPosx() + 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 2][this.getPosx() + 1]);
                }
            }
        }

        return possibleTiles;
    }

    @Override
    public Tile hasSpecialMove(Tile[][] tiles) {
        return null;
    }
}
