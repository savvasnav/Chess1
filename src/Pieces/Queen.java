package Pieces;

import javafx.scene.image.Image;

public class Queen extends Piece {
    public Queen(int x, int y, boolean isWhite){
        super(x,y,isWhite);
    }
    @Override
    public boolean isValidMove(int newX, int newY,Piece[][] board){
        if(((x==newX)&&(y==newY))){
            return false;
        }
        int dx = newX - x;
        int dy = newY - y;

        int xDirection = Integer.compare(dx,0);
        int yDirection = Integer.compare(dy,0);

        if(Math.abs(dx)!=Math.abs(dy) && dx!=0 && dy!=0){
            return false;
        }
        int currX = x+xDirection;
        int currY = y+yDirection;
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
    public void move(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }
    @Override
    public Image getImage() {
        String imgPath = isWhite ? "file:src/Images/white_queen.png" : "file:src/Images/black_queen.png";
        return new Image(imgPath);
    }
}