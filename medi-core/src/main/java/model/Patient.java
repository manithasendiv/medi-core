package model;

public class Patient {
    private int pid;
    private String fullName;
    private String email;
    private String phone;
    private String address;

    public Patient(int pid, String fullName, String email, String phone, String address) {
        this.pid = pid;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getPid() {
        return pid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
