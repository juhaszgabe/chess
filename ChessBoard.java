package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class ChessBoard extends JPanel {

    Game game;
    private ButtonGroup promotionGroup;

    /** Konstruktor 2 paraméterrel */
    public ChessBoard(Tile[][] tiles, Game g) {
        game = g;

        GridLayout layout = new GridLayout(8, 8);

        setLayout(layout);
        boolean isWhiteSquare = false;

        for(int i = 0; i < 8; i++){
            isWhiteSquare = !isWhiteSquare;
            for(int j = 0; j < 8; j++){
                JButton square = tiles[i][j].getButton();
                square.setBackground(isWhiteSquare ? Color.WHITE : Color.DARK_GRAY);
                isWhiteSquare = !isWhiteSquare;
                add(square);
            }
        }
    }

    /** A felső menüsáv hozzáadása */
    void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.addActionListener(e -> {
            try {
                saveGame();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Save error.");
            }
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(ChessBoard.this);
            if (topFrame != null) {
                topFrame.dispose();
            }
        });

        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        JMenu promotionMenu = new JMenu("Pawn Promotion");

        promotionGroup = new ButtonGroup();
        addPromotionOption(promotionMenu, "Queen");
        addPromotionOption(promotionMenu, "Rook");
        addPromotionOption(promotionMenu, "Bishop");
        addPromotionOption(promotionMenu, "Knight");

        menuBar.add(fileMenu);
        menuBar.add(promotionMenu);

        JFrame topFrame = (JFrame) SwingUtilities.getRoot(this);
        if (topFrame != null) {
            topFrame.setJMenuBar(menuBar);
        }
    }

    /** Radio buttonok hozzáadása a Promotion választóhoz */
    private void addPromotionOption(JMenu promotionMenu, String pieceName) {
        JRadioButtonMenuItem promotionOption = new JRadioButtonMenuItem(pieceName);

        if(pieceName.equals("Queen")){
            promotionOption.setSelected(true);
        }

        promotionOption.addActionListener(e -> {

            switch (pieceName) {
                case "Queen" -> setProm(game, 4);
                case "Bishop" -> setProm(game, 3);
                case "Knight" -> setProm(game, 2);
                case "Rook" -> setProm(game, 1);
            }

        });

        promotionGroup.add(promotionOption);
        promotionMenu.add(promotionOption);
    }

    /** Setter */
    private void setProm(Game game, int piece){
        game.setPromotionPiece(piece);
    }

    /** Getter */
    public Game getGame() {
        return game;
    }

    /** Játékállás mentése fileba, szerializálás segítségével */
    public void saveGame() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("pieces.txt");
        ObjectOutputStream output = new ObjectOutputStream(outputStream);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if(!game.getTiles()[i][j].isEmpty()){
                    game.getTiles()[i][j].getPiece().setWm(game.isWhiteMoves());
                    game.getPieces().add(game.getTiles()[i][j].getPiece());
                }
            }
        }
        output.writeObject(game.getPieces());
        output.close();
        JOptionPane.showMessageDialog(null, "Game saved succesfully.");
    }
}
