package data.model

import data.commands.CommandExecutor

class Model(
    val storage: ObjectsStorage,
    commandExecutor: CommandExecutor,
) {

    init {
        commandExecutor.executeAll(storage)
    }
}