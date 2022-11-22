package ru.nsu.fit.team_project.model.fields;

public class DoubleField extends Field {
    double value;

    public DoubleField(String id, String name) {
        super(id, name);
    }

    @Override
    void addValue(Object value) {
        this.value = (Double) value;
    }
}
