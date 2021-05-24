package TrainFinderDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FinderMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((this.getClass().getResource("/TrainFinder.fxml")));

        GridPane gridPane = loader.load();
        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Train APP");
        primaryStage.show();
    }
}
