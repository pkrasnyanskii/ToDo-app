package ui.navigation

import java.util.*

sealed class Screen {
    object Tasks : Screen()
    data class EditTask(val id: UUID, val initialValue: String) : Screen()
}
