package Models;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MiniGameControlButton extends Button {

    private String miniGameFontPath = "src/main/resources/text_font.ttf";
   private String PushedButtonStyle = "-fx-background-color: transparent; -fx-background-image: url('minigame_pushed.png');";
   private String ReleasedButtonStyle = "-fx-background-color: transparent; -fx-background-image: url('minigame_released.png');";


    public MiniGameControlButton(String text) {
        setText(text);
        setMiniGameButtonFont();
            setPrefHeight(25);
            setPrefWidth(55);
        setStyle(ReleasedButtonStyle);
        createButtonListener();
    }
    public void setMiniGameButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(this.miniGameFontPath)), 15));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Impact", 15));
        }

    }    private void setPushedButtonStyle() {
        setStyle(PushedButtonStyle);
        setPrefHeight(23);
        setLayoutY(getLayoutY() + 4);
    }

    private void setReleasedButtonStyle() {
        setStyle(ReleasedButtonStyle);
        setPrefHeight(20);
        setLayoutY(getLayoutY() - 4);
    }

    protected void createButtonListener() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setPushedButtonStyle();
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setReleasedButtonStyle();
                }
            }
        });

    }

}
