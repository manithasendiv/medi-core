package controller;

import serviceLayer.ReportService;

import java.sql.ResultSet;

public class ReportController {
    ReportService reportService;

    public ReportController() {
        reportService = new ReportService();
    }

    public ResultSet findPatientDetails() {
        return reportService.findPatientDetailsDB();
    }

    public ResultSet patientCount() {
        return reportService.patientCountDB();
    }

    public ResultSet findAppointmentDetails() {
        return reportService.findAppointmentDetailsDB();
    }

    public ResultSet appointmentCount() {
        return reportService.appointmentCountDB();
    }

    public ResultSet medicineCount(){
        return reportService.medicineCountDB();
    }

    public ResultSet appointmentRevenue() {
        return reportService.revenueFromAppointmentsDB();
    }

    public ResultSet findInventoryDetails() {
        return reportService.findInventoryDetailsDB();
    }

    public ResultSet costForInventory() {
        return reportService.costOfInventoryDB();
    }

}
