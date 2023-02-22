package data.commands.entity

import data.model.Field
import data.model.ObjectsStorage
import java.util.*

class AddFieldCommand(
    private val objectId: UUID,
    private val fieldId: UUID,
    private val fieldName: String,
    private val fieldType: String
) : Command(COMMAND_NAME) {

    private companion object {

        private const val COMMAND_NAME = "AddField"
    }

    override fun execute(storage: ObjectsStorage) {
        storage.getObjectById(objectId)
            .addField(
                Field(fieldId, fieldName)
            )
    }
}