package com.quizlet;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatisticsController {
    @FXML
    private ComboBox<String> ChooseDirrectoryCombo;
    @FXML
    private VBox StatisticsVBox;
    @FXML
    private TextArea Answer;
    private Topics topics;
    private Questions questions;
    private Statistics statistics;

    @FXML
    public void initialize() throws Exception {
        this.topics = new Topics();
        this.questions = new Questions();
        statistics = new Statistics();
        for (Topic topic : this.topics.getAll()) {
            ChooseDirrectoryCombo.getItems().add(new String(topic.getName()));
        }
    }

    @FXML
    public void handleDirrectorySelection() {
        StatisticsVBox.getChildren().clear();
        try {
            boolean f = true;
            for (String question_id : this.questions.getIdByTopic(ChooseDirrectoryCombo.getValue())) {
                System.out.println(question_id);
                HBox statisticHBox = new HBox();
                Label ticketName = new Label(questions.getNameById(question_id));
                Label percent = new Label(statistics.getPercentByQuestionId(question_id) + " %");
                statisticHBox.getChildren().addAll(ticketName, percent);
                StatisticsVBox.getChildren().add(statisticHBox);
                f = false;
            }
            if (f) {
                StatisticsVBox.getChildren().add(new Label("Вы еще не проходили вопросы по этой теме"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
