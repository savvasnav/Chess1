package Pieces;

import javafx.scene.image.Image;

public class Pawn extends Piece {
    private boolean hasMoved = false;
    public Pawn(int x, int y, boolean isWhite){
        super(x,y,isWhite);
    }
    @Override
    public boolean isValidMove(int newX, int newY, Piece[][] board){

        int startRow = isWhite ? 6 : 1;
        int direction = isWhite ? 1 : -1;

        if (!hasMoved && newX == x + 2 && newY == y && board[x*direction] == null && board[newX][newY] == null){
            return true;
        }
        if (newX == x * direction && newY == y && board[newX][newY] == null){
            return true;
        }
        if (newX == x * direction && y == Math.abs(newY - y) && board[newX][newY].isWhite == !isWhite){
            return true;
        }
        return false;
    }
    public void move(int newX,int newY){
        this.x = newX;
        this.y = newY;
        this.hasMoved = true;
    }
    @Override
    public Image getImage() {
        String imgPath = isWhite ? "file:src/Images/white_pawn.png" : "file:src/Images/black_pawn.png";
        return new Image(imgPath);
    }
}