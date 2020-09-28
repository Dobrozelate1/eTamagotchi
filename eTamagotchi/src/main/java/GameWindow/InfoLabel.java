package GameWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {

    private final static String fontPath = "src/main/resources/text_font.ttf";

    public InfoLabel(String text){
        setPrefWidth(200);
        setPrefHeight(0);
//        BackgroundImage backgroundImage = new BackgroundImage(,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT,
//                null);
//        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER_RIGHT);
        setLabelFont();
        setPadding(new Insets(-650,10,-50,-100));
        setText(text);
    }
    private void setLabelFont(){
//        setFont(Font.loadFont(fontPath,80));
        try {
            setFont(Font.loadFont(new FileInputStream(new File(fontPath)),25));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Impact",15));
        }



    }

}
