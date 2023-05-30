package data.commands.entity

import data.model.ObjectsStorage
import java.util.*

class SetFieldValueCommand(
    private val fieldId: UUID,
    private val fieldValue: Any,
    override var id: Int = Companion.DEFAULT_ID
) : Command(CommandType.SET_FIELD_VALUE) {

    override fun execute(storage: ObjectsStorage) {
        storage.getObjectFieldById(fieldId)
             .value = fieldValue
    }
}