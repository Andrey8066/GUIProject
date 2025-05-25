package com.quizlet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.*;

import java.io.IOException;
import java.sql.SQLException;

public class QuizesController {
    @FXML
    private ComboBox<String> ChooseDirrectoryCombo;
    @FXML
    private ComboBox<String> ChooseTicketCombo;
    @FXML
    private Label QuestionLabel;
    @FXML
    private TextArea Answer;
    private Topics topics;
    private Questions questions;
    private Question question;

    @FXML
    public void initialize() throws Exception {
        this.topics = new Topics();
        this.questions = new Questions();
        for (Topic topic : this.topics.getAll()) {
            ChooseDirrectoryCombo.getItems().add(new String(topic.getName()));
        }

        for (Question question : this.questions.getAll()) {
            ChooseTicketCombo.getItems().add(new String(question.getName()));
        }

    }

    @FXML
    public void buttonAction(int id, ActionEvent event) {
        try {
            App.setRoot("/com/quizlet/SecondScene.fxml");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleTicketSelection() throws SQLException {
        this.question = this.questions.getQuestionByName(ChooseTicketCombo.getValue());
        QuestionLabel.setText(question.getQuestion());

    }

    @FXML
    public void handleDirrectorySelection() {
        ChooseTicketCombo.getItems().clear();
        try {
            for (String questionName : this.questions.getNameByTopic(ChooseDirrectoryCombo.getValue())) {
                ChooseTicketCombo.getItems().add(new String(questionName));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void handleNextButton() throws Exception {
        save();
    }

    @FXML
    public void handleMainMenuButton() throws SQLException {
        try {
            App.setRoot("Welcome");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void save() throws Exception {
        Stage checkAnswerstage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("checkAnswer.fxml"));
        Parent root = loader.load();

        CheckAnswerController controller = loader.getController();
        controller.initData(this.question.getAnswer(), this.question.getId(), this.Answer.getText());

        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(this.QuestionLabel.getScene().getStylesheets().get(0));

        this.Answer.clear();
        this.QuestionLabel.setText(null);

        checkAnswerstage.setScene(newScene);
        checkAnswerstage.show();
    }
}
