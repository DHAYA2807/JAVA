import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickImageFrame extends JFrame implements ActionListener
{
    private JTextField textField;
    private JButton button;
    private JLabel imageLabel;
    private int clickCount = 0;
    private ImageIcon normalIcon, largeIcon;

    public ClickImageFrame()
    {
        setTitle("Button Click & Image Zoom");
        setLayout(new FlowLayout());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(20);
        textField.setEditable(false);
        add(textField);

        button = new JButton("Click Me!");
        button.addActionListener(this);
        add(button);

        normalIcon = new ImageIcon("image.jpg");
        if (normalIcon.getIconWidth() == -1)
            imageLabel = new JLabel("Image not found");
        else
        {
            Image scaled = normalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            normalIcon = new ImageIcon(scaled);

            Image largeScaled = normalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            largeIcon = new ImageIcon(largeScaled);

            imageLabel = new JLabel(normalIcon);

            imageLabel.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(MouseEvent e)
                {
                    imageLabel.setIcon(largeIcon);
                }
                public void mouseExited(MouseEvent e)
                {
                    imageLabel.setIcon(normalIcon);
                }
            });
        }
        add(imageLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        clickCount++;
        textField.setText("Button Clicked: " + clickCount + " times");
    }
    public static void main(String[] args)
    {
        new ClickImageFrame();
    }
}
