package presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import domain.entity.Task
import domain.entity.TaskStatus
import domain.usecase.GetTasksUseCase
import org.koin.java.KoinJavaComponent
import java.util.UUID

class TasksStateHolder {
    val getTasksUseCase: GetTasksUseCase by KoinJavaComponent.inject(GetTasksUseCase::class.java)

    private val _state: MutableState<TasksUiState> = mutableStateOf(TasksUiState.Initial)
    val state: State<TasksUiState> = _state

    fun loadData() {
        _state.value = TasksUiState.Content(getTasksUseCase())
    }

    fun onStatusChange(id: UUID, status: TaskStatus) =
        setState {
            updateTask(id = id) { it.copy(status = status) }
        }

    private fun TasksUiState.Content.updateTask(id: UUID, transformer: (Task) -> Task): TasksUiState =
        copy(tasks = tasks.updateTask(id = id, transformer = transformer))

    private fun List<Task>.updateTask(id: UUID, transformer: (Task) -> Task): List<Task> =
        map { task -> if (task.id == id) transformer(task) else task }

    private inline fun setState(update: TasksUiState.Content.() -> TasksUiState) {
        _state.value = (_state.value as TasksUiState.Content).update()
    }
}
