package data.commands

import data.model.Model

class CommandExecutor(
    private val storage: CommandsStorage,
    private val model: Model
) {

    fun executeAll() {
        storage.commands.forEach { it.execute(model) }
    }
}