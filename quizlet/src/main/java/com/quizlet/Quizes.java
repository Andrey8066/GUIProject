package com.quizlet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.SQLException;

public class Quizes {
    @FXML
    private ComboBox<String> ChooseDirrectoryCombo;
    @FXML
    private ComboBox<String> ChooseTicketCombo;
    @FXML
    private Label QuestionLabel;
    @FXML
    private TextArea TextArea;
    private Topics topics;
    private Questions questions;
        
    @FXML
    public void initialize() throws Exception{
        this.topics = new Topics();
        this.questions = new Questions();
        for (Topic topic : this.topics.getAll()){
            ChooseDirrectoryCombo.getItems().add(new String(topic.getName()));
        }

        for (Question question : this.questions.getAll()){
            ChooseTicketCombo.getItems().add(new String(question.getName()));
        }
        
    }
    @FXML    
    public void buttonAction(int id, ActionEvent event){
        try {
            App.setRoot("/com/quizlet/SecondScene.fxml");
        }
        catch (IOException e){
            System.err.println(e);
        }
    }
    @FXML
    public void handleTicketSelection() throws SQLException{
        QuestionLabel.setText(this.questions.getQuestionByName(ChooseTicketCombo.getValue()).getQuestion());
    }
    @FXML
    public void handleDirrectorySelection(){
        ChooseTicketCombo.getItems().clear();
        try {
            for (String questionName : this.questions.getQuestionByTopic(ChooseDirrectoryCombo.getValue())){
                System.out.println(questionName);
                ChooseTicketCombo.getItems().add(new String(questionName));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    public void handleNextButton() throws SQLException{
        Statistics statistics = new Statistics();
        statistics.addRes(this.TextArea.getText(), Integer.parseInt(questions.getIdByName(this.ChooseTicketCombo.getValue())));

    }
}
