package com.quizlet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Topics { // Класс для работы с темами квизов
    protected ArrayList<Topic> topics = new ArrayList<Topic>();
    protected Database d;

    public Topics() throws SQLException { // Конструктор класса
        this.d = new Database("jdbc:postgresql://10.8.0.1:5432/guipdatabase", "postgres", "123456");

        for (String[] row : d.getAll("topics")) {
            this.topics.add(new Topic(row[0], row[1]));
        }
    }

    public ArrayList<Topic> getAll() { // Метод получения всех тем
        return this.topics;
    }

    public Topic getTopicByName(String name) { // Метод для получения объекта Topic по названию
        for (Topic topic : this.topics) {
            if (topic.getName() == name)
                return topic;
        }
        return null;
    }

    public String getIdByName(String name) throws SQLException { // Метод для получения id темы по ее названию
        return this.d.getDataByParam("topics", "id", "name", "'" + name + "'").get(0)[0];
    }

    public void addNewTopic(String name) throws SQLException { // Метод для добавления новой темы
        this.d.insertIntoDatabase("topics", "name", "'" + name + "'");
    }

    public void print() { // Метод для вывода всех тем и их id
        for (int i = 0; i < this.topics.size(); i++) {
            this.topics.get(i).print();
        }
    }
}

class Topic { // Класс объекта  topic
    protected int id;
    protected String Name;

    public Topic(String id, String name) throws SQLException {

        this.Name = name;
        this.id = Integer.parseInt(id);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.Name;
    }

    public void print() {
        System.out.println(this.id + " " + this.Name);
    }

}
