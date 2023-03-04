package presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import domain.entity.Task
import domain.entity.TaskStatus
import domain.usecase.*
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent.inject
import java.util.UUID

class TasksStateHolder {
    val getTasksUseCase: GetTasksUseCase by inject(GetTasksUseCase::class.java)
    val changeTaskStatusUseCase: ChangeTaskStatusUseCase by inject(ChangeTaskStatusUseCase::class.java)
    val addTaskUseCase: AddTaskUseCase by inject(AddTaskUseCase::class.java)
    val deleteTaskUseCase: DeleteTaskUseCase by inject(DeleteTaskUseCase::class.java)
    val getDataUseCase: GetDataUseCase by inject(GetDataUseCase::class.java)

    private val _state: MutableState<TasksUiState> = mutableStateOf(TasksUiState.Initial)
    val state: State<TasksUiState> = _state

    fun loadData() {
        _state.value = TasksUiState.Content(getTasksUseCase())
    }

    fun onStatusChanged(id: UUID, status: TaskStatus) =
        setState {
            changeTaskStatusUseCase(id = id, status = status)
            updateTask(id = id) { it.copy(status = status) }
        }

    fun onAddTaskClicked() {
        setState {
            val newTask = Task(
                id = UUID.randomUUID(),
                text = inputText,
                status = TaskStatus.ACTIVE
            )
            addTaskUseCase(task = newTask)
            copy(tasks = tasks + newTask, inputText = "")
        }
    }

    fun onTaskDeleteClicked(id: UUID) {
        setState {
            deleteTaskUseCase(id = id)
            copy(tasks = tasks.filterNot { it.id == id })
        }
    }

    fun onInputTextChanged(text: String) {
        setState { copy(inputText = text) }
    }
    
    fun onReceiveDataButtonClicked() {
        runBlocking {
            getDataUseCase()
        }
        setState {
            copy(tasks = getTasksUseCase(), inputText = "")
        }
    }

    private fun TasksUiState.Content.updateTask(id: UUID, transformer: (Task) -> Task): TasksUiState =
        copy(tasks = tasks.updateTask(id = id, transformer = transformer))

    private fun List<Task>.updateTask(id: UUID, transformer: (Task) -> Task): List<Task> =
        map { task -> if (task.id == id) transformer(task) else task }

    private inline fun setState(update: TasksUiState.Content.() -> TasksUiState) {
        _state.value = (_state.value as TasksUiState.Content).update()
    }
}
