package Pieces;

import chess.Piece;
import chess.Tile;
import javafx.scene.image.Image;

public class King extends Piece {
    public King(int x, int y, PieceType type, PieceColor color){
        super(x, y, PieceType.KING, color);
    }
    @Override
    public boolean checkValidMove(int newX, int newY, Tile[][] board){

//        if((((x==newX) && Math.abs(y-newY)== 1 )|| ((y==newY) && Math.abs(x-newX)== 1))&& board[newX][newY].isWhite() == !isWhite){
//            return true;
//        }
        return false;
    }

    @Override
    public Image getImage() {
        String imgPath;
        if(isWhite()){
            imgPath="file:src/Images/white_king.png";
        } else if (isBlack()) {
            imgPath= "file:src/Images/black_king.png";
        }
        else {
            imgPath = "";
        }
        return new Image(imgPath);
    }
}