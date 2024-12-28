package view;

import model.Patient;
import controller.PatientController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PatientUI {
    private JPanel panel1;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textContactNumber;
    private JTextField textAddress;
    private JButton addPatientBtn;

    Patient ObjPatient;
    PatientController objController;

    Patient[] patientArray;

    ArrayList<Patient> patientList;
    int patientCount;

    public PatientUI() {
        objController = new PatientController();
        patientArray = new Patient[100];
        patientList = new ArrayList<>();
        patientCount = 0;

        addPatientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pid = patientCount + 1;
                String name = textName.getText();
                String email = textEmail.getText();
                String contactNumber = textContactNumber.getText();
                String address = textAddress.getText();

                ObjPatient = objController.addPatient(pid, name, email, contactNumber, address);
                if (!objController.addPatientToDataBase()) {
                    JOptionPane.showMessageDialog(null, "Error in adding patient");
                    return;
                }

                patientArray[patientCount] = ObjPatient;
                patientCount++;

                JOptionPane.showMessageDialog(null, "Patient added successfully!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PatientUI");
        frame.setSize(300, 300);
        frame.setContentPane(new PatientUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
