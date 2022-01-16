package game.uno;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        double height = 1000;
        double width = 1000;
        Group root = new Group();
        Scene scene = new Scene(root, width,height);

        Menu menu = new Menu(root);

        stage.setTitle("Uno");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}