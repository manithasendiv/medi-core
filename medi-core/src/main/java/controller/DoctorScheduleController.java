package controller;

import model.DoctorSchedule;
import serviceLayer.DoctorScheduleService;

import java.sql.ResultSet;

public class DoctorScheduleController {
    public DoctorSchedule ObjDoctorSchedule;
    DoctorScheduleService ObjDoctorScheduleService;

    public DoctorScheduleController(){
        ObjDoctorScheduleService = new DoctorScheduleService();
    }

    public void addDoctorSchedule(int did, String name, String specialization, String day, String time) {
        ObjDoctorSchedule = new DoctorSchedule(did, name, specialization, day, time);
    }

    public boolean addDoctorScheduleToDataBase() {
        return ObjDoctorScheduleService.addDoctorSchedule(ObjDoctorSchedule);
    }

    public ResultSet getAllSchedule() {
        return ObjDoctorScheduleService.getAllSchedule();
    }

    public boolean removeDoctorSchedule(int sid) {
        return ObjDoctorScheduleService.removeDoctorSchedule(sid);
    }

    public boolean updateDoctorSchedule(DoctorSchedule doctorSchedule) {
        return ObjDoctorScheduleService.updateDoctorSchedule(doctorSchedule);
    }

}
