package presentation

import domain.entity.Task

sealed interface TasksUiState {

    object Initial : TasksUiState

    object Loading : TasksUiState

    data class Content(val tasks: List<Task>) : TasksUiState

    data class Error(val message: String?) : TasksUiState
}