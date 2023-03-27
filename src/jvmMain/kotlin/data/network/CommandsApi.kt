package data.network

import data.commands.entity.Command
import data.network.model.CommandModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CommandsApi {

    @GET("commands")
    suspend fun getCommands(): List<CommandModel>

    @POST("commands")
    @JvmSuppressWildcards
    suspend fun sendCommands(@Body commands: List<Command>)
}