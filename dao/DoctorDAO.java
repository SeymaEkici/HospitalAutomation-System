package dao;

import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private Connection connection;
    
    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }
    
    public Doctor getDoctorById(int id) throws SQLException {
        String query = "SELECT * FROM Doctor WHERE doctor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractDoctorFromResultSet(rs);
            }
        }
        return null;
    }
    
    public Doctor getDoctorByEmployeeId(int employeeId) throws SQLException {
        String query = "SELECT * FROM Doctor WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractDoctorFromResultSet(rs);
            }
        }
        return null;
    }
    
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM Doctor";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                doctors.add(extractDoctorFromResultSet(rs));
            }
        }
        return doctors;
    }
    
    public List<Doctor> getDoctorsByUnitId(int unitId) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM Doctor WHERE unit_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, unitId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                doctors.add(extractDoctorFromResultSet(rs));
            }
        }
        return doctors;
    }
    
    public void addDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO Doctor (employee_id, unit_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctor.getEmployeeID());
            stmt.setInt(2, doctor.getUnitID());
            stmt.executeUpdate();
        }
    }
    
    public void updateDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE Doctor SET employee_id = ?, unit_id = ? WHERE doctor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctor.getEmployeeID());
            stmt.setInt(2, doctor.getUnitID());
            stmt.setInt(3, doctor.getDoctorID());
            stmt.executeUpdate();
        }
    }
    
    public void deleteDoctor(int id) throws SQLException {
        String query = "DELETE FROM Doctor WHERE doctor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private Doctor extractDoctorFromResultSet(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setDoctorID(rs.getInt("doctor_id"));
        doctor.setEmployeeID(rs.getInt("employee_id"));
        doctor.setUnitID(rs.getInt("unit_id"));
        return doctor;
    }
}