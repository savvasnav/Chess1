import Pieces.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    private final Piece[][] boardTiles ;
    private final GridPane gridPane;
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
            boardTiles[i][1] = new Pawn (i,2,false);
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
                gridPane.add(square,col,row);
            }
        }
    }
    public GridPane getBoard() {
        return this.gridPane;
    }
}
