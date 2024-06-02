package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLevel extends JFrame {
    public SelectLevel() {
        setTitle("Select Level");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton level1Button = new JButton("Level 1");
        JButton level2Button = new JButton("Level 2");
        JButton level3Button = new JButton("Level 3");
        JButton backButton = new JButton("Back");

        add(level1Button);
        add(level2Button);
        add(level3Button);
        add(backButton);

        level1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameWindow(1);
                dispose();
            }
        });

        level2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameWindow(2);
                dispose();
            }
        });

        level3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameWindow(3);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                dispose();
            }
        });

        setVisible(true);
    }
}
