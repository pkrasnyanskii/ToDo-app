package presentation

import domain.entity.Task

sealed interface TasksUiState {

    object Initial : TasksUiState

    object Loading : TasksUiState

    data class Content(var selectedTabIndex: Int, val tasks: List<Task>) : TasksUiState

    data class Error(val message: String?) : TasksUiState
}