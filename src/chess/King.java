package chess;

import javax.swing.*;
import java.util.ArrayList;

public class King extends Piece{

    /** Konstruktor */
    public King(int col){
        color = col;
        if(col == 0){
            this.icon = new ImageIcon("src/res/whiteking.png");
        } else {
            this.icon = new ImageIcon("src/res/blackking.png");
        }
        tile = null;
        type = 5;
        hasMoved = false;
        moved2 = false;
    }

    @Override
    public ArrayList<Tile> checkPossibleTiles(Tile[][] tiles) {
        ArrayList<Tile> possibleTiles = new ArrayList<>();

        if(this.getPosy() > 0){

            if(tiles[this.getPosy() - 1][this.getPosx()].isEmpty()){
                possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx()]);
            } else if (tiles[this.getPosy() - 1][this.getPosx()].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx()]);
            }


            if(this.getPosx() > 0){
                if(tiles[this.getPosy() - 1][this.getPosx() - 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() - 1]);
                } else if (tiles[this.getPosy() - 1][this.getPosx() - 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() - 1]);
                }
            }

            if(this.getPosx() < 7){
                if(tiles[this.getPosy() - 1][this.getPosx() + 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() + 1]);
                } else if (tiles[this.getPosy() - 1][this.getPosx() + 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() - 1][this.getPosx() + 1]);
                }
            }
        }

        if(this.getPosy() < 7){
            if(tiles[this.getPosy() + 1][this.getPosx()].isEmpty()){
                possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx()]);
            } else if (tiles[this.getPosy() + 1][this.getPosx()].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx()]);
            }

            if(this.getPosx() > 0){
                if(tiles[this.getPosy() + 1][this.getPosx() - 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() - 1]);
                } else if (tiles[this.getPosy() + 1][this.getPosx() - 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() - 1]);
                }
            }

            if(this.getPosx() < 7){
                if(tiles[this.getPosy() + 1][this.getPosx() + 1].isEmpty()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() + 1]);
                } else if (tiles[this.getPosy() + 1][this.getPosx() + 1].getPiece().getColor() != this.getColor()){
                    possibleTiles.add(tiles[this.getPosy() + 1][this.getPosx() + 1]);
                }
            }
        }

        if(this.getPosx() > 0){
            if(tiles[this.getPosy()][this.getPosx() - 1].isEmpty()){
                possibleTiles.add(tiles[this.getPosy()][this.getPosx() - 1]);
            } else if (tiles[this.getPosy()][this.getPosx() - 1].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[this.getPosy()][this.getPosx() - 1]);
            }
        }

        if(this.getPosx() < 7){
            if(tiles[this.getPosy()][this.getPosx() + 1].isEmpty()){
                possibleTiles.add(tiles[this.getPosy()][this.getPosx() + 1]);
            } else if (tiles[this.getPosy()][this.getPosx() + 1].getPiece().getColor() != this.getColor()){
                possibleTiles.add(tiles[this.getPosy()][this.getPosx() + 1]);
            }
        }

        ArrayList<Tile> tempList = new ArrayList<>(possibleTiles);

        // Másik király melletti mezők kiszűrése
        for(Tile t : tempList){
            if(isNextToKing(t, tiles, this.getColor())){
                possibleTiles.remove(t);
            }
        }

        return possibleTiles;
    }

    /** Eldönti egy mezőről, hogy a másik király mellett van-e*/
    private boolean isNextToKing(Tile tile, Tile[][] tiles, int c){

        if(tile.getPosy() > 0){

            if(!tiles[tile.getPosy() - 1][tile.getPosx()].isEmpty() && tiles[tile.getPosy() - 1][tile.getPosx()].getPiece().getType() == 5 && tiles[tile.getPosy() - 1][tile.getPosx()].getPiece().getColor() != c){
                return true;
            }

            if(tile.getPosx() > 0){
                if(!tiles[tile.getPosy() - 1][tile.getPosx() - 1].isEmpty() && tiles[tile.getPosy() - 1][tile.getPosx() - 1].getPiece().getType() == 5 && tiles[tile.getPosy() - 1][tile.getPosx() - 1].getPiece().getColor() != c){
                    return true;
                }
            }

            if(tile.getPosx() < 7){
                if(!tiles[tile.getPosy() - 1][tile.getPosx() + 1].isEmpty() && tiles[tile.getPosy() - 1][tile.getPosx() + 1].getPiece().getType() == 5 && tiles[tile.getPosy() - 1][tile.getPosx() + 1].getPiece().getColor() != c){
                    return true;
                }
            }
        }

        if(tile.getPosy() < 7){
            if(!tiles[tile.getPosy() + 1][tile.getPosx()].isEmpty() && tiles[tile.getPosy() + 1][tile.getPosx()].getPiece().getType() == 5 && tiles[tile.getPosy() + 1][tile.getPosx()].getPiece().getColor() != c){
                return true;
            }

            if(tile.getPosx() > 0){
                if(!tiles[tile.getPosy() + 1][tile.getPosx() - 1].isEmpty() && tiles[tile.getPosy() + 1][tile.getPosx() - 1].getPiece().getType() == 5 && tiles[tile.getPosy() + 1][tile.getPosx() - 1].getPiece().getColor() != c){
                    return true;
                }
            }

            if(tile.getPosx() < 7){
                if(!tiles[tile.getPosy() + 1][tile.getPosx() + 1].isEmpty() && tiles[tile.getPosy() + 1][tile.getPosx() + 1].getPiece().getType() == 5 && tiles[tile.getPosy() + 1][tile.getPosx() + 1].getPiece().getColor() != c){
                    return true;
                }
            }
        }

        if(tile.getPosx() > 0){
            if(!tiles[tile.getPosy()][tile.getPosx() - 1].isEmpty() && tiles[tile.getPosy()][tile.getPosx() - 1].getPiece().getType() == 5 && tiles[tile.getPosy()][tile.getPosx() - 1].getPiece().getColor() != c){
                return true;
            }
        }

        if(tile.getPosx() < 7){
            if(!tiles[tile.getPosy()][tile.getPosx() + 1].isEmpty() && tiles[tile.getPosy()][tile.getPosx() + 1].getPiece().getType() == 5 && tiles[tile.getPosy()][tile.getPosx() + 1].getPiece().getColor() != c){
                return true;
            }
        }

        return false;
    }

    @Override
    public Tile hasSpecialMove(Tile[][] tiles) {
        return null;
    }
}
