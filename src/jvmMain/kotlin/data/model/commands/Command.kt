package data.model.commands

import data.model.Model
import java.util.UUID

abstract class Command(
    private val commandName: String
) {

    val id: UUID = UUID.randomUUID()

    abstract fun execute(model: Model)
}