module game.uno {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.uno to javafx.fxml;
    exports game.uno;
}