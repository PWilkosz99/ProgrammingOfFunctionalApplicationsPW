module TrainsGUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.persistence;
    requires mysql.connector.java;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires spring.web;


    opens TrainPlannerSerial;
    opens TrainPassengerGUI;
    opens TrainFinderDB;
    opens TrainModel;
    exports TrainPassengerGUI;
    exports TrainFinderDB;
    exports entity;
}