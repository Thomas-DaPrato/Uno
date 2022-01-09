package game.uno;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Card {
    private Image image;
    private String name;

    public Card(String pathImage, String name) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(pathImage));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
