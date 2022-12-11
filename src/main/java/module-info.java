module ru.nsu.fit.team_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports ru.nsu.fit.team_project;
    exports ru.nsu.fit.team_project.model;
    opens ru.nsu.fit.team_project.model.commands to com.google.gson;
    opens ru.nsu.fit.team_project to javafx.fxml;
}