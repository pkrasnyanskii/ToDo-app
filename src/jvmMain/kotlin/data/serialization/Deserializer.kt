package data.serialization

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import data.commands.entity.Command
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

class Deserializer(
    private val gson: Gson
) {

    private companion object {

        private const val FILE_PATH = "./commands.json"
    }

    fun deserialize(): List<Command> {
        try {
            JsonReader(FileReader(FILE_PATH)).use { reader ->
                val commandsListType = object : TypeToken<List<Command>>() {}.type
                return gson.fromJson(reader, commandsListType) ?: listOf()
            }
        } catch (e: FileNotFoundException) {
            File(FILE_PATH).createNewFile()
            return listOf()
        }
    }
}