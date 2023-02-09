package data.serialization

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import data.model.Model
import data.model.commands.Command
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException




class Serializer {

    private companion object {

        private const val FILE_PATH = "src/jvmMain/kotlin/data/local_data/commands.json"
    }

    fun serialize(model: Model) {
        val gson= GsonBuilder().setPrettyPrinting().create()

        FileWriter(FILE_PATH).use { writer -> gson.toJson(model.commands, writer) }
    }

    fun deserialize(): List<Command> {
        val gson = GsonBuilder().registerTypeAdapter(Command::class.java, CommandDeserializer()).create()
        var commands: List<Command>

        JsonReader(FileReader(FILE_PATH)).use { reader ->
            val commandsListType = object : TypeToken<List<Command>>() {}.type
            commands = gson.fromJson<List<Command>>(reader, commandsListType)
        }

        return commands
    }
}