package data.model.commands

import data.model.Model
import data.model.ModelObject
import java.util.UUID

class CreateObjectCommand(
    private val objectId: UUID,
    private val objectType: String
) : Command(COMMAND_NAME) {

    private companion object {

        private const val COMMAND_NAME = "CreateObject"
    }

    override fun execute(model: Model) {
        model.addObject(
            obj = ModelObject(
                id = objectId,
                type = objectType
            )
        )
    }
}