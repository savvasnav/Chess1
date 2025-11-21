package Pieces;

import chess.Piece;
import chess.Tile;
import javafx.scene.image.Image;

public class Knight extends Piece {
    private boolean hasMoved = false;
    public Knight(int x, int y , PieceType type, PieceColor color){
        super(x, y, PieceType.KNIGHT, color);

    }
    @Override
    public boolean checkValidMove(int newX, int newY, Tile[][] board){
//        int position = isWhite ? -1 : 1;
//        int dx = newX - x;
//        int dy = newY - y;
//
//        boolean var = (dx == -1 || dx == 1) && (dy == -2 || dy == 2 );
//        if(var){
//            Piece target = board[newX][newY];
//            if(target == null || target.isWhite == !isWhite) {
//                return true;
//            }
//        }
        return false;
    }
    @Override
    public Image getImage() {
        String imgPath;
        if(isWhite()){
            imgPath="file:src/Images/white_knight.png";
        } else if (isBlack()) {
            imgPath= "file:src/Images/black_knight.png";
        }
        else {
            imgPath = "";
        }
        return new Image(imgPath);
    }
}