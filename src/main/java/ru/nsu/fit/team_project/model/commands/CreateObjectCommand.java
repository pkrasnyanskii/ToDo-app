package ru.nsu.fit.team_project.model.commands;

import ru.nsu.fit.team_project.model.MObject;
import ru.nsu.fit.team_project.model.Model;

public class CreateObjectCommand extends Command {
    public CreateObjectCommand() {

    }

    @Override
    public void execute(Model model) {
        model.addObject(new MObject());
    }
}
