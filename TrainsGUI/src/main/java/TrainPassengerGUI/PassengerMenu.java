package TrainPassengerGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PassengerMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((this.getClass().getResource("/PassengerMenu.fxml")));

        GridPane gridPane = loader.load();

        Scene scene = new Scene(gridPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Train APP");
        primaryStage.show();
    }
}
