package Pieces;

import javafx.scene.image.Image;

public class Knight extends Piece {
    private boolean hasMoved = false;
    public Knight(int x, int y , boolean isWhite){
        super(x, y, isWhite);

    }
    @Override
    public boolean checkValidMove(int newX, int newY, Piece[][] board){
        int position = isWhite ? -1 : 1;
        int dx = newX - x;
        int dy = newY - y;

        boolean var = (dx == -1 || dx == 1) && (dy == -2 || dy == 2 );
        if(var){
            Piece target = board[newX][newY];
            if(target == null || target.isWhite == !isWhite) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Image getImage() {
        String imgPath = isWhite ? "file:src/Images/white_knight.png" : "file:src/Images/black_knight.png";
        return new Image(imgPath);
    }
}