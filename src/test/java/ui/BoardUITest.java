package ui;

import chess.Main;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class BoardUITest extends ApplicationTest {

    private Main main;

    @Override
    public void start(Stage stage) throws Exception {
        main = new Main();
        main.start(stage); // launch your real UI
        stage.show();
    }

    @Test
    void shouldShowBoard() {
        // Keep it simple for now — we just check that it launches
        System.out.println("Chessboard is visible! Close the window to finish the test.");
        // Keep the app running until you manually close it
        javafx.application.Platform.setImplicitExit(false);
        sleep(Long.MAX_VALUE);
    }


}
