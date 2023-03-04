package data.network

import data.commands.entity.Command
import retrofit2.http.GET
import retrofit2.http.POST

interface CommandsApi {

    @GET("/commands")
    suspend fun getCommands(): List<Command>

    @POST("/commands")
    suspend fun sendCommands()
}