package Pieces;

import javafx.scene.image.Image;

public class Knight extends Piece {
    private boolean hasMoved = false;
    public Knight(int x, int y , boolean isWhite){
        super(x, y, isWhite);

    }
    @Override
    public boolean isValidMove(int newX, int newY, Piece[][] board){
        int position = isWhite ? -1 : 1;
        boolean var = (Math.abs(newX-x)==2&&Math.abs(newY-y)!=2)||(Math.abs(newX-x)!=2&&Math.abs(newY-y)==2);
        if(var){
            Piece target = board[newX][newY];
            if(target == null || target.isWhite == !isWhite) {
                return true;
            }
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
        String imgPath = isWhite ? "file:src/Images/white_knight.png" : "file:src/Images/black_knight.png";
        return new Image(imgPath);
    }
}