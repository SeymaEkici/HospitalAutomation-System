package ui;

import dao.*;
import model.*;
import util.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PatientDashboard extends JFrame {
    private Patient patient;
    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    private JButton bookButton;
    private JButton cancelButton;
    
    public PatientDashboard(Patient patient) {
        this.patient = patient;
        setTitle("Hasta Paneli - " + patient.getFullName());
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        loadAppointments();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Hoş Geldiniz, " + patient.getFullName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Çıkış");
        logoutButton.addActionListener(e -> logout());
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Hasta Bilgileri"));
        infoPanel.add(new JLabel("Ad Soyad: " + patient.getFullName()));
        infoPanel.add(new JLabel("Doğum Tarihi: " + patient.getBirthDate()));
        infoPanel.add(new JLabel("Cinsiyet: " + patient.getGender()));
        infoPanel.add(new JLabel("İletişim: " + patient.getContact_info()));
        
        // Appointments Table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Randevularım"));
        
        String[] columns = {"Randevu ID", "Doktor", "Birim", "Tarih", "Saat", "Durum"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        appointmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Split panel for info and table
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, infoPanel, tablePanel);
        splitPane.setDividerLocation(120);
        mainPanel.add(splitPane, BorderLayout.CENTER);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        bookButton = new JButton("Randevu Al");
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        bookButton.addActionListener(e -> bookAppointment());
        buttonPanel.add(bookButton);
        
        cancelButton = new JButton("Randevu İptal Et");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> cancelAppointment());
        buttonPanel.add(cancelButton);
        
        JButton refreshButton = new JButton("Yenile");
        refreshButton.addActionListener(e -> loadAppointments());
        buttonPanel.add(refreshButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadAppointments() {
        try {
            AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnection.getConnection());
            DoctorDAO doctorDAO = new DoctorDAO(DatabaseConnection.getConnection());
            EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnection.getConnection());
            MedicalUnitDAO unitDAO = new MedicalUnitDAO(DatabaseConnection.getConnection());
            
            List<Appointment> appointments = appointmentDAO.getAppointmentsByPatientId(patient.getPatientID());
            
            tableModel.setRowCount(0);
            for (Appointment apt : appointments) {
                Doctor doctor = doctorDAO.getDoctorById(apt.getDoctorID());
                String doctorName = "Bilinmiyor";
                String unitName = "Bilinmiyor";
                
                if (doctor != null) {
                    Employee emp = employeeDAO.getEmployeeById(doctor.getEmployeeID());
                    if (emp != null) {
                        doctorName = emp.getFull_name();
                    }
                    
                    MedicalUnit unit = unitDAO.getUnitById(doctor.getUnitID());
                    if (unit != null) {
                        unitName = unit.getUnitName();
                    }
                }
                
                tableModel.addRow(new Object[]{
                    apt.getAppointmentID(),
                    doctorName,
                    unitName,
                    apt.getAppointmentDate(),
                    apt.getAppointmentTime(),
                    apt.getStatus()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Randevular yüklenirken hata: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void bookAppointment() {
        try {
            // Step 1: Select Medical Unit
            MedicalUnitDAO unitDAO = new MedicalUnitDAO(DatabaseConnection.getConnection());
            List<MedicalUnit> units = unitDAO.getAllUnits();
            
            if (units.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kayıtlı birim bulunamadı!",
                        "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String[] unitNames = new String[units.size()];
            for (int i = 0; i < units.size(); i++) {
                unitNames[i] = units.get(i).getUnitName();
            }
            
            String selectedUnitName = (String) JOptionPane.showInputDialog(this, 
                    "Birim Seçin:", "Randevu Al - 1. Adım",
                    JOptionPane.QUESTION_MESSAGE, null, unitNames, unitNames[0]);
            
            if (selectedUnitName == null) return;
            
            // Find selected unit
            MedicalUnit selectedUnit = null;
            for (MedicalUnit unit : units) {
                if (unit.getUnitName().equals(selectedUnitName)) {
                    selectedUnit = unit;
                    break;
                }
            }
            
            // Step 2: Select Doctor from that unit
            DoctorDAO doctorDAO = new DoctorDAO(DatabaseConnection.getConnection());
            EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnection.getConnection());
            List<Doctor> allDoctors = doctorDAO.getAllDoctors();
            
            List<Doctor> unitDoctors = new ArrayList<>();
            for (Doctor doc : allDoctors) {
                if (doc.getUnitID() == selectedUnit.getUnitID()) {
                    unitDoctors.add(doc);
                }
            }
            
            if (unitDoctors.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bu birimde doktor bulunamadı!",
                        "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String[] doctorNames = new String[unitDoctors.size()];
            for (int i = 0; i < unitDoctors.size(); i++) {
                Employee emp = employeeDAO.getEmployeeById(unitDoctors.get(i).getEmployeeID());
                doctorNames[i] = emp != null ? emp.getFull_name() : "Doktor " + unitDoctors.get(i).getDoctorID();
            }
            
            String selectedDoctorName = (String) JOptionPane.showInputDialog(this, 
                    "Doktor Seçin:", "Randevu Al - 2. Adım",
                    JOptionPane.QUESTION_MESSAGE, null, doctorNames, doctorNames[0]);
            
            if (selectedDoctorName == null) return;
            
            // Find selected doctor
            Doctor selectedDoctor = null;
            for (int i = 0; i < doctorNames.length; i++) {
                if (doctorNames[i].equals(selectedDoctorName)) {
                    selectedDoctor = unitDoctors.get(i);
                    break;
                }
            }
            
            // Step 3: Select Date and Time
            JPanel dateTimePanel = new JPanel(new GridLayout(2, 2, 10, 10));
            dateTimePanel.add(new JLabel("Tarih (YYYY-MM-DD):"));
            JTextField dateField = new JTextField();
            dateTimePanel.add(dateField);
            
            dateTimePanel.add(new JLabel("Saat (HH:MM):"));
            String[] timeSlots = {
                "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30"
            };
            JComboBox<String> timeCombo = new JComboBox<>(timeSlots);
            dateTimePanel.add(timeCombo);
            
            int result = JOptionPane.showConfirmDialog(this, dateTimePanel, 
                    "Randevu Al - 3. Adım", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                String date = dateField.getText().trim();
                String time = (String) timeCombo.getSelectedItem();
                
                if (date.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lütfen tarih girin!",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Create appointment
                Appointment appointment = new Appointment(
                    selectedDoctor.getDoctorID(),
                    patient.getPatientID(),
                    patient.getFullName(),
                    time,
                    date,
                    "Scheduled"
                );
                
                AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnection.getConnection());
                appointmentDAO.addAppointment(appointment);
                
                JOptionPane.showMessageDialog(this, "Randevu başarıyla oluşturuldu!",
                        "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                loadAppointments();
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Randevu oluşturulurken hata: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void cancelAppointment() {
        int selectedRow = appointmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen iptal edilecek randevuyu seçin!",
                    "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int appointmentId = (int) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 5);
        
        if (status.equals("Cancelled") || status.equals("Completed")) {
            JOptionPane.showMessageDialog(this, "Bu randevu zaten " + status + " durumunda!",
                    "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bu randevuyu iptal etmek istediğinizden emin misiniz?",
                "Onay", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                AppointmentDAO dao = new AppointmentDAO(DatabaseConnection.getConnection());
                Appointment apt = dao.getAppointmentById(appointmentId);
                apt.setStatus("Cancelled");
                dao.updateAppointment(apt);
                
                JOptionPane.showMessageDialog(this, "Randevu iptal edildi!",
                        "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                loadAppointments();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Randevu iptal edilirken hata: " + e.getMessage(),
                        "Hata", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    private void logout() {
        this.dispose();
        new LoginFrame().setVisible(true);
    }
}