package domain.usecase

import domain.repository.CommandRepository

class SendDataUseCase(
    private val repository: CommandRepository
) {

    suspend operator fun invoke() =
        repository.sendList()
}