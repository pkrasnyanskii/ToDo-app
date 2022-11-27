package ru.nsu.fit.team_project.model.fields;

import java.util.UUID;

public abstract class Field {
    UUID id;
    String name;
    Object value;

    public Field(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    abstract void addValue(Object value);
}
