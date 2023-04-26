package ui.navigation

import domain.entity.Task

sealed class Screen {
    object Tasks : Screen()
    data class EditTask(val task: Task) : Screen()
}
