package dao;

import model.DoctorSchedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorScheduleDAO {
    private Connection connection;
    
    public DoctorScheduleDAO(Connection connection) {
        this.connection = connection;
    }
    
    public DoctorSchedule getScheduleById(int id) throws SQLException {
        String query = "SELECT * FROM Doctor_Schedule WHERE schedule_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractScheduleFromResultSet(rs);
            }
        }
        return null;
    }
    
    public List<DoctorSchedule> getSchedulesByDoctorId(int doctorId) throws SQLException {
        List<DoctorSchedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Doctor_Schedule WHERE doctor_id = ? ORDER BY schedule_date";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                schedules.add(extractScheduleFromResultSet(rs));
            }
        }
        return schedules;
    }
    
    public List<DoctorSchedule> getAllSchedules() throws SQLException {
        List<DoctorSchedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Doctor_Schedule ORDER BY schedule_date";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                schedules.add(extractScheduleFromResultSet(rs));
            }
        }
        return schedules;
    }
    
    public void addSchedule(DoctorSchedule schedule) throws SQLException {
        String query = "INSERT INTO Doctor_Schedule (doctor_id, schedule_date, work_hours) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, schedule.getDoctorId());
            stmt.setString(2, schedule.getScheduleDate());
            stmt.setString(3, schedule.getWorkHours());
            stmt.executeUpdate();
        }
    }
    
    public void updateSchedule(DoctorSchedule schedule) throws SQLException {
        String query = "UPDATE Doctor_Schedule SET doctor_id = ?, schedule_date = ?, work_hours = ? WHERE schedule_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, schedule.getDoctorId());
            stmt.setString(2, schedule.getScheduleDate());
            stmt.setString(3, schedule.getWorkHours());
            stmt.setInt(4, schedule.getScheduleId());
            stmt.executeUpdate();
        }
    }
    
    public void deleteSchedule(int id) throws SQLException {
        String query = "DELETE FROM Doctor_Schedule WHERE schedule_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private DoctorSchedule extractScheduleFromResultSet(ResultSet rs) throws SQLException {
        DoctorSchedule schedule = new DoctorSchedule();
        schedule.setScheduleId(rs.getInt("schedule_id"));
        schedule.setDoctorId(rs.getInt("doctor_id"));
        schedule.setScheduleDate(rs.getString("schedule_date"));
        schedule.setWorkHours(rs.getString("work_hours"));
        return schedule;
    }
}