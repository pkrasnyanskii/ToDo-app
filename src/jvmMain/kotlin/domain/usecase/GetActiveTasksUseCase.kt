package domain.usecase

import domain.repository.TaskRepository

class GetActiveTasksUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke() =
        repository.getActiveTasks()
}