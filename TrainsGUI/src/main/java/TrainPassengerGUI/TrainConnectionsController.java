package TrainPassengerGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainConnectionsController {

    @FXML
    Button buttonBack;

    public TrainConnectionsController() {
        System.out.println("WORKING CNTRL");
    }

    @FXML
    void initialize() {
        System.out.println("DZIALAM");
    }

    public void buttonOnClickBack(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("/PassengerMenu.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
