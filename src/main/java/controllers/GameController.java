package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.GameFacade;
import model.modelInterfaces.Observer;

import java.util.List;


public class GameController implements Observer {

    @FXML
    private ChoiceBox levelChoice;
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

    public void initialize(){
        Platform.runLater(this::update);
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
}
