package ru.nsu.fit.team_project.model;

public class StringField extends Field {
    String value;

    public StringField(String id, String name) {
        super(id, name);
    }

    @Override
    void addValue(Object value) {
        this.value = (String) value;
    }
}
