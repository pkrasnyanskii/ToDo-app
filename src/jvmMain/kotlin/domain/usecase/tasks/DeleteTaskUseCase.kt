package domain.usecase.tasks

import domain.repository.TaskRepository
import java.util.UUID

class DeleteTaskUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke(id: UUID) =
        repository.deleteTask(id)
}