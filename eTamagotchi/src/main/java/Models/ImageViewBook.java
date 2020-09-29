package Models;

import javafx.scene.image.ImageView;

public class ImageViewBook extends ImageView {
    private final static String bookPath = "book.png";
    private final static int elementRadius = 16;

    public static int getElementRadius() {
        return elementRadius;
    }

    public ImageViewBook() {
        super("book.png");
    }
}
