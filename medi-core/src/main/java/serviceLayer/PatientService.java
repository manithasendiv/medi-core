package serviceLayer;

import databaseLayer.DatabaseConnection;
import model.Patient;
import java.sql.ResultSet;

public class PatientService {
    private DatabaseConnection singleConnection;

    public PatientService() {
        singleConnection = DatabaseConnection.getSingleInstance();
    }

    public boolean addPatient(Patient patient) {
        try{
            String query = "INSERT INTO patients(fullname, gender, email, contact, address) VALUES('"+patient.getFullName()+ "','"+ patient.getGender() +"','" +patient.getEmail()+ "','"+patient.getPhone()+"','"+patient.getAddress()+"')";
            return singleConnection.ExecuteSQL(query);
        }
        catch(Exception e) {
            System.out.println("Error in adding patient" + e.getMessage());
            return false;
        }
    }

    public ResultSet getPatientByEmail(String email) {
        try {
            String query = "SELECT * FROM patients WHERE email = '" + email + "'";
            singleConnection.setPreparedStatement(query);
            return singleConnection.ExecutePreparedStatement();
        } catch (Exception e) {
            System.out.println("Error in getting patient by email" + e.getMessage());
            return null;
        }
    }

}
