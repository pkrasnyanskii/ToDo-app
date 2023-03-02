package domain.usecase

import domain.entity.TaskStatus
import domain.repository.TaskRepository
import java.util.UUID

class ChangeTaskStatusUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke(id: UUID, status: TaskStatus) =
        repository.changeTaskStatus(id, status)
}