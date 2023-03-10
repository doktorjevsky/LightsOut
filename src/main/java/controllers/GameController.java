package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GameFacade;
import model.modelInterfaces.Observer;

import java.io.IOException;
import java.util.List;



public class GameController implements Observer {

    @FXML
    private ChoiceBox<Integer> levelChoice;
    @FXML
    private GridPane gridView;
    @FXML
    private Scene gameScene;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button solveButton;
    @FXML
    private ChoiceBox<String> gameTypeBox;

    private final Stage mainStage;

    private final GameFacade facade;

    private final double DIMENSION = 500;

    private boolean clickableGrid = true;


    public GameController(GameFacade facade, Stage stage){
        this.facade = facade;
        mainStage = stage;
        facade.addObserver(this);
    }

    public void initialize() {
        Platform.runLater(() -> {
            initChoiceBox();
            initListeners();
            initMenuButton();
            initSolveButton();
            initGameTypeBox();
            update();
        });

    }

    @Override
    public void update() {
        paint();
        if (facade.lightsAreOut()){
            openGameWonDialog();
        }
    }

    public void restart(){
        newGame();
    }

    private void toggleCell(int x, int y){
        facade.toggleCell(x, y);
    }

    // BUTTON HANDLERS

    private void changeLevel(ActionEvent event){
        newGame();
    }


    private void newGame(){
        clickableGrid = true;
        int lvl = levelChoice.getValue();
        int reach = gameTypeBox.getValue().equals("Regular") ? 1 : lvl;
        facade.newGame(reach, lvl);
    }



    private void mainMenuButtonHandler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/mainMenuView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            facade.removeObserver(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void solve(ActionEvent event){
        clickableGrid = false;
        facade.solveGrid();
    }



    // INIT AND PAINTING METHODS

    private void openGameWonDialog(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/gameWonModal.fxml"));
            Stage stage = loader.load();
            GameWonModalController controller = loader.getController();
            controller.setGameController(this);
            stage.setTitle("Game won");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);
            stage.setOnCloseRequest(Event::consume);
            stage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void paint(){
        gridView.getChildren().clear();
        gridView.setGridLinesVisible(true);
        List<List<Integer>> grid = facade.getGrid();
        double d = 32 * grid.size();
        gridView.setPrefSize(d, d);
        for (int y = 0; y < grid.size(); y++){
            for (int x = 0; x < grid.get(0).size(); x++){
                ImageView img;
                if (grid.get(y).get(x) == 1){
                    img = new ImageView("lamps/lighton.png");
                } else {
                    img = new ImageView("lamps/lightoff.png");
                }
                img.setFitWidth(d / (double) grid.get(0).size());
                img.setFitHeight(d / (double) grid.size());
                gridView.add(img, x, y);
            }
        }
        gridView.setHgap(0);
        gridView.setVgap(0);
        if (clickableGrid){
            initListeners();
        }
    }

    private void initChoiceBox(){
        List<Integer> lvls = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17);
        levelChoice.getItems().addAll(lvls);
        levelChoice.setValue(2);
        levelChoice.setOnAction(this::changeLevel);
    }

    private void initGameTypeBox(){
        List<String> types = List.of("Regular", "Unlimited");
        gameTypeBox.setValue("Regular");
        gameTypeBox.getItems().addAll(types);
        gameTypeBox.setOnAction(this::changeLevel);


    }

    private void initListeners(){
        gridView.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int x = GridPane.getColumnIndex(item);
                    int y = GridPane.getRowIndex(item);
                    toggleCell(x, y);

                }
            });
        });
    }

    private void initMenuButton(){
        mainMenuButton.setOnAction(this::mainMenuButtonHandler);
    }

    private void initSolveButton(){
        solveButton.setOnAction(this::solve);
    }

}
