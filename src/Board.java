import Pieces.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    private final Piece[][] boardTiles ;
    private boolean whiteTurn = true;
    private final GridPane gridPane;
    private Piece selectedPiece = null;
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
        boardTiles[7][0]= new Rook(7,0,false);
        boardTiles[0][7] = new Rook(0,7,true);
        boardTiles[7][7] = new Rook(7,7,true);
        //
        // Initialize Bishops
        boardTiles[1][0] = new Bishop(1,0,false);
        boardTiles[6][0]= new Bishop(6,0,false);
        boardTiles[1][7] = new Bishop(1,7,true);
        boardTiles[6][7] = new Bishop(6,7,true);
        //
        // Initialize Knights
        boardTiles[2][0] = new Knight(2,0,false);
        boardTiles[5][0]= new Knight(5,0,false);
        boardTiles[2][7] = new Knight(2,7,true);
        boardTiles[5][7] = new Knight(5,7,true);
        //
        // Initialize Queen
        boardTiles[4][0] = new Queen(4,0,false);
        boardTiles[4][7]= new Queen(4,7,true);
        //
        // Initialize King
        boardTiles[3][0] = new King(3,0,false);
        boardTiles[3][7]= new King(3,7,true);
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
        pieceToMove.move(newX,newY);
        boardTiles[newX][newY] = pieceToMove;
        boardTiles[x][y] = null;




    }

}
