package game.uno;


import javafx.scene.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void ia(Board board, Group root){
        Card cardOnTop = board.getAreaPlaying().get(board.getAreaPlaying().size() -1);
        HashMap<String, Integer> nbCard = new HashMap<>();
        nbCard.put("red",0);
        nbCard.put("green",0);
        nbCard.put("yellow",0);
        nbCard.put("blue",0);
        for (Card card : myCard){
            if (!card.getColor().equals("joker")){
                nbCard.replace(card.getColor(),nbCard.get(card.getColor()) +1);
            }
            if (card.getValue() == cardOnTop.getValue() || card.getColor().equals(cardOnTop.getColor())){
                board.eventMouseClickedOnCard(root,this, card, card.getImageView());
                return;
            }
        }
        for (Card card : myCard){
            if (card.getName().contains("joker")){
                int max = Math.max(nbCard.get("red"),Math.max(nbCard.get("blue"),Math.max(nbCard.get("yellow"),nbCard.get("green"))));
                for(Map.Entry<String,Integer> map: nbCard.entrySet()){
                    if (map.getValue() == max){
                        card.setColor(map.getKey());
                        board.getColorLabel().setText(map.getKey() + " demandé");
                        System.out.println(board.getColorLabel().getText());
                        board.eventMouseClickedOnCard(root,this, card, card.getImageView());
                        return;
                    }
                }

            }
        }
        board.drawCard(root,this);
    }


}
