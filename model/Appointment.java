package model;

public class Appointment {
    
    private int appointmentID;
    private int patientID;
    private int doctorID;
    private String patientFullName;
    private String appointmentTime;
    private String appointmentDate;
    private String status;

    public Appointment() {}
    
    public Appointment(int doctorID, int patientID, String patientFullName, String appointmentTime, String appointmentDate, String status) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.patientFullName = patientFullName;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    public int getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getPatientID() {
        return patientID;
    }
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientFullName() {
        return patientFullName;
    }
    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment [appointmentID=" + appointmentID + ", patientID=" + patientID + ", doctorID=" + doctorID
                + ", patientFullName=" + patientFullName + ", appointmentTime=" + appointmentTime
                + ", appointmentDate=" + appointmentDate + ", status=" + status + "]";
    }
}