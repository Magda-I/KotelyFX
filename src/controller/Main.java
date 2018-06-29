package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene loginScene = new Scene(root, 800, 600);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Koteły");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
