import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.test
import org.koin.core.context.GlobalContext.startKoin
import ui.TasksScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        TasksScreen()
    }
}

fun main() {
    startKoin {
        modules(
            test
        )
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "PlannerScape"
        ) {
            App()
        }
    }
}
