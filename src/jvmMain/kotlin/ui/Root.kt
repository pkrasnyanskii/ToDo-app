package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ui.navigation.Screen

@Composable
fun Root() {
    var screenState by remember { mutableStateOf<Screen>(Screen.Tasks) }

    when (val screen = screenState) {
        is Screen.Tasks ->
            TasksScreen(
                onEditClicked = { id, initialValue ->
                    screenState = Screen.EditTask(id = id, initialValue = initialValue)
                }
            )
        is Screen.EditTask ->
            EditTaskScreen(
                screen.initialValue,
                onBackClicked = { screenState = Screen.Tasks }
            )
    }
}