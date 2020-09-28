package GameModel;

import GamePets.Cat;
import GamePets.Dog;
import GamePets.iPet;
import GameWindow.GameWindowManager;
import Menus.SelectionMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private AnchorPane mainPane;
    private Scene mainScne;
    private Stage mainStage;

    private final static int menuStartPositionOfButtonX = 33;
    private final static int menuStartPositionOfButtonY = 80;

    List<MenuButton> menuButtonList;

    List<SelectionMenu> selectPetList;
    //    private Pets selectPet;
    private iPet iselectPet;

    private Dog dog = new Dog();
    private Cat cat = new Cat();

    private TamagotchiSubScene helpSubScene;
    private TamagotchiSubScene scoreSubScene;
    private TamagotchiSubScene choosePetSubScene;
    private TamagotchiSubScene unhiddenSubScene;
//    private TamagotchiSubScene feedPetSubScene;

    public Controller() {

        menuButtonList = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScne = new Scene(mainPane, 530, 560);
        mainStage = new Stage();
        createButton();
        createBackground();
        mainStage.setScene(mainScne);
        createSubScene();
    }

    private void subSceneAppeared(TamagotchiSubScene subScene) {
        if (unhiddenSubScene != null) {
            unhiddenSubScene.apperSubScene();
        }
        subScene.apperSubScene();
        unhiddenSubScene = subScene;
    }


    public Stage getMainStage() {
        return mainStage;
    }

    private void createBackground() {
        Image roomBackground = new Image("room_background.png" );
        BackgroundImage background = new BackgroundImage(roomBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground((new Background(background)));
    }

    private void createSubScene() {

        helpSubScene = new TamagotchiSubScene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new TamagotchiSubScene();
        mainPane.getChildren().add(scoreSubScene);

//        choosePetSubScene = new GameModel.TamagotchiSubScene();
//        mainPane.getChildren().add(choosePetSubScene);
        createSelectionMenu();

    }

    private void createSelectionMenu() {
        choosePetSubScene = new TamagotchiSubScene();
        mainPane.getChildren().add(choosePetSubScene);

        AdditionalHeadline selectionAdditional = new AdditionalHeadline("Choose Pet" );
        selectionAdditional.setLayoutX(65);
        selectionAdditional.setLayoutY(15);
        choosePetSubScene.getPane().getChildren().add(selectionAdditional);
        choosePetSubScene.getPane().getChildren().add(showPetforChoose());
        choosePetSubScene.getPane().getChildren().add(createPlayButton());
    }

    private HBox showPetforChoose() {
        HBox petBox = new HBox();
        petBox.setSpacing(35);
        selectPetList = new ArrayList<>();

        List<SelectionMenu> userSelectedPet = new ArrayList<>();
        userSelectedPet.add(new SelectionMenu(dog));
        userSelectedPet.add(new SelectionMenu(cat));
        for (SelectionMenu menu : userSelectedPet) {
            selectPetList.add(menu);
            petBox.getChildren().add(menu);
            menu.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (SelectionMenu select : selectPetList) {
                        select.setIsFilledCircle(false);
                    }
                    menu.setIsFilledCircle(true);
                    iselectPet = menu.getiPet();
                }
            });
        }

        petBox.setLayoutY(100);
        petBox.setLayoutX(0);
        return petBox;

    }

    private MenuButton createPlayButton() {
        MenuButton startButton = new MenuButton("Play" );
        startButton.setLayoutY(250);
        startButton.setLayoutX(30);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (iselectPet != null) {
                    GameWindowManager gameManager = new GameWindowManager();
                    try {
                        FileInputStream fileInputStream = new FileInputStream("src/main/resources/save.game" );
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        iselectPet = (iPet) objectInputStream.readObject();
                        System.out.println("возраст после чтения  "+ iselectPet.getAge());
                    } catch (IOException | ClassNotFoundException e ) {
                        e.printStackTrace();
                    }

                    gameManager.startNewGame(mainStage, iselectPet);
                }
            }
        });

        return startButton;
    }


    private void createButton() {
        createStartButton();
        createScoreButton();
        createHelpButton();
        createExitButton();

    }

    private void createStartButton() {
        MenuButton startButton = new MenuButton("Start" );
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subSceneAppeared(choosePetSubScene);
            }
        });

    }
//    private void createFeedButton() {
//        MenuButton startButton = new MenuButton("Feed");
//        addMenuButton(startButton);
//
//        startButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                subSceneAppeared(feedPetSubScene);
//            }
//        });
//
//    }

    private void addMenuButton(MenuButton menuButton) {
        menuButton.setLayoutX(menuStartPositionOfButtonX);
        menuButton.setLayoutY(menuStartPositionOfButtonY + menuButtonList.size() * 80);
        menuButtonList.add(menuButton);
        mainPane.getChildren().add(menuButton);

    }

    private void createScoreButton() {
        MenuButton scoretButton = new MenuButton("Score" );
        addMenuButton(scoretButton);
        scoretButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subSceneAppeared(scoreSubScene);
            }
        });
    }


    private void createHelpButton() {
        MenuButton helpButton = new MenuButton("Help" );
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subSceneAppeared(helpSubScene);
            }
        });

    }

    private void createExitButton() {
        MenuButton exitButton = new MenuButton("Exit" );
        addMenuButton(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
                System.out.println("Exit was pushed!!!" );
            }
        });
    }

}
