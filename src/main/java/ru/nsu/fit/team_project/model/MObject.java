package ru.nsu.fit.team_project.model;

import java.util.List;

public class MObject {
    String id;
    List<Field> fields;

    void addField(Field field) {
        fields.add(field);
    }
}
