package ui

import androidx.compose.runtime.*
import ui.navigation.Screen
import ui.screens.edit_task_screen.EditTaskScreen
import ui.screens.tasks_screen.TasksScreen

@Composable
fun Root() {
    var screenState by remember { mutableStateOf<Screen>(Screen.Tasks) }

    when (val screen = screenState) {
        is Screen.Tasks ->
            TasksScreen(
                onEditClicked = { task ->
                    screenState = Screen.EditTask(task = task)
                }
            )
        is Screen.EditTask ->
            EditTaskScreen(
                screen.task,
                onBackClicked = { screenState = Screen.Tasks }
            )
    }
}