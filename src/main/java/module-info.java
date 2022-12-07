module ru.nsu.fit.team_project.plannerscape {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.fit.team_project.plannerscape to javafx.fxml;
    exports ru.nsu.fit.team_project.plannerscape;
}