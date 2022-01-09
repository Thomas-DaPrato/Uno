package game.uno;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.CornerRadii;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final ArrayList<Card> stack = new ArrayList<>();
    private ArrayList<Card> areaPlaing = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    public Board() throws IOException {
        initStack();
    }

    public void initStack() throws IOException {
        ArrayList<Card> redCards = new ArrayList<>();
        ArrayList<Card> blueCards = new ArrayList<>();
        ArrayList<Card> greenCards = new ArrayList<>();
        ArrayList<Card> yellowCards = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/cartes"));
        for (String line = reader.readLine(); line != null; line = reader.readLine()){
            String[] tabLine = line.split(" - ");
            for (int i = 0; i < Integer.parseInt(tabLine[2]); i+=1){
                Card redCard;
                Card blueCard;
                Card greenCard;
                Card yellowCard;
                if (tabLine[0].equals("C")){
                    redCard = new CasualCard("src/main/resources/images/cartes/red"+tabLine[1]+".png", "red"+tabLine[1], Integer.parseInt(tabLine[1]));
                    blueCard = new CasualCard("src/main/resources/images/cartes/blue"+tabLine[1]+".png", "blue"+tabLine[1], Integer.parseInt(tabLine[1]));
                    greenCard = new CasualCard("src/main/resources/images/cartes/green"+tabLine[1]+".png", "green"+tabLine[1], Integer.parseInt(tabLine[1]));
                    yellowCard = new CasualCard("src/main/resources/images/cartes/yellow"+tabLine[1]+".png", "yellow"+tabLine[1], Integer.parseInt(tabLine[1]));
                }
                else {
                    if (tabLine[1].contains("joker")){
                        redCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], tabLine[1]);
                        blueCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], tabLine[1]);
                        greenCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], tabLine[1]);
                        yellowCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], tabLine[1]);
                    }
                    else {
                        redCard = new SpecialCard("src/main/resources/images/cartes/red"+tabLine[1]+".png", "red"+tabLine[1], tabLine[1]);
                        blueCard = new SpecialCard("src/main/resources/images/cartes/blue"+tabLine[1]+".png", "blue"+tabLine[1], tabLine[1]);
                        greenCard = new SpecialCard("src/main/resources/images/cartes/green"+tabLine[1]+".png", "green"+tabLine[1], tabLine[1]);
                        yellowCard = new SpecialCard("src/main/resources/images/cartes/yellow"+tabLine[1]+".png", "yellow"+tabLine[1], tabLine[1]);
                    }
                }
                redCards.add(redCard);
                blueCards.add(blueCard);
                greenCards.add(greenCard);
                yellowCards.add(yellowCard);
            }
        }

        while (redCards.size() != 0 && blueCards.size() != 0 && greenCards.size() != 0 && yellowCards.size() != 0){
            int randTab = new Random().nextInt(4);
            switch (randTab){
                case 0 -> {
                    if (redCards.size() !=0) addCardInStack(redCards);
                }
                case 1 -> {
                    if (blueCards.size() !=0) addCardInStack(blueCards);
                }
                case 2 -> {
                    if (greenCards.size() !=0) addCardInStack(greenCards);
                }
                case 3 -> {
                    if (yellowCards.size() !=0) addCardInStack(yellowCards);
                }
            }
        }

        for(Card card: stack)
            System.out.println(card.getName());

    }

    private void addCardInStack(ArrayList<Card> cards){
        int randCard = new Random().nextInt(cards.size());
        stack.add(cards.remove(randCard));
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void distibuteCards() {
        System.out.println("--------  Distribution des cartes  -----------");
        for (int i = 0; i < 7; i+=1){
            for (Player player: players){
                System.out.println(stack.get(0).getName());
                player.addCard(stack.remove(0));
            }
        }
    }

    public void initDisplayCard(Group root){
        int xHorizontal = 150;
        int yHorizontal = 375;

        int xVertical = 50;
        int yVertical = 150;

        for (int i = 0; i <players.size();i+=1);

    }

    public void intiDisplayCardsDown(Group root){
        int x = 150;
        int y = 375;

        for(Card card: players.get(0).getMyCard()){
            card.setX(x);
            card.setY(y);
            ImageView imageView = new ImageView(card.getImage());
            imageView.setX(card.getX());
            imageView.setY(card.getY());
            root.getChildren().add(imageView);
            x+=20;
        }

    }

    public void intiDisplayCardsLeft(Group root){
        int x = 0;
        int y = 150;

        for(Card card: players.get(1).getMyCard()){
            card.setX(x);
            card.setY(y);
            ImageView imageView = new ImageView(card.getImage());
            imageView.setX(card.getX());
            imageView.setY(card.getY());
            imageView.setRotate(90);
            root.getChildren().add(imageView);
            y+=20;
        }

    }

    public ArrayList<Card> getStack() {
        return stack;
    }
}
