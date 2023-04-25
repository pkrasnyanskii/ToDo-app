package data.model

import java.util.*

class ObjectsStorage(
    val objects: MutableList<ModelObject> = mutableListOf()
) {

    fun addObject(obj: ModelObject) {
        objects.add(obj)
    }

    fun getObjectById(id: UUID): ModelObject =
        objects.first { obj -> obj.id == id }

    //Use ModelObject method instead
    fun getObjectFieldById(id: UUID): Field =
        objects.first { obj ->
            obj.fields
                .any { f -> f.id == id }
        }.getFieldById(id)
}