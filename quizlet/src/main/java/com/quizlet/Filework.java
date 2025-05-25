package com.quizlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class Filework {
    Questions questions;
    Topics topics;
    public Filework() throws SQLException{
        this.topics = new Topics();
        this.questions = new Questions();
    }
    public void exportData() throws SQLException{
        String userHome = System.getProperty("user.home");
        
        // Создаем путь к папке "Документы"
        File documentsDir = new File(userHome, "Documents"); // Для Linux/Mac может быть "Документы"
        
        // Проверяем, существует ли папка, иначе создаем
        if (!documentsDir.exists()) {
            documentsDir.mkdirs();
        }
        
        // Создаем файл внутри папки "Документы"
        File file = new File(documentsDir, "example.txt");
        try (FileWriter writer = new FileWriter(file)) {
           for (Question question : questions.getAll()){
            writer.write(question.getName() + ";" + question.getQuestion() + ";" + question.getAnswer() + ";" + topics.getNameById(Integer.toString(question.getTopic())) + "\n");
           }
            System.out.println("Файл сохранен: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
