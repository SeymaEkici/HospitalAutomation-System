package model;

public class Doctor {

    private int doctorID;
    private int employeeID;
    private int unitID;

    public Doctor() {}

    public Doctor(int doctorID, int employeeID, int unitID) {
        this.doctorID = doctorID;
        this.employeeID = employeeID;
        this.unitID = unitID;
    }

    public int getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getUnitID() {
        return unitID;
    }
    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    @Override
    public String toString() {
        return "Doctor [doctorID=" + doctorID + ", employeeID=" + employeeID + ", unitID=" + unitID + "]";
    }
}
