package TrainPlannerDB;

import entity.EntityUtil;
import entity.RatingEntity;
import entity.StationsEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlannerMenuDB {
    private JTable tableStation;
    private JPanel panelMenu;
    private JButton btnAddStation;
    private JButton btnSortStation;
    private JButton btnDeleteStation;
    private JLabel txtInfo;
    private JTextField txtSearchStation;
    private JButton btnRate;
    private JButton btnSave;
    static DefaultTableModel model;

    private static final String CSV_SEPARATOR = ",";

    static List<StationsEntity> stationsList;
    static List<TrainTableDB> trainTableslist;


    public StationsEntity findByStationName(String name) {
        return stationsList.stream().filter(station -> name.equals(station.getName())).findFirst().orElse(null);
    }

    public static void RefreshData(int capacity, StationsEntity station) {
        int index = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (String.valueOf(model.getValueAt(i, 0)).equals(station.getName())) {
                index = i;
                break;
            }
        }
        model.setValueAt(capacity, index, 2);
    }

    public static void RefreshTable(){
        model.getDataVector().removeAllElements();
        try {
            stationsList = EntityUtil.loadStationsDB();
            for (var s : stationsList) {
                Object[] row = new Object[4];
                row[0] = s.getName();
                row[1] = s.getCapacityLimit();
                row[2] = s.getCapacity();
                double mean = EntityUtil.getRateMean(s.getId());
                if(mean>0){
                    row[3] = mean;
                }else{
                    row[3]= "N/A";
                }

                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Odczyt nieudany!\n" + e.toString(), "Zapis", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        model.fireTableDataChanged();
    }

    PlannerMenuDB() throws IOException {
        stationsList = new ArrayList<>();
        trainTableslist = new ArrayList<>();

        JFrame frame = new JFrame("Train management app");
        frame.setContentPane(panelMenu);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        Object[] columns = new Object[]{"Nazwa stacji", "Maksymalna pojemność", "Obecna pojemność", "Ocena"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        tableStation.setModel(model);



        try {
            stationsList = EntityUtil.loadStationsDB();
            for (var s : stationsList) {
                Object[] row = new Object[4];
                row[0] = s.getName();
                row[1] = s.getCapacityLimit();
                row[2] = s.getCapacity();
                double mean = EntityUtil.getRateMean(s.getId());
                if(mean>0){
                    row[3] = mean;
                }else{
                    row[3]= "N/A";
                }

                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(frame, "Odczyt nieudany!\n" + e.toString(), "Zapis", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }

        btnAddStation.addActionListener(e -> {
            Object[] row = new Object[3];
            row[0] = JOptionPane.showInputDialog("Podaj nazwę stacji");
            row[1] = JOptionPane.showInputDialog("Podaj maksymalną pojemność stacji");
            row[2] = 0;
            if (row[0].equals("") || row[1].equals("")) {
                JOptionPane.showMessageDialog(null, "Wykryto puste pole");
            } else {
                String name = String.valueOf(row[0]);
                int cap = Integer.parseInt(String.valueOf(row[1]));
                StationsEntity newStation = new StationsEntity(name, 0, cap);
                EntityUtil.addToDB(newStation);
                stationsList.add(newStation);
                model.addRow(row);
            }
        });

        btnDeleteStation.addActionListener(e -> {
            int selectedIndex = tableStation.getSelectedRow();
            if (selectedIndex >= 0) {
                if (JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć stacje " + model.getValueAt(selectedIndex, 0) + "?", "OSTRZEŻENIE",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    StationsEntity actStation = findByStationName(String.valueOf(model.getValueAt(selectedIndex, 0)));
                    model.removeRow(selectedIndex);
                    stationsList.remove(actStation);
                    EntityUtil.deleteFromDB(actStation);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Proszę wybrac stację");
            }
        });

        btnRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tableStation.getSelectedRow();
                if (selectedIndex >= 0) {
                    StationsEntity actStation = findByStationName(String.valueOf(model.getValueAt(selectedIndex, 0)));
                    int id = actStation.getId();
                    int rate = Integer.parseInt(JOptionPane.showInputDialog("Podaj ocenę dla stacji " + actStation.getName() + " (0-5)"));
                    EntityUtil.addToDB(new RatingEntity(rate, id));
                    RefreshTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Proszę wybrac stację");
                }
            }
        });

        btnSortStation.addActionListener(e -> {
            Comparator<StationsEntity> compareByCapacity = (StationsEntity t1, StationsEntity t2) -> Integer.compare(t1.getCapacity(), t2.getCapacity());
            stationsList.sort(compareByCapacity);
            for (StationsEntity ts : stationsList) {
                model.removeRow(0);
                Object[] row = {ts.getName(), ts.getCapacityLimit(), ts.getCapacity()};
                model.addRow(row);
            }
        });

        tableStation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                trainTableslist.add(new TrainTableDB(findByStationName(String.valueOf(model.getValueAt(tableStation.getSelectedRow(), 0)))));
            }
        });

        txtSearchStation.addActionListener(e -> {
            DefaultTableModel Model = (DefaultTableModel) tableStation.getModel();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(Model);
            tableStation.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(txtSearchStation.getText().trim()));
        });
    }

    public static void main(String[] args) throws IOException {
        new PlannerMenuDB();
    }
}

