package Pieces;

import javafx.scene.image.Image;

public class Bishop extends Piece {
    public Bishop(int x, int y, boolean isWhite){
        super(x,y,isWhite);
    }
    @Override
    public boolean checkValidMove(int newX, int newY, Piece[][] board){

        if(Math.abs(x-newX)!=Math.abs(y-newY)){
            return false;
        }
        int xDirection = (newX - x) > 0 ? 1 : -1;
        int yDirection = (newY - y) > 0 ? 1 : -1;
        System.out.println(xDirection);
        System.out.println(yDirection);

        int currX = x + xDirection;
        int currY = y + yDirection;
        System.out.println(currX);
        System.out.println(currY);
        while(currX != newX && currY!=newY){
            if(board [currX] [currY] != null){
                return false;
            }
            currX = currX + xDirection;
            currY = currY + yDirection;
        }
        Piece target = board[newX][newY];
        return (target == null || target.isWhite != this.isWhite) ;


    }


    @Override
    public Image getImage() {
        String imgPath = isWhite ? "file:src/Images/white_bishop.png" : "file:src/Images/black_bishop.png";
        return new Image(imgPath);
    }
}