package ru.nsu.fit.team_project;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.nsu.fit.team_project.model.CommandExecutor;
import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.commands.AddFieldCommand;
import ru.nsu.fit.team_project.model.commands.Command;
import ru.nsu.fit.team_project.model.commands.CreateObjectCommand;
import ru.nsu.fit.team_project.model.commands.SetFieldValueCommand;
import ru.nsu.fit.team_project.model.fields.Field;
import ru.nsu.fit.team_project.serialization.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        List<Command> testData = new ArrayList<>();

        Model model = new Model();

        UUID testId = UUID.randomUUID();
        Command createObj = new CreateObjectCommand("task");
        Command addField = new AddFieldCommand(testId, "taskName", "String");
        Command setFieldValue = new SetFieldValueCommand(testId, "Task1");

        model.addCommand(createObj);
        model.addCommand(addField);
        //model.addCommand(setFieldValue);

        Serializer serializer = new Serializer();

        try {
            serializer.serialize(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<Command> deserialized = serializer.deserialize();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            System.out.println(gson.toJson(deserialized));

        } catch (IOException e) {
            e.printStackTrace();
        }

        CommandExecutor ce = new CommandExecutor(model);
        ce.executeAll();

        MObject mObject = model.getObjects().get(0);
        Field field = mObject.getFields().get(0);
        System.out.println(field.getID());
    }
}