package controller;

import model.DoctorSchedule;
import serviceLayer.DoctorScheduleService;

import java.sql.ResultSet;

public class DoctorScheduleController {
    DoctorSchedule ObjDoctorSchedule;
    DoctorScheduleService ObjDoctorScheduleService;

    public DoctorScheduleController(){
        ObjDoctorScheduleService = new DoctorScheduleService();
    }

    public DoctorSchedule addDoctorSchedule(int did, String name,String specialization, String day, String time) {
        ObjDoctorSchedule = new DoctorSchedule(did, name, specialization, day, time);
        return ObjDoctorSchedule;
    }

    public boolean addDoctorScheduleToDataBase() {
        return ObjDoctorScheduleService.addDoctorSchedule(ObjDoctorSchedule);
    }

    public ResultSet getAllSchedule() {
        ResultSet result = ObjDoctorScheduleService.getAllSchedule();
        return result;
    }

}
