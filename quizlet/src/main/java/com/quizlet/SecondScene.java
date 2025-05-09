package com.quizlet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SecondScene {
    @FXML
    private VBox menuContainer;
        
    @FXML
    public void initialize() throws Exception{
        
        Topics topics = new Topics();
        

        for (Topic topic : topics.getAll()){
            Button menuButton = new Button(topic.getName());
            menuButton.setOnAction(event -> buttonAction(0, event));
            menuButton.setPrefSize(200, 40);
            menuContainer.getChildren().add(menuButton);
        }
        
        Button createNewButton = new Button("Создать новый Квиз");
        createNewButton.setOnAction(event -> {
            
        });
        createNewButton.setPrefSize(200, 40);
        menuContainer.getChildren().add(createNewButton);
        
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
}
