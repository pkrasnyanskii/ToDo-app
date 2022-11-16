package ru.nsu.fit.team_project.model;

public class Field {
    String id;
    String name;
    String value;

    public Field(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addValue(String value) {
        this.value = value;
    }
}
