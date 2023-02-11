package data.model.commands

import data.model.Field
import data.model.Model
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

    override fun execute(model: Model) {
        model.objects
            .stream()
            .filter { o -> o.id == objectId }
            .findAny()
            .orElseThrow()
            .addField(
                Field(
                    fieldId,
                    fieldName
                )
            )
    }
}