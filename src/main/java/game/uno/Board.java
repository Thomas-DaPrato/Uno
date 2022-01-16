package game.uno;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private ArrayList<Player> players;
    private Rectangle stackDisplay = new Rectangle(600,400,100,200);
    private Player currentPlayer;
    private boolean isClockwise = true;
    private Label activePlayer = new Label();
    private Label colorLabel = new Label();




    public Board(Group root, ArrayList<Player> players) throws IOException {
        initStack(root);
        this.currentPlayer = players.get(0);
        this.players = players;
        if (this.players.size() < 4){
            for (int i = players.size(); i < 4; i+=1)
                players.add(new Player("ia"+(i+1)));
        }
        distibuteCards(root);
        for (int i = 0; i < players.size(); i+=1){
            switch (i){
                case 0 -> initDisplayCards(root, players.get(i),350,750);
                case 1 -> initDisplayCards(root, players.get(i),50,250);
                case 2 -> initDisplayCards(root, players.get(i),350,50);
                case 3 -> initDisplayCards(root, players.get(i),850,250);
            }
        }
        activePlayer.setText("au tour de " + currentPlayer.getName());
        activePlayer.setLayoutX(500);
        activePlayer.setLayoutY(350);
        colorLabel.setLayoutX(500);
        colorLabel.setLayoutY(650);
        root.getChildren().addAll(activePlayer,colorLabel);
    }

    public void initStack(Group root) throws IOException {
        ArrayList<Card> redCards = new ArrayList<>();
        ArrayList<Card> blueCards = new ArrayList<>();
        ArrayList<Card> greenCards = new ArrayList<>();
        ArrayList<Card> yellowCards = new ArrayList<>();

        stackDisplay.setFill(Color.BLACK);

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

        root.getChildren().add(stackDisplay);
        stackDisplay.setOnMouseClicked(event -> {
            drawCard(root,currentPlayer);
            currentPlayer = nextPlayer(currentPlayer);
            activePlayer.setText("au tour de " + currentPlayer.getName());
            if (currentPlayer.getName().contains("ia")){
                currentPlayer.ia(this,root);
            }
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
        for (int i = 0; i < 7; i+=1){
            for (Player player: players){
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
                eventMouseClickedOnCard(root, player, card, imageView);
                if (currentPlayer.getName().contains("ia")){
                    currentPlayer.ia(this,root);
                }
                colorLabel.setText("");
            });
            card.setImageView(imageView);
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

    public void eventMouseClickedOnCard(Group root, Player player, Card card, ImageView imageView) {
        if (card.eventMouseClicked(areaPlaying)) {
            root.getChildren().remove(areaPlaying.get(areaPlaying.size() -2).getImageView());
            player.playCard(card);
            imageView.setX(card.getX());
            imageView.setY(card.getY());
            imageView.setRotate(0);
            switch (card.getEffect()){
                case "Plus2" -> {
                    Player p = nextPlayer(currentPlayer);
                    for(int i = 0; i<2; i+=1){
                        drawCard(root,p);
                    }
                }
                case "ChangeSens" -> isClockwise = !isClockwise;
                case "PasseTour" -> {
                    currentPlayer = nextPlayer(currentPlayer);
                    if (currentPlayer.getName().contains("ia"))
                        currentPlayer.ia(this,root);
                }
                case "joker" -> {
                    createButton(card, root);
                }
                case "jokerPlus4" -> {
                    createButton(card, root);
                    Player p = nextPlayer(currentPlayer);
                    for(int i = 0; i<4; i+=1){
                        drawCard(root,p);
                    }
                }
            }
        }
        if (currentPlayer.getMyCard().isEmpty()){
            Rectangle r = new Rectangle(0,0,1000,1000);
            r.setFill(Color.BLACK);
            Label textWin = new Label();
            textWin.setText("Le joueur "+ currentPlayer.getName() + " a gagné");
            textWin.setLayoutX(500);
            textWin.setLayoutY(500);
            textWin.setTextFill(Color.WHITE);
            root.getChildren().addAll(r,textWin);
        }
        currentPlayer = nextPlayer(currentPlayer);
        activePlayer.setText("au tour de " + currentPlayer.getName());
    }

    private void createButton(Card card, Group root) {
        Button redButton = new Button("rouge");
        redButton.setLayoutX(200);
        redButton.setLayoutY(600);


        Button greenButton = new Button("vert");
        greenButton.setLayoutX(300);
        greenButton.setLayoutY(600);

        Button blueButton = new Button("bleu");
        blueButton.setLayoutX(400);
        blueButton.setLayoutY(600);

        Button yellowButton = new Button("jaune");
        yellowButton.setLayoutX(500);
        yellowButton.setLayoutY(600);

        redButton.setOnAction(event1 -> {
            card.setColor("red");
            colorLabel.setText("rouge demandé");
            root.getChildren().removeAll(redButton,blueButton,greenButton,yellowButton);
        });
        greenButton.setOnAction(event1 -> {
            card.setColor("green");
            colorLabel.setText("vert demandé");
            root.getChildren().removeAll(redButton,blueButton,greenButton,yellowButton);
        });
        blueButton.setOnAction(event1 -> {
            card.setColor("blue");
            colorLabel.setText("bleu demandé");
            root.getChildren().removeAll(redButton,blueButton,greenButton,yellowButton);
        });
        yellowButton.setOnAction(event1 -> {
            card.setColor("yellow");
            colorLabel.setText("jaune demandé");
            root.getChildren().removeAll(redButton,blueButton,greenButton,yellowButton);
        });

        root.getChildren().addAll(redButton,blueButton,yellowButton,greenButton);
        if (currentPlayer.getName().contains("ia"))
            root.getChildren().removeAll(redButton,blueButton,greenButton,yellowButton);
    }

    public void drawCard(Group root, Player currentPlayer){
        Card cardDraw = stack.get(0);
        ImageView imageView = new ImageView(cardDraw.getImage());
        if (players.indexOf(currentPlayer) == 0 || players.indexOf(currentPlayer) == 2) {
            cardDraw.setX(currentPlayer.getMyCard().get(currentPlayer.getMyCard().size() -1).getX() + 30);
            cardDraw.setY(currentPlayer.getMyCard().get(currentPlayer.getMyCard().size() -1).getY());
        }
        else {
            cardDraw.setX(currentPlayer.getMyCard().get(currentPlayer.getMyCard().size() -1).getX());
            cardDraw.setY(currentPlayer.getMyCard().get(currentPlayer.getMyCard().size() -1).getY() + 30);
        }
        imageView.setX(cardDraw.getX());
        imageView.setY(cardDraw.getY());
        switch (players.indexOf(currentPlayer)){
            case 1 -> imageView.setRotate(90);
            case 2 -> imageView.setRotate(180);
            case 3 -> imageView.setRotate(270);
        }
        imageView.setOnMouseClicked(event -> {
            eventMouseClickedOnCard(root, currentPlayer, cardDraw, imageView);
            if (currentPlayer.getName().contains("ia")){
                currentPlayer.ia(this,root);
            }
        });
        root.getChildren().add(imageView);
        currentPlayer.addCard(stack.remove(0));
        currentPlayer.getMyCard().get(currentPlayer.getMyCard().size() - 1).setImageView(imageView);
    }

    public Player nextPlayer(Player player){
        if (isClockwise)
            return players.get(players.indexOf(player) + 1 >= players.size() ? 0 : players.indexOf(player) + 1);
        else
            return players.get(players.indexOf(player) - 1 < 0 ? players.size() - 1 : players.indexOf(player) - 1);
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public Rectangle getStackDisplay() {
        return stackDisplay;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getAreaPlaying() {
        return areaPlaying;
    }

    public Label getColorLabel() {
        return colorLabel;
    }
}
