package data.model

import data.model.commands.Command
import data.serialization.Serializer

//TODO: Split commands and objects storage???
class Model(
) {

    val commands = Serializer().deserialize().toMutableList()
    val objects = mutableListOf<ModelObject>()

    fun addObject(obj: ModelObject) =
        objects.add(obj)

    fun addCommand(command: Command) =
        commands.add(command)
}