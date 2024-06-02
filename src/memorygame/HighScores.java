package memorygame;

import java.io.*;
import java.util.*;

public class HighScores {
    private static final String FILE_PATH = "highscores.txt";

    // Son 10 skoru döndüren metot
    public static List<String> getHighScores() {
        List<String> scores = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            // Dosya yoksa oluştur
            if (!file.exists()) file.createNewFile();

            // Dosyayı oku ve puanları listeye ekle
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Son 10 skoru döndür
        int start = Math.max(scores.size() - 10, 0);
        return scores.subList(start, scores.size());
    }

    // Yeni bir puan eklemek için metot
    public static void addScore(String name, int score) {
        List<String> scores = getHighScores();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // İsmi ve puanı dosyaya yaz
            writer.write(name + ": " + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scores.add(name + ": " + score);

        // Eğer 10'dan fazla skor varsa, eskileri sil
        if (scores.size() > 10) {
            scores = scores.subList(scores.size() - 10, scores.size());
        }

        // Dosyayı güncelle
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String s : scores) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
