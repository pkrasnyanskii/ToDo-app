package domain.usecase.tasks

import domain.entity.Task
import domain.repository.TaskRepository

class GetTasksUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke(): List<Task> =
        repository.getTasks()
}