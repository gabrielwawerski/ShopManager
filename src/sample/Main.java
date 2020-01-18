package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.DataModel;

// TODO split data from view / controller
// so that i retain the data when switching back and forth between scenes!!!

// TODO find a way to add navbar to every app window programmatically
public class Main extends Application {
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_HEIGHT = 300;

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataModel model = DataModel.getInstance();

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
