package com.quizlet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Statistics { // Класс для работы с отслеживанием результатов
    protected Database d;
    protected Questions q;

    public Statistics() throws SQLException { // Конструктор класса
        d = new Database("jdbc:postgresql://10.8.0.1:5432/guipdatabase", "postgres", "123456");
        q = new Questions();
    }

    public void addRes(int answer, String questionId) throws SQLException { // Метод для сохранения результата решения
                                                                            // вопроса
        String[] statistic = d.getAllByParam("statistics", "question_id", questionId).get(0);
        int countRightAnswers = Integer.parseInt(statistic[1]);
        int countAnswers = Integer.parseInt(statistic[2]);
        d.updateValueInDatabase("statistics", "count_right_answers", Integer.toString(countRightAnswers + answer),
                "question_id", questionId);
        d.updateValueInDatabase("statistics", "count_answers", Integer.toString(countAnswers + 1), "question_id",
                questionId);
    }

    public ArrayList<String> getNamesByTopic(String name) throws SQLException { // Метод для получения результатов
                                                                                // решения всех билетов по теме
        return this.d.getDataByParamWithJoin("statistics", "topics", "statistics.question_id = topics.id", "name",
                "topics.name", name);
    }

    public ArrayList<Statistic> getStat(String name) throws SQLException {
        ArrayList<Statistic> res = new ArrayList();
        for (String question_id : q.getIdByTopic(name)) {
            res.add(new Statistic(q.getNameById(question_id), getTriesByQuestionId(question_id),
                    getPercentByQuestionId(question_id)));
        }
        return res;
    }

    public int getTriesByQuestionId(String id) throws SQLException { // Метод для получения процента правильных
        // решений по билету
        String[] data = this.d.getDataByParam("statistics", "count_answers", "question_id", id)
                .get(0);
        int count_answers = Integer.parseInt(data[0]);

        return count_answers;

    }

    public float getPercentByQuestionId(String id) throws SQLException { // Метод для получения процента правильных
                                                                         // решений по билету
        String[] data = this.d.getDataByParam("statistics", "count_answers, count_right_answers", "question_id", id)
                .get(0);
        float count_answers = Float.parseFloat(data[0]);
        float count_right_answers = Float.parseFloat(data[1]);

        return count_right_answers / count_answers * 100;

    }

    public void clearTable() throws SQLException{
    }
}