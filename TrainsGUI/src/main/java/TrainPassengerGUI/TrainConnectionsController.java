package TrainPassengerGUI;

import TrainModel.TrainMatchedModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainConnectionsController {


    @FXML
    private TableView<TrainMatchedModel> tableMatchedTrains;
    @FXML
    public TableColumn<TrainMatchedModel, String> clmnname;
    @FXML
    public TableColumn<TrainMatchedModel, Integer> clmnDeparture;
    @FXML
    public TableColumn<TrainMatchedModel, Integer> clmnArrival;
    @FXML
    public TableColumn<TrainMatchedModel, Integer> clmnTime;
    @FXML
    public TableColumn<TrainMatchedModel, Integer> clmnCost;
    @FXML
    private Button buttonBack;


    public ObservableList<TrainMatchedModel> dataList = FXCollections.observableArrayList();

    public TrainConnectionsController() {
        System.out.println("WORKING CNTRL");
    }

    @FXML
    void initialize() {
        for (TrainMatchedModel t:SesssionData.matchTrains) {
            dataList.add(t);
        }
        clmnname.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, String>("name"));
        clmnArrival.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("arrivalTime"));
        clmnDeparture.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("departureTime"));
        clmnTime.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("travelTime"));
        clmnCost.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("ticketCost"));
        tableMatchedTrains.setItems(dataList);
    }

    public void buttonOnClickBack(ActionEvent event) throws IOException {

        System.out.println(SesssionData.matchTrains);
        SesssionData.matchTrains = null;
        Parent blah = FXMLLoader.load(getClass().getResource("/PassengerMenu.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
