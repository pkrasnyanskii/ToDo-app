package data.commands

import data.commands.entity.Command
import data.model.ObjectsStorage

class CommandExecutor(
    private val storage: CommandsStorage,
) {

    fun executeAll(objectStorage: ObjectsStorage) {
        this.storage.commands.forEach { it.execute(objectStorage) }
    }

    fun executeList(commands: List<Command>, objectStorage: ObjectsStorage) {
        commands.forEach { it.execute(objectStorage) }
    }
}