package com.quizlet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Topics {
    protected ArrayList<Topic> topics =  new ArrayList<Topic>();

    public Topics() throws SQLException{
        Database d = new Database("jdbc:postgresql://138.124.113.97:5432/guipdatabase", "postgres", "123456");

        for (String[] row : d.getAll("topics")){
            this.topics.add(new Topic(row[0], row[1]));
        }
    }

    public ArrayList<Topic> getAll(){
        return this.topics;
    }

    public Topic getTopicByName(String name){
        for (Topic topic : this.topics){
            if (topic.getName() == name) return topic;
        }
        return null;
    }
    public void print(){
        for (int i = 0; i < this.topics.size(); i++){
            this.topics.get(i).print();
        }
    }
}
class Topic {
    protected int id;
    protected String Name;

    public Topic(String id, String name) throws SQLException{

        this.Name = name;
        this.id = Integer.parseInt(id);
        print();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.Name;
    }
    public void print(){
        System.out.println(this.id+ " " + this.Name);
    }
    
}
