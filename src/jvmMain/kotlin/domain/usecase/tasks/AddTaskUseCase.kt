package domain.usecase.tasks

import domain.entity.Task
import domain.repository.TaskRepository

class AddTaskUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke(task: Task) =
        repository.addTask(task)
}