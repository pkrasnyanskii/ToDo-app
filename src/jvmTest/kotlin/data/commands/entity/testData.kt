package data.commands.entity

import data.model.ModelObject
import data.model.ObjectsStorage
import java.util.*

val testObjectId: UUID = UUID.randomUUID()

val testModelObject = ModelObject(
    id = testObjectId,
    type = "todo"
)

val testObjectsStorage = ObjectsStorage(mutableListOf(testModelObject))

val testFieldId: UUID = UUID.randomUUID()