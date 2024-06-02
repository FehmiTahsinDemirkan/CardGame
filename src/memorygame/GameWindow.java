package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    private int level;
    private int triesLeft;
    private int score;
    private List<JButton> cards;
    private List<ImageIcon> images;
    private List<ImageIcon> selectedImages;
    private JButton firstSelectedCard;
    private JButton secondSelectedCard;
    private Timer timer;

    private static final int CARD_WIDTH = 100;  // Kart genişliği
    private static final int CARD_HEIGHT = 100; // Kart yüksekliği

    public GameWindow(int level) {
        this.level = level;
        this.score = 0;
        this.firstSelectedCard = null;
        this.secondSelectedCard = null;

        if (level == 1) {
            this.triesLeft = 18;
        } else if (level == 2) {
            this.triesLeft = 15;
        } else {
            this.triesLeft = 12;
        }

        setTitle("Memory Cards Game - Level " + level);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cards = new ArrayList<>();
        images = loadImages(level);
        selectedImages = new ArrayList<>();
        initializeCards();
        setupMenuBar();

        setVisible(true);
    }

    private List<ImageIcon> loadImages(int level) {
        ImageManager imageManager = new ImageManager(level);
        return imageManager.getShuffledImages();
    }

    private void initializeCards() {
        if (images.size() < 8) {
            JOptionPane.showMessageDialog(this, "Not enough images to start the game.");
            System.exit(1);
        }

        JPanel cardPanel = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 8; i++) {
            selectedImages.add(images.get(i));
            selectedImages.add(images.get(i));
        }
        Collections.shuffle(selectedImages);

        for (int i = 0; i < 16; i++) {
            JButton cardButton = new JButton();
            cardButton.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            cardButton.setIcon(new ImageIcon("src/images/question_20.png")); // Placeholder for card back
            cardButton.addActionListener(new CardActionListener(i));
            cards.add(cardButton);
            cardPanel.add(cardButton);
        }
        add(cardPanel, BorderLayout.CENTER);

        JLabel triesLabel = new JLabel("Tries Left: " + triesLeft);
        JLabel scoreLabel = new JLabel("Score: " + score);
        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        infoPanel.add(triesLabel);
        infoPanel.add(scoreLabel);
        add(infoPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkMatch();
                triesLabel.setText("Tries Left: " + triesLeft);
                scoreLabel.setText("Score: " + score);
            }
        });
        timer.setRepeats(false);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenuItem restartMenuItem = new JMenuItem("Restart");
        JMenuItem highScoresMenuItem = new JMenuItem("High Scores");

        restartMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameWindow(level);
                dispose();
            }
        });

        highScoresMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> highScores = HighScores.getHighScores();
                JOptionPane.showMessageDialog(null, String.join("\n", highScores));
            }
        });

        gameMenu.add(restartMenuItem);
        gameMenu.add(highScoresMenuItem);

        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutGameMenuItem = new JMenuItem("About the Game");
        JMenuItem aboutDeveloperMenuItem = new JMenuItem("About the Developer");

        aboutGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new About();
            }
        });

        aboutDeveloperMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Developer: Kaya Berk KARAKELLE\nStudent Number: 20210702069");
            }
        });

        aboutMenu.add(aboutGameMenuItem);
        aboutMenu.add(aboutDeveloperMenuItem);

        JMenu exitMenu = new JMenu("Exit");
        JMenuItem exitMenuItem = new JMenuItem("Exit to Main Menu");

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                dispose();
            }
        });

        exitMenu.add(exitMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);
        menuBar.add(exitMenu);
        setJMenuBar(menuBar);
    }

    private class CardActionListener implements ActionListener {
        private int index;

        public CardActionListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = cards.get(index);
            if (firstSelectedCard == null) {
                firstSelectedCard = clickedButton;
                firstSelectedCard.setIcon(selectedImages.get(index));
            } else if (secondSelectedCard == null && clickedButton != firstSelectedCard) {
                secondSelectedCard = clickedButton;
                secondSelectedCard.setIcon(selectedImages.get(index));
                timer.start();
            }
        }
    }

    private void checkMatch() {
        if (firstSelectedCard != null && secondSelectedCard != null) {
            if (firstSelectedCard.getIcon().toString().equals(secondSelectedCard.getIcon().toString())) {
                score += getPointsForLevel();
                firstSelectedCard.setEnabled(false);
                secondSelectedCard.setEnabled(false);
            } else {
                triesLeft--;
                score -= getPenaltyForLevel();
                firstSelectedCard.setIcon(new ImageIcon("src/images/question_20.png"));
                secondSelectedCard.setIcon(new ImageIcon("src/images/question_20.png"));
                if (level == 3) {
                    shuffleCards();
                }
            }
            firstSelectedCard = null;
            secondSelectedCard = null;
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        if (triesLeft == 0) {
            saveScore();
            JOptionPane.showMessageDialog(null, "Game Over! You lost.");
            new GameWindow(1); // Kullanıcı kaybederse tekrar Seviye 1'e yönlendirilir
            dispose();
        } else if (allCardsMatched()) {
            if (level == 1) {
                JOptionPane.showMessageDialog(null, "Congratulations! You won this level.");
                new GameWindow(2); // Kullanıcı Seviye 1'i kazanırsa Seviye 2'ye yönlendirilir
            } else if (level == 2) {
                JOptionPane.showMessageDialog(null, "Congratulations! You won this level.");
                new GameWindow(3); // Kullanıcı Seviye 2'yi kazanırsa Seviye 3'e yönlendirilir
            } else if (level == 3) {
                String playerName = JOptionPane.showInputDialog("Congratulations! You completed the game. Enter your name:");
                HighScores.addScore(playerName, score);
                JOptionPane.showMessageDialog(null, "Your score has been saved.");
                new MainMenu();
            }
            dispose();
        }
    }

    private void saveScore() {
        String playerName = JOptionPane.showInputDialog("Game Over! Enter your name to save your score:");
        if (playerName != null && !playerName.trim().isEmpty()) {
            HighScores.addScore(playerName, score);
        }
    }

    private boolean allCardsMatched() {
        for (JButton card : cards) {
            if (card.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private int getPointsForLevel() {
        if (level == 1) return 5;
        if (level == 2) return 4;
        return 3;
    }

    private int getPenaltyForLevel() {
        if (level == 1) return 1;
        if (level == 2) return 2;
        return 3;
    }

    private void shuffleCards() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Collections.shuffle(selectedImages);
                for (int i = 0; i < cards.size(); i++) {
                    cards.get(i).setIcon(new ImageIcon("src/images/question_20.png"));
                    cards.get(i).setEnabled(true);
                }
            }
        }).start();
    }
}
