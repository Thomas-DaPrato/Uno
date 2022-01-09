package game.uno;

import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class SpecialCard extends Card{
    private String effect;
    public SpecialCard(String pathImage,String name, String effect) throws FileNotFoundException {
        super(pathImage, name);
        this.effect = effect;
    }
}
