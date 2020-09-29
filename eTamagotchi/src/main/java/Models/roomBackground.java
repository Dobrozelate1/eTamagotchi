package Models;

import javafx.scene.image.Image;

public class roomBackground extends Image {
    private String backgroundPath = "room_background.png";
    public roomBackground() {
        super("room_background.png");
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }
}
