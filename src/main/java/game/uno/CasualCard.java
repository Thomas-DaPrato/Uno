package game.uno;

import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class CasualCard extends Card{
    private int value;
    public CasualCard(String pathImage, String name, int value) throws FileNotFoundException {
        super(pathImage, name);
        this.value = value;
    }
}
