package data.network

import data.network.model.CommandModel
import retrofit2.http.GET
import retrofit2.http.POST

interface CommandsApi {

    @GET("/commands")
    suspend fun getCommands(): List<CommandModel>

    @POST("/commands")
    suspend fun sendCommands()
}