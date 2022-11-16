package ru.nsu.fit.team_project.model;

public class IntegerField extends Field {
    int value;

    public IntegerField(String id, String name) {
        super(id, name);
    }

    @Override
    void addValue(Object value) {
        this.value = (Integer) value;
    }
}
