package ru.nsu.fit.team_project;


import ru.nsu.fit.team_project.model.CommandExecutor;
import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.commands.Command;
import ru.nsu.fit.team_project.model.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Command> testData = new ArrayList<>();

        Model model = new Model();

        CommandExecutor ce = new CommandExecutor(model);
        ce.executeAll();

        MObject mObject = model.getObjects().get(0);
        Field field = mObject.getFields().get(0);
        System.out.println(field.getID());
        System.out.println(field);
    }
}