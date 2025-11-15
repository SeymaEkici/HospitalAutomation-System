package model;

public class Patient {
    
    private int patientID;
    private String fullName;
    private String birthDate;
    private String gender;
    private String contact_info;
    private String medical_info;

    public Patient() {}

    public Patient(int patientID, String fullName, String birthDate, String gender, String contact_info, String medical_info) {
        this.patientID = patientID;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.contact_info = contact_info;
        this.medical_info = medical_info;
    }

    public int getPatientID() {
        return patientID;
    }
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_info() {
        return contact_info;
    }
    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getMedical_info() {
        return medical_info;
    }
    public void setMedical_info(String medical_info) {
        this.medical_info = medical_info;
    }

    @Override
    public String toString() {
        return "Patient [patientID=" + patientID + ", fullName=" + fullName + ", birthDate=" + birthDate + ", gender=" + gender + ", contact_info=" + contact_info + ", medical_info=" + medical_info + "]";
    }
}