package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.GameFacade;
import model.modelInterfaces.Observer;

import java.util.List;


public class GameController implements Observer {

    @FXML
    private ChoiceBox<Integer> levelChoice;
    @FXML
    private GridPane gridView;
    @FXML
    private Scene gameScene;

    private GameFacade facade;

    private final double DIMENSION = 350;



    public GameController(GameFacade facade){
        this.facade = facade;
        facade.addObserver(this);
    }

    public void initialize() {
        Platform.runLater(() -> {
            initChoiceBox();
            update();
        });

    }

    @Override
    public void update() {
        paint();
    }

    private void paint(){
        gridView.getChildren().clear();
        gridView.setPrefSize(DIMENSION, DIMENSION);
        gridView.setGridLinesVisible(true);
        List<List<Integer>> grid = facade.getGrid();
        for (int y = 0; y < grid.size(); y++){
            for (int x = 0; x < grid.get(0).size(); x++){
                ImageView img;
                if (grid.get(y).get(x) == 1){
                    img = new ImageView("lamps/lighton.png");
                } else {
                    img = new ImageView("lamps/lightoff.png");
                }
                img.setFitWidth(DIMENSION / (double) grid.get(0).size());
                img.setFitHeight(DIMENSION / (double) grid.size());
                gridView.add(img, x, y);
            }
        }
        gridView.setHgap(0);
        gridView.setVgap(0);
    }

    public void changeLevel(ActionEvent event){
        int level = levelChoice.getValue();
        facade.newGame(level, level);
    }

    private void initChoiceBox(){
        List<Integer> lvls = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10);
        levelChoice.getItems().addAll(lvls);
        levelChoice.setOnAction(this::changeLevel);
    }
}
