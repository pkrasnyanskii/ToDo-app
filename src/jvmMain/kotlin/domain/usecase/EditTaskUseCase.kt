package domain.usecase

import domain.repository.TaskRepository
import java.util.UUID

class EditTaskUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke(id: UUID, text: String) =
        repository.editTaskText(id, text)
}