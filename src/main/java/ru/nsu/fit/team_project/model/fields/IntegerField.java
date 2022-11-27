package ru.nsu.fit.team_project.model.fields;

public class IntegerField extends Field {
    int value;

    public IntegerField(String name) {
        super(name);
    }

    @Override
    public void addValue(Object value) {
        this.value = (Integer) value;
    }
}
