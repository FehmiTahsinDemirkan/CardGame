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
        aboutText.setText("Memory Cards Game\n\n\n"
                + "Memory Cards Game is an engaging and fun single-player game \n" +
                "designed to test and improve your memory skills. The game consists of three levels,\n " +
                "each increasing in difficulty. The objective is simple: match all the pairs of cards \n" +
                "with the fewest tries possible.\n\n\n"
                + "Level 1: Start with a set of easy cards to get you warmed up.\n"
                + "Level 2: Challenge yourself with a more difficult set of cards.\n"
                + "Level 3: Face the ultimate test with the hardest set of cards.");
        aboutText.setEditable(false);

        add(new JScrollPane(aboutText), BorderLayout.CENTER);

        setVisible(true);
    }
}
