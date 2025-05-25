package com.quizlet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.*;

import java.sql.SQLException;

import javafx.scene.Node;

public class CheckAnswerController {
    @FXML
    private ComboBox<String> ChooseDirrectoryCombo;
    @FXML
    private ComboBox<String> ChooseTicketCombo;
    @FXML
    private Label RightAnswer;
    @FXML
    private Label UserAnswer;
    @FXML
    private TextArea Answer;
    private Statistics statistics;
    private int question_id;

    @FXML
    public void initialize() throws Exception {
        this.statistics = new Statistics();

    }

    public void initData(String rightAnswer, int questionId, String userAnswer) {
        System.out.println(rightAnswer+ questionId+ userAnswer);
        this.question_id = questionId;
        RightAnswer.setText(rightAnswer);
        UserAnswer.setText(userAnswer);
    }

    public void handleRightAnswerButton(ActionEvent event) throws SQLException {
        this.statistics.addRes(1, Integer.toString(question_id));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    public void handleWrongAnswerButton(ActionEvent event) throws SQLException {
        this.statistics.addRes(0, Integer.toString(question_id));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
