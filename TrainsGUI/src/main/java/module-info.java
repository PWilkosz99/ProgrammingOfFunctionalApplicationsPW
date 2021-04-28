module TrainsGUI {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;


    opens TrainPlannerGUI;
    opens TrainPassengerGUI;
    exports TrainPassengerGUI;
}