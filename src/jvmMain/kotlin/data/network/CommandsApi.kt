package data.network

import data.commands.entity.Command
import data.network.model.CommandModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommandsApi {

    @GET("commands")
    suspend fun getAllCommands(): List<CommandModel>

    @GET("commands/{id}")
    suspend fun getCommands(@Path("id") id: Int): List<CommandModel>

    @POST("commands")
    @JvmSuppressWildcards
    suspend fun sendCommands(@Body commands: List<Command>): List<Int>
}