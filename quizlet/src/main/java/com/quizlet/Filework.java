package com.quizlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.sql.SQLException;

/*import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;*/

public class Filework {
    Questions questions;
    Topics topics;
    Database database;

    public Filework() throws SQLException{
        this.topics = new Topics();
        this.questions = new Questions();
        this.database = new Database("jdbc:postgresql://127.0.0.1:5432/guipdatabase", "postgres", "123456");
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
        File file = new File(documentsDir, "questions.csv");
        try (FileWriter writer = new FileWriter(file)) {
           for (Question question : questions.getAll()){
            writer.write(question.getName() + ";" + question.getQuestion() + ";" + question.getAnswer() + ";" + topics.getNameById(Integer.toString(question.getTopic())) + "\n");
           }
            System.out.println("Файл сохранен: " + file.getAbsolutePath());
            //createPDF();
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    /*public void createPDF(){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("itext-example.pdf"));
            document.open();
            document.add(new Paragraph("Hello World with iText!"));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void importData(File  filePath) throws SQLException{

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(";");
                System.out.println(parts);
                if (topics.getIdByName(parts[3])== null)
                {
                    topics.addNewTopic(parts[3]);
                }
                questions.addNewQuestion(parts[1], parts[0], parts[2], topics.getIdByName(parts[3]));
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public void clearDatabase() throws SQLException{
        System.out.println("TRUNCATE TABLE questions, topics, statistics");
        database.execCommand("TRUNCATE TABLE questions, topics, statistics");
        }
}
