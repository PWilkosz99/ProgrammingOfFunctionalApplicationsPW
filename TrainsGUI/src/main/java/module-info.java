module TrainsGUI {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    requires java.persistence;


    opens TrainPlannerGUI;
    opens TrainPassengerGUI;
    opens TrainModel;
    exports TrainPassengerGUI;
}