package TrainPassengerGUI;

import TrainModel.Train;
import TrainModel.TrainState;
import TrainModel.TrainStation;
import TrainModel.TrainsContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class TrainPassengerGUIController {

    TrainsContainer trainsContainer;

    @FXML
    private Button buttonSearch;
    public TextField txtFrom;
    public TextField txtTo;
    public ChoiceBox choiceboxHour;

    public TrainPassengerGUIController() {
        System.out.println("WORKING CNTRL");
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

        Train train = new Train("train1", route1, tt1, TrainState.New);
        Train train2 = new Train("train2", route2, tt2, TrainState.Delayed);
        trainsContainer.add(train);
        trainsContainer.add(train2);


        choiceboxHour.setValue("DOWOLNA");
        choiceboxHour.getItems().add("DOWOLNA");
        for (int i = 0; i < 24; i++) {
            choiceboxHour.getItems().add(i);
        }
    }

    public void buttonSearchOnClick() {

        String hourStr = choiceboxHour.getValue().toString();
        String to = txtTo.getText();
        String from = txtFrom.getText();
        int hour = -1;
        if (!hourStr.equals("DOWOLNA")) {
            hour = parseInt(hourStr);
            System.out.println(hour);
        }

        for (Train t: trainsContainer.searchConnections(from, to, hour)){
            System.out.println(t.getName());
        }
    }
}
