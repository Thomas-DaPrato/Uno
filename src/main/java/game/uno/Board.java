package game.uno;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final ArrayList<Card> stack = new ArrayList<>();
    private ArrayList<Card> areaPlaying = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    public Board(Group root, Player currentPlayer) throws IOException {
        initStack(root, currentPlayer);
    }

    public void initStack(Group root,Player currentPlayer) throws IOException {
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
                    redCard = new CasualCard("src/main/resources/images/cartes/red"+tabLine[1]+".png", "red"+tabLine[1], "red",Integer.parseInt(tabLine[1]));
                    blueCard = new CasualCard("src/main/resources/images/cartes/blue"+tabLine[1]+".png", "blue"+tabLine[1], "blue",Integer.parseInt(tabLine[1]));
                    greenCard = new CasualCard("src/main/resources/images/cartes/green"+tabLine[1]+".png", "green"+tabLine[1], "green",Integer.parseInt(tabLine[1]));
                    yellowCard = new CasualCard("src/main/resources/images/cartes/yellow"+tabLine[1]+".png", "yellow"+tabLine[1], "yellow",Integer.parseInt(tabLine[1]));
                }
                else {
                    if (tabLine[1].contains("joker")){
                        redCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], "joker", tabLine[1]);
                        blueCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], "joker", tabLine[1]);
                        greenCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], "joker", tabLine[1]);
                        yellowCard = new SpecialCard("src/main/resources/images/cartes/"+tabLine[1]+".png", tabLine[1], "joker", tabLine[1]);
                    }
                    else {
                        redCard = new SpecialCard("src/main/resources/images/cartes/red"+tabLine[1]+".png", "red"+tabLine[1], "red", tabLine[1]);
                        blueCard = new SpecialCard("src/main/resources/images/cartes/blue"+tabLine[1]+".png", "blue"+tabLine[1], "blue", tabLine[1]);
                        greenCard = new SpecialCard("src/main/resources/images/cartes/green"+tabLine[1]+".png", "green"+tabLine[1], "green", tabLine[1]);
                        yellowCard = new SpecialCard("src/main/resources/images/cartes/yellow"+tabLine[1]+".png", "yellow"+tabLine[1], "yellow", tabLine[1]);
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

        Rectangle stackDisplay = new Rectangle(600,400,100,200);
        stackDisplay.setFill(Color.BLACK);
        root.getChildren().add(stackDisplay);
        stackDisplay.setOnMouseClicked(event -> {
            stack.get(0).addCardInHand(root,currentPlayer,players);
            stack.remove(stack.get(0));
        });

    }

    private void addCardInStack(ArrayList<Card> cards){
        int randCard = new Random().nextInt(cards.size());
        stack.add(cards.remove(randCard));
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void distibuteCards(Group root) {
        System.out.println("--------  Distribution des cartes  -----------");
        for (int i = 0; i < 7; i+=1){
            for (Player player: players){
                System.out.println(stack.get(0).getName());
                player.addCard(stack.remove(0));
            }
        }
        areaPlaying.add(stack.remove(0));
        ImageView imageView = new ImageView(areaPlaying.get(0).getImage());
        imageView.setX(450);
        imageView.setY(400);
        root.getChildren().add(imageView);
    }

    public void initDisplayCards(Group root, Player player, int x, int y){
        for(Card card: player.getMyCard()){
            card.setX(x);
            card.setY(y);
            ImageView imageView = new ImageView(card.getImage());
            imageView.setX(card.getX());
            imageView.setY(card.getY());
            imageView.setOnMouseClicked(event -> {
                if (card.eventMouseClicked(stack)) {
                    player.playCard(card);
                    imageView.setX(card.getX());
                    imageView.setY(card.getY());
                    imageView.setRotate(0);
                }
            });
            root.getChildren().add(imageView);
            switch (players.indexOf(player)){
                case 0 -> x += 30;
                case 1 -> {
                    y += 30;
                    imageView.setRotate(90);
                }
                case 2 -> {
                    x += 30;
                    imageView.setRotate(180);
                }
                case 3 -> {
                    y += 30;
                    imageView.setRotate(270);
                }
            }
        }
    }

    public Player nextPlayer(Player player){
        return players.get(players.indexOf(player) + 1 >= players.size() ? 0 : players.indexOf(player) + 1);
    }


    public ArrayList<Card> getStack() {
        return stack;
    }
}
