package di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import data.commands.CommandExecutor
import data.commands.CommandsStorage
import data.commands.entity.Command
import data.converter.CommandConverter
import data.converter.TaskConverter
import data.model.Model
import data.model.ObjectsStorage
import data.network.CommandsApi
import data.repository.CommandRepositoryImpl
import data.repository.TaskRepositoryImpl
import data.serialization.CommandDeserializer
import data.serialization.Deserializer
import data.serialization.Serializer
import domain.repository.CommandRepository
import domain.repository.TaskRepository
import domain.usecase.*
import okhttp3.OkHttpClient
import org.koin.dsl.module
import presentation.tasks.TasksStateHolder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val COMMANDS_BASE_URL = "http://127.0.0.1:8080"
private const val CONNECTION_TIMEOUT = 5L
private const val WRITE_TIMEOUT = 5L
private const val READ_TIMEOUT = 5L


val data = module {
    single {
        provideGson()
    }
    single { Deserializer(get()) }
    single { CommandsStorage(get()) }
    single { Serializer(get(), get()) }
    single { ObjectsStorage() }
    single { CommandExecutor(get()) }
    single { Model(get(), get(), get()) }
    single { TaskConverter() }
    single { CommandConverter() }
    single {
        provideOkHttpClient()
    }
    single {
        provideRetrofit(get())
    }
    single {
        provideCommandsApi(get())
    }
}

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

fun provideGson(): Gson =
    GsonBuilder()
        .registerTypeAdapter(Command::class.java, CommandDeserializer())
        .setPrettyPrinting()
        .create()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(COMMANDS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .build()

fun provideCommandsApi(retrofit: Retrofit): CommandsApi =
    retrofit.create(CommandsApi::class.java)


val domain = module {
    single<TaskRepository> { TaskRepositoryImpl(get(), get(), get()) }
    single { AddTaskUseCase(get()) }
    single { GetTasksUseCase(get()) }
    single { ChangeTaskStatusUseCase(get()) }
    single { EditTaskUseCase(get()) }
    single { DeleteTaskUseCase(get()) }
    single<CommandRepository> { CommandRepositoryImpl(get(), get(), get(), get()) }
    single { GetDataUseCase(get()) }
    single { SendDataUseCase(get()) }
}

val presentation = module {
    single { TasksStateHolder() }
}