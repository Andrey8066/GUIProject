package com.quizlet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Questions { // Класс для работы с темами квизов
    protected ArrayList<Question> questions = new ArrayList<Question>();
    protected Database d;

    public Questions() throws SQLException { // Конструктор класса
        d = new Database("jdbc:postgresql://127.0.0.1:5432/guipdatabase", "postgres", "123456");

        for (String[] row : d.getAll("questions")) {
            this.questions.add(new Question(row[0], row[1], row[2], row[3], row[4]));
        }
    }

    public ArrayList<Question> getAll() { // Метод для получения всех объектов Question
        return this.questions;
    }

    public Question getQuestionByName(String name) throws SQLException { // Метод для получения объекта Question по
                                                                         // названию вопроса
        String[] questionSettings = this.d.getAllByParam("questions", "name", name).get(0);
        return new Question(questionSettings[0], questionSettings[1], questionSettings[2], questionSettings[3],
                questionSettings[4]);
    }

    public Question getQuestionByNameTopic(String name, String topic) throws SQLException { // Метод для получения объекта Question по
        // названию вопроса и темы
        String[] questionSettings = this.d.getAllBy2Param("questions", "name", name, "topic", topic).get(0);
        return new Question(questionSettings[0], questionSettings[1], questionSettings[2], questionSettings[3],
                questionSettings[4]);
    }

    public String getIdByName(String name) throws SQLException { // Метод для получения id объекта Question по названию
        String id = this.d.getDataByParam("questions", "id", "name", "'" + name + "'").get(0)[0];
        return id;
    }

    public String getNameById(String id) throws SQLException { // Метод для получения названия объекта Question по id
        String name = this.d.getDataByParam("questions", "name", "id", "'" + id + "'").get(0)[0];
        return name;
    }

    public ArrayList<String> getNameByTopic(String name) throws SQLException { // Метод для получения названий всех
                                                                               // объектов Question по теме
        return this.d.getDataByParamWithJoin("questions", "topics", "questions.topic = topics.id", "name",
                "topics.name", name);
    }

    public ArrayList<String> getIdByTopic(String name) throws SQLException { // Метод для получения id всех объектов
                                                                             // Question по теме
        return this.d.getDataByParamWithJoin("questions", "topics", "questions.topic = topics.id", "id", "topics.name",
                name);
    }

    public void addNewQuestion(String question, String name, String answer, String topic) throws SQLException { // Метод
                                                                                                                // для
                                                                                                                // добавления
                                                                                                                // нового
                                                                                                                // вопроса
        String[] param = { question, name, answer, topic };
        this.d.executeStatement(param);
    }

}

class Question { // Класс объекта Question
    protected int id;
    protected String question;
    protected String name;
    protected String answer;
    protected int topic;

    public Question(String id, String question, String name, String answer, String topic) throws SQLException {
        this.name = name;
        this.question = question;
        this.id = Integer.parseInt(id);
        this.topic = Integer.parseInt(topic);
        this.answer = answer;
    }

    public int getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getName() {
        return this.name;
    }

    public int getTopic() {
        return this.topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void print() {
        System.out.println(this.id + " " + this.question);
    }

}
