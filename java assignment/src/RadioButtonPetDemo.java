import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonPetDemo extends JFrame implements ActionListener {

    private JLabel imageLabel;
    private JRadioButton birdBtn, catBtn, dogBtn, rabbitBtn, pigBtn;

    // You should put these images in the SAME folder as your .java or .class file
    // Or use full path like "C:/Users/You/Pictures/bird.png"
    private final String BIRD   = "bird.png";
    private final String CAT    = "Cat.png";
    private final String DOG    = "Dog.png";
    private final String RABBIT = "Rabbit.png";
    private final String PIG    = "Pig.png";

    public RadioButtonPetDemo() {
        super("RadioButtonPetDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null); // center window

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Radio buttons panel
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        birdBtn   = new JRadioButton("Bird", false);
        catBtn    = new JRadioButton("Cat", false);
        dogBtn    = new JRadioButton("Dog", false);
        rabbitBtn = new JRadioButton("Rabbit", false);
        pigBtn    = new JRadioButton("Pig", true);   // start with Pig selected

        // Group them so only one can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(birdBtn);
        group.add(catBtn);
        group.add(dogBtn);
        group.add(rabbitBtn);
        group.add(pigBtn);

        radioPanel.add(birdBtn);
        radioPanel.add(catBtn);
        radioPanel.add(dogBtn);
        radioPanel.add(rabbitBtn);
        radioPanel.add(pigBtn);

        // Image display area
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to main panel
        mainPanel.add(radioPanel);
        mainPanel.add(imageLabel);

        add(mainPanel);

        // Add listeners
        birdBtn.addActionListener(this);
        catBtn.addActionListener(this);
        dogBtn.addActionListener(this);
        rabbitBtn.addActionListener(this);
        pigBtn.addActionListener(this);

        // Show initial image (Pig)
        updateImage("Pig", PIG);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = "";
        String imagePath = "";

        if (e.getSource() == birdBtn) {
            selection = "Bird";
            imagePath = BIRD;
        } else if (e.getSource() == catBtn) {
            selection = "Cat";
            imagePath = CAT;
        } else if (e.getSource() == dogBtn) {
            selection = "Dog";
            imagePath = DOG;
        } else if (e.getSource() == rabbitBtn) {
            selection = "Rabbit";
            imagePath = RABBIT;
        } else if (e.getSource() == pigBtn) {
            selection = "Pig";
            imagePath = PIG;
        }

        updateImage(selection, imagePath);

        // Show message dialog
        JOptionPane.showMessageDialog(this,
                "You selected: " + selection,
                "Pet Selected",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateImage(String petName, String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            // Optional: scale image if too big
            if (icon.getIconWidth() > 300) {
                icon = new ImageIcon(icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH));
            }
            imageLabel.setIcon(icon);
            imageLabel.setText(""); // remove any previous text
        } catch (Exception ex) {
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found: " + petName);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RadioButtonPetDemo().setVisible(true);
        });
    }
}