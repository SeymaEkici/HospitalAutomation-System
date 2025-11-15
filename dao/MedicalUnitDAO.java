package dao;

import model.MedicalUnit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalUnitDAO {
    private Connection connection;
    
    public MedicalUnitDAO(Connection connection) {
        this.connection = connection;
    }
    
    public MedicalUnit getUnitById(int id) throws SQLException {
        String query = "SELECT * FROM Medical_Unit WHERE unit_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractUnitFromResultSet(rs);
            }
        }
        return null;
    }
    
    public List<MedicalUnit> getAllUnits() throws SQLException {
        List<MedicalUnit> units = new ArrayList<>();
        String query = "SELECT * FROM Medical_Unit ORDER BY unit_name";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                units.add(extractUnitFromResultSet(rs));
            }
        }
        return units;
    }
    
    public void addUnit(MedicalUnit unit) throws SQLException {
        String query = "INSERT INTO Medical_Unit (unit_name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unit.getUnitName());
            stmt.executeUpdate();
        }
    }
    
    public void updateUnit(MedicalUnit unit) throws SQLException {
        String query = "UPDATE Medical_Unit SET unit_name = ? WHERE unit_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, unit.getUnitName());
            stmt.setInt(2, unit.getUnitID());
            stmt.executeUpdate();
        }
    }
    
    public void deleteUnit(int id) throws SQLException {
        String query = "DELETE FROM Medical_Unit WHERE unit_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private MedicalUnit extractUnitFromResultSet(ResultSet rs) throws SQLException {
        MedicalUnit unit = new MedicalUnit();
        unit.setUnitID(rs.getInt("unit_id"));
        unit.setUnitName(rs.getString("unit_name"));
        return unit;
    }
}