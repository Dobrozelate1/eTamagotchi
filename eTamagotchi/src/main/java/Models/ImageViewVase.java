package Models;

import javafx.scene.image.ImageView;

public class ImageViewVase extends ImageView {
    private final static String vasePath = "vase.png";
    private final static int elementRadius = 16;

    public static int getElementRadius() {
        return elementRadius;
    }
    public ImageViewVase(){
        super(vasePath);
    }

}
