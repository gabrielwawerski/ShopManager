package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.app.database.DatabaseHandler;
import sample.app.Context;

// TODO split data from view / controller
// so that i retain the data when switching back and forth between scenes!!!

// TODO switch double to BigDecimal for representing moneys
public class Main extends Application {
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_HEIGHT = 300;

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Context context = Context.getInstance();
        context.init();
        context.db.initDatabase();

        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage = primaryStage;
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        DatabaseHandler.getInstance().close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
