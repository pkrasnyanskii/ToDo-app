package domain.repository

interface CommandRepository {

    suspend fun getList()

    suspend fun sendList()
}