package serviceLayer;
import databaseLayer.DatabaseConnection;
import model.Appointment;

public class AppointmentService {
    private DatabaseConnection singleConnection;

    public AppointmentService() {
        singleConnection = DatabaseConnection.getSingleInstance();
    }

    public boolean addAppointment(Appointment appointment) {
        try{
            String query = "INSERT INTO appointment(patientName, doctorName, date, time, fee) VALUES('" +appointment.getPatientName()+ "','"+appointment.getDoctorSchedule()+"','"+appointment.getDate()+"','"+appointment.getTime()+"','"+appointment.getFee()+"')";
            return singleConnection.ExecuteSQL(query);
        }
        catch(Exception e) {
            System.out.println("Error in adding appointment" + e.getMessage());
            return false;
        }
    }
}
