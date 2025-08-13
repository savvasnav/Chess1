import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // Circle cir = new Circle(40,40,30);
        AnchorPane anchorPane = new AnchorPane();
        Board board = new Board();
        GridPane gridPane = board.getBoard();
        anchorPane.getChildren().add(gridPane);



        Group root = new Group(anchorPane);
        Scene scene = new Scene(root, 650, 650);

        stage.setTitle("Chess Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }
} 