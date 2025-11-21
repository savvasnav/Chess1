package Pieces;

import chess.Piece;
import chess.Tile;
import javafx.scene.image.Image;

public class Bishop extends Piece {
    public Bishop(int x, int y, PieceType type, PieceColor color){
        super(x,y,PieceType.BISHOP,color);
    }
    @Override
    public boolean checkValidMove(int newX, int newY, Tile[][] board){

//        if(Math.abs(x-newX)!=Math.abs(y-newY)){
//            return false;
//        }
//        int xDirection = (newX - x) > 0 ? 1 : -1;
//        int yDirection = (newY - y) > 0 ? 1 : -1;
//        System.out.println(xDirection);
//        System.out.println(yDirection);
//
//        int currX = x + xDirection;
//        int currY = y + yDirection;
//        System.out.println(currX);
//        System.out.println(currY);
//        while(currX != newX && currY!=newY){
//            if(board [currX] [currY] != null){
//                return false;
//            }
//            currX = currX + xDirection;
//            currY = currY + yDirection;
//        }
//        Piece target = board[newX][newY];
//        return target == null ;
    return false;

    }


    @Override
    public Image getImage() {
        String imgPath;
        if(isWhite()){
            imgPath="file:src/Images/white_bishop.png";
        } else if (isBlack()) {
            imgPath= "file:src/Images/black_bishop.png";
        }
        else {
            imgPath = "";
        }
        return new Image(imgPath);
    }
}