package Pieces;

import javafx.scene.image.Image;

public class Queen extends Piece {
    public Queen(int x, int y, boolean isWhite){
        super(x,y,isWhite);
    }
    @Override
    public boolean isValidMove(int newX, int newY,Piece[][] board){
        int direction = isWhite ? -1 : 1;
        int startRow = isWhite ? 7 : 0;
        if(Math.abs(x - newX) == Math.abs(y - newY) && board[newX][newY] == null){
            return true;
        }
        if (Math.abs(x - newX) == Math.abs(y - newY) && board[newX][newY] == null && board[newX][newY].isWhite == !isWhite){
            return true;
        }
        if((x==newX)||(y==newY)){
            return true;
        }
        if((x==newX)||(y==newY) && board[newX][newY].isWhite == !isWhite){
            return true;
        }
        return false;
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