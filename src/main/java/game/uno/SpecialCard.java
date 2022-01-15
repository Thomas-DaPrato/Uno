package game.uno;


import java.io.FileNotFoundException;

public class SpecialCard extends Card{
    private String effect;
    public SpecialCard(String pathImage,String name, String color, String effect) throws FileNotFoundException {
        super(pathImage, name,color);
        this.effect = effect;
    }

    @Override
    public int getValue() {
        return -1;
    }

    @Override
    public String getEffect() {
        return effect;
    }


}
