package Models;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class BigMainButton extends Button {
    private Font gameFont = new Font("Impact", 15);
    //    private final  String pushedbuttonpath = "";
    private  String PushedButtonStyle = "-fx-background-color: transparent; -fx-background-image: url('pushed_blue_button.png');";
    private  String ReleasedButtonStyle = "-fx-background-color: transparent; -fx-background-image: url('released_blue_button.png');";

    public BigMainButton(String text) {
        setText(text);
//        PushedButtonStyle = "-fx-background-color: transparent; -fx-background-image: url('pushed_blue_button.png');";
        setGameButtonFont();
        setPrefHeight(49);
        setPrefWidth(190);
//        if (text == "Feed" || text == "game" ) {
////            setPrefHeight(20);
//            setPrefWidth(60);
//        }
        setStyle(ReleasedButtonStyle);
        createButtonListener();
    }

    public void setGameButtonFont() {

        setFont(gameFont);
    }

    private void setPushedButtonStyle() {
        setStyle(PushedButtonStyle);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setReleasedButtonStyle() {
        setStyle(ReleasedButtonStyle);
        setPrefHeight(49);
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
