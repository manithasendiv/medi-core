package controller;

import model.Patient;
import serviceLayer.PatientService;

public class PatientController {
    Patient ObjPatient;
    PatientService ObjPatientService;

    public PatientController() {
        ObjPatientService = new PatientService();
    }

    public Patient addPatient(int pid, String fullName, String email, String phone, String address) {
        ObjPatient = new Patient(pid, fullName, email, phone, address);
        return ObjPatient;
    }

    public boolean addPatientToDataBase() {
        return ObjPatientService.addPatient(ObjPatient);
    }
}
