package ru.nsu.fit.team_project.model;

public class CommandExecutor {
    private final Model model;

    public CommandExecutor(Model model) {
        this.model = model;
    }

    public void executeAll() {
        model.getCommands().forEach(c -> c.execute(model));
    }
}
