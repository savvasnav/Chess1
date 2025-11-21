package chess;

import Pieces.EmptyPiece;

public class Tile {
    private final int x;
    private final int y;
    private Piece piece;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = EmptyPiece.INSTANCE;
    }


    public boolean isOccupied(){
        return piece!=EmptyPiece.INSTANCE;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        if(piece == null){
            piece = EmptyPiece.INSTANCE;
        }
        else {
            this.piece = piece;
        }
    }
    public boolean isEmpty(){
        return piece == EmptyPiece.INSTANCE;
    }
}
