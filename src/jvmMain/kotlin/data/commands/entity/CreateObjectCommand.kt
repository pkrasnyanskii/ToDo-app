package data.commands.entity

import data.model.ObjectsStorage
import data.model.ModelObject
import java.util.*

class CreateObjectCommand(
    private val objectId: UUID,
    private val objectType: String,
    override var id: Int = Companion.DEFAULT_ID
) : Command(CommandType.CREATE_OBJECT_COMMAND) {

    override fun execute(storage: ObjectsStorage) {
        if (!storage.objects
                .stream()
                .filter { obj -> obj.id == objectId }
                .findFirst()
                .isPresent
        ) {
            storage.addObject(ModelObject(objectId, objectType))
        }
    }
}