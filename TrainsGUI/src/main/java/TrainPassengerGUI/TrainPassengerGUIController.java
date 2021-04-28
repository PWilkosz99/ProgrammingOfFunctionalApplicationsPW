package TrainPassengerGUI;

import TrainModel.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class TrainPassengerGUIController {

    TrainsContainer trainsContainer;

    @FXML
    private Button buttonSearch;
    public TextField txtFrom;
    public TextField txtTo;
    public ChoiceBox choiceboxHour;

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
                        System.out.println(t.stationList.get(i));
                    }
                } else {
                    if (t.stationList.get(i).toString().equals(from) && t.timeTableList.get(i).equals(hour)) {
                        hit = true;
                        h1 = i;
                        System.out.println(t.stationList.get(i));
                    }
                }

                if (hit) {
                    if (t.stationList.get(i).toString().equals(to)) {
                        connection = true;
                        h2 = i;
                        System.out.println(t.stationList.get(i));
                        break;
                    }
                }
            }
            if (connection) {
                connections.add(new TrainMatchedModel(t.getName(), t.timeTableList.get(h1), t.timeTableList.get(h2), t.timeTableList.get(h2) - t.timeTableList.get(h1), t.getTicketCost()));
            }
        }
        return connections;
    }

    @FXML
    void initialize() {
        trainsContainer = new TrainsContainer();
        TrainStation krakow = new TrainStation("Krakow");
        TrainStation warszawa = new TrainStation("Warszawa");
        TrainStation katowice = new TrainStation("Katowice");
        ArrayList<TrainStation> route1 = new ArrayList<TrainStation>();
        ArrayList<TrainStation> route2 = new ArrayList<TrainStation>();
        route1.add(krakow);
        route1.add(warszawa);
        route1.add(katowice);
        route2.add(krakow);
        route2.add(warszawa);

        ArrayList<Integer> tt1 = new ArrayList<Integer>();
        tt1.add(2);
        tt1.add(3);
        tt1.add(4);

        ArrayList<Integer> tt2 = new ArrayList<Integer>();
        tt2.add(12);
        tt2.add(14);

        Train train = new Train("train1", route1, tt1, TrainState.New, 10);
        Train train2 = new Train("train2", route2, tt2, TrainState.Delayed, 20);
        trainsContainer.add(train);
        trainsContainer.add(train2);


        choiceboxHour.setValue("DOWOLNA");
        choiceboxHour.getItems().add("DOWOLNA");
        for (int i = 0; i < 24; i++) {
            choiceboxHour.getItems().add(i);
        }
    }

    public void buttonSearchOnClick(javafx.event.ActionEvent event) throws IOException {
        ArrayList<TrainMatchedModel> trainMatch = new ArrayList<TrainMatchedModel>();

        String hourStr = choiceboxHour.getValue().toString();
        String to = txtTo.getText();
        String from = txtFrom.getText();
        int hour = -1;
        if (!hourStr.equals("DOWOLNA")) {
            hour = parseInt(hourStr);
            System.out.println(hour);
        }

        for (TrainMatchedModel t : searchConnections2(from, to, hour, trainsContainer)) {
            trainMatch.add(t);
        }

        SesssionData.matchTrains = trainMatch;

        Parent blah = FXMLLoader.load(getClass().getResource("/TrainConnections.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
