package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    // card names
    private static final String LOGIN_PANEL = "LoginPanel";
    private static final String ADMIN_PANEL = "AdminPanel";
    private static final String USER_PANEL = "UserPanel";

    public MainFrame() {

        // global font
        setGlobalFont(new Font("Arial", Font.PLAIN, 16));
        
        setTitle("Hospital Management System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // card layout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // add panels
        contentPanel.add(createLoginPanel(), LOGIN_PANEL);
        contentPanel.add(new Admin(() -> showPanel(LOGIN_PANEL)), ADMIN_PANEL);
        contentPanel.add(new User(() -> showPanel(LOGIN_PANEL)), USER_PANEL);

        add(contentPanel);
        setVisible(true);
    }

    private void setGlobalFont(Font font) {
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
    }

    // Main Login Panel – has 2 buttons
    private JPanel createLoginPanel() {
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Hospital Appointment System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        // Buttons
        JButton adminButton = new JButton("Login as Admin");
        JButton userButton = new JButton("Login as User");

        adminButton.setPreferredSize(new Dimension(200, 50));
        userButton.setPreferredSize(new Dimension(200, 50));

        adminButton.setFont(new Font("Arial", Font.BOLD, 18));
        userButton.setFont(new Font("Arial", Font.BOLD, 18));

        adminButton.setBackground(new Color(70, 130, 180));
        adminButton.setForeground(Color.WHITE);
        userButton.setBackground(new Color(100, 149, 237));
        userButton.setForeground(Color.WHITE);

        // click events
        adminButton.addActionListener(e -> showPanel(ADMIN_PANEL));
        userButton.addActionListener(e -> showPanel(USER_PANEL));

        // center the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(adminButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonPanel.add(userButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    // method to switch panels
    private void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }
}