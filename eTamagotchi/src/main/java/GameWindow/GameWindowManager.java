package GameWindow;

//import GamePets.Pets;

import GamePets.iPet;
import javafx.animation.AnimationTimer;
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
    private int angle;
    private AnimationTimer gameTimer;

    private Stage menuStage;
    private ImageView petImage;

    private final static String bookPath = "book.png";
    private final static String vasePath = "vase.png";

    private ImageView[] book;
    private ImageView[] vase;
    private Random positionGenerator;

    private ImageView[] heart;
    private InfoLabel scoreInfoLabel;
    private int playerLife;
    private int score;

    private final static int miniGameElementRadius = 16;
    private final static int miniGamePetRadius = 55;

    LocalDateTime startTime = LocalDateTime.now();

    private iPet currentPet;
    private long petAge;

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
        gameStage.setScene(gameScene);
    }

    public void startNewGame(Stage stage, iPet choosenPet) {
        currentPet = choosenPet;
        menuStage = stage;
        menuStage.hide();
        createBackground();
        creatPet(choosenPet);
        createMiniGameElements(choosenPet);

        createGameFrame();
        System.out.println("ДАТА ДАТА" + startTime);
        System.out.println("ВОЗРАСТ ВО ВРЕМЯ ИГРЫЭ " + currentPet.getAge());
//        startTime = LocalDateTime.now();

        gameStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("NOT EXIT WAS PUSHED" );
                System.out.println("ВОЗРАСТ" + petAge + " минут, " );
                try {
                    currentPet.setAge(currentPet.getAge() + petAge);
                    FileOutputStream outputStream = new FileOutputStream("save.game" );
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(currentPet);
                    objectOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

//                ****

            }
        });
        gameStage.show();
    }

    private void createMiniGameElements(iPet choosenPet) {

        playerLife = 2;
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


    private void setMiniGameElementPosition(ImageView image) {

        image.setLayoutX(positionGenerator.nextInt(483));
        image.setLayoutY(-(positionGenerator.nextInt(300)));
    }

    private void moveMiniGameElement() {
        for (int i = 0; i < book.length; i++) {
            book[i].setLayoutY(book[i].getLayoutY() + 5);
            book[i].setRotate(book[i].getRotate() + 3);
        }

        for (int i = 0; i < vase.length; i++) {
            vase[i].setLayoutY(vase[i].getLayoutY() + 7);
            vase[i].setRotate(vase[i].getRotate() + 4);
        }
    }

    private void ifMiniGameElementLanded() {
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
//                changeBackground();
                moveMiniGameElement();
                ifMiniGameElementLanded();
                ifElementCollide();
                movePet();


                petAge = ChronoUnit.MINUTES.between(startTime, LocalDateTime.now());
                System.out.println("Время работы " + petAge);

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

    private void moveImagePet() {

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

    private void changeBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);

        if (gridPane2.getLayoutY() >= 600) {
            gridPane2.setLayoutY(600);
        }

        if (gridPane1.getLayoutY() >= 600) {
            gridPane1.setLayoutY(600);
        }
    }

    //    ******
    private void ifElementCollide() {


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

    //    ******
    private void removeLife() {
        gamePane.getChildren().remove(heart[playerLife]);
        playerLife--;
        if (playerLife < 0) {
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double distanceBetweenElements(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
