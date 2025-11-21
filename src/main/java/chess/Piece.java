package chess;

import Pieces.PieceColor;
import Pieces.PieceType;
import javafx.scene.image.Image;

public abstract class Piece {
    protected int x;
    protected int y;
    protected final PieceType type;
    protected final PieceColor color;

    public Piece(int x, int y, PieceType type, PieceColor color){
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = color;
    }
    public abstract boolean checkValidMove(int x, int y, Tile[][] board);
    public boolean isWhite(){
        return color == PieceColor.WHITE;
    }
    public boolean isBlack(){
        return color == PieceColor.BLACK;
    }
    public boolean isOppositeColor(@org.jetbrains.annotations.NotNull Piece enemy){
        return this.color!=enemy.color;
    }
    public abstract Image getImage();
    public void setNewCoordinates(int newX,int newY){
        this.x = newX;
        this.y = newY;
    }
    public PieceType getType(){
        return type;
    }
    public PieceColor getColor(){
        return color;
    }
}