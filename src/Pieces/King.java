package Pieces;

import javafx.scene.image.Image;

public class King extends Piece {
    public King(int x, int y, boolean isWhite){
        super(x, y, isWhite);
    }
    @Override
    public boolean isValidMove(int newX, int newY, Piece[][] board){

        if((((x==newX) && Math.abs(y-newY)== 1 )|| ((y==newY) && Math.abs(x-newX)== 1))&& board[newX][newY].isWhite == !isWhite){
            return true;
        }
        return false;
    }
    public void move(int newX,int newY){
        this.x = newX;
        this.y = newY;

    }
    @Override
    public Image getImage() {
        String imgPath = isWhite ? "file:src/Images/white_king.png" : "file:src/Images/black_king.png";
        return new Image(imgPath);
    }
}