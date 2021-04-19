import javax.swing.*;
import java.awt.event.*;

public class Menu {
    private JButton btnAddTrain;
    private JPanel panelMain;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel title;

    public Menu() {
        JFrame frame = new JFrame("Train management app");
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        btnAddTrain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        new AddTrainForm();
            }
        });
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        /*JFrame frame = new JFrame("Train management app");
        frame.setContentPane(new Menu().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);*/
    }
}
