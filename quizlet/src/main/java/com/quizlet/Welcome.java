package com.quizlet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Welcome {
    
    @FXML    
    public void newQuiz() throws Exception{
        App.setRoot("SecondScene.fxml");
}
    @FXML    
    public void openQuizes() throws Exception{
        App.setRoot("Quizes");
}
    @FXML    
    public void Statistic() throws Exception{
            App.setRoot("/com/quizlet/SecondScene.fxml");
    }
}
