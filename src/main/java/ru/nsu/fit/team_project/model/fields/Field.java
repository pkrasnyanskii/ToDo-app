package ru.nsu.fit.team_project.model.fields;

public abstract class Field {
    String id;
    String name;
    Object value;

    public Field(String id, String name) {
        this.id = id;
        this.name = name;
    }

    abstract void addValue(Object value);
}
