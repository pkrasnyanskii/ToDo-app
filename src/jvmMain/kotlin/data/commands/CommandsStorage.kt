package data.commands

import data.commands.entity.Command
import data.serialization.Deserializer

class CommandsStorage(
    deserializer: Deserializer
) {
    val commands = deserializer.deserialize().toMutableList()

    fun addCommand(command: Command) {
        commands.add(command)
    }
}