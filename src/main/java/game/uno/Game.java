package game.uno;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        double height = 1000;
        double width = 1000;
        Group root = new Group();
        Scene scene = new Scene(root, width,height);


        Player j1 = new Player("j1");
        Player j2 = new Player("j2");

        final Player[] currentPlayer = {j1};
        //Player j3 = new Player("j3");

        Board board = new Board(root, currentPlayer[0]);


        board.addPlayer(j1);
        board.addPlayer(j2);
        //board.addPlayer(j3);
        board.distibuteCards(root);
        board.initDisplayCards(root,j1,350,750);
        board.initDisplayCards(root,j2,50,150);
        //board.initDisplayCards(root,j3,250,150);


        stage.setTitle("Uno");
        stage.setScene(scene);
        stage.show();

        scene.setOnMouseClicked(event -> {
            currentPlayer[0] = board.nextPlayer(currentPlayer[0]);
            System.out.println(currentPlayer[0].getName());
        });
    }

    public static void main(String[] args) {
        launch();
    }
}