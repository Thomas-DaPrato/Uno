package game.uno;

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

    }

    private void addCardInStack(ArrayList<Card> cards){
        int randCard = new Random().nextInt(cards.size());
        stack.add(cards.remove(randCard));
    }

    public void distibuteCards() {
        for (int i = 0; i < 7; i+=1){
            for (Player player: players){
                player.addCard(stack.remove(0));
            }
        }
    }

    public ArrayList<Card> getStack() {
        return stack;
    }
}
