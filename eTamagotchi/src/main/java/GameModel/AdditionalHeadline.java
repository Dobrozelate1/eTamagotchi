package GameModel;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdditionalHeadline extends Label {
    public final static String fontPath = "src/main/resources/text_font.ttf";
    public final static String backGroundPath = "violet_window.png";

    public AdditionalHeadline(String text) {

        setPrefHeight(45);
//        setPrefSize();
        setPadding(new Insets(0, 15, 15, 15));
//        setAlignment(Pos.CENTER);
        setPrefWidth(150);
        setText(text);
        setWrapText(true);
        setHeadLineFont();
        BackgroundImage backgroundImage = new BackgroundImage(new Image(backGroundPath,125,
                30,false,true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
    }

    private void setHeadLineFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(fontPath)), 17));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Impact", 17));
        }
    }
}
