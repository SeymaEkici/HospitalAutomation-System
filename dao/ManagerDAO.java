package dao;

import model.Manager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {
    private Connection connection;

    public ManagerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addManager(Manager manager) throws SQLException {
        String sql = "INSERT INTO Manager (employeeID) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, manager.getEmployeeID());
            stmt.executeUpdate();
        }
    }

    public Manager getManagerById(int managerID) throws SQLException {
        String sql = "SELECT * FROM Manager WHERE managerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Manager(rs.getInt("managerID"), rs.getInt("employeeID"));
            }
        }
        return null;
    }

    public List<Manager> getAllManagers() throws SQLException {
        List<Manager> managers = new ArrayList<>();
        String sql = "SELECT * FROM Manager";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                managers.add(new Manager(rs.getInt("managerID"), rs.getInt("employeeID")));
            }
        }
        return managers;
    }

    public void updateManager(Manager manager) throws SQLException {
        String sql = "UPDATE Manager SET employeeID = ? WHERE managerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, manager.getEmployeeID());
            stmt.setInt(2, manager.getManagerID());
            stmt.executeUpdate();
        }
    }

    public void deleteManager(int managerID) throws SQLException {
        String sql = "DELETE FROM Manager WHERE managerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerID);
            stmt.executeUpdate();
        }
    }
}