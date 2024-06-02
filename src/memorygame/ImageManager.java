package memorygame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageManager {
    private List<ImageIcon> images;  // Resimleri tutacak liste
    private static final int CARD_WIDTH = 100;  // Kart genişliği
    private static final int CARD_HEIGHT = 100; // Kart yüksekliği
    // Constructor: Seviye bilgisi alır ve resimleri yükler
    public ImageManager(int level) {
        images = new ArrayList<>();
        loadImages(level);
    }
    // Resimleri yükler
    private void loadImages(int level) {
        String folderPath = "";  // Klasör yolu

        // Seviye bilgisine göre klasör yolunu belirle
        if (level == 1) {
            folderPath = "src/level1img";
        } else if (level == 2) {
            folderPath = "src/level2img";
        } else if (level == 3) {
            folderPath = "src/level3img";
        }
        File folder = new File(folderPath);  // Klasörü temsil eden dosya nesnesi
        File[] files = folder.listFiles();  // Klasördeki dosyaları listele

        // Dosyalar varsa işle
        if (files != null) {
            for (File file : files) {
                // Dosyanın resim dosyası olup olmadığını kontrol et (.jpg veya .png)
                if (file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))) {
                    // Resmi yükle ve ölçeklendir
                    ImageIcon icon = new ImageIcon(file.getPath());
                    Image scaledImage = icon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                    images.add(new ImageIcon(scaledImage));
                }
                // Her seviyede sadece 8 görsel kullan
                if (images.size() == 8) {
                    break;
                }
            }
        }
    }
    // Karıştırılmış resimleri döndür
    public List<ImageIcon> getShuffledImages() {
        Collections.shuffle(images);  // Resimleri karıştır
        return images;
    }
}
