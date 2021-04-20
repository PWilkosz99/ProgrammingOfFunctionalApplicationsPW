import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TrainTable {
    DefaultTableModel model;
    private JPanel panelTrains;
    private JTable tableTrains;
    private JButton btnAddTrain;
    private JButton btnDeleteTrain;

    List<Train> trainList;

    public static TrainState parseStringtoState(String sstate) {
        if (sstate == "Nowy") {
            return TrainState.New;
        } else if (sstate == "Opóźniony") {
            return TrainState.Delayed;
        } else if (sstate == "Planowy") {
            return TrainState.Scheduled;
        } else if (sstate == "Odowłany") {
            return TrainState.Cancelled;
        }
        return null;
    }

    TrainTable(TrainStation currentStation) {
        JFrame frame = new JFrame("Train management app");
        frame.setContentPane(panelTrains);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        Object[] columns = {"Nazwa pociagu", "Ilosc wagonow", "Ilosc miejsc", "Czas przejazdu", "Stan pociagu"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        tableTrains.setModel(model);

        for (Train train : currentStation.trainsList) {
            Object[] row = new Object[5];
            row[0] = train.getName();
            row[1] = train.getNumber();
            row[2] = train.getCapacity();
            row[3] = train.getTravelTime();
            row[4] = train.getTrainState().toString();
            model.addRow(row);
        }

        btnAddTrain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    currentStation.addTrain(new Train(name, cars, capacity, time, state));
                    cap++;
                    currentStation.setCapacity(cap);
                    Menu.RefreshData(cap, currentStation);
                    model.addRow(row);
                }
            }
        });
        btnDeleteTrain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tableTrains.getSelectedRow();
                if (selectedIndex >= 0) {
                    if (JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć pociąg " + String.valueOf(model.getValueAt(selectedIndex, 0)) + "?", "OSTRZEŻENIE",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        currentStation.deleteAllTrainsNamed(new Train(String.valueOf(model.getValueAt(selectedIndex, 0))));
                        model.removeRow(selectedIndex);

                        int cap = currentStation.getCapacity();
                        currentStation.setCapacity(cap);
                        Menu.RefreshData(cap, currentStation);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Proszę wybrac stację");
                }
            }
        });
    }
}
