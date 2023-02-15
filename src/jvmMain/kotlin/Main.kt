import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.TasksScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        TasksScreen()
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PlannerScape"
    ) {
        App()
    }
}
