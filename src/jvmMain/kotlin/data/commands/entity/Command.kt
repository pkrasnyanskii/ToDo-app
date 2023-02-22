package data.commands.entity

import data.model.ObjectsStorage
import java.util.*

abstract class Command(
    private val commandName: String
) {

    val id: UUID = UUID.randomUUID()

    abstract fun execute(storage: ObjectsStorage)
}