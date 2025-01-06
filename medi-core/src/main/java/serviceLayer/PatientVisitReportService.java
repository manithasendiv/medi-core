package serviceLayer;

import databaseLayer.DatabaseConnection;

import java.sql.ResultSet;

public class PatientVisitReportService {
    private DatabaseConnection singleConnection;

    public PatientVisitReportService() {
        singleConnection = DatabaseConnection.getSingleInstance();
    }

    public ResultSet getPatientRecords(){
        try{
            String query = "SELECT * FROM patients";
            singleConnection.setPreparedStatement(query);
            ResultSet result = singleConnection.ExecutePreparedStatement();
            return result;
        }
        catch(Exception e){
            System.out.println("Error in getting patient records" + e.getMessage());
            return null;
        }
    }
}
