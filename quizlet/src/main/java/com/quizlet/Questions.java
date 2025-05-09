package com.quizlet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Questions {
    protected ArrayList<Question> questions =  new ArrayList<Question>();

    public Questions() throws SQLException{
        Database d = new Database("jdbc:postgresql://127.0.0.1:5432/topics", "postgres", "123456");

        for (String[] row : d.getAll("questions")){
            this.questions.add(new Question(row[0], row[1], row[2], row[3], row[4]));
        }
    }

    public ArrayList<Question> getAll(){
        return this.questions;
    }

    public Question getQuestionByName(String name){
        for (Question question : this.questions){
            if (question.getName() == name) return question;
        }
        return null;
    }
}
class Question {
    protected int id;
    protected String question;
    protected String name;
    protected String answer;
    protected int topic;

    public Question(String id, String question, String name, String answer, String topic) throws SQLException{
        this.name = name;
        this.question = question;
        this.id = Integer.parseInt(id);
        this.topic = Integer.parseInt(topic);
        this.answer = answer;
        print();
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

    public void print(){
        System.out.println(this.id+ " " + this.question);
    }
    
}
