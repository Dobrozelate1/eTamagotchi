package GameWindow;

//import GamePets.Pets;

import GameModel.MenuButton;
import GamePets.iPet;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class GameWindowManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    //530, 560
    private static final int gameWidth = 530;
    private static final int gameHeight = 560;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String backgroundImagePath = "room_background.png";
    private final static String backgroundImagePathDark = "room_background_dark.png";

    private boolean isLeftPushed = false;
    private boolean isRightPushed = false;
    private AnimationTimer gameTimer;

    private Stage menuStage;
    private ImageView petImage;

    private final static String bookPath = "book.png";
    private final static String vasePath = "vase.png";

    private ImageView[] book;
    private ImageView[] vase;
    private ImageView food;
    private Random positionGenerator;

    private ImageView[] heart;
    private InfoLabel scoreInfoLabel;
    private int playerLife;
    private long scoreAfterFeed;

    private final static int miniGameElementRadius = 16;
    private final static int miniGamePetRadius = 55;

    LocalDateTime startTime = LocalDateTime.now();

    private iPet currentPet;
    private long petAge;
    private  boolean startMiniGame;

//    FileOutputStream outputStream;
//    ObjectOutputStream objectOutputStream;

    public GameWindowManager() {
        invokeInitStage();
        initKeyListener();
        positionGenerator = new Random();
    }

    private void initKeyListener() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.LEFT) {
                    isLeftPushed = true;

                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightPushed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.LEFT) {
                    isLeftPushed = false;

                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightPushed = false;
                }

            }
        });
    }

    private void invokeInitStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, gameWidth, gameHeight);
        gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
    }

    public void startNewGame(Stage stage, iPet choosenPet) {
        createGameButton();
        createFeedButton();
        currentPet = choosenPet;
        food = new ImageView(currentPet.getFoodImageOfPet());
        food.setVisible(false);
        gamePane.getChildren().add(food);
        System.out.println("JIZNEI" + currentPet.getLifes());
        menuStage = stage;
        menuStage.hide();
        createBackground();
        creatPet(choosenPet);
        createTamagotchiGameElements(choosenPet);
//        createMiniGameElements(choosenPet);

        createGameFrame();
        gameStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("NOT EXIT WAS PUSHED" );
                try {
                    currentPet.setAge(currentPet.getAge() + petAge);
                    FileOutputStream outputStream = new FileOutputStream("save.game" );
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(currentPet);
                    objectOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        gameStage.show();
    }
    private void createTamagotchiGameElements(iPet choosenPet){
        scoreInfoLabel = new InfoLabel("Возраст: " + currentPet.getAge());
        scoreInfoLabel.setLayoutY(350);
        scoreInfoLabel.setLayoutX(20);
        gamePane.getChildren().add(scoreInfoLabel);

        heart = new ImageView[3];

        for (int i = 0; i < heart.length; i++) {
            heart[i] = new ImageView(choosenPet.getHeartPath());
            heart[i].setLayoutX(375 + (i * 40));
            heart[i].setLayoutY(50);
            gamePane.getChildren().add(heart[i]);
        }

    }



    private void createMiniGameElements(iPet choosenPet) {

//        scoreInfoLabel = new InfoLabel("Возраст: " + currentPet.getAge());
//        scoreInfoLabel.setLayoutY(350);
//        scoreInfoLabel.setLayoutX(20);
//        gamePane.getChildren().add(scoreInfoLabel);
//
//        heart = new ImageView[3];


//        for (int i = 0; i < heart.length; i++) {
//            heart[i] = new ImageView(choosenPet.getHeartPath());
//            heart[i].setLayoutX(375 + (i * 40));
//            heart[i].setLayoutY(50);
//            gamePane.getChildren().add(heart[i]);
//        }


        book = new ImageView[2];
        for (int i = 0; i < book.length; i++) {
            book[i] = new ImageView(bookPath);
            setMiniGameElementPosition(book[i]);
            gamePane.getChildren().add(book[i]);
        }
        vase = new ImageView[3];
        for (int i = 0; i < vase.length; i++) {

            vase[i] = new ImageView(vasePath);
            setMiniGameElementPosition(vase[i]);
            gamePane.getChildren().add(vase[i]);
        }

    }




    private void setFeedElementPosition() {
        food.setLayoutX(positionGenerator.nextInt(400));
        System.out.println("LayoutX FOOOOD" + food.getLayoutX());
        food.setLayoutY(520);
    }
    private void setMiniGameElementPosition(ImageView image) {

        image.setLayoutX(positionGenerator.nextInt(483));
        image.setLayoutY(-(positionGenerator.nextInt(300)));
    }

    private void moveMiniGameElement() {
        if (startMiniGame) {
            for (int i = 0; i < book.length; i++) {
                book[i].setLayoutY(book[i].getLayoutY() + 5);
                book[i].setRotate(book[i].getRotate() + 3);
            }

            for (int i = 0; i < vase.length; i++) {
                vase[i].setLayoutY(vase[i].getLayoutY() + 7);
                vase[i].setRotate(vase[i].getRotate() + 4);
            }
        }
    }

    private void ifMiniGameElementLanded() {
        if (startMiniGame) {
            for (int i = 0; i < book.length; i++) {
                if (book[i].getLayoutY() > 530) {
                    setMiniGameElementPosition(book[i]);
                }
            }

            for (int i = 0; i < vase.length; i++) {
                if (vase[i].getLayoutY() > 530) {
                    setMiniGameElementPosition(vase[i]);
                }
            }
        }
    }


    private void creatPet(iPet newPet) {
        petImage = new ImageView(newPet.getIdleImageOfPet());
        petImage.setLayoutX(gameWidth - 96);
        petImage.setLayoutY(gameHeight - 107);
        gamePane.getChildren().add(petImage);
    }

    private void createGameFrame() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveMiniGameElement();
                ifMiniGameElementLanded();
                ifElementCollide();
                movePet();
                if (food.isVisible()) {
                    eatFood();
                }
                currentPet.setAge(ChronoUnit.MINUTES.between(startTime, LocalDateTime.now()) + currentPet.getAge());
                scoreInfoLabel.setText("Возраст: " + currentPet.getAge());
                ChronoUnit.MINUTES.between(startTime, LocalDateTime.now());
                scoreFeed();
                if (scoreFeed() > 2) {
                    System.out.println("SCOLKO BEZ EDI" + scoreFeed());
                    removeLife();
                    currentPet.setLastFeed(LocalDateTime.now());
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream("src/main/resources/save.game" );
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(currentPet);
                    objectOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        gameTimer.start();
    }

    private void createFeedButton() {
        MenuButton feedButton = new MenuButton("Feed" );
        feedButton.setLayoutX(400);
        feedButton.setLayoutY(155);
        gamePane.getChildren().add(feedButton);
        feedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!food.isVisible()) {
                    setFeedElementPosition();
                    food.setVisible(true);
                    currentPet.setLastFeed(LocalDateTime.now());
                    eatFood();
                }
            }
        });

    }

    private void createGameButton() {
        MenuButton gameButton = new MenuButton("Game" );
        gameButton.setLayoutX(460);
        gameButton.setLayoutY(155);
        //            setPrefHeight(20);
        gameButton.setPrefWidth(60);
        gamePane.getChildren().add(gameButton);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!startMiniGame){
                    createMiniGameElements(currentPet);
                    startMiniGame = true;
                }else
                    startMiniGame = false;


            }
        });


    }

    private long scoreFeed() {
        scoreAfterFeed = ChronoUnit.MINUTES.between(currentPet.getLastFeed(), LocalDateTime.now());
//        System.out.println("ПОСЛЕ ПОЕЛ" + scoreAfterFeed);
        return scoreAfterFeed;
    }

    private void moveImagePet() {

    }

    private void Feed() {
        currentPet.setLastFeed(LocalDateTime.now());
    }

    private void movePet() {
        if (isLeftPushed && !isRightPushed) {
            if (petImage.getLayoutX() > -20) {
                petImage.setLayoutX(petImage.getLayoutX() - 3);
            }

        }
        if (isRightPushed && !isLeftPushed) {
            if (petImage.getLayoutX() < 450) {
                petImage.setLayoutX(petImage.getLayoutX() + 3);
            }
        }
        if (isRightPushed && isLeftPushed) {

        }
        if (!isRightPushed && !isLeftPushed) {

        }
    }

    private void feedPet() {


    }

    private void createBackground() {
        Image roomBackground = new Image("room_background.png" );
        BackgroundImage background = new BackgroundImage(roomBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground((new Background(background)));
    }

//    private void changeBackground() {
//        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
//        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);
//
//        if (gridPane2.getLayoutY() >= 600) {
//            gridPane2.setLayoutY(600);
//        }
//
//        if (gridPane1.getLayoutY() >= 600) {
//            gridPane1.setLayoutY(600);
//        }
//    }

    //    ******
    private void ifElementCollide() {
        if (startMiniGame) {

//        for (int i = 0; i < book.length; i++) {
//            if (miniGameElementRadius + miniGamePetRadius > distanceBetweenElements(
//                    petImage.getLayoutX()+105, book[i].getLayoutX()+55,
//                    petImage.getLayoutY()+95, book[i].getLayoutY()+25)) {
//                removeLife();
//                setMiniGameElementPosition(book[i]);
//            }
//        }
//        for (int i = 0; i < vase.length; i++) {
//            if (miniGameElementRadius + miniGamePetRadius > distanceBetweenElements(
//                    petImage.getLayoutX()+105, vase[i].getLayoutX()+55,
//                    petImage.getLayoutY()+95, vase[i].getLayoutY()+55)) {
//
//               removeLife();
//                setMiniGameElementPosition(vase[i]);
//            }
//        }
        }
    }

    //    ******
    private void removeLife() {
//        gamePane.getChildren().remove(heart[playerLife]);
        gamePane.getChildren().remove(heart[currentPet.getLifes()]);
        currentPet.setLifes(currentPet.getLifes() - 1);
        if (currentPet.getLifes() < 0) {
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private void eatFood() {
        if (petImage.getLayoutX() > food.getLayoutX()) {
            petImage.setLayoutX(petImage.getLayoutX() - 3);
        } else if (petImage.getLayoutY() < food.getLayoutX()) {
            petImage.setLayoutX(petImage.getLayoutX() + 3);
        } else {
//            gamePane.getChildren().remove(food);
            addLife();
            food.setVisible(false);
        }
//    addLife();
//    food = null;

    }

    private void addLife() {

        if (currentPet.getLifes() < 2) {
            System.out.println("ZIJNEI POSLE ADD BUTTON" + currentPet.getLifes());
            currentPet.setLifes(currentPet.getLifes() + 1);
            gamePane.getChildren().add(heart[currentPet.getLifes()]);
        }
    }

    private double distanceBetweenElements(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
