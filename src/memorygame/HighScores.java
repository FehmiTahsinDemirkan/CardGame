package memorygame;

import java.io.*;
import java.util.*;

public class HighScores {
    private static final String FILE_PATH = "highscores.txt";

    public static List<String> getHighScores() {
        List<String> scores = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static void addScore(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(name + ": " + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
