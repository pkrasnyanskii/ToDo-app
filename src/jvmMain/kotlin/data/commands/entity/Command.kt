package data.commands.entity

import data.model.ObjectsStorage

abstract class Command(
    private val commandName: String
) {

    val id: Int = -1

    abstract fun execute(storage: ObjectsStorage)
}