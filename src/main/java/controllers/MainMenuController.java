package controllers;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.stage.Window;
import model.GameFacade;


import java.io.IOException;
import java.util.Objects;

public class MainMenuController {




    public void exitButtonHandler(ActionEvent actionEvent) {
    }

    public void howToPlayButtonHandler(ActionEvent actionEvent) {
    }

    public void playButtonHandler(ActionEvent actionEvent) throws IOException {
        GameController controller = new GameController(new GameFacade());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/gameView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
