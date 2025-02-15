package controller;

import model.Patient;
import serviceLayer.PatientService;

import java.sql.ResultSet;

public class PatientController {
    Patient ObjPatient;

    PatientService ObjPatientService;

    public PatientController() {
        ObjPatientService = new PatientService();
    }

    public Patient addPatient(int pid, String fullName, String email, String gender, String phone, String address) {
        ObjPatient = new Patient(pid, fullName, gender, email, phone, address);
        return ObjPatient;
    }

    public boolean addPatientToDataBase() {
        return ObjPatientService.addPatient(ObjPatient);
    }

    public ResultSet getPatientByEmail(String email) {
        return ObjPatientService.getPatientByEmail(email);
    }
}
