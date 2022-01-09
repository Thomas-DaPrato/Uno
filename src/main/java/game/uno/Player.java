package game.uno;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> myCard;

    public Player(String name){
        this.name = name;
    }

    public void addCard(Card card){
        myCard.add(card);
    }

    public void playCard(Card card){
        myCard.remove(card);
    }
}
