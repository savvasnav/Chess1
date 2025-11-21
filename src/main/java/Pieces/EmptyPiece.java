package Pieces;

import chess.Piece;
import chess.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class EmptyPiece extends Piece {
    private static final Image EMPTY_IMAGE = new WritableImage(1,1);
    public static final EmptyPiece INSTANCE = new EmptyPiece();

    public EmptyPiece() {
        super(-1, -1, PieceType.EMPTY, PieceColor.NONE);
    }

    @Override
    public boolean checkValidMove(int x, int y, Tile[][] board) {
        return false;
    }

    @Override
    public Image getImage() {
        return EMPTY_IMAGE;
    }
}
