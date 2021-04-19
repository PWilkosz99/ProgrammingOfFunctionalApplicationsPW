import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Menu {
    private JTable tableStation;
    private JPanel panelMenu;
    static DefaultTableModel model;

    Menu(){
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


        Object[] row = new Object[3];
        row[0] = "STATION NAME";
        row[1] = "3";
        row[2] = 0;
        String nazwa = String.valueOf(row[0]);
        int max = Integer.parseInt(String.valueOf(row[1]));
        //listOfTrainStations.add(new TrainStation(nazwa,max));
        model.addRow(row);
        model.addRow(row);
        model.addRow(row);
        model.addRow(row);
    }




    public static void main(String[] args) {
        new Menu();
    }

}

