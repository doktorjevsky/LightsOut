package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import model.GameFacade;


public class GameController {

    @FXML
    private ChoiceBox levelChoice;
    @FXML
    private GridPane gridView;
    @FXML
    private Scene gameScene;

    private GameFacade facade;


}
