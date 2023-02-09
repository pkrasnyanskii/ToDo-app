package data.model

import data.model.commands.Command
import data.serialization.Serializer

class Model(
) {

    val commands = Serializer().deserialize().toMutableList()
    val objects = mutableListOf<ModelObject>()

    fun addObject(obj: ModelObject) =
        objects.add(obj)

    fun addCommand(command: Command) =
        commands.add(command)
}