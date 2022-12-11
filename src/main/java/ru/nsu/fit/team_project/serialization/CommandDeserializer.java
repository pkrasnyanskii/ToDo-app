package ru.nsu.fit.team_project.serialization;

import com.google.gson.*;
import ru.nsu.fit.team_project.model.commands.Command;

import java.lang.reflect.Type;

public class CommandDeserializer implements JsonDeserializer<Command> {
    private static final String COMMAND_NAME_KEY = "commandName";
    private static final String COMMANDS_PACKAGE = "ru.nsu.fit.team_project.model.commands.";

    @Override
    public Command deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String className = jsonObject.get(COMMAND_NAME_KEY).getAsString();

        try {
            Class<?> clz = Class.forName(COMMANDS_PACKAGE + className);
            return context.deserialize(json, clz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
