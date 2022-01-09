package game.uno;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        double height = 600;
        double width = 600;
        Group root = new Group();
        Scene scene = new Scene(root, width,height);
        Board board = new Board();
        stage.setTitle("Uno");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}