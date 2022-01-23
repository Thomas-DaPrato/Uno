package game.uno;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Card {
    protected Image image;
    protected ImageView imageView;
    protected String name;
    protected String color;
    protected int value;

    protected int x;
    protected int y;

    public Card(String pathImage, String name, String color, int value) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(pathImage));
        this.name = name;
        this.color = color;
        this.value = value;
    }

    public boolean eventMouseClicked(ArrayList<Card> areaPlaying) {
        Card cardOnTop = areaPlaying.get(areaPlaying.size()-1);
        if (this.color.equals(cardOnTop.getColor()) || getValue() == cardOnTop.getValue() || this.name.contains("joker")){
            setX(450);
            setY(400);
            areaPlaying.add(this);
            return true;
        }
        else {
            System.out.println("carte non valide");
            return false;
        }
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

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor(){ return this.color; }

    public int getValue(){
        return value;
    }

    public abstract String getEffect();

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
