package TrainPassengerGUI;

import TrainModel.*;
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


public class TrainPassengerGUIController {


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

    public ArrayList<TrainMatchedModel> searchConnections2(String from, String to, int hour, TrainsContainer trainsContainer) {
        ArrayList<TrainMatchedModel> connections = new ArrayList<TrainMatchedModel>();
        for (Train t : trainsContainer.trainList) {
            Boolean hit = false;
            int h1 = -1;
            int h2 = -1;
            Boolean connection = false;
            for (int i = 0; i < t.stationList.size(); i++) {
                if (hour == -1) {
                    if (t.stationList.get(i).toString().equals(from)) {
                        hit = true;
                        h1 = i;
                    }
                } else {
                    if (t.stationList.get(i).toString().equals(from) && t.timeTableList.get(i).equals(hour)) {
                        hit = true;
                        h1 = i;
                    }
                }

                if (hit) {
                    if (t.stationList.get(i).toString().equals(to)) {
                        connection = true;
                        h2 = i;
                        break;
                    }
                }
            }
            if (connection) {
                connections.add(new TrainMatchedModel(t.getName(), t.timeTableList.get(h1), t.timeTableList.get(h2), t.timeTableList.get(h2) - t.timeTableList.get(h1), t.getTicketCost(), t.getCapacity()));
            }
        }
        return connections;
    }

    void trainInit() {
        //trainsContainer = new TrainsContainer();
        TrainStation krakow = new TrainStation("Krakow");
        TrainStation warszawa = new TrainStation("Warszawa");
        TrainStation katowice = new TrainStation("Katowice");
        TrainStation opole = new TrainStation("Opole");
        TrainStation wroclaw = new TrainStation("Wroclaw");
        TrainStation kielce = new TrainStation("Kielce");
        TrainStation radom = new TrainStation("Radom");
        ArrayList<TrainStation> routeKRWR = new ArrayList<TrainStation>();
        ArrayList<TrainStation> routeKRWWA = new ArrayList<TrainStation>();
        ArrayList<TrainStation> routeKRKTWA = new ArrayList<TrainStation>();

        routeKRWR.add(krakow);
        routeKRWR.add(katowice);
        routeKRWR.add(opole);
        routeKRWR.add(wroclaw);

        routeKRWWA.add(krakow);
        routeKRWWA.add(kielce);
        routeKRWWA.add(radom);
        routeKRWWA.add(warszawa);

        routeKRKTWA.add(krakow);
        routeKRKTWA.add(katowice);
        routeKRKTWA.add(radom);
        routeKRKTWA.add(warszawa);


        ArrayList<Integer> tt1 = new ArrayList<Integer>();
        tt1.add(6);
        tt1.add(7);
        tt1.add(9);
        tt1.add(10);

        ArrayList<Integer> tt2 = new ArrayList<Integer>();
        tt2.add(16);
        tt2.add(17);
        tt2.add(19);
        tt2.add(20);

        ArrayList<Integer> tt3 = new ArrayList<Integer>();
        tt3.add(11);
        tt3.add(12);
        tt3.add(13);
        tt3.add(14);

        Train pogorze = new Train("Pogorze", routeKRWR, tt1, TrainState.Scheduled, 70, 2);
        Train chelmonski = new Train("Chelmonski", routeKRWR, tt2, TrainState.Scheduled, 80, 2);
        Train lwow = new Train("Lwow-Ekspres", routeKRWR, tt3, TrainState.Scheduled, 76, 2);

        Train hancza = new Train("Hancza", routeKRWWA, tt1, TrainState.Delayed, 51, 2);
        Train zeromski = new Train("Zeromski", routeKRWWA, tt2, TrainState.Scheduled, 78, 2);
        Train sienkiewicz = new Train("Sienkiewicz", routeKRKTWA, tt3, TrainState.New, 64, 2);
        SesssionData.trainsContainer.add(pogorze);
        SesssionData.trainsContainer.add(chelmonski);
        SesssionData.trainsContainer.add(lwow);

        SesssionData.trainsContainer.add(hancza);
        SesssionData.trainsContainer.add(zeromski);
        SesssionData.trainsContainer.add(sienkiewicz);
    }

    @FXML
    void initialize() {
        if (!SesssionData.initStatus) {
            trainInit();
            SesssionData.initStatus = true;
        }

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
            for (TrainMatchedModel t : SesssionData.boughtTickesFor) {
                dataList.add(t);
            }
            clmnname.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, String>("name"));
            clmnArrival.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("arrivalTime"));
            clmnDeparture.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("departureTime"));
            clmnTime.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("travelTime"));
            clmnCost.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("ticketCost"));
            tableTickets.setItems(dataList);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void buttonSearchOnClick(javafx.event.ActionEvent event) throws IOException {
        ArrayList<TrainMatchedModel> trainMatch = new ArrayList<TrainMatchedModel>();

        String hourStr = choiceboxHour.getValue().toString();
        String to = txtTo.getText();
        String from = txtFrom.getText();
        Boolean found = false;
        int hour = -1;
        if (!hourStr.equals("DOWOLNA")) {
            hour = parseInt(hourStr);
        }

        for (TrainMatchedModel t : searchConnections2(from, to, hour, SesssionData.trainsContainer)) {
            trainMatch.add(t);
            found = true;
        }

        if (!found) {
            Alert a1 = new Alert(Alert.AlertType.WARNING, "Brak pociągów o podanej trasie i godzinie", ButtonType.OK);
            a1.show();
        } else {
            SesssionData.matchTrains = trainMatch;

            Parent blah = FXMLLoader.load(getClass().getResource("/TrainConnections.fxml"));
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
            for (var t : SesssionData.trainsContainer.trainList) {
                if (p.getName() == t.getName()) {
                    tableTickets.getItems().remove(p);
                    SesssionData.boughtTickesFor.remove(p);
                    t.increaseCapacity();
                    Alert a1 = new Alert(Alert.AlertType.CONFIRMATION, "Bilet został anulowany", ButtonType.OK);
                    a1.show();
                }
            }
        }
    }
}
