package game.uno;


import java.io.FileNotFoundException;

public class CasualCard extends Card{
    public CasualCard(String pathImage, String name, String color,int value) throws FileNotFoundException {
        super(pathImage, name, color, value);
    }


    @Override
    public String getEffect() {
        return "";
    }
}
