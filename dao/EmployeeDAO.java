package dao;

import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connection;
    
    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }
    
    public Employee getEmployeeById(int id) throws SQLException {
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }
        }
        return null;
    }
    
    public Employee getEmployeeByName(String name) throws SQLException {
        String query = "SELECT * FROM Employee WHERE full_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }
        }
        return null;
    }
    
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employees.add(extractEmployeeFromResultSet(rs));
            }
        }
        return employees;
    }
    
    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employee (full_name, password, role, contact_info) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, employee.getFull_name());
            stmt.setString(2, employee.getPassword());
            stmt.setString(3, employee.getRole());
            stmt.setString(4, employee.getContact_info());
            stmt.executeUpdate();
        }
    }
    
    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE Employee SET full_name = ?, password = ?, role = ?, contact_info = ? WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, employee.getFull_name());
            stmt.setString(2, employee.getPassword());
            stmt.setString(3, employee.getRole());
            stmt.setString(4, employee.getContact_info());
            stmt.setInt(5, employee.getEmployeeId());
            stmt.executeUpdate();
        }
    }
    
    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM Employee WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFull_name(rs.getString("full_name"));
        employee.setPassword(rs.getString("password"));
        employee.setRole(rs.getString("role"));
        employee.setContact_info(rs.getString("contact_info"));
        return employee;
    }
}