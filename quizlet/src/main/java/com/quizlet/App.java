package com.quizlet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResource("/fonts/Baumans-Regular.ttf").toExternalForm(), 12);
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/quizlet/Welcome.fxml")));
        scene.getStylesheets().add(getClass().getResource("/com/quizlet/light-theme.css").toExternalForm());
        stage.setHeight(700);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        String styles = scene.getStylesheets().get(0);
        scene.setRoot(loadFXML(fxml));
        scene.getStylesheets().add(styles);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}