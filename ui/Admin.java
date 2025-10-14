package ui;

import javax.swing.*;
import java.awt.*;

public class Admin extends JPanel {
    
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Runnable backToMainCallback;
    
    private static final String LOGIN_CARD = "AdminLogin";
    private static final String MENU_CARD = "AdminMenu";
    
    public Admin(Runnable backToMainCallback) {
        this.backToMainCallback = backToMainCallback;
        
        setLayout(new BorderLayout());
        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        contentPanel.add(createLoginCard(), LOGIN_CARD);
        contentPanel.add(createMenuCard(), MENU_CARD);
        
        add(contentPanel);
        showCard(LOGIN_CARD);
    }
    
    private JPanel createLoginCard() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField usernameField = new JTextField(15);
        panel.add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(100, 35));
        
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 35));
        
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.equals("admin") && password.equals("admin")) {
                usernameField.setText("");
                passwordField.setText("");
                showCard(MENU_CARD);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid credentials. Use username: admin, password: admin",
                    "Login Failed", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        backButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            backToMainCallback.run();
        });
        
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(backButton);
        
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private JPanel createMenuCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));
        
        JLabel titleLabel = new JLabel("Admin Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton viewSchedulesButton = createMenuButton("View Doctor Schedules");
        JButton sortDoctorsButton = createMenuButton("Sort Doctors by Availability");
        JButton exitButton = createMenuButton("Exit");
        
        viewSchedulesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "View Doctor Schedules - Feature to be implemented",
                "Doctor Schedules", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        sortDoctorsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Sort Doctors by Availability - Feature to be implemented",
                "Sort Doctors", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        exitButton.addActionListener(e -> {
            showCard(LOGIN_CARD);
            backToMainCallback.run();
        });
        
        gbc.gridy = 0;
        menuPanel.add(viewSchedulesButton, gbc);
        gbc.gridy = 1;
        menuPanel.add(sortDoctorsButton, gbc);
        gbc.gridy = 2;
        menuPanel.add(exitButton, gbc);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(menuPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(350, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
    
    private void showCard(String cardName) {
        cardLayout.show(contentPanel, cardName);
    }
}