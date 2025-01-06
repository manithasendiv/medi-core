package serviceLayer;

import databaseLayer.DatabaseConnection;
import model.DoctorSchedule;

import java.sql.ResultSet;

public class DoctorScheduleService {
    private DatabaseConnection singleConnection;

    public DoctorScheduleService() {
        singleConnection = DatabaseConnection.getSingleInstance();
    }

    public boolean addDoctorSchedule(DoctorSchedule doctorSchedule) {
        try{
            String query = "INSERT INTO doctor_schedule(doctorName, doctorSpecialization, date, time) VALUES('"+doctorSchedule.getDoctorName()+"','"+doctorSchedule.getDoctorSpecialization()+"','"+doctorSchedule.getDate()+"','"+doctorSchedule.getTime()+"')";
            boolean result = singleConnection.ExecuteSQL(query);
            return result;
        }
        catch(Exception e) {
            System.out.println("Error in adding doctor schedule" + e.getMessage());
            return false;
        }
    }

    public ResultSet getAllSchedule() {
        try {
            String query = "SELECT * FROM doctor_schedule";
            singleConnection.setPreparedStatement(query);
            ResultSet result = singleConnection.ExecutePreparedStatement();
            return result;

        } catch (Exception e) {
            System.out.println("Error in getting all schedule" + e.getMessage());
            return null;
        }
    }
}

