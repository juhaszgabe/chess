package chess;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Menu extends JFrame {

    public JPanel cardPanel;
    private ChessBoard chessBoard;

    /** Getter */
    public ChessBoard getChessBoard(){
        return chessBoard;
    }

    /** Konstruktor */
    public Menu() {
        setTitle("Chess Game");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardPanel = new JPanel(new CardLayout());
        createMainMenu();
        add(cardPanel);
    }

    /** JButton-ok és feliratok létrehozása és hozzáadása a panelhez */
    private void createMainMenu() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));

        mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Add space

        JLabel chessLabel = new JLabel("C H E S S");
        chessLabel.setFont(new Font("Arial", Font.BOLD, 60));
        chessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuPanel.add(chessLabel);
        mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 150))); // Add space

        JButton newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setMaximumSize(new Dimension(150, 50));
        newGameButton.addActionListener(e -> {
            createChessBoard();
            switchToChessBoard();
        });

        mainMenuPanel.add(newGameButton);
        mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.setMaximumSize(new Dimension(150, 50));
        loadGameButton.addActionListener(e -> {
            try {
                loadChessBoard();
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Load error.");
            }
            switchToChessBoard();
        });

        mainMenuPanel.add(loadGameButton);
        mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(new Dimension(150, 50));
        exitButton.addActionListener(e -> System.exit(0));

        mainMenuPanel.add(exitButton);

        cardPanel.add(mainMenuPanel, "MainMenu");
    }

    /** Sakktábla létrehozása új játékhoz */
    public void createChessBoard() {
        Tile[][] tiles = new Tile[8][8];
        Game game = new Game();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j] = new Tile(j, i);
                int finalI = i;
                int finalJ = j;
                tiles[i][j].getButton().addActionListener(e -> game.move(tiles[finalI][finalJ]));
            }
        }
        chessBoard = new ChessBoard(tiles, game);
        chessBoard.getGame().setTiles(tiles);
        chessBoard.getGame().setupGame();
        chessBoard.getGame().startGame();

        cardPanel.add(chessBoard, "ChessBoard");
    }

    /** Sakktábla létrehozása mentett játékállás visszatöltése esetén */
    public void loadChessBoard() throws IOException, ClassNotFoundException {
        Tile[][] tiles = new Tile[8][8];
        Game game = new Game();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j] = new Tile(j, i);
                int finalI = i;
                int finalJ = j;
                tiles[i][j].getButton().addActionListener(e -> game.move(tiles[finalI][finalJ]));
            }
        }
        chessBoard = new ChessBoard(tiles, game);
        chessBoard.getGame().setTiles(tiles);

        FileInputStream inputStream = new FileInputStream("pieces.txt");
        ObjectInputStream input = new ObjectInputStream(inputStream);
        chessBoard.getGame().setPieces((ArrayList<Piece>) input.readObject());
        chessBoard.getGame().loadGame();
        chessBoard.getGame().startGame();
        cardPanel.add(chessBoard, "ChessBoard");
        input.close();
    }

    /** Átváltás a sakktáblára */
    public void switchToChessBoard() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "ChessBoard");
        chessBoard.addMenuBar();
    }
}

