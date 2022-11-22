package ru.nsu.fit.team_project.serialization;

import com.google.gson.Gson;
import ru.nsu.fit.team_project.model.Model;

public class Serializer {
    public void serialize(Model model) {
        Gson gson = new Gson();
        String json = gson.toJson(model.getCommands());
    }
}
