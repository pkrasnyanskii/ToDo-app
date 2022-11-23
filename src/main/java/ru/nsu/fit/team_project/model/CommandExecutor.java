package ru.nsu.fit.team_project.model;

import ru.nsu.fit.team_project.model.commands.Command;

import java.util.List;

//todo transfer current db state to commands
public class CommandExecutor {
    private final Model model;

    public CommandExecutor(Model model) {
        this.model = model;
    }

    public void executeAll() {
        model.commands.forEach(c -> c.execute(model));
    }
}
