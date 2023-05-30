package data.repository

import data.converter.CommandConverter
import data.model.Model
import data.network.CommandsApi
import data.serialization.Serializer
import domain.repository.CommandRepository

class CommandRepositoryImpl(
    private val commandsApi: CommandsApi,
    private val model: Model,
    private val serializer: Serializer,
    private val converter: CommandConverter
) : CommandRepository {

    override suspend fun getList() {
        val commands = commandsApi.getCommands(model.commandsStorage.getMaxCommandId())
            .map { commandModel -> converter.convert(commandModel) }

        model.commandsStorage.commands.addAll(commands)
        model.commandExecutor.executeList(commands, model.objectsStorage)
        serializer.serialize()
    }

    //send list = sync
    override suspend fun sendList() {
        /*val ids: List<Int> = commandsApi.sendCommands(model.commandsStorage.commands)
        println(ids)
        println("||||||")
        model.commandsStorage.setIds(ids)
        model.commandsStorage.commands.forEach { c -> println(c.id) }
        serializer.serialize()*/
//        model.commandsStorage.commands =
//            model.commandsStorage.commands.filter { command -> command.id != -1 }.toMutableList()

        //send all (adds all commands with id -1 to table)
        val ids: List<Int> = commandsApi.sendCommands(model.commandsStorage.commands)
        println(ids)
        //get all commands (all with id's), execute them and write to json file
        model.commandsStorage.commands = commandsApi.getAllCommands()
            .map { commandModel -> converter.convert(commandModel) }
            .toMutableList()
        model.commandExecutor.executeAll(model.objectsStorage)
        serializer.serialize()
    }
}