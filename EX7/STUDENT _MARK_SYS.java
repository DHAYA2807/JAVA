import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class StudentMarkSystem extends JFrame implements ActionListener {

    // Database connection details - Replace with your Oracle DB credentials
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/orcl21c"; // Example URL
    private static final String USER = "system";
    private static final String PASS = "Pass#123";

    // UI Components
    private JLabel lblRegNo, lblName, lblSub1, lblSub2, lblSub3, lblSub4, lblSub5;
    private JTextField txtRegNo, txtName, txtSub1, txtSub2, txtSub3, txtSub4, txtSub5;
    private JButton btnInsert, btnView, btnDelete, btnUpdate;

    public StudentMarkSystem() {
        // Frame setup
        setTitle("Student Mark System");
        setSize(500, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 240, 240));
        setResizable(false);
        setLocationRelativeTo(null); // Center the frame

        // Initialize Labels
        lblRegNo = new JLabel("Reg No:");
        lblName = new JLabel("Name:");
        lblSub1 = new JLabel("Subject 1:");
        lblSub2 = new JLabel("Subject 2:");
        lblSub3 = new JLabel("Subject 3:");
        lblSub4 = new JLabel("Subject 4:");
        lblSub5 = new JLabel("Subject 5:");

        // Set label positions and sizes
        lblRegNo.setBounds(30, 30, 100, 25);
        lblName.setBounds(30, 70, 100, 25);
        lblSub1.setBounds(30, 110, 100, 25);
        lblSub2.setBounds(30, 150, 100, 25);
        lblSub3.setBounds(30, 190, 100, 25);
        lblSub4.setBounds(30, 230, 100, 25);
        lblSub5.setBounds(30, 270, 100, 25);

        // Add labels to the frame
        add(lblRegNo);
        add(lblName);
        add(lblSub1);
        add(lblSub2);
        add(lblSub3);
        add(lblSub4);
        add(lblSub5);

        // Initialize TextFields
        txtRegNo = new JTextField();
        txtName = new JTextField();
        txtSub1 = new JTextField();
        txtSub2 = new JTextField();
        txtSub3 = new JTextField();
        txtSub4 = new JTextField();
        txtSub5 = new JTextField();

        // Set TextField positions and sizes
        txtRegNo.setBounds(150, 30, 300, 25);
        txtName.setBounds(150, 70, 300, 25);
        txtSub1.setBounds(150, 110, 300, 25);
        txtSub2.setBounds(150, 150, 300, 25);
        txtSub3.setBounds(150, 190, 300, 25);
        txtSub4.setBounds(150, 230, 300, 25);
        txtSub5.setBounds(150, 270, 300, 25);

        // Add TextFields to the frame
        add(txtRegNo);
        add(txtName);
        add(txtSub1);
        add(txtSub2);
        add(txtSub3);
        add(txtSub4);
        add(txtSub5);

        // Initialize Buttons
        btnInsert = createStyledButton("Insert");
        btnView = createStyledButton("View");
        btnDelete = createStyledButton("Delete");
        btnUpdate = createStyledButton("Update");

        // Set Button positions and sizes
        btnInsert.setBounds(30, 320, 205, 40);
        btnView.setBounds(245, 320, 205, 40);
        btnDelete.setBounds(30, 370, 205, 40);
        btnUpdate.setBounds(245, 370, 205, 40);

        // Add Buttons to the frame
        add(btnInsert);
        add(btnView);
        add(btnDelete);
        add(btnUpdate);

        // Add Action Listeners
        btnInsert.addActionListener(this);
        btnView.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);

        setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(220, 230, 250));
        button.setForeground(new Color(50, 50, 100));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEtchedBorder());
        return button;
    }

    // Database Connection Method
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        // Register the Oracle driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInsert) {
            insertStudent();
        } else if (e.getSource() == btnView) {
            viewStudent();
        } else if (e.getSource() == btnDelete) {
            deleteStudent();
        } else if (e.getSource() == btnUpdate) {
            updateStudent();
        }
    }

    private boolean validateFields() {
        if (txtRegNo.getText().isEmpty() || txtName.getText().isEmpty() || txtSub1.getText().isEmpty() ||
            txtSub2.getText().isEmpty() || txtSub3.getText().isEmpty() || txtSub4.getText().isEmpty() ||
            txtSub5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(txtRegNo.getText());
            Integer.parseInt(txtSub1.getText());
            Integer.parseInt(txtSub2.getText());
            Integer.parseInt(txtSub3.getText());
            Integer.parseInt(txtSub4.getText());
            Integer.parseInt(txtSub5.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Reg No and Subject marks must be valid numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtRegNo.setText("");
        txtName.setText("");
        txtSub1.setText("");
        txtSub2.setText("");
        txtSub3.setText("");
        txtSub4.setText("");
        txtSub5.setText("");
    }

    private void insertStudent() {
        if (!validateFields()) return;

        String sql = "INSERT INTO students(reg_no, student_name, subject1, subject2, subject3, subject4, subject5, average_marks) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int s1 = Integer.parseInt(txtSub1.getText());
            int s2 = Integer.parseInt(txtSub2.getText());
            int s3 = Integer.parseInt(txtSub3.getText());
            int s4 = Integer.parseInt(txtSub4.getText());
            int s5 = Integer.parseInt(txtSub5.getText());
            double average = (s1 + s2 + s3 + s4 + s5) / 5.0;

            pstmt.setInt(1, Integer.parseInt(txtRegNo.getText()));
            pstmt.setString(2, txtName.getText());
            pstmt.setInt(3, s1);
            pstmt.setInt(4, s2);
            pstmt.setInt(5, s3);
            pstmt.setInt(6, s4);
            pstmt.setInt(7, s5);
            pstmt.setDouble(8, average);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record inserted successfully!");
            clearFields();

        } catch (SQLIntegrityConstraintViolationException e) {
             JOptionPane.showMessageDialog(this, "Error: Registration number already exists.", "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inserting record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStudent() {
        if (txtRegNo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Registration Number to view.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM students WHERE reg_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(txtRegNo.getText()));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtName.setText(rs.getString("student_name"));
                txtSub1.setText(String.valueOf(rs.getInt("subject1")));
                txtSub2.setText(String.valueOf(rs.getInt("subject2")));
                txtSub3.setText(String.valueOf(rs.getInt("subject3")));
                txtSub4.setText(String.valueOf(rs.getInt("subject4")));
                txtSub5.setText(String.valueOf(rs.getInt("subject5")));
            } else {
                JOptionPane.showMessageDialog(this, "No records found for Reg No: " + txtRegNo.getText());
                clearFields();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error viewing record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        if (txtRegNo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Registration Number to delete.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM students WHERE reg_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(txtRegNo.getText()));
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "No records found to delete for Reg No: " + txtRegNo.getText());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
         if (!validateFields()) return;

        String sql = "UPDATE students SET student_name = ?, subject1 = ?, subject2 = ?, subject3 = ?, subject4 = ?, subject5 = ?, average_marks = ? WHERE reg_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int s1 = Integer.parseInt(txtSub1.getText());
            int s2 = Integer.parseInt(txtSub2.getText());
            int s3 = Integer.parseInt(txtSub3.getText());
            int s4 = Integer.parseInt(txtSub4.getText());
            int s5 = Integer.parseInt(txtSub5.getText());
            double average = (s1 + s2 + s3 + s4 + s5) / 5.0;

            pstmt.setString(1, txtName.getText());
            pstmt.setInt(2, s1);
            pstmt.setInt(3, s2);
            pstmt.setInt(4, s3);
            pstmt.setInt(5, s4);
            pstmt.setInt(6, s5);
            pstmt.setDouble(7, average);
            pstmt.setInt(8, Integer.parseInt(txtRegNo.getText()));

            int affectedRows = pstmt.executeUpdate();
             if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No records found to update for Reg No: " + txtRegNo.getText());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        // Set a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new StudentMarkSystem());
    }
}
