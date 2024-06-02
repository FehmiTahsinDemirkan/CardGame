package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Memory Cards Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton startButton = new JButton("Start Game");
        JButton selectLevelButton = new JButton("Select Level");
        JButton instructionsButton = new JButton("Instructions");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(startButton);
        buttonPanel.add(selectLevelButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameWindow(1);
                dispose();
            }
        });

        selectLevelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SelectLevel();
                dispose();
            }
        });

        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Instructions();
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
