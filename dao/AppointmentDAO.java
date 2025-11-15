package dao;

import model.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private Connection connection;

    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO Appointment (doctorID, patientID, patientFullName, appointmentTime, appointmentDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getDoctorID());
            stmt.setInt(2, appointment.getPatientID());
            stmt.setString(3, appointment.getPatientFullName());
            stmt.setString(4, appointment.getAppointmentTime());
            stmt.setString(5, appointment.getAppointmentDate());
            stmt.setString(6, appointment.getStatus());
            stmt.executeUpdate();
        }
    }

    public Appointment getAppointmentById(int appointmentID) throws SQLException {
        String sql = "SELECT * FROM Appointment WHERE appointmentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointmentID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAppointment(rs);
            }
        }
        return null;
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByDoctorId(int doctorID) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE doctorID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByPatientId(int patientID) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE patientID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

    public void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE Appointment SET doctorID = ?, patientID = ?, patientFullName = ?, appointmentTime = ?, appointmentDate = ?, status = ? WHERE appointmentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getDoctorID());
            stmt.setInt(2, appointment.getPatientID());
            stmt.setString(3, appointment.getPatientFullName());
            stmt.setString(4, appointment.getAppointmentTime());
            stmt.setString(5, appointment.getAppointmentDate());
            stmt.setString(6, appointment.getStatus());
            stmt.setInt(7, appointment.getAppointmentID());
            stmt.executeUpdate();
        }
    }

    public void deleteAppointment(int appointmentID) throws SQLException {
        String sql = "DELETE FROM Appointment WHERE appointmentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointmentID);
            stmt.executeUpdate();
        }
    }

    private Appointment mapResultSetToAppointment(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment(rs.getInt("doctorID"), rs.getInt("patientID"),
                rs.getString("patientFullName"), rs.getString("appointmentTime"),
                rs.getString("appointmentDate"), rs.getString("status"));
        appointment.setAppointmentID(rs.getInt("appointmentID"));
        return appointment;
    }

    public List<Appointment> getAppointmentsByDate(String appointmentDate) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE appointmentDate = ? ORDER BY appointmentTime";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, appointmentDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByDateRange(String startDate, String endDate) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE appointmentDate BETWEEN ? AND ? ORDER BY appointmentDate, appointmentTime";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByDoctorIdAndDate(int doctorID, String appointmentDate) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE doctorID = ? AND appointmentDate = ? ORDER BY appointmentTime";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorID);
            stmt.setString(2, appointmentDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByStatus(String status) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE status = ? ORDER BY appointmentDate DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByPatientIdAndDateRange(int patientID, String startDate, String endDate) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE patientID = ? AND appointmentDate BETWEEN ? AND ? ORDER BY appointmentDate";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientID);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }
        return appointments;
    }

}