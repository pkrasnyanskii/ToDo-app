package ru.nsu.fit.team_project.model;

public class CommandExecutor {
    private final Model model;

    public CommandExecutor(Model model) {
        this.model = model;
    }

    public void executeAll() {
        if (model.getCommands() != null) {
            model.getCommands().forEach(c -> c.execute(model));
        }
    }
}
