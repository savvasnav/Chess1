package chess;

import Pieces.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    /// private final piece[][] boardtiles is the board tiles of the game is the class Piece that is just null on Pieces that do not exist
    private final Piece[][] boardTiles;
    /// whiteTurn is the variable which contains the currentTurn: if true its white's turn else if false it's black's turn
    private boolean whiteTurn = true;
    private final GridPane gridPane;
    /// SelectedPiece is the piece which is clicked and selected to move.
    public Piece selectedPiece = null;
    /// the enPassantTarget is ....
    public Pawn enPassantTarget = null;
    /// the selectedX and selectedY variables are the variables that are selected right now
    private int selectedX = -1;
    private int selectedY = -1;
    public Board(){

        boardTiles = new Piece[8][8];
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

    /**
     * Draws board by using for loop for its tile
     * it's a 8x8 board ( 2 players ) so 8 rows and 8 columns
     */
    private void drawBoard() {
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

    /**
     *
     * @return gridPane = board
     */
    public GridPane getBoard() {
        return this.gridPane;
    }

    /**


     * if selectedPiece variable is null which means the piece hasn't been selected yet in the turn then
     *      if clickedPiece variable isn't not null and clickedPiece the turn is correct (white pieces moves when white turn, black pieces moves on black turn)
     *      selectedPiece is the clickedPiece and selectedX and selectedY is the coordinates which the piece is at
     * else if you have selected the Piece coordinates
     *      then selectedPiece checkValidMove is called where it checks the move, if it is all good it moves the Piece and ends the turn
     *              if the moves not valid prints invalid move
     *      and if the turn is over selectedPiece is null and selectedX and selectedY is -1 which just means that we deselect the coordinates for the next player
     * @param x is the coordinate which is used for the horizontal coordinate of the piece
     * @param y is the coordinate which is used for the vertical coordinate of the piece
     *
     */
    public void handleClick(int x, int y) {
        /// clickedPiece is the piece which is clicked by the user right now and it's getting the coordinates of the boardtiles of the piece.
        Piece clickedPiece = boardTiles[x][y];

        if (selectedPiece == null) {
            if (clickedPiece != null && clickedPiece.isWhite() == whiteTurn) {
                selectedPiece = clickedPiece;
                selectedX = x;
                selectedY = y;
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

            selectedPiece = null;
            selectedX = -1;
            selectedY = -1;
        }

        drawBoard();
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







}
