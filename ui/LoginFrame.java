package ui;

import dao.EmployeeDAO;
import dao.PatientDAO;
import model.Employee;
import model.Patient;
import util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    
    public LoginFrame() {
        setTitle("Hastane Yönetim Sistemi - Giriş");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Hastane Yönetim Sistemi", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JButton patientButton = new JButton("Hasta");
        patientButton.setFont(new Font("Arial", Font.BOLD, 16));
        patientButton.setPreferredSize(new Dimension(200, 50));
        patientButton.addActionListener(e -> showPatientLogin());
        buttonPanel.add(patientButton);
        
        JButton employeeButton = new JButton("Hastane Çalışanı");
        employeeButton.setFont(new Font("Arial", Font.BOLD, 16));
        employeeButton.setPreferredSize(new Dimension(200, 50));
        employeeButton.addActionListener(e -> showEmployeeLogin());
        buttonPanel.add(employeeButton);
        
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private void showPatientLogin() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        panel.add(new JLabel("Ad Soyad:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        
        panel.add(new JLabel("Doğum Tarihi (YYYY-MM-DD):"));
        JTextField birthDateField = new JTextField();
        panel.add(birthDateField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Hasta Girişi", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String birthDate = birthDateField.getText().trim();
            
            if (name.isEmpty() || birthDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", 
                        "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                PatientDAO patientDAO = new PatientDAO(DatabaseConnection.getConnection());
                Patient patient = patientDAO.getPatientByNameAndBirthDate(name, birthDate);
                
                if (patient != null) {
                    JOptionPane.showMessageDialog(this, "Giriş başarılı!");
                    this.dispose();
                    new PatientDashboard(patient).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Hasta bulunamadı!", 
                            "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Giriş hatası: " + e.getMessage(), 
                        "Hata", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    private void showEmployeeLogin() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        panel.add(new JLabel("Rol:"));
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Doctor", "Manager"});
        panel.add(roleComboBox);
        
        panel.add(new JLabel("Kullanıcı Adı:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);
        
        panel.add(new JLabel("Şifre:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Çalışan Girişi", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", 
                        "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                EmployeeDAO employeeDAO = new EmployeeDAO(DatabaseConnection.getConnection());
                Employee employee = employeeDAO.getEmployeeByName(username);
                
                if (employee != null && employee.getPassword().equals(password) && 
                        employee.getRole().equalsIgnoreCase(role)) {
                    JOptionPane.showMessageDialog(this, "Giriş başarılı!");
                    this.dispose();
                    
                    if (role.equals("Doctor")) {
                        new DoctorDashboard(employee).setVisible(true);
                    } else if (role.equals("Manager")) {
                        new ManagerDashboard(employee).setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Geçersiz kullanıcı bilgileri!", 
                            "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Giriş hatası: " + e.getMessage(), 
                        "Hata", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}