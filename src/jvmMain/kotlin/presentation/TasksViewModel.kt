package presentation

import androidx.compose.runtime.mutableStateOf
import domain.entity.Task
import domain.usecase.GetActiveTasksUseCase
import domain.usecase.GetCompletedTasksUseCase
import domain.usecase.GetTasksUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TasksViewModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val getActiveTasksUseCase: GetActiveTasksUseCase,
    private val getCompletedTasksUseCase: GetCompletedTasksUseCase
) {

    companion object {

        const val ACTIVE_TAB_INDEX = 0
        const val COMPLETED_TAB_INDEX = 1
    }

    //private val _state = MutableStateFlow(listOf<Task>())
    //val state = _state.asStateFlow()

    val state = mutableStateOf<TasksUiState>(TasksUiState.Initial)

    fun loadData(selectedTabIndex: Int) {
        state.value = TasksUiState.Loading

        when(selectedTabIndex) {
            ACTIVE_TAB_INDEX -> TasksUiState.Content(selectedTabIndex, getActiveTasksUseCase())
            COMPLETED_TAB_INDEX -> TasksUiState.Content(selectedTabIndex, getCompletedTasksUseCase())
            else -> TasksUiState.Error("Wrong Tab Index")
        }
    }
}