package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ui.navigation.Screen
import ui.screens.EditTaskScreen
import ui.screens.TasksScreen

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