package di

import com.google.gson.GsonBuilder
import data.commands.CommandExecutor
import data.commands.CommandsStorage
import data.commands.entity.Command
import data.converter.TaskConverter
import data.model.Model
import data.model.ObjectsStorage
import data.repository.TaskRepositoryImpl
import data.serialization.CommandDeserializer
import data.serialization.Deserializer
import data.serialization.Serializer
import domain.repository.TaskRepository
import domain.usecase.AddTaskUseCase
import domain.usecase.GetTasksUseCase
import org.koin.dsl.module
import presentation.TasksStateHolder

val data = module {
    single { GsonBuilder()
        .registerTypeAdapter(Command::class.java, CommandDeserializer())
        .setPrettyPrinting()
        .create()
    }
    single { Deserializer(get()) }
    single { CommandsStorage(get()) }
    single { Serializer(get(), get()) }
    single { ObjectsStorage() }
    single { CommandExecutor(get()) }
    single { Model(get(), get(), get()) }
    single { TaskConverter() }
}

val domain = module {
    single<TaskRepository> { TaskRepositoryImpl(get(), get()) }
    single { AddTaskUseCase(get()) }
    single { GetTasksUseCase(get()) }
}

val presentation = module {
    single { TasksStateHolder() }
}