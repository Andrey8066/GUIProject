package com.quizlet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Welcome {

    private boolean darkTheme = true;

    @FXML
    private Button themeToggleButton;

    @FXML
    private StackPane StackPane;

    private static Scene scene;

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
        
        this.scene = StackPane.getScene();
        if (scene == null) return;
        boolean check = scene.getStylesheets().get(0).contains("style.css");
        scene.getStylesheets().clear();
        if (check) {
            scene.getStylesheets().add(getClass().getResource("/com/quizlet/light-theme.css").toExternalForm());
            themeToggleButton.setText("☀");
        } else {
            scene.getStylesheets().add(getClass().getResource("/com/quizlet/style.css").toExternalForm());
            themeToggleButton.setText("🌙");
        }
        darkTheme = !darkTheme;
    }
}
