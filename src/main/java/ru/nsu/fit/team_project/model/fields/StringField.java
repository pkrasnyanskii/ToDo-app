package ru.nsu.fit.team_project.model.fields;

public class StringField extends Field {
    String value;

    public StringField(String name) {
        super(name);
    }

    @Override
    void addValue(Object value) {
        this.value = (String) value;
    }
}
