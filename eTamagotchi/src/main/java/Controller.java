import Models.AdditionalHeadline;
import Models.BigMainButton;
import Pets.Pet;
import TamagotchiScenes.SelectionMenu;
import Pets.Cat;
import Pets.Dog;
import Pets.iPet;
import TamagotchiScenes.TamagotchiSubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private AnchorPane mainPane;
    private Scene mainScne;
    private Stage mainStage;

    private final static int menuStartPositionOfButtonX = 33;
    private final static int menuStartPositionOfButtonY = 80;

    List<BigMainButton> bigMainButtonList;

    List<SelectionMenu> selectPetList;
    private iPet iSelectPet;
    private Dog dog = new Dog();
    private Cat cat = new Cat();

    private TamagotchiSubScene helpSubScene;
    private TamagotchiSubScene scoreSubScene;
    private TamagotchiSubScene choosePetSubScene;
    private TamagotchiSubScene unhiddenSubScene;
//    private TamagotchiSubScene feedPetSubScene;

    public Controller() {

        bigMainButtonList = new ArrayList<>();
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
            menu.setOnMouseClicked(event -> {
                for (SelectionMenu select : selectPetList) {
                    select.setIsFilledCircle(false);
                }
                menu.setIsFilledCircle(true);
                iSelectPet = menu.getiPet();
            });
        }

        petBox.setLayoutY(100);
        petBox.setLayoutX(0);
        return petBox;

    }

    private BigMainButton createPlayButton() {
        BigMainButton startButton = new BigMainButton("Play" );
        startButton.setLayoutY(250);
        startButton.setLayoutX(30);
        startButton.setOnAction(event -> {
            if (iSelectPet != null) {
                GameWindowManager gameManager = new GameWindowManager();
                try {
                    FileInputStream fileInputStream = new FileInputStream("src/main/resources/save.game" );
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    iSelectPet = (iPet) objectInputStream.readObject();
                    System.out.println("возраст после чтения  "+ iSelectPet.getAge());
                    checkDead(iSelectPet.getAge());

                } catch (IOException | ClassNotFoundException e ) {
                    e.printStackTrace();
                }

                if (iSelectPet.isCanBeBorn()) {
                    if (iSelectPet.getTimeDead() != null) {
                        iSelectPet = new Pet();
                    }

                    gameManager.startNewGame(mainStage, iSelectPet);
                }
            }
        });
        return startButton;
    }
    private void checkDead(long age){
        if(age == 0){
            System.out.println("AAAUUUUUUUUUUUUUUUU");
            iSelectPet.setLastFeed(LocalDateTime.now());
        }

    }

    private void createButton() {
        createStartButton();
        createScoreButton();
        createHelpButton();
        createExitButton();

    }

    private void createStartButton() {
        BigMainButton startButton = new BigMainButton("Start" );
        addMenuButton(startButton);

        startButton.setOnAction(event -> subSceneAppeared(choosePetSubScene));

    }

    private void addMenuButton(BigMainButton bigMainButton) {
        bigMainButton.setLayoutX(menuStartPositionOfButtonX);
        bigMainButton.setLayoutY(menuStartPositionOfButtonY + bigMainButtonList.size() * 80);
        bigMainButtonList.add(bigMainButton);
        mainPane.getChildren().add(bigMainButton);

    }

    private void createScoreButton() {
      BigMainButton scoretButton = new BigMainButton("Score" );
        addMenuButton(scoretButton);
        scoretButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subSceneAppeared(scoreSubScene);
            }
        });
    }


    private void createHelpButton() {
        BigMainButton helpButton = new BigMainButton("Help" );
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subSceneAppeared(helpSubScene);
            }
        });

    }

    private void createExitButton() {
        BigMainButton exitButton = new BigMainButton("Exit" );
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
