package controller;

import serviceLayer.ReportService;

import java.sql.ResultSet;

public class ReportController {
    ReportService reportService;

    public ReportController() {
        reportService = new ReportService();
    }

    public ResultSet findPatientDetails() {
        ResultSet resultSet = reportService.findPatientDetailsDB();
        return resultSet;
    }

    public ResultSet patientCount() {
        ResultSet resultSet = reportService.patientCountDB();
        return resultSet;
    }

    public ResultSet findAppointmentDetails() {
        ResultSet resultSet = reportService.findAppointmentDetailsDB();
        return resultSet;
    }

    public ResultSet appointmentCount() {
        ResultSet resultSet = reportService.appointmentCountDB();
        return resultSet;
    }

    public ResultSet medicineCount(){
        ResultSet resultSet = reportService.medicineCountDB();
        return resultSet;
    }

    public ResultSet appointmentRevenue() {
        ResultSet resultSet = reportService.revenueFromAppointmentsDB();
        return resultSet;
    }

    public ResultSet findInventoryDetails() {
        ResultSet resultSet = reportService.findInventoryDetailsDB();
        return resultSet;
    }

    public ResultSet costForInventory() {
        ResultSet resultSet = reportService.costOfInventoryDB();
        return resultSet;
    }

}
