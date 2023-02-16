package data.commands.entity

import data.model.Model
import java.util.*

class SetFieldValueCommand(
    private val fieldId: UUID,
    private val value: Any
) : Command(COMMAND_NAME) {

    private companion object {

        private const val COMMAND_NAME = "SetFieldValue"
    }

    override fun execute(model: Model) {
        model.getObjectFieldById(fieldId)
             .value = value
    }
}