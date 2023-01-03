package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import model.GameFacade;
import model.modelInterfaces.Observer;


public class GameController implements Observer {

    @FXML
    private ChoiceBox levelChoice;
    @FXML
    private GridPane gridView;
    @FXML
    private Scene gameScene;

    private GameFacade facade;


    @Override
    public void update() {

    }
}
