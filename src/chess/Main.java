package chess;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu chessGame = new Menu();
            chessGame.setVisible(true);
        });
    }
}
