package TrainPlannerDB;

import TrainModel.TrainState;
import entity.EntityUtil;
import entity.StationsEntity;
import entity.TrainsEntity;
import entity.TrainsonstationsEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public class TrainTableDB {
    DefaultTableModel model;
    private JPanel panelTrains;
    private JTable tableTrains;
    private JButton btnAddTrain;
    private JButton btnDeleteTrain;
    private JTextField txtSearchTrain;

    public static TrainState parseStringtoState(String sstate) {
        return switch (sstate) {
            case "Nowy" -> TrainState.New;
            case "New" -> TrainState.New;
            case "Opóźniony" -> TrainState.Delayed;
            case "Delayed" -> TrainState.Delayed;
            case "Planowy" -> TrainState.Scheduled;
            case "Scheduled" -> TrainState.Scheduled;
            case "Odowołany" -> TrainState.Cancelled;
            case "Cancelled" -> TrainState.Cancelled;
            default -> null;
        };
    }

    TrainTableDB(StationsEntity currentStation) {
        JFrame frame = new JFrame("TrainModel.Train management app");
        frame.setContentPane(panelTrains);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        Object[] columns = {"Nazwa pociagu", "Ilosc wagonow", "Ilosc miejsc", "Czas przejazdu", "Stan pociagu"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        tableTrains.setModel(model);

        List<TrainsEntity> trainsOnStation= new ArrayList<TrainsEntity>();

        try{
                trainsOnStation.addAll(EntityUtil.loadTrainsDB(currentStation.getId()));
        }catch (Exception e){
            System.out.println(e.toString());
        }

        if (trainsOnStation.size() > 0) {
            for (var train : EntityUtil.loadTrainsDB(currentStation.getId())) {
                Object[] row = new Object[5];
                row[0] = train.getName();
                row[1] = train.getCars();
                row[2] = train.getCapacity();
                row[3] = train.getTraveltime();
                row[4] = train.getState().toString();
                model.addRow(row);
            }
        }


        btnAddTrain.addActionListener(e -> {
            int cap = currentStation.getCapacity();
            int maxcap = currentStation.getCapacityLimit();
            if (cap >= maxcap) {
                JOptionPane.showMessageDialog(null, "Osiągnięto limit stacji!");
            } else {
                String[] states = new String[4];
                states[0] = "Nowy";
                states[1] = "Opóźniony";
                states[2] = "Planowy";
                states[3] = "Odowłany";

                Object[] row = new Object[5];
                row[0] = JOptionPane.showInputDialog("Podaj nazwę pociągu");
                row[1] = JOptionPane.showInputDialog("Podaj ilość wagonów w pociągu");
                row[2] = JOptionPane.showInputDialog("Podaj ilość miejsc pociagu");
                row[3] = JOptionPane.showInputDialog("Podaj czas przejazdu pociagu");
                row[4] = JOptionPane.showInputDialog(null, "Wybierz stan pociągu", "Wprowadzanie danych o pociągu", JOptionPane.QUESTION_MESSAGE, null, states, "New");


                String name = String.valueOf(row[0]);
                int cars = Integer.parseInt(String.valueOf(row[1]));
                int capacity = Integer.parseInt(String.valueOf(row[2]));
                int time = Integer.parseInt(String.valueOf(row[3]));
                TrainState state = parseStringtoState(row[4].toString());

                TrainsEntity newtrain = new TrainsEntity(name, cars, capacity, time, state);
                EntityUtil.addToDB(newtrain);
                EntityUtil.addToDB(new TrainsonstationsEntity(currentStation.getId(), newtrain.getId(), 0));
                cap++;
                EntityUtil.updateCapacity(currentStation, cap);
                PlannerMenuDB.RefreshData(cap, currentStation);
                model.addRow(row);
            }
        });
        btnDeleteTrain.addActionListener(e -> {
            int selectedIndex = tableTrains.getSelectedRow();
            if (selectedIndex >= 0) {
                if (JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć pociąg " + model.getValueAt(selectedIndex, 0) + "?", "OSTRZEŻENIE",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    EntityUtil.deleteTrainByName(String.valueOf(model.getValueAt(selectedIndex, 0)));
                    model.removeRow(selectedIndex);

                    int cap = currentStation.getCapacity();
                    EntityUtil.updateCapacity(currentStation, cap);
                    PlannerMenuDB.RefreshData(cap, currentStation);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Proszę wybrac stację");
            }
        });

        txtSearchTrain.addActionListener(e -> {
            DefaultTableModel Model = (DefaultTableModel) tableTrains.getModel();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(Model);
            tableTrains.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(txtSearchTrain.getText().trim()));
        });
    }
}
