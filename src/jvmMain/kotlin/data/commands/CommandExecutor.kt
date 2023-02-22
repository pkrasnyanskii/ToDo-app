package data.commands

import data.model.ObjectsStorage

class CommandExecutor(
    private val storage: CommandsStorage,
) {

    fun executeAll(storage: ObjectsStorage) {
        this.storage.commands.forEach { it.execute(storage) }
    }
}