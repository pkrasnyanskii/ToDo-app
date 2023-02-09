package domain.usecase

import domain.entity.Task
import domain.repository.TaskRepository

class GetTasksUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke(): List<Task> =
        repository.getTasks()
}