package com.quizlet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Statistics {
    protected Database d;
    public Statistics() throws SQLException{
        d = new Database("jdbc:postgresql://10.8.0.1:5432/guipdatabase", "postgres", "123456");
    }
    public void addRes(int answer, String questionId) throws SQLException{
        String[] statistic = d.getAllByParam("statistics", "question_id", questionId).get(0);
        int countRightAnswers = Integer.parseInt(statistic[1]);
        int countAnswers = Integer.parseInt(statistic[2]);
        d.updateValueInDatabase("statistics", "count_right_answers", Integer.toString(countRightAnswers+answer), "question_id", questionId);
        d.updateValueInDatabase("statistics", "count_answers", Integer.toString(countAnswers+1), "question_id", questionId);
    }

    public ArrayList<String> getNamesByTopic(String name) throws SQLException {
        return this.d.getDataByParamWithJoin("statistics", "topics", "statistics.question_id = topics.id", "name", "topics.name", name);

    }

    public float getPercentByQuestionId(String id) throws SQLException {
        String[] data = this.d.getDataByParam("statistics", "count_answers, count_right_answers", "question_id", id).get(0);
        float count_answers = Float.parseFloat(data[0]);
        float count_right_answers = Float.parseFloat(data[1]);

        return count_right_answers/count_answers*100;

    }    
}
