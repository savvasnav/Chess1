package Pieces;

import javafx.scene.image.Image;

public class Pawn extends Piece {
    private boolean hasMoved = false;
    private int lastDoubleStepTurn = -1;
    boolean enPassant = false;
    public Pawn(int x, int y, boolean isWhite){
        super(x,y,isWhite);

    }


    @Override
    public boolean isValidMove(int newX, int newY, Piece[][] board){
        int startRow = isWhite ? 6 : 1;
        int direction = isWhite ? -1 : 1;


        if (newX == x && y == startRow && newY == y + 2 * direction &&
                board[newX][y + direction] == null && board[newX][newY] == null) {
            lastDoubleStepTurn = 1;
            return true;
        }
        if (newX == x && newY == y + direction && board[newX][newY] == null){

            return true;
        }
        if (Math.abs(newX - x) == 1  && newY == y + direction){
            Piece target = board[newX][newY];
            if (target != null && target.isWhite != isWhite) {
                return true;
            }
        }
           Piece sidePawn = board[newX][y];

           if(sidePawn instanceof Pawn){
               Pawn pawn = (Pawn) sidePawn;

               if(pawn.isWhite != isWhite &&
               pawn.lastDoubleStepTurn == 1){
                   return true;
               }

               lastDoubleStepTurn = -1;
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