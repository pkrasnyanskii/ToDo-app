package domain.usecase

import domain.repository.TaskRepository

class GetCompletedTasksUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke() =
        repository.getCompletedTasks()
}