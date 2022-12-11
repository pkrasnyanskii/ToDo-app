package ru.nsu.fit.team_project.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.nsu.fit.team_project.model.Model;
import ru.nsu.fit.team_project.model.commands.Command;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Serializer {
    private static final String file = "src/main/java/ru/nsu/fit/team_project/data/commands.json";

    public void serialize(Model model) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(model.getCommands(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Command> deserialize() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Command.class, new CommandDeserializer()).create();
        List<Command> commands;

        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            TypeToken<List<Command>> commandsListType = new TypeToken<>() {};
            commands = gson.fromJson(reader, commandsListType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return commands;
    }
}
