package ru.nsu.fit.team_project;


import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.commands.AddFieldCommand;
import ru.nsu.fit.team_project.model.commands.Command;
import ru.nsu.fit.team_project.model.commands.CreateObjectCommand;
import ru.nsu.fit.team_project.serialization.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        List<Command> testData = new ArrayList<>();

        Model model = new Model();

        UUID testId = UUID.randomUUID();
        Command createObj = new CreateObjectCommand("task");
        Command addField = new AddFieldCommand(testId, "test_field", "String");

        model.addCommand(createObj);
        model.addCommand(addField);

        Serializer serializer = new Serializer();
        serializer.serialize(model);
    }
}