import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage mainStage;

    public static void main(String[] args){
        launch(args);
    }

    public static Stage getMainStage(){return mainStage;}

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainMenuView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Lights Out");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
