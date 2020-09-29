package Models;

import javafx.scene.image.ImageView;

public class Heart {
     private String heartPath ;

    private int number;
public Heart(){
    heartPath = "pets/heart.png";
}

    public void setHeartPath(String heartPath) {
        this.heartPath = heartPath;
    }
    public String getHeartPath() {
        return heartPath;
    }


}
