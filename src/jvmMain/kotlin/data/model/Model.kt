package data.model

import data.commands.CommandExecutor
import data.commands.CommandsStorage

class Model(
    val commandsStorage: CommandsStorage,
    val objectsStorage: ObjectsStorage,
    val commandExecutor: CommandExecutor,
) {

    init {
        commandExecutor.executeAll(objectsStorage)
    }
}