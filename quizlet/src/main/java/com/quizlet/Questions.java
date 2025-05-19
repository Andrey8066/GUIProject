package com.quizlet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Questions {
    protected ArrayList<Question> questions = new ArrayList<Question>();
    protected Database d;

    public Questions() throws SQLException {
        d = new Database("jdbc:postgresql://10.8.0.1:5432/guipdatabase", "postgres", "123456");

        for (String[] row : d.getAll("questions")) {
            this.questions.add(new Question(row[0], row[1], row[2], row[3], row[4]));
        }
    }

    public ArrayList<Question> getAll() {
        return this.questions;
    }

    public Question getQuestionByName(String name) throws SQLException {
        String[] questionSettings = this.d.getAllByParam("questions", "name", name).get(0);
        return new Question(questionSettings[0], questionSettings[1], questionSettings[2], questionSettings[3],
                questionSettings[4]);
    }

    public String getIdByName(String name) throws SQLException {
        String id = this.d.getDataByParam("questions", "id", "name", "'" + name + "'").get(0)[0];
        return id;
    }

    public String getNameById(String id) throws SQLException {
        String name = this.d.getDataByParam("questions", "name", "id", "'" + id + "'").get(0)[0];
        return name;
    }

    public ArrayList<String> getNameByTopic(String name) throws SQLException {
        return this.d.getDataByParamWithJoin("questions", "topics", "questions.topic = topics.id", "name",
                "topics.name", name);
    }

    public ArrayList<String> getIdByTopic(String name) throws SQLException {
        return this.d.getDataByParamWithJoin("questions", "topics", "questions.topic = topics.id", "id", "topics.name",
                name);
    }

    public void addNewQuestion(String question, String name, String answer, String topic) throws SQLException {

        this.d.insertIntoDatabase("questions", "question, name, answer, topic",
                "'" + question + "','" + name + "','" + answer + "','" + topic + "'");
    }

}

class Question {
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
