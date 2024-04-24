package chess;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;


public class Game {

    Tile[][] tiles;
    ArrayList<Piece> pieces = new ArrayList<>();
    int promotionPiece = 4;
    Piece selectedPiece = null;
    public boolean isWhiteMoves() {
        return whiteMoves;
    }
    boolean whiteMoves = true;
    Piece whiteKing;
    Piece blackKing;

    //Getterek és setterek
    public void setTiles(Tile[][] t){
        tiles = t;
    }

    public Tile[][] getTiles(){
        return tiles;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public int getPromotionPiece() {
        return promotionPiece;
    }

    public void setPromotionPiece(int promotionPiece) {
        this.promotionPiece = promotionPiece;
    }

    private void setSelectedPiece(Piece piece){
        selectedPiece = piece;
    }

    /** Kiválasztott figura áthelyezése a paraméterként kapott mezőre */
    private void getSelectedPiece(Tile tile){
        selectedPiece.getTile().getButton().setBorder(null);
        selectedPiece.getTile().setEnabled(true);
        selectedPiece.getTile().removePiece();
        selectedPiece.setHasMoved(true);
        selectedPiece.setSpecialMove(false);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(!tiles[i][j].isEmpty()){
                    tiles[i][j].getPiece().setMoved2(false);
                }
            }
        }

        selectedPiece.setMoved2(selectedPiece.getType() == 0 && Math.abs(selectedPiece.getTile().getPosy() - tile.getPosy()) > 1);
        tile.addPiece(selectedPiece);
        selectedPiece = null;
    }

    /** Alapfelállás felhelyezése */
    public void setupGame(){

        //add pawns
        for(int i = 0; i < 8; i++){
            tiles[1][i].addPiece(new Pawn(1));
            tiles[6][i].addPiece(new Pawn(0));
        }


        //add rooks
        tiles[0][0].addPiece(new Rook(1));
        tiles[0][7].addPiece(new Rook(1));
        tiles[7][0].addPiece(new Rook(0));
        tiles[7][7].addPiece(new Rook(0));

        //add knights
        tiles[0][1].addPiece(new Knight(1));
        tiles[0][6].addPiece(new Knight(1));
        tiles[7][1].addPiece(new Knight(0));
        tiles[7][6].addPiece(new Knight(0));

        //add bishops
        tiles[0][2].addPiece(new Bishop(1));
        tiles[0][5].addPiece(new Bishop(1));
        tiles[7][2].addPiece(new Bishop(0));
        tiles[7][5].addPiece(new Bishop(0));

        //add king and queen
        whiteKing = new King(0);
        blackKing = new King(1);

        tiles[0][3].addPiece(new Queen(1));
        tiles[7][3].addPiece(new Queen(0));
        tiles[0][4].addPiece(blackKing);
        tiles[7][4].addPiece(whiteKing);
    }

    /** Mentett játék visszatöltése */
    public void loadGame(){
        for(Piece piece : pieces){
            tiles[piece.getPosy()][piece.getPosx()].addPiece(piece);
            if(piece.getType() == 5){
                if(piece.getColor() == 0){
                    whiteKing = piece;
                } else {
                    blackKing = piece;
                }
            }
        }
        if(!pieces.isEmpty()){
            whiteMoves = pieces.get(0).isWm();
        }
    }

    /** Játék elindítása */
    public void startGame() {

        boolean whiteCanMove = false;
        boolean blackCanMove = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if(tiles[i][j].isEmpty()){
                    tiles[i][j].setEnabled(false);
                    tiles[i][j].getButton().setBorder(null);
                }

                if (tiles[i][j].getPiece() != null && whiteMoves) {

                    if(tiles[i][j].getPiece().getColor() != 1 && !tilesInCheck(tiles[i][j].getPiece()).isEmpty()){
                        tiles[i][j].setEnabled(true);
                        tiles[i][j].getButton().setBorder(new LineBorder(Color.GREEN, 2));
                        whiteCanMove = true;
                    } else {
                        tiles[i][j].setEnabled(false);
                        tiles[i][j].getButton().setBorder(null);
                    }

                } else if (tiles[i][j].getPiece() != null) {

                    if(tiles[i][j].getPiece().getColor() != 0 && !tilesInCheck(tiles[i][j].getPiece()).isEmpty()){
                        tiles[i][j].setEnabled(true);
                        tiles[i][j].getButton().setBorder(new LineBorder(Color.GREEN, 2));
                        blackCanMove = true;
                    } else {
                        tiles[i][j].setEnabled(false);
                        tiles[i][j].getButton().setBorder(null);
                    }
                }
            }
        }

        if(whiteMoves && !whiteCanMove){
            if(isCheck(whiteKing)){
                win(1);
            } else {
                JOptionPane.showMessageDialog(null, "Draw!");
                System.exit(0);
            }

        } else if (!whiteMoves && !blackCanMove){
            if(isCheck(blackKing)){
                win(0);
            } else {
                JOptionPane.showMessageDialog(null, "Draw!");
                System.exit(0);
            }
        }

    }

    /** Győzelem esetén */
    public void win(int color){
        if(color == 0){
            JOptionPane.showMessageDialog(null, "Checkmate! White wins!");
        } else {
            JOptionPane.showMessageDialog(null, "Checkmate! Black wins!");
        }
        System.exit(0);
    }

    /** Sáncolás végrehajtása */
    public void castling(Tile tile){
        if(tile.getPiece().getColor() == 0){
            if(tile.getPosx() < 4){
                getSelectedPiece(tiles[7][3]);
                setSelectedPiece(tiles[7][4].getPiece());
                getSelectedPiece(tiles[7][2]);
            } else {
                getSelectedPiece(tiles[7][5]);
                setSelectedPiece(tiles[7][4].getPiece());
                getSelectedPiece(tiles[7][6]);
            }
        } else {
            if(tile.getPosx() < 4){
                getSelectedPiece(tiles[0][3]);
                setSelectedPiece(tiles[0][4].getPiece());
                getSelectedPiece(tiles[0][2]);
            } else {
                getSelectedPiece(tiles[0][5]);
                setSelectedPiece(tiles[0][4].getPiece());
                getSelectedPiece(tiles[0][6]);
            }
        }
    }

    /** Átváltozás végrehajtása */
    public void promotion(Tile tile){
        if(tile.getPosy() == 0){
            selectedPiece.getTile().removePiece();

            switch (this.getPromotionPiece()) {
                case 1 -> tile.addPiece(new Rook(0));
                case 2 -> tile.addPiece(new Knight(0));
                case 3 -> tile.addPiece(new Bishop(0));
                case 4 -> tile.addPiece(new Queen(0));
            }

        } else {
            selectedPiece.getTile().removePiece();

            switch (this.getPromotionPiece()) {
                case 1 -> tile.addPiece(new Rook(1));
                case 2 -> tile.addPiece(new Knight(1));
                case 3 -> tile.addPiece(new Bishop(1));
                case 4 -> tile.addPiece(new Queen(1));
            }
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(!tiles[i][j].isEmpty()){
                    tiles[i][j].getPiece().setMoved2(false);
                }
            }
        }
        this.setSelectedPiece(null);
    }

    /** En Passant végrehajtása */
    private void enPassant(Tile tile){

        if(selectedPiece.getColor() == 0){
            tiles[3][tile.getPosx()].removePiece();
        } else {
            tiles[4][tile.getPosx()].removePiece();
        }
        getSelectedPiece(tile);
    }

    /** A paraméterként kapott király sakkban van-e */
    public boolean isCheck(Piece king){

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(!tiles[i][j].isEmpty() && (tiles[i][j].getPiece().checkPossibleTiles(tiles).contains(king.getTile()))){
                        return true;
                }
            }
        }
        return false;
    }

    /** A lehetséges mezők szűrése, hogy a lépés után a saját király ne lehessen sakkban */
    public ArrayList<Tile> tilesInCheck(Piece piece){
        ArrayList<Tile> temp = piece.checkPossibleTiles(tiles);

        Tile originalTile = piece.getTile();

        for(Tile t : piece.checkPossibleTiles(tiles)){
            //elvégezzük a lépést
            Piece p2 = t.getPiece();
            t.addPiece(piece);
            originalTile.removePiece();

            //megnézzük, hogy van e sakk, ha igen akkor törlünk
            if(piece.getColor() == 0){
                if(isCheck(whiteKing)){
                    temp.remove(t);
                }
            } else {
                if(isCheck(blackKing)){
                    temp.remove(t);
                }
            }

            //visszacsinálunk mindent
            originalTile.addPiece(piece);
            t.removePiece();
            if(p2 != null){
                t.addPiece(p2);
            }
        }
        return temp;
    }

    /** Lépés elvégzése */
    public void move(Tile tile){

        if(tile.isEnabled()){

            if(selectedPiece != null && selectedPiece.hasSpecialMove(tiles) == tile){
                if(selectedPiece.getType() == 1){
                    castling(selectedPiece.getTile());
                } else if (selectedPiece.getType() == 0){
                    enPassant(tile);
                }
                whiteMoves = !whiteMoves;
                startGame();
            } else if(selectedPiece != null){

                if(selectedPiece.getType() == 0 && selectedPiece.getPosy() == 1 && selectedPiece.getColor() == 0){
                    if(tilesInCheck(selectedPiece).contains(tile)){
                        promotion(tile);
                    }
                } else if (selectedPiece.getType() == 0 && selectedPiece.getPosy() == 6 && selectedPiece.getColor() == 1) {
                    if(tilesInCheck(selectedPiece).contains(tile)){
                        promotion(tile);
                    }
                } else {
                    getSelectedPiece(tile);
                }

                whiteMoves = !whiteMoves;
                startGame();

                if(whiteMoves){
                    if(isCheck(whiteKing)){
                        JOptionPane.showMessageDialog(null, "White king is in check!");
                    }
                } else {
                    if(isCheck(blackKing)){
                        JOptionPane.showMessageDialog(null, "Black king is in check!");
                    }
                }

            } else if(!tile.isEmpty()) {

                setSelectedPiece(tile.getPiece());

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        tiles[i][j].setEnabled(false);
                        tiles[i][j].getButton().setBorder(null);
                    }
                }

                tile.getButton().setBorder(new LineBorder(Color.BLUE, 2));

                for(Tile t : tilesInCheck(selectedPiece)){
                    t.setEnabled(true);
                    t.getButton().setBorder(new LineBorder(Color.GREEN, 2));
                }


                if(selectedPiece.hasSpecialMove(tiles) != null){
                    selectedPiece.hasSpecialMove(tiles).setEnabled(true);
                    selectedPiece.hasSpecialMove(tiles).getButton().setBorder(new LineBorder(Color.RED, 2));
                }

                //promotion
                if(selectedPiece.getType() == 0 && selectedPiece.getPosy() == 1 && selectedPiece.getColor() == 0){
                    for(Tile t : tilesInCheck(selectedPiece)){
                        t.setEnabled(true);
                        t.getButton().setBorder(new LineBorder(Color.RED, 2));
                    }
                }

                if(selectedPiece.getType() == 0 && selectedPiece.getPosy() == 6 && selectedPiece.getColor() == 1){
                    for(Tile t : tilesInCheck(selectedPiece)){
                        t.setEnabled(true);
                        t.getButton().setBorder(new LineBorder(Color.RED, 2));
                    }
                }
            }
        }
    }

    //Getter and setter
    public Piece getWhiteKing() {
        return whiteKing;
    }

    public Piece getBlackKing() {
        return blackKing;
    }
}
