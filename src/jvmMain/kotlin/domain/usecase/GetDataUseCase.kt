package domain.usecase

import domain.repository.CommandRepository

class GetDataUseCase(
    private val repository: CommandRepository
) {

    suspend operator fun invoke() =
        repository.getList()
}