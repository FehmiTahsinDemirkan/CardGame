package memorygame;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    public About() {
        setTitle("About the Game");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea aboutText = new JTextArea();
        aboutText.setText("Memory Cards Game\n\n"
                + "Developer: [Your Name]\n"
                + "Student Number: [Your Student Number]");
        aboutText.setEditable(false);

        add(new JScrollPane(aboutText), BorderLayout.CENTER);

        setVisible(true);
    }
}
