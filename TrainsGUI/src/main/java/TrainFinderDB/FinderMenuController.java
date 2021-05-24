package TrainFinderDB;

import TrainModel.*;
import entity.EntityUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class FinderMenuController {


    TrainsContainer trainsContainer;

    @FXML
    private Button buttonSearch;
    @FXML
    public TextField txtFrom;
    @FXML
    public TextField txtTo;
    @FXML
    public ChoiceBox choiceboxHour;
    @FXML
    public TableView<TrainMatchedModel> tableTickets;
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

    public ObservableList<TrainMatchedModel> dataList = FXCollections.observableArrayList();

    public ArrayList<TrainMatchedModel> searchConnections2(String from, String to, int hour) {

        var cons = EntityUtil.searchConnections(from, to, hour);

        for (var t : cons) {
            System.out.println(t.getTrainId());
        }

        ArrayList<TrainMatchedModel> connections = new ArrayList<TrainMatchedModel>();

        for (var t: cons) {
            var tmpTR = EntityUtil.getTrainByID(t.getTrainId());
            connections.add(new TrainMatchedModel(tmpTR.getName(), hour, tmpTR.getTraveltime(), tmpTR.getTraveltime()-hour, tmpTR.getTicketCost(), tmpTR.getCapacity()));
        }

        return connections;
    }

    @FXML
    void initialize() {

        choiceboxHour.setValue("DOWOLNA");
        choiceboxHour.getItems().add("DOWOLNA");
        for (int i = 0; i < 24; i++) {
            choiceboxHour.getItems().add(i);
        }

        if (SesssionData.bought) {
            for (var t : SesssionData.trainsContainer.trainList) {
                if (t.getName() == SesssionData.lastBoughtName) {
                    t.decreaseCapacity();
                    break;
                }
            }
            SesssionData.bought = false;
            SesssionData.lastBoughtName = null;
        }

        try {
            for (TrainMatchedModel t : EntityUtil.getTickets()) {
                dataList.add(t);
            }
            clmnname.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, String>("name"));
            clmnArrival.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("arrivalTime"));
            clmnDeparture.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("departureTime"));
            clmnTime.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("ticketCost"));
            clmnCost.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("ticketCost"));
            tableTickets.setItems(dataList);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void buttonSearchOnClick(ActionEvent event) throws IOException {
        ArrayList<TrainMatchedModel> trainMatch = new ArrayList<TrainMatchedModel>();

        String hourStr = choiceboxHour.getValue().toString();
        String to = txtTo.getText();
        String from = txtFrom.getText();
        Boolean found = false;
        int hour = -1;
        if (!hourStr.equals("DOWOLNA")) {
            hour = parseInt(hourStr);
        }

        for (TrainMatchedModel t : searchConnections2(from, to, hour)) {
            trainMatch.add(t);
            found = true;
        }

        if (!found) {
            Alert a1 = new Alert(Alert.AlertType.WARNING, "Brak pociągów o podanej trasie i godzinie", ButtonType.OK);
            a1.show();
        } else {
            SesssionData.matchTrains = trainMatch;

            Parent blah = FXMLLoader.load(getClass().getResource("/TrainFinderConnectionsDB.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add("/style.css");
            appStage.setScene(scene);
            appStage.show();
        }
    }

    public void buttonCancelOnClick(ActionEvent actionEvent) {
        var p = tableTickets.getSelectionModel().getSelectedItem();
        if (p != null) {
            for (var t : dataList) {
                if (p.getName() == t.getName()) {
                    tableTickets.getItems().remove(p);
                    SesssionData.boughtTickesFor.remove(p);
                    //t.increaseCapacity();
                    EntityUtil.cancelTicketByID(p.getTicketID());
                    Alert a1 = new Alert(Alert.AlertType.CONFIRMATION, "Bilet został anulowany", ButtonType.OK);
                    a1.show();
                }
            }
        }
    }
}
