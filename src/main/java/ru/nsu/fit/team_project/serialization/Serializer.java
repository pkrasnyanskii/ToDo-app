package ru.nsu.fit.team_project.serialization;

import com.google.gson.Gson;
import ru.nsu.fit.team_project.model.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Serializer {
    public void serialize(Model model) {
        Gson gson = new Gson();
        String json = gson.toJson(model.getCommands());

        String file = "src/main/java/ru/nsu/fit/team_project/data/data_test.json";
        System.out.println(json);
        /*try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}
