package data.serialization

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import data.commands.entity.Command
import java.io.FileReader

class Deserializer(
    private val gson: Gson
) {

    private companion object {

        private const val FILE_PATH = "src/jvmMain/kotlin/data/local_data/commands.json"
    }

    fun deserialize(): List<Command> {
        var commands: List<Command>

        JsonReader(FileReader(FILE_PATH)).use { reader ->
            val commandsListType = object : TypeToken<List<Command>>() {}.type
            commands = gson.fromJson<List<Command>>(reader, commandsListType)
        }

        return commands
    }
}