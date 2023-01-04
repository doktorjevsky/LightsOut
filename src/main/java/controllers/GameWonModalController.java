package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class GameWonModalController {

    private GameController gameController;

    public void setGameController(GameController controller){
        gameController = controller;
    }

    public void initialize(){
        Platform.runLater(() -> {});
    }

    public void closeModalHandler(ActionEvent actionEvent) {
        gameController.restart();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }
}
