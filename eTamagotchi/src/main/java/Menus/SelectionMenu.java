package Menus;

import GamePets.iPet;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SelectionMenu extends VBox {

    private ImageView circleImage;
    private ImageView petImage;

    private String filledCircle = "fill_circle.png";
    private String unFilledCircle = "empty_circle.png";
    private boolean isFilledCircle = false;

    private  iPet ipet;

    public SelectionMenu(iPet pet) {
        circleImage = new ImageView(unFilledCircle);
        petImage = new ImageView(pet.getIdleImageOfPet());
        this.ipet = pet;
        isFilledCircle = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(petImage);
    }

    public iPet getiPet() {
        return ipet;
    }

    public boolean getIsFilledCircle() {
        return isFilledCircle;
    }

    public void setIsFilledCircle(boolean isFilledCircle) {
        this.isFilledCircle = isFilledCircle;
        String neededImage = this.isFilledCircle ? filledCircle : unFilledCircle;
        circleImage.setImage(new Image(neededImage));

    }

}
