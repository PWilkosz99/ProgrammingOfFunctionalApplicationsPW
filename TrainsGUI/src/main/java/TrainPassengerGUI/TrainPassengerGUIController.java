package TrainPassengerGUI;

import TrainModel.TrainsContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import static java.lang.Integer.parseInt;


public class TrainPassengerGUIController {


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
        TrainsContainer trainsContainer = new TrainsContainer();







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

        System.out.println(txtFrom.getText());
        System.out.println(to);
    }
}
