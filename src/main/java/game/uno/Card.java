package game.uno;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Card {
    private Image image;
    private String name;

    private int x;
    private int y;

    public Card(String pathImage, String name) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(pathImage));
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
