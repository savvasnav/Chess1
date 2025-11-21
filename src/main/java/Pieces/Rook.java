package Pieces;

import chess.Piece;
import chess.Tile;
import javafx.scene.image.Image;

public class Rook extends Piece {
    private boolean hasMoved = false;
    public Rook(int x, int y, PieceType type, PieceColor color){
        super(x, y, PieceType.ROOK, color);
    }
    @Override
    public boolean checkValidMove(int newX, int newY, Tile[][] board){
//        if(((x==newX)||(y==newY)) && (board[newX][newY].getPiece().isOppositeColor())){
//            return true;
//        }
        return false;
    }
    public void move(int newX,int newY, boolean hasMoved){
        this.x = newX;
        this.y = newY;
        this.hasMoved = hasMoved;
    }
    @Override
    public Image getImage() {
        String imgPath;
        if(isWhite()){
            imgPath="file:src/Images/white_rook.png";
        } else if (isBlack()) {
            imgPath= "file:src/Images/black_rook.png";
        }
        else {
            imgPath = "";
        }
        return new Image(imgPath);
    }
}