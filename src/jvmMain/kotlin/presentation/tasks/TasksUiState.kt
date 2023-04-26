package presentation.tasks

import domain.entity.Task

sealed interface TasksUiState {

    object Initial : TasksUiState

    object Loading : TasksUiState

    data class Content(
        val tasks: List<Task>,
        val inputText: String = ""
    ) : TasksUiState

    data class Error(val message: String?) : TasksUiState
}