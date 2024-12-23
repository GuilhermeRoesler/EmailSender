package utils;

import javax.swing.ImageIcon;

public class ImageLoader {
    public static ImageIcon loadImage(String nome) {
        String caminho = "/images/" + nome;
        try {
            java.net.URL imageURL = ImageLoader.class.getResource(caminho);
            if (imageURL != null) {
                return new ImageIcon(imageURL);
            } else {
                System.err.println("Couldn't find file: " + caminho);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
