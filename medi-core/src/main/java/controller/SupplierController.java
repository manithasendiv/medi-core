package controller;

import model.Supplier;
import serviceLayer.SupplierService;

import java.sql.ResultSet;

public class SupplierController {

    Supplier ObjSupplier;

    SupplierService ObjSupplierService;

    public SupplierController(){
        ObjSupplierService = new SupplierService();
    }

    public void addSupplier(int id, String supplier_name, String supplier_email) {
        ObjSupplier = new Supplier(id, supplier_name, supplier_email);
    }

    public boolean addSupplierToDB() {
        return ObjSupplierService.addSupplier(ObjSupplier);
    }

    public boolean removeSupplier(int id){
        return ObjSupplierService.removeSupplier(id);
    }

    public ResultSet getSuppliers() {
        return ObjSupplierService.getSuppliers();
    }

    public ResultSet sendSupplierEmail() {
        return ObjSupplierService.sendSupplierEmail();
    }
}
