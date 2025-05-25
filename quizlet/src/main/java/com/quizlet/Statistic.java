package com.quizlet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Statistic {
    private final StringProperty name;
    private final StringProperty percent;
    private final StringProperty tries;

    public Statistic(String name, int tries, Float percent) {
        this.name = new SimpleStringProperty(name);
        this.percent = new SimpleStringProperty(String.format("%.1f%%", percent));
        this.tries = new SimpleStringProperty(Integer.toString(tries));
    }

    // Геттеры
    public String getName() {
        return name.get();
    }

    public String getPercent() {
        return percent.get();
    }

    public String getTries() {
        return tries.get();
    }

    // Сеттеры
    public void setTries(String tries) {
        this.tries.set(tries);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPercent(Float percent) {
        this.percent.set(String.format("%.1f%%", percent));
    }

    // Property-геттеры (важно для TableView!)
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty percentProperty() {
        return percent;
    }

    public StringProperty triesProperty() {
        return tries;
    }
}