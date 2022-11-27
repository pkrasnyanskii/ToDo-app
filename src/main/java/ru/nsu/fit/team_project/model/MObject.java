package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.fields.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MObject {
    UUID id;
    String type;
    List<Field> fields = new ArrayList<>();

    public MObject() {

    }

    public MObject(UUID id, String type) {
        this.id = id;
        this.type = type;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public Field getField(UUID fieldID) {
        return fields.stream()
                     .filter(f -> f.getID().equals(id))
                     .findAny()
                     .orElseThrow();
    }

    public List<Field> getFields() {
        return fields;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
