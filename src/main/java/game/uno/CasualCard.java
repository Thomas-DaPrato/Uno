package game.uno;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CasualCard extends Card{
    public CasualCard(String pathImage, String name, String color,int value) throws FileNotFoundException {
        super(pathImage, name, color);
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
