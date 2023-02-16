package data.commands.entity

import data.model.Model
import data.model.ModelObject
import java.util.*

class CreateObjectCommand(
    private val objectId: UUID,
    private val objectType: String
) : Command(COMMAND_NAME) {

    private companion object {

        private const val COMMAND_NAME = "CreateObject"
    }

    override fun execute(model: Model) {
        model.addObject(
            ModelObject(objectId, objectType)
        )
    }
}