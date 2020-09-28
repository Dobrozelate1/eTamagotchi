package GameModel;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class TamagotchiSubScene extends SubScene {
    private Font gameFont = Font.loadFont("text_font.ttf", 20);
    private Image subBackgroundImage = new Image("green_panel.png",
            250,
            350,
            false,
            true);
    ;

    private boolean isVisible = true;


    public TamagotchiSubScene() {
        super(new AnchorPane(), 250, 350);
        prefHeight(350);
        prefWidth(250);

        BackgroundImage bgImage = new BackgroundImage(subBackgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane subroot = (AnchorPane) this.getRoot();
        subroot.setBackground(new Background(bgImage));

        setLayoutY(70);
        setLayoutX(600);
    }

    public void apperSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(this);
        if (isVisible) {
            transition.setToX(-350);
            isVisible = false;
        }
        else{
            transition.setToX(0);
            isVisible = true;
        }

        transition.play();


    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }






    //        GameModel.TamagotchiSubScene tamagotchiSubScene = new GameModel.TamagotchiSubScene();
//        tamagotchiSubScene.setLayoutX(250);
//        tamagotchiSubScene.setLayoutY(70);
//        mainPane.getChildren().add(tamagotchiSubScene);
}
