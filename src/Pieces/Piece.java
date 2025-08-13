package Pieces;

import javafx.scene.image.Image;

public abstract class Piece {
    protected int x;
    protected int y;
    protected boolean isWhite;
    public Piece(int x, int y,boolean isWhite){
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }
    public abstract boolean isValidMove(int x, int y, Piece[][] board);
    public boolean isWhite(){
        return isWhite;
    }
    public abstract Image getImage();
    public void move(int newX,int newY){
        this.x = newX;
        this.y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



}