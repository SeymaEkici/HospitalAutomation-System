package ui;

import javax.swing.*;
import java.awt.*;

public class User extends JPanel {
    
    private Runnable backToMainCallback;
    
    public User(Runnable backToMainCallback) {
        this.backToMainCallback = backToMainCallback;
        
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));
        
        JLabel titleLabel = new JLabel("User Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton viewDoctorsButton = createMenuButton("View All Doctors");
        JButton bookAppointmentButton = createMenuButton("Book an Appointment");
        JButton cancelAppointmentButton = createMenuButton("Cancel an Appointment");
        JButton viewAppointmentsButton = createMenuButton("View All Booked Appointments");
        JButton exitButton = createMenuButton("Exit");
        
        viewDoctorsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "View All Doctors - Feature to be implemented",
                "All Doctors", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        bookAppointmentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Book an Appointment - Feature to be implemented",
                "Book Appointment", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        cancelAppointmentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Cancel an Appointment - Feature to be implemented",
                "Cancel Appointment", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        viewAppointmentsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "View All Booked Appointments - Feature to be implemented",
                "Booked Appointments", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        exitButton.addActionListener(e -> backToMainCallback.run());
        
        gbc.gridy = 0;
        menuPanel.add(viewDoctorsButton, gbc);
        gbc.gridy = 1;
        menuPanel.add(bookAppointmentButton, gbc);
        gbc.gridy = 2;
        menuPanel.add(cancelAppointmentButton, gbc);
        gbc.gridy = 3;
        menuPanel.add(viewAppointmentsButton, gbc);
        gbc.gridy = 4;
        menuPanel.add(exitButton, gbc);
        
        add(titleLabel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
    }
    
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(350, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}