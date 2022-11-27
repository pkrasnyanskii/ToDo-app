package ru.nsu.fit.team_project.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.nsu.fit.team_project.model.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Serializer {
    public void serialize(Model model) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(model.getCommands());

        String file = "src/main/java/ru/nsu/fit/team_project/data/commands.json";

        //debug data
        System.out.println("Serialized commands:");
        System.out.println(json);
        //

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(json);
        }
    }
}
