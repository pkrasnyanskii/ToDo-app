package data.model

import data.commands.CommandExecutor
import data.commands.CommandsStorage
import java.util.*

class Model(
    storage: CommandsStorage
) {

    val objects: MutableList<ModelObject> = mutableListOf<ModelObject>()

    init {
        CommandExecutor(storage, this).executeAll()
    }


    fun addObject(obj: ModelObject) {
        objects.add(obj)
    }

    fun getObjectById(id: UUID): ModelObject =
        objects.first { obj -> obj.id == id }

    fun getObjectFieldById(id: UUID): Field =
        objects.first { obj ->
            obj.fields
                .any { f -> f.id == id }
        }.getFieldById(id)
}