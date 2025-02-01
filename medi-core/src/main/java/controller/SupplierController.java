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

    public Supplier addSupplier(int id, String supplier_name, String supplier_email) {
        ObjSupplier = new Supplier(id, supplier_name, supplier_email);
        return ObjSupplier;
    }

    public boolean addSupplierToDB() {
        return ObjSupplierService.addSupplier(ObjSupplier);
    }

    public boolean removeSupplier(int id){
        return ObjSupplierService.removeSupplier(id);
    }

    public ResultSet getSuppliers() {
        ResultSet result = ObjSupplierService.getSuppliers();
        return result;
    }

    public ResultSet sendSupplierEmail() {
        ResultSet result = ObjSupplierService.sendSupplierEmail();
        return result;
    }
}
