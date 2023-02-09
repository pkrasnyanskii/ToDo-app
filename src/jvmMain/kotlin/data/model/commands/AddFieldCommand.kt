package data.model.commands

import data.model.Model
import java.util.UUID

class AddFieldCommand(
    private val objectId: UUID,
    private val fieldId: UUID,
    private val fieldName: String,
    private val fieldType: String
) : Command(COMMAND_NAME) {

    private companion object {

        private const val COMMAND_NAME = "AddField"
    }

    override fun execute(model: Model) {
        TODO("Not yet implemented")
    }
}