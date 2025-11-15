package ui;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import model.Appointment;
import model.Doctor;
import model.Employee;
import util.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DoctorDashboard extends JFrame {
    private Employee employee;
    private Doctor doctor;
    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    
    public DoctorDashboard(Employee employee) {
        this.employee = employee;
        setTitle("Doctor Dashboard - " + employee.getFull_name());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        loadDoctorInfo();
        initComponents();
        loadAppointments();
    }
    
    private void loadDoctorInfo() {
        try {
            DoctorDAO doctorDAO = new DoctorDAO(DatabaseConnection.getConnection());
            doctor = doctorDAO.getDoctorByEmployeeId(employee.getEmployeeId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Doctor Dashboard - " + employee.getFull_name());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Appointments Table
        String[] columns = {"ID", "Patient Name", "Date", "Time", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        appointmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton completeButton = new JButton("Mark as Completed");
        completeButton.addActionListener(e -> markCompleted());
        buttonPanel.add(completeButton);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadAppointments());
        buttonPanel.add(refreshButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadAppointments() {
        if (doctor == null) {
            JOptionPane.showMessageDialog(this, "Doctor information not found!");
            return;
        }
        
        try {
            AppointmentDAO dao = new AppointmentDAO(DatabaseConnection.getConnection());
            List<Appointment> appointments = dao.getAppointmentsByDoctorId(doctor.getDoctorID());
            
            tableModel.setRowCount(0);
            for (Appointment apt : appointments) {
                tableModel.addRow(new Object[]{
                    apt.getAppointmentID(),
                    apt.getPatientFullName(),
                    apt.getAppointmentDate(),
                    apt.getAppointmentTime(),
                    apt.getStatus()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading appointments: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void markCompleted() {
        int selectedRow = appointmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment!");
            return;
        }
        
        int appointmentId = (int) tableModel.getValueAt(selectedRow, 0);
        
        try {
            AppointmentDAO dao = new AppointmentDAO(DatabaseConnection.getConnection());
            Appointment apt = dao.getAppointmentById(appointmentId);
            apt.setStatus("Completed");
            dao.updateAppointment(apt);
            
            JOptionPane.showMessageDialog(this, "Appointment marked as completed!");
            loadAppointments();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating appointment: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void logout() {
        this.dispose();
        new LoginFrame().setVisible(true);
    }
}