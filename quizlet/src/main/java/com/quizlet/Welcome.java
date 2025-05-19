package com.quizlet;
import javafx.fxml.FXML;

public class Welcome {
    
    @FXML    
    public void newQuiz() throws Exception{
        App.setRoot("NewQuiz");
}
    @FXML    
    public void openQuizes() throws Exception{
        App.setRoot("Quizes");
}
    @FXML    
    public void Statistic() throws Exception{
            App.setRoot("Statistics");
    }
}
