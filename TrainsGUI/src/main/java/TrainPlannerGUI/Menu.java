package TrainPlannerGUI;

import TrainModel.TrainStation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Menu {
    private JTable tableStation;
    private JPanel panelMenu;
    private JButton btnAddStation;
    private JButton btnSortStation;
    private JButton btnDeleteStation;
    private JLabel txtInfo;
    private JTextField txtSearchStation;
    static DefaultTableModel model;

    static List<TrainStation> stationsList;
    static List<TrainTable> trainTableslist;


    public TrainStation findByStationName(String name) {
        return stationsList.stream().filter(station -> name.equals(station.getName())).findFirst().orElse(null);
    }

    public static void RefreshData(int capacity, TrainStation station) {
        int index = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (String.valueOf(model.getValueAt(i, 0)).equals(station.getName())) {
                index = i;
                break;
            }
        }
        model.setValueAt(capacity, index, 2);
    }

    public void save() throws IOException {
        FileOutputStream fileOutputStream
                = new FileOutputStream("yourfile.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        for (var s:stationsList) {
            objectOutputStream.writeObject(s);
        }
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    Menu() {
        stationsList = new ArrayList<>();
        trainTableslist = new ArrayList<>();

        JFrame frame = new JFrame("TrainModel.Train management app");
        frame.setContentPane(panelMenu);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);


        Object[] columns = new Object[]{"Nazwa stacji", "Maksymalna pojemność", "Obecna pojemność"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        tableStation.setModel(model);


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
                stationsList.add(new TrainStation(name, cap));
                model.addRow(row);
            }
        });

        btnDeleteStation.addActionListener(e -> {
            int selectedIndex = tableStation.getSelectedRow();
            if (selectedIndex >= 0) {
                if (JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć stacje " + model.getValueAt(selectedIndex, 0) + "?", "OSTRZEŻENIE",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    TrainStation actStation = findByStationName(String.valueOf(model.getValueAt(selectedIndex, 0)));
                    model.removeRow(selectedIndex);
                    stationsList.remove(actStation);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Proszę wybrac stację");
            }
        });

        btnSortStation.addActionListener(e -> {
            Comparator<TrainStation> compareByCapacity = (TrainStation t1, TrainStation t2) -> Integer.compare(t1.getCapacity(), t2.getCapacity());
            stationsList.sort(compareByCapacity);
            for (TrainStation ts : stationsList) {
                model.removeRow(0);
                Object[] row = {ts.getName(), ts.getCapacityLimit(), ts.getCapacity()};
                model.addRow(row);
            }
        });
        tableStation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                trainTableslist.add(new TrainTable(findByStationName(String.valueOf(model.getValueAt(tableStation.getSelectedRow(), 0)))));
            }
        });
        txtSearchStation.addActionListener(e -> {
            DefaultTableModel Model = (DefaultTableModel) tableStation.getModel();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(Model);
            tableStation.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(txtSearchStation.getText().trim()));
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, "Zapisać wprowadzone dane?", "Zapis", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.out.println("SAVED");
                    try {
                        save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("DO NOTHING");
                }
            }
        });
    }


    public static void main(String[] args) {
        new Menu();
    }
}

