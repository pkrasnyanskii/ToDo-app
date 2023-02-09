package data.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import data.model.commands.Command
import java.lang.reflect.Type

class CommandDeserializer : JsonDeserializer<Command> {

    private companion object {

        private const val COMMAND_NAME_KEY = "commandName"
        private const val COMMANDS_PACKAGE = "data.model.commands."
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Command {
        val jsonObject = json.asJsonObject
        val className = jsonObject.get(COMMAND_NAME_KEY).asString

        try {
            val clz = Class.forName(COMMANDS_PACKAGE + className)
            return context.deserialize(json, clz)
        } catch (e: ClassNotFoundException) {
            throw e
        }
    }
}