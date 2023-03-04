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
        val commands = commandsApi.getCommands()
            .map { commandModel -> converter.convert(commandModel) }

        model.commandsStorage.commands.addAll(commands)
        model.commandExecutor.executeList(commands, model.objectsStorage)
        serializer.serialize()
    }

    override suspend fun sendList() {
        commandsApi.sendCommands()
    }
}