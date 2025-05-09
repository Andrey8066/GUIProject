package com.quizlet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class Quizes {
    @FXML
    private ComboBox<String> ChooseDirrectoryCombo;
    @FXML
    private ComboBox<String> ChooseTicketCombo;
    @FXML
    private Label QuestionLabel;

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
    public void handleTicketSelection(){
        QuestionLabel.setText(this.questions.getQuestionByName(ChooseTicketCombo.getValue()).getName());
    }
    @FXML
    public void handleDirrectorySelection(){
        ChooseTicketCombo.getItems().clear();
        try {
            for (Question question : this.questions.getAll()){
                System.out.println(this.topics.getTopicByName(ChooseDirrectoryCombo.getValue()).getName());
                if (question.getTopic() == this.topics.getTopicByName(ChooseDirrectoryCombo.getValue()).getId()){
                ChooseTicketCombo.getItems().add(new String(question.getName()));}
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
