package presentation

import domain.entity.Task
import domain.usecase.GetTasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TasksViewModel(
    private val getTasksUseCase: GetTasksUseCase
) {

    private val _state = MutableStateFlow(listOf<Task>())
    val state = _state.asStateFlow()

    fun loadData() {
        _state.value = getTasksUseCase()
    }
}