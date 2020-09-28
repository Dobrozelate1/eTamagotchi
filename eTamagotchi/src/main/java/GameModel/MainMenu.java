package GameModel;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            Controller controller = new Controller();
            primaryStage = controller.getMainStage();
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
