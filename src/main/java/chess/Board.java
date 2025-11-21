package chess;

import Pieces.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {

    private final Tile[][] boardTiles;

    public boolean whiteTurn = true;
    private final GridPane gridPane;
    Piece emptyPiece = EmptyPiece.INSTANCE;
    Piece selectedPiece = emptyPiece;
    public boolean pieceSelected = false;

    public Pawn enPassantTarget = null;

    private int selectedX = -1;
    private int selectedY = -1;
    private PieceType type;
    private PieceColor color;
    public Board(){

        boardTiles = new Tile[8][8];
        gridPane = new GridPane();

        // Initialize board state (logic)
        initPieces();

        // Draw the board UI
        drawBoard();
    }

    /**
     * Initializes board Pieces one by one using classes that construct its piece
     */
    private void initPieces(){
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                boardTiles[col][row] = new Tile(col,row);
                boardTiles[col][row].setPiece(emptyPiece);
            }
        }

        for (int col = 0; col < 8; col++) {
            boardTiles[col][6].setPiece(new Pawn(col,6,PieceColor.WHITE));
            boardTiles[col][1].setPiece(new Pawn(col,1,PieceColor.BLACK));

        }
        // Initialize Rooks
        boardTiles[0][0].setPiece(new Rook(0,0,PieceType.ROOK,PieceColor.BLACK));
        boardTiles[7][0].setPiece(new Rook(7,0,PieceType.ROOK,PieceColor.BLACK));
        boardTiles[0][7].setPiece(new Rook(0,7,PieceType.ROOK,PieceColor.WHITE));
        boardTiles[7][7].setPiece(new Rook(7,7,PieceType.ROOK,PieceColor.WHITE));
        //
       // Initialize Knights
        boardTiles[1][0].setPiece(new Knight(1,0,PieceType.KNIGHT,PieceColor.BLACK));
        boardTiles[6][0].setPiece(new Knight(6,0,PieceType.KNIGHT,PieceColor.BLACK));
        boardTiles[1][7].setPiece(new Knight(1,7,PieceType.KNIGHT,PieceColor.WHITE));
        boardTiles[6][7].setPiece(new Knight(6,7,PieceType.KNIGHT,PieceColor.WHITE));
        //
       // Initialize Bishops
        boardTiles[2][0].setPiece(new Bishop(2,0,PieceType.BISHOP,PieceColor.BLACK));
        boardTiles[5][0].setPiece(new Bishop(5,0,PieceType.BISHOP,PieceColor.BLACK));
        boardTiles[2][7].setPiece(new Bishop(2,7,PieceType.BISHOP,PieceColor.WHITE));
        boardTiles[5][7].setPiece(new Bishop(5,7,PieceType.BISHOP,PieceColor.WHITE));
         //
        // Initialize Queen
        boardTiles[3][0].setPiece(new Queen(3,0,PieceType.QUEEN,PieceColor.BLACK));
        boardTiles[3][7].setPiece(new Queen(3,7,PieceType.QUEEN,PieceColor.WHITE));
        //
        // Initialize King
        boardTiles[4][0].setPiece(new King(4,0,PieceType.KING,PieceColor.BLACK));
        boardTiles[4][7].setPiece(new King(4,7,PieceType.KING,PieceColor.WHITE));
    }

    /**
     * Draws board by using for loop for its tile
     * it's a 8x8 board ( 2 players ) so 8 rows and 8 columns
     */
    private void drawBoard() {
        gridPane.getChildren().clear();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane square = new StackPane();
                Color color = (row + col) % 2 == 0 ? Color.rgb(153, 255, 255) : Color.DARKGRAY;
                Rectangle rect = new Rectangle(80,80);
                rect.setFill(color);
                square.getChildren().add(rect);
                Tile tile = boardTiles[col][row];
                if (tile.isOccupied()) {
                    ImageView imageView = new ImageView(tile.getPiece().getImage());
                    imageView.setFitWidth(60);
                    imageView.setFitHeight(60);
                    square.getChildren().add(imageView);
                }
                final int x = col;
                final int y = row;
                square.setId("tile-" + col + "-" + row);
                square.setOnMouseClicked(event -> handleClick(x,y));
                gridPane.add(square,col,row);
            }

        }
    }

    /**
     *
     * @return gridPane = board
     */
    public GridPane getBoard() {
        return this.gridPane;
    }
    public Tile getTile(int x, int y) {
        return boardTiles[x][y];
    }

    public void handleClick(int x, int y) {
        Piece clickedPiece = boardTiles[x][y].getPiece();
        if (!pieceSelected) {
            if (clickedPiece != emptyPiece ) {
                selectedPiece = clickedPiece;
                selectedX = x;
                selectedY = y;
                pieceSelected = true;
                System.out.println("Selected piece at " + x + ", " + y);
            }
        } else {
            if (selectedPiece.checkValidMove(x, y, boardTiles)) {
                System.out.println("New path at " + x + ", " + y);
                movePiece(selectedX, selectedY, x, y);
                whiteTurn = !whiteTurn;

            } else {
                System.out.println("Invalid move to " + x + ", " + y);

            }
            selectedPiece = emptyPiece;
            selectedX = -1;
            selectedY = -1;
            pieceSelected = false;
        }

        drawBoard();
    }

    private void movePiece(int x,int y, int newX, int newY){
        Piece pieceToMove = boardTiles[x][y].getPiece();
        EmptyPiece emptyPiece = EmptyPiece.INSTANCE;
        pieceToMove.setNewCoordinates(newX,newY);
        boardTiles[newX][newY].setPiece(pieceToMove);
        boardTiles[x][y].setPiece(emptyPiece);
    }
    public boolean isWhiteTurn(){
        return whiteTurn;
    }






}
