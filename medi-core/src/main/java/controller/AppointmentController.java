package controller;

import model.Appointment;
import model.DoctorSchedule;
import model.Patient;
import serviceLayer.AppointmentService;

public class AppointmentController {
    Appointment ObjAppointment;
    AppointmentService ObjAppointmentService;

    public AppointmentController() {
        ObjAppointmentService = new AppointmentService();
    }

    // return type method
    public Appointment addAppointment(int appointmentId, Patient patient, DoctorSchedule doctorSchedule, Double fee) {
        ObjAppointment = new Appointment(appointmentId, patient, doctorSchedule, fee);
        return ObjAppointment;
    }

    public boolean addAppointmentToDataBase() {
        return ObjAppointmentService.addAppointment(ObjAppointment);
    }
}
