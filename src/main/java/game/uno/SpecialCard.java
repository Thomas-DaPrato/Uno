package game.uno;


import java.io.FileNotFoundException;

public class SpecialCard extends Card{
    private String effect;
    public SpecialCard(String pathImage,String name, String color, String effect, int value) throws FileNotFoundException {
        super(pathImage, name,color, value);
        this.effect = effect;
    }

    @Override
    public String getEffect() {
        return effect;
    }


}
