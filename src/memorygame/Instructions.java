package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructions extends JFrame {
    public Instructions() {
        setTitle("Instructions");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea instructionsText = new JTextArea();
        instructionsText.setText("Instructions for playing the Memory Cards game:\n\n"
                + "1. Press Start Game to begin from Level 1.\n"
                + "2. Press Select Level to choose a level.\n"
                + "3. Match the cards with as few tries as possible.\n"
                + "4. Points are awarded for correct matches and deducted for incorrect matches.\n"
                + "5. Progress through all levels to complete the game.");
        instructionsText.setEditable(false);

        add(new JScrollPane(instructionsText), BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit to Main Menu");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                dispose();
            }
        });

        add(exitButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
