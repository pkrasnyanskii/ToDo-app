package ru.nsu.fit.team_project.model.fields;

import java.util.UUID;

public abstract class Field {
    private final UUID id;
    private final String name;
    private Object value;

    public Field(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getID() {
        return id;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " = " + value;
    }
}

