package Models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {
    private final static String fontPath = "src/main/resources/text_font.ttf";

    public InfoLabel(String text){
        setPrefWidth(200);
        setPrefHeight(0);
        setAlignment(Pos.CENTER_RIGHT);
        setLabelFont();
        setPadding(new Insets(-650,10,-50,-100));
        setText(text);
    }
    private void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(new File(fontPath)),25));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Impact",15));
        }
    }
}
