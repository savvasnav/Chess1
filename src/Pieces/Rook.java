package Pieces;

import javafx.scene.image.Image;

public class Rook extends Piece {
    private boolean hasMoved = false;
    public Rook(int x, int y, boolean isWhite){
        super(x, y, isWhite);
    }
    @Override
    public boolean isValidMove(int newX, int newY, Piece[][] board){
        int direction = isWhite ? -1 : 1;
        int startRow = isWhite ? 6 : 1;
        if((x==newX)||(y==newY)){
            return true;
        }
        if((x==newX)||(y==newY) && board[newX][newY].isWhite == !isWhite){
            return true;
        }
        if (newX == x + direction && y == Math.abs(newY - y) && board[newX][newY].isWhite == !isWhite){
            return true;
        }
        return false;
    }
    public void move(int newX,int newY, boolean hasMoved){
        this.x = newX;
        this.y = newY;
        this.hasMoved = hasMoved;
    }
    @Override
    public Image getImage() {
        String imgPath = isWhite ? "file:src/Images/white_rook.png" : "file:src/Images/black_rook.png";
        return new Image(imgPath);
    }
}