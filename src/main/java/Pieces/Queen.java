package Pieces;

import chess.Piece;
import chess.Tile;
import javafx.scene.image.Image;

public class Queen extends Piece {
    public Queen(int x, int y, PieceType type, PieceColor color){
        super(x,y,PieceType.QUEEN,color);
    }
    @Override
    public boolean checkValidMove(int newX, int newY, Tile[][] board){
        int dx = newX - x;
        int dy = newY - y;
        int xDirection = Integer.compare(dx,0);
        int yDirection = Integer.compare(dy,0);
        int currX = x+xDirection;
        int currY = y+yDirection;

        if(((x==newX)&&(y==newY))){
                return false;
            }

        if(Math.abs(dx)!=Math.abs(dy) && dx!=0 && dy!=0){
            return false;
        }

        while(currX != newX && currY!=newY){
            if(board [currX] [currY] != null){
                return false;
            }
            currX = currX + xDirection;
            currY = currY + yDirection;
        }
        return true;



    }

    @Override
    public Image getImage() {
        String imgPath;
        if(isWhite()){
            imgPath="file:src/Images/white_queen.png";
        } else if (isBlack()) {
            imgPath= "file:src/Images/black_queen.png";
        }
        else {
            imgPath = "";
        }
        return new Image(imgPath);
    }
}