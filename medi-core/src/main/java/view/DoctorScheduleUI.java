package view;

import controller.DoctorScheduleController;
import model.DoctorSchedule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
