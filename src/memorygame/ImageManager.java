package memorygame;

import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageManager {
    private List<ImageIcon> images;
    private static final int CARD_WIDTH = 100;  // Kart genişliği
    private static final int CARD_HEIGHT = 100; // Kart yüksekliği

    public ImageManager() {
        images = new ArrayList<>();
        loadImages();
    }

    private void loadImages() {
        File folder = new File("src/images");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))) {
                    ImageIcon icon = new ImageIcon(file.getPath());
                    Image scaledImage = icon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                    images.add(new ImageIcon(scaledImage));
                }
            }
        }
    }

    public List<ImageIcon> getShuffledImages() {
        Collections.shuffle(images);
        return images;
    }
}
