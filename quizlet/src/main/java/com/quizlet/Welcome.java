package com.quizlet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Welcome {

    private boolean darkTheme = true;

    @FXML
    private Button themeToggleButton;

    private static Scene scene;


    /*@FXML 
    public void initialize(){
        Platform.runLater(() -> 
        {
            this.scene = themeToggleButton.getScene();
            scene.getStylesheets().add(getClass().getResource("scene.css").toExternalForm());
        });
        
    }*/

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
        
        this.scene = themeToggleButton.getScene();
        if (scene == null) return;

        scene.getStylesheets().clear();
        if (darkTheme) {
            System.out.println("toggleTheme");
            
            scene.getStylesheets().add(getClass().getResource("/com/quizlet/light-theme.css").toExternalForm());
            themeToggleButton.setText("☀");
        } else {
            scene.getStylesheets().add(getClass().getResource("/com/quizlet/style.css").toExternalForm());
            themeToggleButton.setText("🌙");
        }
        darkTheme = !darkTheme;
    }
}
