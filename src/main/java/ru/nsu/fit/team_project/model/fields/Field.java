package ru.nsu.fit.team_project.model.fields;

import java.util.UUID;

public abstract class Field {
    private final UUID id = UUID.randomUUID();
    private final String name;
    private Object value;

    public Field(String name) {
        this.name = name;
    }

    public UUID getID() {
        return id;
    }

    public abstract void addValue(Object value);
}

