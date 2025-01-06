package view;

import controller.DoctorScheduleController;
import model.DoctorSchedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DoctorScheduleUI {
    private JPanel BackPanel;
    private JTextField textDoctorName;
    private JTextField textDoctorSpecialization;
    private JTextField textDate;
    private JTextField textTime;
    private JButton addScheduleButton;

    DoctorScheduleController ObjDoctorScheduleController;

    public DoctorScheduleUI(){
        ObjDoctorScheduleController = new DoctorScheduleController();
        int scheduleCount = 0;


        addScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sid = scheduleCount + 1;
                String doctorName = textDoctorName.getText();
                String doctorSpecialization = textDoctorSpecialization.getText();
                String date = textDate.getText();
                String time = textTime.getText();

                ObjDoctorScheduleController.addDoctorSchedule(sid, doctorName, doctorSpecialization, date, time);

                if (!ObjDoctorScheduleController.addDoctorScheduleToDataBase()) {
                    JOptionPane.showMessageDialog(null, "Error in adding schedule");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Schedule added successfully!");

                textDoctorName.setText("");
                textDoctorSpecialization.setText("");
                textDate.setText("");
                textTime.setText("");
            }
        });
        textDate.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (textDate.getText().isEmpty()) {
                    textDate.setText("yyyy-mm-dd");
                }
            }
        });
        textTime.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (textTime.getText().isEmpty()) {
                    textTime.setText("hh:mm:ss");
                }
            }
        });

        textDate.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (textDate.getText().equals("yyyy-mm-dd")) {
                    textDate.setText("");
                }
            }
        });
        textTime.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (textTime.getText().equals("hh:mm:ss")) {
                    textTime.setText("");
                }
            }
        });
    }

    public JPanel getPanel1(){
        return BackPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DoctorScheduleUI");
        frame.setContentPane(new DoctorScheduleUI().BackPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        textDoctorName = new CreateUIComponentDashboard.RoundedJTextField(20);
        textDoctorName.setFont(new Font("Arial", Font.PLAIN, 15));

        textDoctorSpecialization = new CreateUIComponentDashboard.RoundedJTextField(20);
        textDoctorSpecialization.setFont(new Font("Arial", Font.PLAIN, 15));

        textDate = new CreateUIComponentDashboard.RoundedJTextField(20);
        textDate.setFont(new Font("Arial", Font.PLAIN, 15));
        textDate.setText("yyyy-mm-dd");

        textTime = new CreateUIComponentDashboard.RoundedJTextField(20);
        textTime.setFont(new Font("Arial", Font.PLAIN, 15));
        textTime.setText("hh:mm:ss");

        addScheduleButton = new CreateUIComponentDashboard.CustomButton("Add Schedule");
        addScheduleButton.setMinimumSize(new Dimension(200, 35));

        ImageIcon addIcon = new ImageIcon("D:\\y1s2\\OOP\\group assignment\\Project\\medi-core\\src\\main\\java\\assets\\icons\\heart.png");
        Image iconImage = addIcon.getImage();
        Image newAddIcon = iconImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newAddIcon);
        addScheduleButton.setIcon(addIcon);

    }
}
