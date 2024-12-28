package serviceLayer;

import databaseLayer.DatabaseConnection;
import model.Patient;

public class PatientService {
    private DatabaseConnection singleConnection;

    public PatientService() {
        singleConnection = DatabaseConnection.getSingleInstance();
    }

    public boolean addPatient(Patient patient) {
        try{
            String query = "INSERT INTO patients(fullname, email, contact, address) VALUES('"+patient.getFullName()+"','" +patient.getEmail()+ "','"+patient.getPhone()+"','"+patient.getAddress()+"')";
            boolean result = singleConnection.ExecuteSQL(query);
            return result;
        }
        catch(Exception e) {
            System.out.println("Error in adding patient" + e.getMessage());
            return false;
        }
    }
//    another query same as this change code
//    public boolean addPatient(Patient patient) {
//        try{
//            String query = "INSERT INTO patient VALUES('"+patient.getFullName()+"','" +patient.getEmail()+ "','"+patient.getPhone()+"','"+patient.getAddress()+")";
//            boolean result = singleConnection.ExecuteSQL(query);
//            return result;
//        }
//        catch(Exception e) {
//            System.out.println("Error in adding patient" + e.getMessage());
//            return false;
//        }
//    }
}
