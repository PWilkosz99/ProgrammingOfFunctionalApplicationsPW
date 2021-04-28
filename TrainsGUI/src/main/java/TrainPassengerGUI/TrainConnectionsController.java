package TrainPassengerGUI;

import TrainModel.Train;
import TrainModel.TrainMatchedModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class TrainConnectionsController {

    @FXML
    public Button btnBuy;
    @FXML
    public TextField txtSearch;
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

    public TrainConnectionsController() { }

    @FXML
    void initialize() {
        for (TrainMatchedModel t : SesssionData.matchTrains) {
            dataList.add(t);
        }
        clmnname.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, String>("name"));
        clmnArrival.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("arrivalTime"));
        clmnDeparture.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("departureTime"));
        clmnTime.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("travelTime"));
        clmnCost.setCellValueFactory(new PropertyValueFactory<TrainMatchedModel, Integer>("ticketCost"));
        FilteredList<TrainMatchedModel> filteredata = new FilteredList<>(dataList, e-> true);
        tableMatchedTrains.setItems(dataList);
        int i = 0;
        for (TableColumn<TrainMatchedModel, ?> trainMatchedModelTableColumn : tableMatchedTrains.getColumns()) {
            addTooltipToColumnCells(trainMatchedModelTableColumn, i);
            i++;
        }
    }

    private <T> void addTooltipToColumnCells(TableColumn<TrainMatchedModel,T> column, int i) {

        Callback<TableColumn<TrainMatchedModel, T>, TableCell<TrainMatchedModel,T>> existingCellFactory
                = column.getCellFactory();

        column.setCellFactory(c -> {
            TableCell<TrainMatchedModel, T> cell = existingCellFactory.call(c);

            Tooltip tooltip = new Tooltip();
            if(i==0){
                tooltip.setText("Pociąg spółki PKP Intercity");
            }else if(i==4){
                tooltip.setText("PLN");
            }else{
                tooltip.setText("CEST(GMT+2)");
            }

            cell.setTooltip(tooltip);
            return cell ;
        });
    }

    public void buttonOnClickBack(ActionEvent event) throws IOException {
        SesssionData.matchTrains = null;
        Parent blah = FXMLLoader.load(getClass().getResource("/PassengerMenu.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void buttonOnClickBuy(ActionEvent actionEvent) throws IOException {

        var p = tableMatchedTrains.getSelectionModel().getSelectedItem();
        if (p != null) {
            if (p.capacity > 0) {
                SesssionData.boughtTickesFor.add(p);
                SesssionData.bought = true;
                SesssionData.lastBoughtName = p.getName();
                SesssionData.matchTrains = null;

                Alert a1 = new Alert(Alert.AlertType.CONFIRMATION, "Zakup biletu udany", ButtonType.OK);
                a1.show();


                Parent blah = FXMLLoader.load(getClass().getResource("/PassengerMenu.fxml"));
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } else {
                Alert a2 = new Alert(Alert.AlertType.WARNING, "Brak wolnych miejsc na wybrany pociąg", ButtonType.OK);
                a2.show();
            }
        } else {
            Alert a1 = new Alert(Alert.AlertType.WARNING, "Prosze wybrać poprawny pociąg", ButtonType.OK);
            a1.show();
        }

    }

    public void OnMousePressedMatchedTrains(MouseEvent mouseEvent) {
        ObservableList<TablePosition> selectedCells = tableMatchedTrains.getSelectionModel().getSelectedCells() ;
        selectedCells.addListener((ListChangeListener.Change<? extends TablePosition> change) -> {
            Boolean clicked=false;
            if (selectedCells.size() > 0) {
                if(!clicked){
                    TablePosition selectedCell = selectedCells.get(0);
                    var firstCol = tableMatchedTrains.getColumns().get(0);
                    TableColumn column = selectedCell.getTableColumn();
                    int rowIndex = selectedCell.getRow();
                    Object data = firstCol.getCellObservableValue(rowIndex).getValue();

                    Train properTrain = null;
                    for (var t: SesssionData.trainsContainer.trainList) {
                        if(t.getName().equals(data.toString())){
                            properTrain=t;
                            break;
                        }
                    }
                    String stops = "";
                    for (int i = 1; i < properTrain.stationList.size()-1; i++) {
                        stops+=(properTrain.stationList.get(i).toString() + " ");
                    }

                    String trainInfo="Pocig spółki PKP Intercity "+properTrain.getName()+"\nZ stacji: "+properTrain.stationList.get(0).toString()+"\nDo stacji: "+
                    properTrain.stationList.get(properTrain.stationList.size()-1).toString()+"\nStacje posśrednie:" + stops+"\nStatus:"+properTrain.getTrainState();
                    Alert a1 = new Alert(Alert.AlertType.INFORMATION, trainInfo, ButtonType.OK);
                    a1.show();
                    clicked=true;
                }
            }
        });
    }
}


