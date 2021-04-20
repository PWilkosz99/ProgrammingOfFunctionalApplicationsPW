import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Menu {
    private JTable tableStation;
    private JPanel panelMenu;
    private JButton btnAddStation;
    private JButton btnSortStation;
    private JButton btnDeleteStation;
    static DefaultTableModel model;

    static List<TrainStation> stationsList;


    public TrainStation findByStationName(String name) {
        return stationsList.stream().filter(station -> name.equals(station.getName())).findFirst().orElse(null);
    }

    Menu() {
        stationsList = new ArrayList<>();

        JFrame frame = new JFrame("Train management app");
        frame.setContentPane(panelMenu);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);


        Object[] columns = new Object[]{"Nazwa stacji", "Maksymalna pojemność", "Obecna pojemność"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        tableStation.setModel(model);



        btnAddStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] row = new Object[3];
                row[0] = JOptionPane.showInputDialog("Podaj nazwę stacji");
                row[1] = JOptionPane.showInputDialog("Podaj maksymalną pojemność stacji");
                row[2] = 0;
                if (row[0].equals("") || row[1].equals("")) {
                    JOptionPane.showMessageDialog(null, "Wykryto puste pole");
                } else {
                    String name = String.valueOf(row[0]);
                    int cap = Integer.parseInt(String.valueOf(row[1]));
                    stationsList.add(new TrainStation(name, cap));
                    model.addRow(row);
                }
            }
        });

        btnDeleteStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tableStation.getSelectedRow();
                if (selectedIndex >= 0) {
                    if (JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć stacje " + String.valueOf(model.getValueAt(selectedIndex, 0)) + "?", "OSTRZEŻENIE",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        TrainStation actStation = findByStationName(String.valueOf(model.getValueAt(selectedIndex, 0)));
                        model.removeRow(selectedIndex);
                        stationsList.remove(actStation);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Proszę wybrac stację");
                }
            }
        });

        btnSortStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comparator<TrainStation> compareByCapacity = (TrainStation t1, TrainStation t2) -> Integer.compare(t1.getCapacity(), t2.getCapacity());
                stationsList.sort(compareByCapacity);
                for (TrainStation ts : stationsList) {
                    model.removeRow(0);
                    Object[] row = {ts.getName(), ts.getCapacityLimit(), ts.getCapacity()};
                    model.addRow(row);
                }
            }
        });
    }


    public static void main(String[] args) {
        new Menu();
    }
}

