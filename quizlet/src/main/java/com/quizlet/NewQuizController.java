package com.quizlet;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewQuizController {
    @FXML
    private ComboBox<String> ChooseDirrectoryCombo;
    @FXML
    private HBox ChooseDirrectory;
    @FXML
    private VBox NewQuizVBox;
    @FXML
    private TextArea NewQuizQuestion;
    @FXML
    private TextArea NewQuizName;
    @FXML
    private TextArea NewQuizAnswer;
    @FXML
    private TextArea Answer;
    private Topics topics;
    private Questions questions;
    private TextArea newTopic;
    private String newQuizTopic;
    private String newTopicOption = "Новая тема";

    @FXML
    public void initialize() throws Exception {
        this.topics = new Topics();
        this.questions = new Questions();
        for (Topic topic : this.topics.getAll()) {
            ChooseDirrectoryCombo.getItems().add(new String(topic.getName()));
        }
        ChooseDirrectoryCombo.getItems().add(newTopicOption);
    }

    @FXML
    public void handleDirrectorySelection() {
        try {
            System.out.println(ChooseDirrectoryCombo.getValue());
            if (ChooseDirrectoryCombo.getValue() == "Новая тема") {

                this.newTopic = new TextArea();
                newTopic.setPrefSize(100, 20);
                ChooseDirrectory.getChildren().add(newTopic);
            } else {
                newQuizTopic = ChooseDirrectoryCombo.getValue();
                ChooseDirrectory.getChildren().remove(newTopic);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void handleSaveQuizButton() throws SQLException {

        newQuizTopic = this.ChooseDirrectoryCombo.getValue();
        if (newQuizTopic == newTopicOption) {
            newQuizTopic = newTopic.getText();
            this.topics.addNewTopic(newQuizTopic);
        }
        this.questions.addNewQuestion(this.NewQuizQuestion.getText(), this.NewQuizName.getText(),
                this.NewQuizAnswer.getText(), this.topics.getIdByName(this.newQuizTopic));
    }

    @FXML
    public void handleMainMenuButton() throws SQLException {
        try {
            App.setRoot("Welcome");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
