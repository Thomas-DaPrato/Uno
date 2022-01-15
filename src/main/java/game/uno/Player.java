package game.uno;


import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> myCard = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public void addCard(Card card){
        myCard.add(card);
    }

    public void playCard(Card card){
        myCard.remove(card);
    }

    public ArrayList<Card> getMyCard() {
        return myCard;
    }

    public String getName() { return name; }

    public void ia(ArrayList<Card> areaPlaying, Board board, Group root) throws InterruptedException {
        Card cardOnTop = areaPlaying.get(areaPlaying.size() -1);
        for (int i = 0; i < myCard.size(); i+=1){
            if (myCard.get(i).getValue() == cardOnTop.getValue() || myCard.get(i).getColor().equals(cardOnTop.getColor())){
                myCard.get(i).setX(450);
                myCard.get(i).setY(400);
                areaPlaying.add(myCard.get(i));
                ImageView imageView = new ImageView(myCard.get(i).getImage());
                myCard.get(i).setImageView(imageView);
                imageView.setX(myCard.get(i).getX());
                imageView.setY(myCard.get(i).getY());
                imageView.setRotate(0);
                board.eventMouseClickedOnCard(root,this, myCard.get(i), imageView);
                break;
            }
            else {
                board.drawCard(root,this);
            }
        }
    }

}
