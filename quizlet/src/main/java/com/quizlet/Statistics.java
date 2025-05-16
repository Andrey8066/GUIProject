package com.quizlet;

import java.sql.SQLException;

public class Statistics {
    protected Database d;
    public Statistics() throws SQLException{
        d = new Database("jdbc:postgresql://127.0.0.1:5432/topics", "postgres", "123456");
    }
    public void addRes(String answer, Integer questionId){

    }
}
