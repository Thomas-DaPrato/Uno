package game.uno;

import javafx.scene.Group;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    public Menu(Group root){
        initMenu(root);
    }

    public void initMenu(Group root){
        Button singlePLayer = new Button("solo");
        singlePLayer.setLayoutX(500);
        singlePLayer.setLayoutY(400);

        Button multiPlayer = new Button("multi");
        multiPlayer.setLayoutX(500);
        multiPlayer.setLayoutY(600);

        root.getChildren().addAll(singlePLayer,multiPlayer);

        singlePLayer.setOnAction(event -> {
            root.getChildren().clear();
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("j1"));
            players.add(new Player("j2"));
            players.add(new Player("j3"));

            try {
                Board board = new Board(root, players);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

}
