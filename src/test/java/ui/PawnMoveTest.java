package ui;

import Pieces.PieceType;
import chess.Board;
import chess.Tile;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PawnMoveTest extends ApplicationTest {

    private Board board;

    @Override
    public void start(Stage stage) {
        board = new Board();
        stage.setScene(new Scene(board.getBoard(), 800, 800));
        stage.show();
    }

    @Test
    public void testWhitePawnMovesForward() {
        // Click pawn at (4,6)
        clickOn("#tile-4-6");

        // Click target tile (4,5)
        clickOn("#tile-4-5");



        // Now assert the pawn is at (4,5)
        Tile tile = board.getTile(4, 5);  // You need to add this accessor
        assertTrue(tile.isOccupied());
        assertEquals(PieceType.PAWN, tile.getPiece().getType());
        assertTrue(tile.getPiece().isWhite());

        clickOn("#tile-4-1");
        clickOn("#tile-4-2");

        Tile tile1 = board.getTile(4,2);
        assertTrue(tile1.isOccupied());
        assertEquals(PieceType.PAWN, tile1.getPiece().getType());
        assertTrue(tile1.getPiece().isBlack());
    }

    @Test
    public void testBlackPawnSelection() {
        System.out.println("=== TEST: Selecting black pawn at (4,1) ===");
        board.whiteTurn = false;
        clickOn("#tile-4-1");  // Black pawn should be here
        // after clicking once, your board should have:
        // selectedPiece == black pawn
        // pieceSelected == true
        // whiteTurn == false
        Tile tile = board.getTile(4, 1);

        System.out.println("Tile occupied: " + tile.isOccupied());
        System.out.println("Clicked piece color: " + tile.getPiece().getColor());
        System.out.println("pieceSelected: " + board.pieceSelected);
        System.out.println("whiteTurn: " + board.isWhiteTurn());
    }
}
