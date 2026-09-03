import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationForm extends JFrame {

    // --- NEW: Declare components as class fields to access their values later ---
    private JTextField firstNameField, lastNameField;
    private JTextField streetAddressField, cityField, stateField;
    private JTextField monthField, dayField, yearField;
    private JRadioButton maleRadio, femaleRadio, naRadio;
    private ButtonGroup genderGroup;
    private JTextField studentNumberField, studentEmailField, companyField, dateField;
    private List<JCheckBox> courseCheckBoxes;
    private JTextArea commentsArea;

    public RegistrationForm() {
        super("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 850); // Increased height for the button
        setLocationRelativeTo(null);

        // --- MODIFIED: The main panel now has a BorderLayout to accommodate the button panel ---
        JPanel mainLayout = new JPanel(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // --- Full Name ---
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Full Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.weightx = 1.0;
        JPanel namePanel = new JPanel(new GridLayout(1, 2, 5, 0));
        firstNameField = new JTextField(15); // --- MODIFIED ---
        lastNameField = new JTextField(15);  // --- MODIFIED ---
        namePanel.add(firstNameField);
        namePanel.add(lastNameField);
        mainPanel.add(namePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JPanel nameLabelsPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        nameLabelsPanel.add(createPlaceholderLabel("First Name"));
        nameLabelsPanel.add(createPlaceholderLabel("Last Name"));
        mainPanel.add(nameLabelsPanel, gbc);
        gbc.weightx = 0;

        // --- Address ---
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.weightx = 1.0;
        streetAddressField = new JTextField(30); // --- MODIFIED ---
        mainPanel.add(streetAddressField, gbc);
        gbc.weightx = 0;

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(createPlaceholderLabel("Street Address"), gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.weightx = 1.0;
        JPanel cityStatePanel = new JPanel(new GridLayout(1, 2, 5, 0));
        cityField = new JTextField(15);   // --- MODIFIED ---
        stateField = new JTextField(15);  // --- MODIFIED ---
        cityStatePanel.add(cityField);
        cityStatePanel.add(stateField);
        mainPanel.add(cityStatePanel, gbc);
        gbc.weightx = 0;

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JPanel cityStateLabelsPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        cityStateLabelsPanel.add(createPlaceholderLabel("City"));
        cityStateLabelsPanel.add(createPlaceholderLabel("State/Province"));
        mainPanel.add(cityStateLabelsPanel, gbc);

        // --- Birth Date ---
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Birth Date:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.weightx = 1.0;
        JPanel birthDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        monthField = new JTextField(3); // --- MODIFIED ---
        dayField = new JTextField(3);   // --- MODIFIED ---
        yearField = new JTextField(5);  // --- MODIFIED ---
        birthDatePanel.add(monthField);
        birthDatePanel.add(dayField);
        birthDatePanel.add(yearField);
        mainPanel.add(birthDatePanel, gbc);
        gbc.weightx = 0;

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JPanel dateLabelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        dateLabelsPanel.add(createPlaceholderLabel("Month"));
        dateLabelsPanel.add(createPlaceholderLabel("Day"));
        dateLabelsPanel.add(createPlaceholderLabel("Year"));
        mainPanel.add(dateLabelsPanel, gbc);

        // --- Gender ---
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Gender:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.weightx = 1.0;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        maleRadio = new JRadioButton("Male");     // --- MODIFIED ---
        femaleRadio = new JRadioButton("Female"); // --- MODIFIED ---
        naRadio = new JRadioButton("N/A");        // --- MODIFIED ---
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(naRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(naRadio);
        mainPanel.add(genderPanel, gbc);
        gbc.weightx = 0;

        // --- Student Number, Email, Company, Courses... ---
        // (Similar modifications for all other input fields)

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Student Number:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row++;
        studentNumberField = new JTextField(20);
        mainPanel.add(studentNumberField, gbc);
        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(createPlaceholderLabel("Phone Number"), gbc);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Student Email:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row++;
        studentEmailField = new JTextField(25);
        studentEmailField.setText("example@example.com");
        mainPanel.add(studentEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("College:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row++;
        companyField = new JTextField(25);
        mainPanel.add(companyField, gbc);

        // --- Courses ---
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Courses:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row++;
        courseCheckBoxes = new ArrayList<>();
        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS)); // Y_AXIS for vertical stacking
        String[] courseNames = {
        "23MA303 Maths", "23CS301 Data Structures & Algorithm",
        "23CS302 Digital Principles and System Design",
        "23CS303 Computer Organization & Architecture",
        "23CS304 Programming with JAVA"};
        for(String name : courseNames) {
            JCheckBox cb = new JCheckBox(name);
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);
            courseCheckBoxes.add(cb);
            coursesPanel.add(cb);
        }
        mainPanel.add(coursesPanel, gbc);

        // --- Additional Comments ---
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Additional Comments:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.fill = GridBagConstraints.BOTH;
        commentsArea = new JTextArea(5, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentsArea);
        mainPanel.add(scrollPane, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Date ---
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row++;
        dateField = new JTextField(10);
        mainPanel.add(dateField, gbc);

        // --- NEW: Create a button panel for the bottom ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        // Add the main panel and button panel to the frame
        mainLayout.add(mainPanel, BorderLayout.CENTER);
        mainLayout.add(buttonPanel, BorderLayout.SOUTH);

        add(mainLayout);

        // --- NEW: Add an action listener to the submit button ---
        submitButton.addActionListener(e -> handleSubmit());

        setVisible(true);
    }

    // --- NEW: This method is called when the submit button is clicked ---
    private void handleSubmit() {
        // 1. Gather data from all fields
        String fullName = firstNameField.getText() + " " + lastNameField.getText();
        String address = streetAddressField.getText() + ", " + cityField.getText() + ", " + stateField.getText();
        String birthDate = monthField.getText() + "/" + dayField.getText() + "/" + yearField.getText();
        String gender = getSelectedGender();
        String studentNumber = studentNumberField.getText();
        String studentEmail = studentEmailField.getText();
        String company = companyField.getText();
        String courses = getSelectedCourses();
        String comments = commentsArea.getText();
        String date = dateField.getText();

        // 2. Format the data into an impressive HTML string for the pop-up
        StringBuilder summaryBuilder = new StringBuilder("<html><body style='width: 350px; font-family: sans-serif; padding: 10px;'>");
        summaryBuilder.append("<h1 style='color: #005A9C;'>Registration Summary</h1>");
        summaryBuilder.append("<hr>");
        summaryBuilder.append(formatEntry("Full Name", fullName));
        summaryBuilder.append(formatEntry("Address", address));
        summaryBuilder.append(formatEntry("Birth Date", birthDate));
        summaryBuilder.append(formatEntry("Gender", gender));
        summaryBuilder.append(formatEntry("Student Number", studentNumber));
        summaryBuilder.append(formatEntry("Student Email", studentEmail));
        summaryBuilder.append(formatEntry("Company", company));
        summaryBuilder.append(formatEntry("Courses", courses));
        summaryBuilder.append(formatEntry("Comments", comments.isEmpty() ? "<i>None</i>" : comments));
        summaryBuilder.append(formatEntry("Date", date));
        summaryBuilder.append("<hr>");
        summaryBuilder.append("<p style='text-align: center; color: green;'>Thank you for registering!</p>");
        summaryBuilder.append("</body></html>");

        // 3. Display the pop-up dialog
        JOptionPane.showMessageDialog(this, summaryBuilder.toString(), "Submission Details", JOptionPane.INFORMATION_MESSAGE);
    }

    // --- NEW: Helper methods to get data and format the summary ---
    private String getSelectedGender() {
        if (maleRadio.isSelected()) return "Male";
        if (femaleRadio.isSelected()) return "Female";
        if (naRadio.isSelected()) return "N/A";
        return "Not Selected";
    }

    private String getSelectedCourses() {
        String selected = courseCheckBoxes.stream()
                .filter(JCheckBox::isSelected)
                .map(JCheckBox::getText)
                .collect(Collectors.joining(", "));
        return selected.isEmpty() ? "None" : selected;
    }

    private String formatEntry(String label, String value) {
        return "<p><b>" + label + ":</b> " + value + "</p>";
    }

    // Helper method to create a small, greyed-out label
    private JLabel createPlaceholderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 10));
        label.setForeground(Color.GRAY);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm());
    }
