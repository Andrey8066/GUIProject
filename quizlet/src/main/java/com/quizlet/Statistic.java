package com.quizlet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Statistic {
    private final StringProperty name;
    private final StringProperty percent;

    public Statistic(String name, Float percent) {
        this.name = new SimpleStringProperty(name);
        this.percent = new SimpleStringProperty(String.format("%.1f%%", percent));
    }

    // Геттеры
    public String getName() { 
        return name.get();
    }

    public String getPercent() { 
        return percent.get();
    }

    // Сеттеры
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
}