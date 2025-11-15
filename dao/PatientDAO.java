package dao;

import model.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private Connection connection;
    
    public PatientDAO(Connection connection) {
        this.connection = connection;
    }
    
    public Patient getPatientById(int id) throws SQLException {
        String query = "SELECT * FROM Patient WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPatientFromResultSet(rs);
            }
        }
        return null;
    }
    
    public Patient getPatientByName(String name) throws SQLException {
        String query = "SELECT * FROM Patient WHERE full_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPatientFromResultSet(rs);
            }
        }
        return null;
    }
    
    public Patient getPatientByNameAndBirthDate(String name, String birthDate) throws SQLException {
        String query = "SELECT * FROM Patient WHERE full_name = ? AND birth_date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, birthDate);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPatientFromResultSet(rs);
            }
        }
        return null;
    }
    
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                patients.add(extractPatientFromResultSet(rs));
            }
        }
        return patients;
    }
    
    public void addPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (full_name, birth_date, gender, contact_info, medical_info) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getFullName());
            stmt.setString(2, patient.getBirthDate());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContact_info());
            stmt.setString(5, patient.getMedical_info());
            stmt.executeUpdate();
        }
    }
    
    public void updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE Patient SET full_name = ?, birth_date = ?, gender = ?, contact_info = ?, medical_info = ? WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getFullName());
            stmt.setString(2, patient.getBirthDate());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContact_info());
            stmt.setString(5, patient.getMedical_info());
            stmt.setInt(6, patient.getPatientID());
            stmt.executeUpdate();
        }
    }
    
    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM Patient WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private Patient extractPatientFromResultSet(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientID(rs.getInt("patient_id"));
        patient.setFullName(rs.getString("full_name"));
        patient.setBirthDate(rs.getString("birth_date"));
        patient.setGender(rs.getString("gender"));
        patient.setContact_info(rs.getString("contact_info"));
        patient.setMedical_info(rs.getString("medical_info"));
        return patient;
    }
}