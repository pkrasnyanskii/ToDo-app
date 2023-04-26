package data.commands

import data.commands.entity.Command
import data.serialization.Deserializer

class CommandsStorage(
    deserializer: Deserializer
) {
    var commands = deserializer.deserialize().toMutableList()

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun getMaxCommandId() =
        commands.maxWith(Comparator.comparingInt {it.id}).id
}