package data.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import data.commands.entity.Command
import data.commands.entity.CommandType
import java.lang.reflect.Type

class CommandDeserializer : JsonDeserializer<Command> {

    private companion object {

        private const val COMMAND_TYPE_KEY = "commandType"
        private const val COMMANDS_PACKAGE = "data.commands.entity."
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Command {
        val jsonObject = json.asJsonObject
        val className = CommandType.valueOf(jsonObject.get(COMMAND_TYPE_KEY).asString)

        try {
            val clz = Class.forName(COMMANDS_PACKAGE + className)
            return context.deserialize(json, clz)
        } catch (e: ClassNotFoundException) {
            throw e
        }
    }
}