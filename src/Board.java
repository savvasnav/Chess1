import Pieces.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    public int currentTurn = 1;
    private final Piece[][] boardTiles ;
    private boolean isCheck;
    private boolean whiteTurn = true;
    private final GridPane gridPane;
    public Piece selectedPiece = null;
    public Pawn enPassantTarget = null;
    private int selectedX = -1;
    private int selectedY = -1;
    private int x;
    private int y;
    public Board(){

        boardTiles = new Piece[8][8];
        gridPane = new GridPane();

        // Initialize board state (logic)
        initPieces();

        // Draw the board UI
        drawBoard();
    }
    private void initPieces(){
        for (int i = 0; i < 8; i++) {
            boardTiles[i][6] = new Pawn (i,6,true);
            boardTiles[i][1] = new Pawn (i,1,false);
        }
        // Initialize Rooks
        boardTiles[0][0] = new Rook(0,0,false);
        boardTiles[7][0] = new Rook(7,0,false);
        boardTiles[0][7] = new Rook(0,7,true);
        boardTiles[7][7] = new Rook(7,7,true);
        //
        // Initialize Knights
        boardTiles[1][0] = new Knight(1,0,false);
        boardTiles[6][0] = new Knight(6,0,false);
        boardTiles[1][7] = new Knight(1,7,true);
        boardTiles[6][7] = new Knight(6,7,true);
        //
        // Initialize Bishops
        boardTiles[2][0] = new Bishop(2,0,false);
        boardTiles[5][0] = new Bishop(5,0,false);
        boardTiles[2][7] = new Bishop(2,7,true);
        boardTiles[5][7] = new Bishop(5,7,true);
        //
        // Initialize Queen
        boardTiles[3][0] = new Queen(3,0,false);
        boardTiles[3][7] = new Queen(3,7,true);
        //
        // Initialize King
        boardTiles[4][0] = new King(4,0,false);
        boardTiles[4][7] = new King(4,7,true);
    }
    private void drawBoard(){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane square = new StackPane();
                Color color = (row + col) % 2 == 0 ? Color.rgb(153, 255, 255) : Color.DARKGRAY;
                Rectangle rect = new Rectangle(80,80);
                rect.setFill(color);
                square.getChildren().add(rect);
                Piece piece = boardTiles[col][row];
                if (piece != null && piece.getImage() != null) {
                    ImageView imageView = new ImageView(piece.getImage());
                    imageView.setFitWidth(60);
                    imageView.setFitHeight(60);
                    square.getChildren().add(imageView);
                }
                final int x = col;
                final int y = row;
                square.setOnMouseClicked(event -> handleClick(x,y));
                gridPane.add(square,col,row);
            }

        }
    }
    public GridPane getBoard() {
        return this.gridPane;
    }

    public void handleClick(int x, int y) {
        Piece clickedPiece = boardTiles[x][y];

        if (selectedPiece == null) {
            // No piece selected yet: select if it's the current player's piece
            if (clickedPiece != null && clickedPiece.isWhite() == whiteTurn) {
                selectedPiece = clickedPiece;
                selectedX = x;
                selectedY = y;
                System.out.println("Selected piece at " + x + ", " + y);
            }
        } else {
            // Piece already selected: try to move
            if (selectedPiece.isValidMove(x, y, boardTiles)) {

                System.out.println("New path at " + x + ", " + y);
                movePiece(selectedX, selectedY, x, y);
                whiteTurn = !whiteTurn; // switch turn
            } else {
                System.out.println("Invalid move to " + x + ", " + y);
            }
            // Reset selection after move attempt
            selectedPiece = null;
            selectedX = -1;
            selectedY = -1;
        }

        drawBoard();  // Refresh UI
    }


    private void movePiece(int x,int y, int newX, int newY){
        Piece pieceToMove = boardTiles[x][y];

        if(pieceToMove instanceof Pawn && boardTiles[newX][newY]==null && Math.abs(newX-x)==1 ){
           boardTiles[newX][newY + (pieceToMove.isWhite() ? 1 : -1) ]= null;
        }
        pieceToMove.move(newX,newY);

        boardTiles[newX][newY] = pieceToMove;
        boardTiles[x][y] = null;
        if(pieceToMove == enPassantTarget && Math.abs(newY - y) == 2){
            enPassantTarget = (Pawn) pieceToMove;
        }
        else{
            enPassantTarget = null;
        }
    }

    private boolean kingCheck(King king, Pawn pawn, Bishop bishop, Knight knight, Rook rook, Queen queen){

        if(rook.getX() == king.getX() || rook.getY() == king.getY()){
            return true;
        }
        if(Math.abs(bishop.getX()-king.getX())!=Math.abs(bishop.getY()- king.getY())){
            return true;
        }
        if(pawn.isWhite() && pawn.getX()-king.getX()== -1 && pawn.getY()-king.getY()==-1 ){
            return true;
        }
        if(!pawn.isWhite() && pawn.getX()-king.getX()== 1 && pawn.getY()-king.getY()== 1 ){
            return true;
        }
        if(queen.getX() == king.getX() || queen.getY() == king.getY()){
            return true;
        }
        if(Math.abs(queen.getX()-king.getX())!=Math.abs(queen.getY()- king.getY())){
            return true;
        }
        if((Math.abs(knight.getX()-king.getX())==2&& Math.abs(knight.getY()- king.getY())!=2)||(Math.abs(knight.getX())-king.getX())!=2&& Math.abs(king.getY()- knight.getY())==2) {
            return true;
        }

        return false;

    }

}
