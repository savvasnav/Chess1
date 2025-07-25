package Pieces;

import javafx.scene.image.Image;

public class Bishop extends Piece {
    public Bishop(int x, int y, boolean isWhite){
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
    return false;
    }
    public void move(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    @Override
    public Image getImage() {
        String imgPath = isWhite ? "file:src/Images/white_bishop.png" : "file:src/Images/black_bishop.png";
        return new Image(imgPath);
    }
}