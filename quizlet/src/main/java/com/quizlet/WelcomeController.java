package com.quizlet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class WelcomeController {

    private boolean darkTheme = true;

    @FXML
    private Button themeToggleButton;

    @FXML
    private ImageView themeIcon;

    @FXML
    private StackPane StackPane;

    @FXML
    public void initialize() {
        
        Platform.runLater(()-> {
            setupHoverEffects();
            
        
        themeIcon
                .setImage(new Image(getClass().getResource(darkTheme ? "/com/quizlet/images/light_0_button.png"
                : "/com/quizlet/images/dark_0_button.png").toExternalForm()));
            });
        
    }

    private void setupHoverEffects() {
        Scene scene = StackPane.getScene();
        this.darkTheme = scene.getStylesheets().get(0).contains("light-theme.css");
        themeToggleButton.setOnMouseEntered(e -> {
            String path = darkTheme ? "/com/quizlet/images/light_1_button.png"
                    : "/com/quizlet/images/dark_1_button.png";
            themeIcon.setImage(new Image(getClass().getResource(path).toExternalForm()));
        });

        themeToggleButton.setOnMouseExited(e -> {
            String path = darkTheme ? "/com/quizlet/images/light_0_button.png"
                    : "/com/quizlet/images/dark_0_button.png";
            themeIcon.setImage(new Image(getClass().getResource(path).toExternalForm()));
        });
    }

    @FXML
    public void newQuiz() throws Exception {
        App.setRoot("NewQuiz");
    }

    @FXML
    public void openQuizes() throws Exception {
        App.setRoot("Quizes");
    }

    @FXML
    public void Statistic() throws Exception {
        App.setRoot("Statistics");
    }

    @FXML
    private void toggleTheme() {
        Scene scene = StackPane.getScene();
        if (scene == null)
            return;

        boolean check = scene.getStylesheets().get(0).contains("light-theme.css");
        scene.getStylesheets().clear();

        if (check) {
            scene.getStylesheets().add(getClass().getResource("/com/quizlet/style.css").toExternalForm());
            themeIcon.setImage(
                    new Image(getClass().getResource("/com/quizlet/images/dark_0_button.png").toExternalForm()));
        } else {
            scene.getStylesheets().add(getClass().getResource("/com/quizlet/light-theme.css").toExternalForm());
            themeIcon.setImage(
                    new Image(getClass().getResource("/com/quizlet/images/light_0_button.png").toExternalForm()));
        }

        darkTheme = !darkTheme;
    }
}
