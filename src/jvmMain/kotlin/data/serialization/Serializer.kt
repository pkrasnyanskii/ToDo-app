package data.serialization

import com.google.gson.Gson
import data.commands.CommandsStorage
import java.io.FileWriter

class Serializer(
    private val storage: CommandsStorage,
    private val gson: Gson
) {

    private companion object {

        private const val FILE_PATH = "src/jvmMain/kotlin/data/local_data/commands.json"
    }

    fun serialize() =
        FileWriter(FILE_PATH).use { writer -> gson.toJson(storage.commands, writer) }
}