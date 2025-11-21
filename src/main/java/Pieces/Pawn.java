package Pieces;

import chess.Piece;
import chess.Tile;
import javafx.scene.image.Image;

public class Pawn extends Piece {


    public Pawn(int x, int y, PieceColor color){
        super(x,y,PieceType.PAWN,color);

    }


    @Override
    public boolean checkValidMove(int newX, int newY, Tile[][] board){
        int dir = isWhite() ? -1 : 1;
        int startRow = isWhite() ? 6 : 1;

        if (board[newX][newY].isEmpty()){
            if (newX == x && newY == y + dir ){
                return true;
            }
        }

        return false;
    }

    @Override
    public Image getImage() {
        String imgPath;
        if(isWhite()){
            imgPath="file:src/Images/white_pawn.png";
        } else if (isBlack()) {
            imgPath= "file:src/Images/black_pawn.png";
        }
        else {
            imgPath = "";
        }
        return new Image(imgPath);
    }


}