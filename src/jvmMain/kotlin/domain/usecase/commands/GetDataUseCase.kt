package domain.usecase.commands

import domain.repository.CommandRepository

class GetDataUseCase(
    private val repository: CommandRepository
) {

    suspend operator fun invoke() =
        repository.getList()
}