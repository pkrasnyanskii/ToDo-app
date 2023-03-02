package data.commands.entity

import data.model.ObjectsStorage
import data.model.ModelObject
import java.util.*

class CreateObjectCommand(
    private val objectId: UUID,
    private val objectType: String
) : Command(COMMAND_NAME) {

    private companion object {

        private const val COMMAND_NAME = "CreateObjectCommand"
    }

    override fun execute(storage: ObjectsStorage) {
        storage.addObject(
            ModelObject(objectId, objectType)
        )
    }
}