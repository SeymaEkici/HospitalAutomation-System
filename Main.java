import ui.LoginFrame;
import util.DatabaseConnection;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Test database connection
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("Starting Hospital Management System...");
            
            // Launch login window
            SwingUtilities.invokeLater(() -> {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            });
        } else {
            System.err.println("Failed to connect to database. Please check your configuration.");
        }
    }
}