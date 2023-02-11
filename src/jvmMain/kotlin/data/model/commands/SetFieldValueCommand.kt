package data.model.commands

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
        model.objects
            .stream()
            .filter { o -> o.fields
                    .stream()
                    .anyMatch { f -> f.id == fieldId }
            }
            .findAny()
            .orElseThrow()
            .getFieldById(fieldId)
            .value = value

    }
}