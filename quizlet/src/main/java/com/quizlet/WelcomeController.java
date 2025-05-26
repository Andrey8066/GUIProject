package com.quizlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class WelcomeController {

    private boolean darkTheme = true;
    @FXML
    private Button themeToggleButton;
    @FXML
    private ImageView themeIcon;
    @FXML
    private StackPane StackPane;

    Filework filework;
    @FXML
    public void initialize() throws SQLException{
        this.filework = new Filework();
        Platform.runLater(()-> {
            setupHoverEffects();
        themeIcon.setImage(new Image(getClass().getResource(darkTheme ? "/com/quizlet/images/light_0_button.png"
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

    @FXML
    public void exportQuizes() throws Exception {
        filework.exportData();
    }

    @FXML
    public void importQuizes() throws Exception {
       FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        
        // Установка начальной директории
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        
        // Добавление фильтров расширений
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Текстовые файлы", "*.csv"),
            new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );
        
        // Открытие диалога выбора файла
        File selectedFile = fileChooser.showOpenDialog(StackPane.getScene().getWindow());

        filework.importData(selectedFile);
        
    }

    @FXML
    public void clearQuizes() throws Exception{
        filework.clearDatabase();
    }
}
