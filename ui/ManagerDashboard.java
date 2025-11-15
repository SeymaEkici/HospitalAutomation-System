package ui;

import dao.*;
import model.*;
import util.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ManagerDashboard extends JFrame {
    private Employee employee;
    private JTabbedPane tabbedPane;
    
    public ManagerDashboard(Employee employee) {
        this.employee = employee;
        setTitle("Manager Dashboard - " + employee.getFull_name());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Manager Dashboard - " + employee.getFull_name());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Appointments", createAppointmentsPanel());
        tabbedPane.addTab("Doctors", createDoctorsPanel());
        tabbedPane.addTab("Patients", createPatientsPanel());
        tabbedPane.addTab("Schedules", createSchedulesPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel createAppointmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        String[] columns = {"ID", "Patient", "Doctor ID", "Date", "Time", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        // Load appointments
        try {
            AppointmentDAO dao = new AppointmentDAO(DatabaseConnection.getConnection());
            List<Appointment> appointments = dao.getAllAppointments();
            for (Appointment apt : appointments) {
                tableModel.addRow(new Object[]{
                    apt.getAppointmentID(),
                    apt.getPatientFullName(),
                    apt.getDoctorID(),
                    apt.getAppointmentDate(),
                    apt.getAppointmentTime(),
                    apt.getStatus()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            try {
                AppointmentDAO dao = new AppointmentDAO(DatabaseConnection.getConnection());
                List<Appointment> appointments = dao.getAllAppointments();
                for (Appointment apt : appointments) {
                    tableModel.addRow(new Object[]{
                        apt.getAppointmentID(),
                        apt.getPatientFullName(),
                        apt.getDoctorID(),
                        apt.getAppointmentDate(),
                        apt.getAppointmentTime(),
                        apt.getStatus()
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        panel.add(refreshButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createDoctorsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        String[] columns = {"Doctor ID", "Employee ID", "Unit ID", "Name"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        // Load doctors
        try {
            DoctorDAO doctorDAO = new DoctorDAO(DatabaseConnection.getConnection());
            EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnection.getConnection());
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            for (Doctor doc : doctors) {
                Employee emp = employeeDAO.getEmployeeById(doc.getEmployeeID());
                tableModel.addRow(new Object[]{
                    doc.getDoctorID(),
                    doc.getEmployeeID(),
                    doc.getUnitID(),
                    emp != null ? emp.getFull_name() : "N/A"
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPatientsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        String[] columns = {"Patient ID", "Name", "Birth Date", "Gender", "Contact"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        // Load patients
        try {
            PatientDAO dao = new PatientDAO(DatabaseConnection.getConnection());
            List<Patient> patients = dao.getAllPatients();
            for (Patient patient : patients) {
                tableModel.addRow(new Object[]{
                    patient.getPatientID(),
                    patient.getFullName(),
                    patient.getBirthDate(),
                    patient.getGender(),
                    patient.getContact_info()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createSchedulesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        String[] columns = {"Schedule ID", "Doctor ID", "Date", "Work Hours"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        // Load schedules
        try {
            DoctorScheduleDAO dao = new DoctorScheduleDAO(DatabaseConnection.getConnection());
            List<DoctorSchedule> schedules = dao.getAllSchedules();
            for (DoctorSchedule schedule : schedules) {
                tableModel.addRow(new Object[]{
                    schedule.getScheduleId(),
                    schedule.getDoctorId(),
                    schedule.getScheduleDate(),
                    schedule.getWorkHours()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private void logout() {
        this.dispose();
        new LoginFrame().setVisible(true);
    }
}