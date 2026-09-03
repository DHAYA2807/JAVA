
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A JFrame containing 5 JPanels arranged in a BorderLayout.
 * The background color of each panel changes to a new random color every 3 seconds.
 */
public class ColorChangingFrame extends JFrame {

    // A list to hold our 5 panels so we can easily iterate over them.
    private final List<JPanel> panels;

    // A random number generator to create new colors.
    private final Random random;

    public ColorChangingFrame() {
        // 1. Set up the main frame
        super("Color Changing Panels"); // Sets the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Use BorderLayout with a 5-pixel gap horizontally and vertically
        setLayout(new BorderLayout(5, 5));

        // Initialize our list and random generator
        panels = new ArrayList<>();
        random = new Random();

        // 2. Create the 5 panels
        JPanel northPanel = createPanel(new Dimension(0, 100)); // Width is flexible, height is 100
        JPanel southPanel = createPanel(new Dimension(0, 100));
        JPanel eastPanel = createPanel(new Dimension(150, 0));  // Height is flexible, width is 150
        JPanel westPanel = createPanel(new Dimension(150, 0));
        JPanel centerPanel = createPanel(null); // No preferred size, it will fill the rest

        // 3. Add panels to the frame using BorderLayout constraints
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        // 4. Set the initial random colors for all panels
        changeAllPanelColors();

        // 5. Set up the timer to trigger every 3 seconds (3000 milliseconds)
        int delay = 3000; // milliseconds
        Timer timer = new Timer(delay, e -> changeAllPanelColors());
        timer.start(); // Start the timer
    }

    /**
     * A helper method to create a JPanel, set its preferred size,
     * and add it to our list of panels.
     *
     * @param preferredSize The preferred dimension for the panel. Can be null.
     * @return The newly created JPanel.
     */
    private JPanel createPanel(Dimension preferredSize) {
        JPanel panel = new JPanel();
        if (preferredSize != null) {
            panel.setPreferredSize(preferredSize);
        }
        // Add the created panel to our list for easy access later
        panels.add(panel);
        return panel;
    }

    /**
     * Iterates through all panels in the list and sets their background
     * to a new, randomly generated color.
     */
    private void changeAllPanelColors() {
        for (JPanel panel : panels) {
            // Generate random red, green, and blue values (0-255)
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            Color randomColor = new Color(r, g, b);

            // Set the panel's background color
            panel.setBackground(randomColor);
        }
    }

    /**
     * The main method to run the application.
     */
    public static void main(String[] args) {
        // It's best practice to create and show Swing GUIs on the Event Dispatch Thread.
        SwingUtilities.invokeLater(() -> {
            ColorChangingFrame frame = new ColorChangingFrame();
            frame.setVisible(true);
        });
    }
